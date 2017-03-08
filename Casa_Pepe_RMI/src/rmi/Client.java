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
			Plat p = new Plat();
			p = serveur.getPlat(19);
			if(serveur.deletePlat(p)){
				System.out.println("OK");
			}else{
				System.out.println("KO");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
