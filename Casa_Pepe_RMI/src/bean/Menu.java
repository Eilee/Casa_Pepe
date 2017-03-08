package bean;

import java.io.Serializable;
import java.util.ArrayList;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_menu")
public class Menu implements Serializable{

	@NonVide(mess="Veuillez saisir un nom pour votre menu")
	int ident;
	String nom;
	ArrayList<Plat> listPlat;
	
	public Menu(int id,String n){
		this.ident = id;
		this.nom = n;
		listPlat = new ArrayList<Plat>();
	}
	public Menu(int id,String n,ArrayList<Plat> list){
		this.ident = id;
		this.nom = n;
		this.listPlat = list;
	}
	
	public int getId() {
		return ident;
		}
	public void setId(int id) {
		this.ident = id;
	}
	public String getNom() {
		return nom;
		}
	public void setNom(String nom) {
		this.nom = nom;
	}
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
		return "Menu [nom=" + nom + ",]";
	}
}
