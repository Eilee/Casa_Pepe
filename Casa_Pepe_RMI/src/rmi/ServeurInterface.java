package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.Menu;
import bean.Plat;

public interface ServeurInterface extends Remote {
	public boolean identificationValid(String id,String mdp)throws RemoteException;
<<<<<<< HEAD
	public boolean recupereAllPlats()throws RemoteException;
=======
	public Menu getMenu(String nom) throws RemoteException;
	public ArrayList<Plat> getMenuPlat(int numMenu) throws RemoteException;
	public ArrayList<Plat> getAllPlat() throws RemoteException;
	public Plat getPlat(int idPlat) throws RemoteException;
>>>>>>> a33590e76f84c3592291b8d32655c1eee9c2647f
}
