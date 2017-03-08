package bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

import annotation.Table;

@Table(name="t_plat")
public class Plat implements Serializable{

	int ident;
	String nom;
	String description;
	float prix;
	int idPhoto;
	int idGroupe;
	String groupe;
	
	public Plat(){};
	public Plat(int id,String n,String desc,float p,int grp,String g,byte[] im){
		this.ident = id;
		this.nom = n;
		this.description = desc;
		this.idGroupe = grp;
		this.groupe =g;
		this.idPhoto = idPhoto;
		this.prix = p;
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
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
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
	@Override
	public String toString() {
		return "Plat [ident=" + ident + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ ", idPhoto=" + idPhoto + ", groupe=" + groupe + "]";
	}
	
}
