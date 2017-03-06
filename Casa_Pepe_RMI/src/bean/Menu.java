package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_menu")
public class Menu implements Serializable{

	@NonVide(mess="Veuillez saisir un nom pour votre menu")
	String nom;
	ArrayList<Plat> listPlat;
	public Menu(String n){
		this.nom = n;
		listPlat = new ArrayList<Plat>();
	}
	public Menu(String n,ArrayList<Plat> list){
		this.nom = n;
		this.listPlat = list;
	}

	public String getNom() {return nom;}

	public void setNom(String nom) {this.nom = nom;}
	public ArrayList<Plat> getListPlat() {
		return listPlat;
	}
	public void setListPlat(ArrayList<Plat> listPlat) {
		this.listPlat = listPlat;
	}
	public void addList(Plat p){
		if(!listPlat.contains(p)){
			listPlat.add(p);
		}
	}
	@Override
	public String toString() {
		return "Menu [nom=" + nom + ", listPlat=" + listPlat + "]";
	}
}
