package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurInterface extends Remote {
	public boolean identificationValid(String id,String mdp)throws RemoteException;
}
