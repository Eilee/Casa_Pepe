package bean;

import java.io.Serializable;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_groupe")
public class Groupe implements Serializable{
	@NonVide(mess="Veuillez saisir un nom pour votre groupe")
	int ident;
	String nom;
	
	public Groupe(){}
	public Groupe(int id,String n){
		this.ident = id;
		this.nom = n;
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
	@Override
	public String toString() {
		return "Groupe [nom=" + nom + "]";
	}
	
	
}
