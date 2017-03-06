package bean;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_groupe")
public class Groupe {
	@NonVide(mess="Veuillez saisir un nom pour votre groupe")
	String nom;
	
	public Groupe(String n){this.nom = n;}

	public String getNom() {return nom;}

	public void setNom(String nom) {this.nom = nom;}
	
	
}