package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bdd.Bdd;
import bean.Groupe;
import bean.Menu;
import bean.Plat;

public class ServeurImpl implements ServeurInterface{

	Bdd base = new Bdd();
	
	//Ouverture et fermeture de la BDD
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

	// Test si existant
	public boolean identificationValid(String id,String mdp) throws RemoteException{
		return base.identIsValid(id, mdp);
	}

	//Accesseur
	public ArrayList<Menu> getAllMenu() throws RemoteException{
		return base.getAllMenu();
	}
	public Menu getMenu(String nom) throws RemoteException{
		return base.getMenu(nom);
	}
	public ArrayList<Plat> getMenuPlat(int numMenu) throws RemoteException{
		return base.getMenuPlat(numMenu);
	}
	public ArrayList<Plat> getAllPlat() throws RemoteException{
		return base.getAllPlat();
	}
	public Plat getPlat(int idPlat) throws RemoteException{
		return base.getPlat(idPlat);
	}
	public ArrayList<Groupe> getAllGroupe() throws RemoteException{
		return base.getAllGroupe();
	}
	public Groupe getGroupe(int idGroupe) throws RemoteException{
		return base.getGroupe(idGroupe);
	}
	
	//Gestion des entitées
	public boolean deletePlat(Plat p) throws RemoteException{
		return base.deletePlat(p);
	}

	public boolean createGroupe(String nomGroupe) throws RemoteException{
		return base.createGroupe(nomGroupe);
	}
	public boolean deleteGroupe(String nomGroupe) throws RemoteException{
		return base.deleteGroupe(nomGroupe);
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
