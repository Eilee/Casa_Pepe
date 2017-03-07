package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Menu;
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
			if(serveur.createGroupe("test")){
				System.out.println("Create OK");
			}else{
				System.out.println("Create KO");
			}
			if(serveur.deleteGroupe("test")){
				System.out.println("Delete OK");
			}else{
				System.out.println("Delete KO");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
