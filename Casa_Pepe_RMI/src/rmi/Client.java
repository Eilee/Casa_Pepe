package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bean.Photo;
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
			/*Plat p = new Plat(25, "cookies", "cookies au chocolat et a la nougatine", 2, 4, 1);
			if(serveur.createPlat(p)){
				System.out.println("Creation plat - OK");
			}else{
				System.out.println("Creation plat - KO");
			}
			if(serveur.deletePlat(p)){
				System.out.println("DELETE plat - OK");
			}else{
				System.out.println("DELETE plat - KO");
			}*/
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
