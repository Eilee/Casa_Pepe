package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
		}catch(Exception e){
			
		}
	}
}
