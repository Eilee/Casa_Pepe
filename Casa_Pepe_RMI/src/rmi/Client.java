package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Groupe;
import bean.Menu;
import bean.Photo;
import bean.Plat;

public class Client {
	int port;
	Registry registry;
	ServeurInterface serveur;
	public Client(){
		port = 20000;
		registry = null;
		serveur = null;
	}
	public void testAccesseurBDD(){
		try{
			System.out.println("\n Test Connexion");
			/*	Test sur connexion	*/
			if(serveur.identificationValid("admin", "admin")){
				System.out.println("Connexion : id = 'admin' & mdp = 'admin' -> acc�pt� !");
			}
			
			if(!serveur.identificationValid("test", "test")){
				System.out.println("Connexion : id = 'test' & mdp = 'test' -> refus� !");
			}
			
			System.out.println("\n Test Menu");
			/*	Test Menu	*/
				/*	Test Recuperation de tout les Menus	*/
			ArrayList<Menu> listMenu = serveur.getAllMenu();
			for(Menu m : listMenu){
				System.out.println(m.toString());
			}
				/*	Test de r�cuperation d'un menu via son ID	*/
			Menu m = serveur.getMenu(1);
			String resultatAttendu = "Menu de la terre";
			if(m.getNom().equals(resultatAttendu)){
				System.out.println("getMenu - OK");
			}
				/*	Test r�cuperation des plats d'un menu	*/
			ArrayList<Plat> listPlatMenu = serveur.getMenuPlat(1);
			for(Plat p : listPlatMenu){
				System.out.println(p.toString());
			}
			

			System.out.println("\n Test Plats");
			/*	Test Plat	*/
				/*	R�cup�ration de touts les plats	*/
			ArrayList<Plat> listAllPlat = serveur.getAllPlat();
			for(Plat p : listAllPlat){
				System.out.println(p.toString());
			}
				/*	R�cup�ration d'un plat via son ID	*/
			Plat plat = serveur.getPlat(6);
			String nomPlatAttendu = "La burrata";
			if(plat.getNom().equals(nomPlatAttendu)){
				System.out.println("GetPlat - OK");
			}
			
			System.out.println("\n Test Groupe");
			/*	Test Groupe	*/
				/*	R�cup�ration de touts les groupes	*/
			ArrayList<Groupe> listAllGroupe = serveur.getAllGroupe();
			for(Groupe g : listAllGroupe){
				System.out.println(g.toString());
			}
				/*	R�cup�ration des plats d'un groupe	*/
			ArrayList<Plat> listPlatGroupe = serveur.getPlatsDuGroupe(2);
			for(Plat p : listPlatGroupe){
				System.out.println(p.toString());
			}
				/*	R�cup�ration d'un groupe via son ID	*/
			Groupe groupe = serveur.getGroupe(2);
			String nomGroupeAttendu = "Entr�e";
			if(groupe.getNom().equals(nomGroupeAttendu)){
				System.out.println("GetGroupe - OK");
			}
			
			System.out.println("\n Test Photo");
			/*	Test Photo	*/
				/*	R�cup�ration de la photo via son ID	*/
			Photo photo = serveur.getPhoto(1);
			if(photo.getImg().length > 0 ){
				System.out.println("GetPhoto - OK");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void testModificateurBDD(){
		try{
			byte[] newImg =  {1,2,3,4,5};
			Plat pNonExistant = new Plat(22,"Cookies","Cookies au p�pites de chocolats",2,new Groupe(4,"Dessert"),1);
			/*	Test sur les Plats	*/
		
		System.out.println("Test sur la modification / cr�ation / suppression d'un plat en BDD");
		
			/*	Test Cr�ation		*/
			System.out.println("Creation");
			if(serveur.createPlat(pNonExistant, new Photo(newImg))){
				System.out.println("Cr�ation Plat - OK");
			}else{
				System.out.println("Cr�ation Plat - KO");
			}
			/*	Test Modification	*/
			System.out.println("Modification");
			Plat pExistant = new Plat(6,"La burrata","Fromage pas bon ",
					15,new Groupe(2,"Entr�e"),1);
			
			if(serveur.updatePlat(pExistant, new Photo(newImg))){
				System.out.println("Update Plat - OK");
			}else{
				System.out.println("Update Plat - KO");
			}
			pExistant.setDescription("C�est un fromage frais � base de lait de vache ou de bufflonne (moins courante) qui se compose de p�te fil�e � l�ext�rieur, comme celle de la mozzarella, de stracciatella � l�int�rieur, c�est-�-dire de petits morceaux de mozzarellastracciati, d�chir�s, et de cr�me. Le nom de burrata, qui fait penser au beurre (burro en italien), est donc trompeur.");
			serveur.updatePlat(pExistant, new Photo(newImg));
			/*	Test Suppression	*/
			System.out.println("Suppression");
			//Pensez a modifier l'id en fonction du contenu de la base de donn�es ( a cause de l'auto Incr�mentation)
			pNonExistant.setId(40);
			if(serveur.deletePlat(pNonExistant)){
				System.out.println("Suppression - OK");
			}else{
				System.out.println("Suppression Plat - KO");
			}

			System.out.println("Test sur la modification / cr�ation / suppression d'un groupe en BDD");
			/*	Test sur les groupes	*/
			Groupe groupe = new Groupe(1,"GroupeTest");
			/*	Test Cr�ation		*/			
			if(serveur.createGroupe(groupe)){
				System.out.println("Create Groupe - OK");
			}else{
				System.out.println("Create Groupe KO");
			}
			/*	Test Modification	*/
			// Changement de l'id en fonction du contenu de la base ( identifiant auto incr�ment�)
			groupe.setId(12);
			groupe.setNom("groupe Test modifi�");
			if(serveur.updateGroupe(groupe)){
				System.out.println("update Groupe - OK");
			}else{
				System.out.println("update Groupe KO");
			}
			/*	Test Suppression	*/
			if(serveur.deleteGroupe(12)){
				System.out.println("Delete Groupe - OK");
			}else{
				System.out.println("Delete Groupe KO");
			}
		}catch(Exception  e){
			System.out.println(e);
		}
	}
	public static void main(String[] args){
		Client client = new Client();
		try {
			client.registry = LocateRegistry.getRegistry(client.port);
		} catch (RemoteException e) {
			System.out.println("Erreur Registry"+e.getMessage());
		}
		
		try {
			client.serveur = (ServeurInterface)client.registry.lookup("monServeurRmi");
			System.out.println();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.testAccesseurBDD();
		System.out.println("\n");
		client.testModificateurBDD();
		
	}
}
