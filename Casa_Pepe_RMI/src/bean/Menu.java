package bean;

import java.util.HashMap;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_menu")
public class Menu {

	@NonVide(mess="Veuillez saisir un nom pour votre menu")
	String nom;
	
	public Menu(String n){
		this.nom = n;
		
	}

	public String getNom() {return nom;}

	public void setNom(String nom) {this.nom = nom;}
}
