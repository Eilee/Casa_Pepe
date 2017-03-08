package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.Groupe;
import bean.Menu;
import bean.Photo;
import bean.Plat;

public interface ServeurInterface extends Remote {
	public String test()throws RemoteException;
	public boolean identificationValid(String id,String mdp)throws RemoteException;
	public Menu getMenu(String nom) throws RemoteException;
	public ArrayList<Plat> getMenuPlat(int numMenu) throws RemoteException;
	public ArrayList<Plat> getAllPlat() throws RemoteException;
	public Plat getPlat(int idPlat) throws RemoteException;
	public ArrayList<Groupe> getAllGroupe() throws RemoteException;
	public ArrayList<Menu> getAllMenu() throws RemoteException;
	public Groupe getGroupe(int idGroupe) throws RemoteException;
	public int getIdGroupe(String nomGroupe)throws RemoteException;
	public Photo getPhoto(int idPhoto) throws RemoteException;
	
	public boolean createPlat(Plat p) throws RemoteException;
	public boolean deletePlat(Plat p) throws RemoteException;
	public boolean createGroupe(String nomGroupe) throws RemoteException;
	public boolean deleteGroupe(String nomGroue) throws RemoteException;
	public boolean createPhoto(Photo photo) throws RemoteException;
	//public boolean deletePhoto(Photo photo) throws RemoteException;

}
