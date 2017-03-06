package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bdd.Bdd;

public class ServeurImpl implements ServeurInterface{

	Bdd base = new Bdd();
	
	public boolean ouvrirBdd(){
		if(base.ouvrir()){
			System.out.println("Bdd ouverte - OK !");
			return true;
		}
		System.out.println("Bdd ouverte - KO !");
		return false;
		
	}
	public void fermerBdd(){
		base.fermer();
	}

	public boolean identificationValid(String id,String mdp) throws RemoteException{
		return base.identIsValid(id, mdp);
	}

	public boolean recupereAllPlats() throws RemoteException{
		return base.recupAllPlats();
	}
	
	public static void main(String[] args){
		int port = 20000;
		ServeurImpl si = new ServeurImpl();
		si.ouvrirBdd();
		ServeurInterface serveur = null;
		Registry registry = null;
		
		//Cr�ation de Registry
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur Registry"+e.getMessage());
		}
		
		//Cr�ation de l'objet distant
		try {
			serveur = (ServeurInterface)UnicastRemoteObject.exportObject(si,0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur exportRegistry"+e.getMessage());
		}
		try {
			registry.rebind("monServeurRmi", serveur);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Serveur RMI lanc� !");
		
		

	}
}
