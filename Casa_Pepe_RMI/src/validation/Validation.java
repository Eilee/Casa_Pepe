package validation;

import java.lang.reflect.Field;
import java.util.Hashtable;

import annotation.NonVide;

public class Validation {

	boolean valide = true;	//résultat final du formulaire
	Hashtable <String,String> valeurs = new Hashtable<String,String>();	//valeurs courantes des champs
	Hashtable <String,String> erreurs = new Hashtable<String,String>();	//erreurs détéctées dans les champs
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public Hashtable<String, String> getValeurs() {
		return valeurs;
	}
	public void setValeurs(Hashtable<String, String> valeurs) {
		this.valeurs = valeurs;
	}
	public Hashtable<String, String> getErreurs() {
		return erreurs;
	}
	public void setErreurs(Hashtable<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	
	public boolean nonVide(Class c,String param,String val){
		//param =  nom du parametre, val = valeur du parametre, c = classe contenant les messages d'erreurs
		boolean res = true;
		String mes ="";
		try {
			Field f = c.getDeclaredField(param);
			NonVide ann = f.getAnnotation(NonVide.class);
			mes = ann.mess() ;
			if(val == null){
				res = false;
				val ="";
			}else if(val.trim().length()==0){
				res = false;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block

			res = false;
			valide = false;
			e.printStackTrace();
		}
		if(!res){
			erreurs.put(param, mes);
			valide = false;
		}
		valeurs.put(param, val);
		
		return res;
	}
}
