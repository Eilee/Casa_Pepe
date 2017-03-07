package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Menu;
import bean.Plat;

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
/*			Menu m1 = serveur.getMenu("Menu de la mer");
			System.out.println(m1);
			ArrayList<Plat> list = m1.getListPlat();
			for(Plat p : list){System.out.println(p.getNom());}
			Menu m2 = serveur.getMenu("Menu de la terre");
			System.out.println(m2);
			ArrayList<Plat> list2 = m2.getListPlat();
			for(Plat p : list2){System.out.println(p.getNom());}
*/
			ArrayList<Menu> listMenu = new ArrayList<Menu>();
			listMenu = serveur.getAllMenu();
			for(Menu m : listMenu){System.out.println(m.toString()+" -> "+m.getListPlat());}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
