package bean;

import java.io.Serializable;

import annotation.Table;

@Table(name="t_plat")
public class Plat implements Serializable{

	int imgMax = 800000;
	String nom;
	String description;
	float prix;
	byte[] img = new byte[imgMax];
	String groupe;
	
	public Plat(){};
	public Plat(String n,String desc,float p,String g,byte[] im){
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	@Override
	public String toString() {
		return "Plat [nom=" + nom + ", description=" + description + ", prix=" + prix + ", groupe=" + groupe + ",img = {"+this.toStringImg()+"}]";
	}
	public int getImgMax(){return this.imgMax;}
	public String toStringImg(){
		String res = "";
		for(int i = 0 ; i < img.length;i++){
			res += img[i];
		}
		return res;
	}
	
}
