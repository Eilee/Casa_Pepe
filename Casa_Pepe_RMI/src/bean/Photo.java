package bean;

import annotation.Table;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;

@Table(name="t_photo")
public class Photo implements Serializable{
	int idPhoto;
	byte[] img;
	String imgValue;
	
	public Photo(){}
	public Photo(byte[] newImg){
		this.img = newImg;
	}
	public void setImg(byte[] img) {
		this.img = img;
		byte[] encodeBase64 = Base64.encodeBase64(img);
         try {
			this.imgValue = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getImg(){
		return this.img;
	}
	@Override
	public String toString() {
		return "Photo [img=" + Arrays.toString(img) + ", imgValue=" + imgValue + "]";
	}
	public int getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}
	public String getImgValue() {
		return imgValue;
	}
	public void setImgValue(String imgValue) {
		this.imgValue = imgValue;
	}
}
