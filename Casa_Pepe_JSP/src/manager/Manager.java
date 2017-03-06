package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.ServeurInterface;

public class Manager {

	Registry registry;
	ServeurInterface serveur;
	public Manager(){
		int port = 20000;
		registry = null;
		serveur = null;
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
	}
	
	public boolean isIdentificationValid(String id,String mdp){
		boolean res = false;
		try{
			res = serveur.identificationValid(id, mdp);
		}catch(Exception e){
			System.out.println("Exception - Client isIdentificationValid(id,mdp");
		}
		return res;
	}
}
