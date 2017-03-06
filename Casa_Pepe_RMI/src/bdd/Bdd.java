package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import bean.Menu;
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
	
	public boolean recupAllPlats(){
		String sql ="SELECT * FROM `t_plat`,`t_groupe` WHERE `id_groupe`=`fk_id_grp`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Menu menu = new Menu();
		
		try {
			ps = connection.prepareStatement(sql);
			System.out.println("sql = "+ps.toString());
			rs = ps.executeQuery();
			/*if(rs.next()){
				menu.add(rs.getString("nom_plat"),rs.getString("desc_plat"),rs.getString("img_plat"),rs.getString("nom_grp"),rs.getString("prix_plat"));
			}*/
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {if (ps != null) ps.close();} catch (Exception e) {}
		
		//return menu;
		return false;
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
					System.out.println(rs.getString("nom_menu"));
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
			if(rs.next()){
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
		String sqlContient = "SELECT * FROM `t_plat`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sqlContient);
			rs = ps.executeQuery();
			//Si le menu existe en BDD on crée un menu avec ce nom
			if(rs.next()){
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
		String sql ="SELECT * FROM `t_plat` WHERE `id_plat` = ?";
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
				res.setGroupe(rs.getInt("fk_id_grp"));
				res.setPrix(rs.getFloat("prix_plat"));
				//AJOUT DE L'IMAGE !!!!
				
				System.out.println(res.toString());
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
}
