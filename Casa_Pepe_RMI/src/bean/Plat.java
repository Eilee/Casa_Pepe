package bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

import annotation.Table;

@Table(name="t_plat")
public class Plat implements Serializable{

	String nom;
	String description;
	float prix;
	int idPhoto;
	String groupe;
	
	public Plat(){};
	public Plat(String n,String desc,float p,String g,int idPhoto){
		this.nom = n;
		this.description = desc;
		this.groupe =g;
		this.idPhoto = idPhoto;
		this.prix = p;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getIdPhoto() {
		return this.idPhoto;
	}

	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public void setIdPhoto(int id) {
		this.idPhoto = id;
	}
	
}
