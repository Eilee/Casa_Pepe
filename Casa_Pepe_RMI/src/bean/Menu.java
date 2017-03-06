package bean;

import java.util.HashMap;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_menu")
public class Menu {

	@NonVide(mess="Veuillez saisir un nom pour votre menu")
	String nom;
	HashMap<Integer,Plat> listPlat;
	public Menu(String n){
		this.nom = n;
		listPlat = new HashMap<Integer,Plat>();
	}
	public Menu(String n,HashMap<Integer,Plat> list){
		this.nom = n;
		this.listPlat = list;
	}

	public String getNom() {return nom;}

	public void setNom(String nom) {this.nom = nom;}
	public HashMap<Integer, Plat> getListPlat() {
		return listPlat;
	}
	public void setListPlat(HashMap<Integer, Plat> listPlat) {
		this.listPlat = listPlat;
	}
	
}
