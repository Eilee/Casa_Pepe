package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bdd.Bdd;
import bean.Groupe;
import bean.Menu;
import bean.Photo;
import bean.Plat;

public class ServeurImpl implements ServeurInterface{

	Bdd base = new Bdd();
	
	public String test()throws RemoteException{
		return "test";
	}
	
	//Ouverture de la BDD
	public boolean ouvrirBdd(){
		if(base.ouvrir()){
			System.out.println("Bdd ouverte - OK !");
			return true;
		}
		System.out.println("Bdd ouverte - KO !");
		return false;
		
	}
	
	//Fermeture de la BDD
	public void fermerBdd(){
		base.fermer();
	}

	public boolean identificationValid(String id,String mdp) throws RemoteException{
		return base.identIsValid(id, mdp);
	}
	public ArrayList<Menu> getAllMenu() throws RemoteException{
		return base.getAllMenu();
	}
	public Menu getMenu(int idMenu) throws RemoteException{
		return base.getMenu(idMenu);
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
	public Plat getPlat(String nomPlat) throws RemoteException{
		return base.getPlat(nomPlat);
	}
	public ArrayList<Groupe> getAllGroupe() throws RemoteException{
		return base.getAllGroupe();
	}
	public ArrayList<Plat> getPlatsDuGroupe(int idGroupe) throws RemoteException{
		return base.getPlatsDuGroupe(idGroupe);
	}
	public Groupe getGroupe(int idGroupe) throws RemoteException{
		return base.getGroupe(idGroupe);
	}
	public Groupe getGroupe(String nomGroupe) throws RemoteException{
		return base.getGroupe(nomGroupe);
	}
	public Photo getPhoto(int idPhoto) throws RemoteException{
		return base.getPhoto(idPhoto);
	}
	public boolean createPlat(Plat p,Photo ph) throws RemoteException{
		boolean res =  base.createPlat(p,ph);
		return res;
	}
	public boolean deletePlat(Plat p) throws RemoteException{
		return base.deletePlat(p);
	}
	public boolean updatePlat(Plat p,Photo ph) throws RemoteException{
		return base.updatePlat(p,ph);
	}
	public boolean createGroupe(Groupe groupe) throws RemoteException{
		return base.createGroupe(groupe);
	}
	public boolean updateGroupe(Groupe groupe) throws RemoteException{
		return base.updateGroupe(groupe);
	}
	public boolean deleteGroupe(int idGroupe) throws RemoteException{
		return base.deleteGroupe(idGroupe);
	}
	public boolean createPhoto(Photo photo) throws RemoteException {
		return base.createPhoto(photo);
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
