package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Plat;

public class Client {
	
	public static void main(String[] args){
		
		int port = 20000;
		Registry registry = null;
		ServeurInterface serveur = null;
		try {
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur Registry"+e.getMessage());
		}
		
		try {
			serveur = (ServeurInterface)registry.lookup("monServeurRmi");
			System.out.println();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
<<<<<<< HEAD
			if(serveur.identificationValid("admin", "admin")){
				System.out.println("Identification OK!");
			}else{
				System.out.println("Identification KO !");
			}
			
			if(serveur.identificationValid("admin", "nua")){
				System.out.println("Identification OK!");
			}else{
				System.out.println("Identification KO !");
			}
			if(serveur.recupereAllPlats()){
				System.out.println("Recup OK!");
			}else{
				System.out.println("Recup KO !");
			}
=======
			ArrayList<Plat> listMenu = serveur.getMenuPlat(1);
			for(Plat p : listMenu){System.out.println(p.toString());}

>>>>>>> a33590e76f84c3592291b8d32655c1eee9c2647f
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
