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
	private boolean menuExist(String n){
		boolean res = false;
		String sql ="SELECT nom_menu FROM `t_menu` WHERE `nom_menu` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,n);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString("nom_menu") +" exist !");
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
	public boolean platExist(String nom){
		boolean res = false;
		String sql ="SELECT nom_plat FROM `t_plat` WHERE `nom_plat` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,nom);
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
	public boolean groupeExist(String nom){
		boolean res = false;
		String sql ="SELECT nom_groupe FROM `t_groupe` WHERE `nom_groupe` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,nom);
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
		String sql = "SELECT nom_menu FROM t_menu";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String nomMenu = rs.getString("nom_menu");
				System.out.println(nomMenu);
				res.add(this.getMenu(nomMenu));
			}
		} catch (SQLException e) {
			System.out.println("Erreur Base.getMenu "+e.getMessage());
			e.printStackTrace();
		}
		try {if (ps != null) ps.close();} catch (Exception e) {}
		try {if (rs != null) rs.close();} catch (Exception e) {}
		return res;
	}
	public Menu getMenu(String nom){
		Menu res = null;
		if(menuExist(nom)){
			String sql ="SELECT * FROM `t_menu` WHERE `nom_menu` = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(1,nom);
				rs = ps.executeQuery();
				//Si le menu existe en BDD on crée un menu avec ce nom
				if(rs.next()){
					res = new Menu(rs.getString("nom_menu"));
					
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
				res.setNom(rs.getString("nom_plat"));
				res.setDescription(rs.getString("desc_plat"));
				res.setGroupe(rs.getString("nom_groupe"));
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
	public int getIdGroupe(String nomGroupe){
		int res =-1;
		String sql ="SELECT id_groupe FROM `t_groupe` WHERE `nom_groupe` = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(groupeExist(nomGroupe)){
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(1,nomGroupe);
				System.out.println(nomGroupe);
				rs = ps.executeQuery();
				if(rs.next()){
					res = rs.getInt("id_groupe");
				}
			} catch (SQLException e) {
				System.out.println("Erreur Base.getIdGroupe "+e.getMessage());
				e.printStackTrace();
			}
			try {if (ps != null) ps.close();} catch (Exception e) {}
			try {if (rs != null) rs.close();} catch (Exception e) {}
		}
		
		return res;
	}
	public Photo getPhoto(int idPhoto){
		Photo res = null;
		String sql = "SELECT img_photo FROM t_photo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(photoExist(idPhoto)){
			res = new Photo();
			try {
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
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

	public boolean createPlat(Plat p){
		boolean res = false;
		/*String req = "INSERT INTO `t_plat`(`nom_plat`, `desc_plat`, `img_plat`, `fk_id_grp`, `prix_plat`) VALUES (?,?,?,?,?)";
		if(!platExist(p.getNom())){
			PreparedStatement ps = null;
			try {
				ps = connection.prepareStatement(req);
				ps.setString(1,p.getNom());
				ps.setString(2,p.getDescription());
				byte[] img = p.getImg();
				ByteArrayInputStream bis = new ByteArrayInputStream(img);
				ps.setBinaryStream(3, bis);
				int idGrp = getIdGroupe(p.getGroupe());
				if(idGrp != -1){
					ps.setInt(4,idGrp);
					ps.setFloat(5,p.getPrix());				
					ps.execute();
					res = true;
				}
			} catch (SQLException e) {
				System.out.println("Erreur Base.platExist "+e.getMessage());
				e.printStackTrace();
			}
			try {if (ps != null) ps.close();} catch (Exception e) {}
		}*/
		return res;
	}

	public boolean deletePlat(Plat p){
		boolean res = false;
		int idPlat;
		String reqPlat = "SELECT id_plat FROM `t_plat` WHERE `nom_plat` = ?";
		String reqDelete ="DELETE FROM `t_plat` WHERE id_plat = ?";
		PreparedStatement psPlat = null;
		PreparedStatement psDelete = null;
		ResultSet rsPlat = null;
		if(platExist(p.getNom())){
			try{
				psPlat = connection.prepareStatement(reqPlat);
				psPlat.setString(1, p.getNom());
				rsPlat = psPlat.executeQuery();
				if(rsPlat.next()){
					//deletePhoto(p.getIdPhoto());
					idPlat = rsPlat.getInt("id_plat");
					psDelete = connection.prepareStatement(reqDelete);
					psDelete.setInt(1, idPlat);
					res = psDelete.execute();
					
				}		
			}catch(Exception e){
				System.out.println("Erreur Base.deletePlat");
			}
			try {if (psPlat != null) psPlat.close();} catch (Exception e) {}
			try {if (rsPlat != null) rsPlat.close();} catch (Exception e) {}
			try {if (psDelete != null) psDelete.close();} catch (Exception e) {}
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
/*	public boolean deletePhoto(int idPhoto){
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
*/	
	public boolean createGroupe(String nomGroupe){
		boolean res = false;
		String req = "INSERT INTO `t_groupe`(`nom_groupe`) VALUES (?)";
		if(!groupeExist(nomGroupe)){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				ps = connection.prepareStatement(req);
				ps.setString(1, nomGroupe);
				ps.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.CreateGroupe");
			}
		}
		return res;
	}
	public boolean deleteGroupe(String nomGroupe){
		boolean res = false;
		String req = "DELETE FROM `t_groupe` WHERE nom_groupe = ?";
		if(groupeExist(nomGroupe)){
			PreparedStatement ps = null;
			try{
				ps = connection.prepareStatement(req);
				ps.setString(1, nomGroupe);
				ps.execute();
				res = true;
			}catch(Exception e){
				System.out.println("Erreur Base.CreateGroupe");
			}
		}
		return res;
	}
	
}
