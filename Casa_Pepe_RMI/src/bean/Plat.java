package bean;

import annotation.Table;

@Table(name="t_plat")
public class Plat {

	String nom;
	String description;
	float prix;
	Byte[] img = new Byte[512];
	Groupe groupe;
	
	public Plat(){};
	public Plat(String n,String desc,float p,Groupe g,Byte[] im){
		this.nom = n;
		this.description = desc;
		this.groupe =g;
		this.img = im;
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
	public Byte[] getImg() {
		return img;
	}
	public void setImg(Byte[] img) {
		this.img = img;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
}
