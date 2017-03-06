package bdd;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

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
			System.out.println("sql = "+ps.toString());
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString("ident_admin"));
				res = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {if (ps != null) ps.close();} catch (Exception e) {}
		return res;
	}
	
	public boolean recupAllPlats(){
		String sql ="SELECT * FROM `t_plat`,`t_groupe` WHERE `id_groupe`=`fk_id_grp`";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Menu menu = new Menu();
		
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
		
		return menu;
	}

}
