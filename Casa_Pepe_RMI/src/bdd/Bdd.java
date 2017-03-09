package bdd;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bean.Groupe;
import bean.Menu;
import bean.Photo;
import bean.Plat;

public class Bdd {
	String ficData = "data";
	String url = null;
	String user = null;
	String pwd = null;
	
	Connection connection = null;

	public Bdd() {
		ResourceBundle rb = ResourceBundle.getBundle(ficData);
		url = rb.getString("url");
		user = rb.getString("user");
		pwd = rb.getString("pwd");
		System.out.println("url = "+url);
		System.out.println("user = "+user);
		System.out.println("pwd = "+pwd);
	}
	
	//Fonction d'ouverture et fermeture de la BDD
	public boolean ouvrir() {
		boolean res = false;
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			res = true;
		} catch (SQLException e) {
			System.out.println("Erreur DriverManager.getConnection");
			e.printStackTrace();
		}
		return res;
	}
	public void fermer() {
		try {
			if (connection != null) connection.close();
		}
		catch (Exception e) {
			System.out.println("Erreur Base.fermer "+e.getMessage());
			e.printStackTrace();
		}

	}
	
	//Test si existant
	public boolean identIsValid(String id,String mdp){
		boolean res = false;
		String sql ="SELECT ident_admin FROM `t_administrateur` WHERE `ident_admin` = ? AND`mdp_admin` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2, mdp);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString("ident_admin"));
				res = true;
			}

		} catch (SQLException e) {
			System.out.println("Erreur Base.identIsValid "+e.getMessage());
			e.printStackTrace();
		}
		
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		
		return res;
	}
	private boolean menuExist(int idMenu){
		boolean res = false;
		String sql ="SELECT * FROM `t_menu` WHERE `id_menu` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idMenu);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getInt("id_menu")+ " - "+rs.getString("nom_menu") +" exist !");
				res = true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.menuExist "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public boolean platExist(int idPlat){
		boolean res = false;
		String sql ="SELECT * FROM `t_plat` WHERE `id_plat` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idPlat);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString("nom_plat") +" exist !");
				res = true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.platExist "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public boolean groupeExist(int idGroupe){
		boolean res = false;
		String sql ="SELECT * FROM `t_groupe` WHERE `id_groupe` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idGroupe);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString("nom_groupe") +" exist !");
				res = true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.groupExist "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public boolean photoExist(int idPhoto){
		boolean res = false;
		String sql ="SELECT id_photo FROM `t_photo` WHERE `id_photo` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idPhoto);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("photo "+rs.getString("id_photo") +" exist !");
				res = true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.groupExist "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}

	//Accesseur
	public ArrayList<Menu> getAllMenu(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		String sql = "SELECT * FROM t_menu";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int idMenu = rs.getInt("id_menu");
				System.out.println(idMenu);
				res.add(this.getMenu(idMenu));
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.AllgetMenu "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public Menu getMenu(int idMenu){
		Menu res = null;
		if(menuExist(idMenu)){
			String sql ="SELECT * FROM `t_menu` WHERE `id_menu` = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1,idMenu);
				rs = ps.executeQuery();
				//Si le menu existe en BDD on crée un menu avec ce nom
				if(rs.next()){
					res = new Menu(rs.getInt("id_menu"),rs.getString("nom_menu"));
					
					//Puis on va charger la liste des plat qu'il contient
					ArrayList<Plat> list = this.getMenuPlat(rs.getInt("id_menu"));
					res.setListPlat(list);
				}
			} catch (SQLException e) {
				System.out.println("Erreur Base.getMenu "+e.getMessage());
				e.printStackTrace();
			}
			try {if (ps != null) ps.close();} catch (Exception e) {}
			try {if (rs != null) rs.close();} catch (Exception e) {}
		}

		return res;
	}	
	public ArrayList<Plat> getMenuPlat(int numMenu){
		ArrayList<Plat> list = new ArrayList<Plat>();
		String sqlContient = "SELECT id_plat FROM `t_contient` WHERE `id_menu` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sqlContient);
			ps.setInt(1,numMenu);
			rs = ps.executeQuery();
			//Si le menu existe en BDD on crée un menu avec ce nom
			while(rs.next()){
				int id = rs.getInt("id_plat");
				list.add(this.getPlat(id));
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getMenu "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return list;
	}
	public ArrayList<Plat> getAllPlat(){
		ArrayList<Plat> list = new ArrayList<Plat>();
		String sqlContient = "SELECT * FROM `t_plat`,`t_groupe` WHERE `id_groupe`=`fk_id_grp`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sqlContient);
			rs = ps.executeQuery();
			//Si le menu existe en BDD on crée un menu avec ce nom
			while(rs.next()){
				int id = rs.getInt("id_plat");
				list.add(this.getPlat(id));
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getMenu "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return list;
	}
	public Plat getPlat(int idPlat){
		Plat res = null;
		String sql ="SELECT * FROM `t_plat`,`t_groupe` WHERE `id_plat` = ? AND `id_groupe`=`fk_id_grp`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idPlat);
			rs = ps.executeQuery();
			//Si le menu existe en BDD on crée un menu avec ce nom
			if(rs.next()){
				res = new Plat();
				res.setId(rs.getInt("id_plat"));
				res.setNom(rs.getString("nom_plat"));
				res.setDescription(rs.getString("desc_plat"));
				res.setGroupe(rs.getInt("fk_id_grp"));
				res.setPrix(rs.getFloat("prix_plat"));
				res.setIdPhoto(rs.getInt("fk_img_plat"));
				return res;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getMenu "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public ArrayList<Groupe> getAllGroupe(){
		ArrayList<Groupe> list = new ArrayList<Groupe>();
		String sqlContient = "SELECT * FROM `t_groupe`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sqlContient);
			rs = ps.executeQuery();
			//Si le menu existe en BDD on crée un menu avec ce nom
			while(rs.next()){
				int id = rs.getInt("id_groupe");
				list.add(this.getGroupe(id));
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getAllGroupe "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return list;
	}
	public Groupe getGroupe(int idGroupe){
		Groupe res = null;
		String sql ="SELECT * FROM `t_groupe` WHERE `id_groupe` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,idGroupe);
			rs = ps.executeQuery();
			//Si le groupe existe en BDD on crée un groupe avec ce nom
			if(rs.next()){
				res = new Groupe();
				res.setId(rs.getInt("id_groupe"));
				res.setNom(rs.getString("nom_groupe"));
				System.out.println(res.toString());
				return res;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getGroupe "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public Photo getPhoto(int idPhoto){
		Photo res = null;
		String sql = "SELECT * FROM t_photo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(photoExist(idPhoto)){
			res = new Photo();
			try {
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					res.setIdPhoto(rs.getInt("id_photo"));
					Blob blob = rs.getBlob("img_photo");
					int blobLength =(int) blob.length();
					res.setImg(blob.getBytes(1, blobLength));
					blob.free();
				}
			} catch (SQLException e) {
				System.out.println("Erreur Base.getMenu "+e.getMessage());
				e.printStackTrace();
			}
			try {if (ps != null) ps.close();} catch (Exception e) {}
			try {if (rs != null) rs.close();} catch (Exception e) {}
		}
		
		return res;
	}
	
	//Gestion des  entitées
	public boolean createPlat(Plat p,Photo ph){
		boolean res = false;
		String req = "INSERT INTO `t_plat`(`nom_plat`, `desc_plat`, `fk_img_plat`, `fk_id_grp`, `prix_plat`) VALUES (?,?,?,?,?)";
		PreparedStatement ps = null;
		createPhoto(ph);
		try {
			ps = connection.prepareStatement(req);
			ps.setString(1,p.getNom());
			ps.setString(2,p.getDescription());
			if(groupeExist(p.getGroupe())){
				ps.setInt(4,p.getGroupe());
				ps.setFloat(5,p.getPrix());	
				String req1 ="SELECT MAX(id_photo) AS max_id FROM `t_photo`";
				PreparedStatement ps1 = connection.prepareStatement(req1);
				ResultSet rs = ps1.executeQuery();
				int max = 0;
				while(rs.next()){
					max = rs.getInt("max_id");
				}
				ps.setFloat(3,max);	
				ps.execute();
				res = true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.platExist "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		return res;
	}
	public boolean deletePlat(Plat p){
		boolean res = false;
		String reqDelete ="DELETE FROM `t_plat` WHERE id_plat = ?";
		PreparedStatement psPlat = null;
		if(platExist(p.getId())){
			System.out.println("Le plat existe");
			try{
				System.out.println("try");
				psPlat = connection.prepareStatement(reqDelete);
				psPlat.setInt(1, p.getId());
				psPlat.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.deletePlat");
			}
			try {if (psPlat != null) psPlat.close();} catch (Exception e) {}
			return res;
		}
		
		return res;
	}
	public boolean createPhoto(Photo photo){
		boolean res = false;
		String req = "INSERT INTO `t_photo`(`img_photo`) VALUES (?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(req);
			byte[] img = photo.getImg();
			ByteArrayInputStream bis = new ByteArrayInputStream(img);
			ps.setBinaryStream(1, bis);
			res = ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.createPhoto "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		return res;
	}
	public boolean deletePhoto(int idPhoto){
		boolean res = false;
		if(photoExist(idPhoto)){
			String reqDelete ="DELETE FROM `t_photo` WHERE id_photo = ?";
			PreparedStatement psDelete = null;
			try{
				psDelete = connection.prepareStatement(reqDelete);
				psDelete.setInt(1, idPhoto);
				res = psDelete.execute();
			}catch(Exception e){
				System.out.println("Erreur Base.deletePlat");
			}
			try {if (psDelete != null) psDelete.close();} catch (Exception e) {}
			return res;
		}
		return res;
	}	
	public boolean createGroupe(Groupe groupe){
		boolean res = false;
		String req = "INSERT INTO `t_groupe`(`nom_groupe`) VALUES (?)";
		if(!groupeExist(groupe.getId())){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				ps = connection.prepareStatement(req);
				ps.setString(1, groupe.getNom());
				ps.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.CreateGroupe");
			}
		}
		return res;
	}
	public boolean deleteGroupe(int idGroupe){
		boolean res = false;
		String req = "DELETE FROM `t_groupe` WHERE id_groupe = ?";
		if(groupeExist(idGroupe)){
			PreparedStatement ps = null;
			try{
				ps = connection.prepareStatement(req);
				ps.setInt(1, idGroupe);
				ps.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.CreateGroupe");
			}
		}
		return res;
	}
	public boolean updatePlat(Plat p){
		boolean res = false;
		String req = "UPDATE `t_plat` SET `nom_plat`= ? , `desc_plat`= ? , `fk_id_grp`= ? , `prix_plat`= ? WHERE `t_plat`.`id_plat` = ?";
		if(platExist(p.getId())){
			PreparedStatement ps = null;
			try{
				ps = connection.prepareStatement(req);
				ps.setString(1,p.getNom());
				ps.setString(2,p.getDescription());
				ps.setInt(3,p.getGroupe());
				ps.setFloat(4,p.getPrix());
				ps.setInt(5,p.getId());
				ps.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.UpdatePlat");
			}
		}
		
		return res;
	}
	
}
