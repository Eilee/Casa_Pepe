package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Groupe;
import bean.Menu;
import bean.Photo;
import bean.Plat;
import rmi.ServeurInterface;

public class Manager {

	boolean isConnect = false;
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
		} catch (Exception e) {
			System.out.println("Erreur lookup "+e.getMessage());
		}
	}
	
	public boolean getIsConnect(){
		return isConnect;
	}
	public void setIsConnect(boolean b){
		isConnect = b;
	}
	public boolean isIdentificationValid(String id,String mdp){
		boolean res = false;
		try{
			res = serveur.identificationValid(id, mdp);
			if(res){
				isConnect = true;
			}
		}catch(Exception e){
			System.out.println("Exception - Client isIdentificationValid(id,mdp");
		}
		return res;
	}
	public ArrayList<Menu> getAllMenu(){
		ArrayList<Menu> res = null;
		try {
			res = serveur.getAllMenu();
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public Menu getMenu(int idMenu){
		Menu res = null;
		try {
			res = serveur.getMenu(idMenu);
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Plat> getMenuPlat(int numMenu){
		ArrayList<Plat> res = null;
		try {
			res = serveur.getMenuPlat(numMenu);
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Plat> getAllPlat(){
		ArrayList<Plat> res = null;
		try {
			res = serveur.getAllPlat();
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public Plat getPlat(int idPlat){
		Plat res = null;
		try {
			res = serveur.getPlat(idPlat);
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Groupe> getAllGroupe(){
		ArrayList<Groupe> res = null;
		try {
			res = serveur.getAllGroupe();
			return res;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public Photo getPhoto(int idPhoto){
		Photo res = new Photo();
		try{
			res = serveur.getPhoto(idPhoto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean deletePlat(int idPlat){
		boolean res = false;
		Plat p = new Plat();
		System.out.println("id a supprimer :"+idPlat);
		p.setId(idPlat);
		try {
			serveur.deletePlat(p);
			return res = true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return res;
	}
	public boolean updatePlat(Plat p,Photo ph){
		boolean res = false;
		try{
			res = serveur.updatePlat(p,ph);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean createPlat(Plat p, Photo ph){
		boolean res = false;
		try{
			res = serveur.createPlat(p,ph);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public Groupe getGroupe(int idGroupe){
		Groupe res = new Groupe();
		try{
			res = serveur.getGroupe(idGroupe);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean createGroupe(Groupe grp){
		boolean res = false;
		try{
			res = serveur.createGroupe(grp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean updateGroupe(Groupe grp){
		boolean res = false;
		try{
			res = serveur.updateGroupe(grp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public boolean deleteGroupe(int idGroupe){
		boolean res = false;
		try{
			res = serveur.deleteGroupe(idGroupe);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Plat> getPlatsDuGroupe(int idGroupe){
		ArrayList<Plat> res = null;
		try {
			res = serveur.getPlatsDuGroupe(idGroupe);
			return res;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
