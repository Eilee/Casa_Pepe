package rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bdd.Bdd;

public class ServeurImpl implements ServeurInterface{

	Bdd base = new Bdd();
	
	public String meth(){
		System.out.println("client  OK !");
		return "serveur RMI OK !";
	}
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
	public static void main(String[] args){
		int port = 20000;
		ServeurImpl si = new ServeurImpl();
		si.ouvrirBdd();
		ServeurInterface serveur = null;
		Registry registry = null;
		
		//Création de Registry
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur Registry"+e.getMessage());
		}
		
		//Création de l'objet distant
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
		System.out.println("Serveur RMI lancé !");
		
		
	}
}
