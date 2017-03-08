package bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

import annotation.Table;

@Table(name="t_plat")
public class Plat implements Serializable{

	int ident;
	int imgMax = 800000;
	String nom;
	String description;
	float prix;
	byte[] img = new byte[imgMax];
	String imgValue;
	String groupe;
	
	public Plat(){};
	public Plat(int id,String n,String desc,float p,String g,byte[] im){
		this.ident = id;
		this.nom = n;
		this.description = desc;
		this.groupe =g;
		this.img = im;
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		byte[] encodeBase64 = Base64.encodeBase64(img);
         try {
			this.imgValue = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getImgValue() {
		return this.imgValue;
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
