package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import bean.Groupe;
import bean.Photo;
import bean.Plat;
import manager.Manager;

@WebServlet("/Edition")
@MultipartConfig(maxFileSize = 16177215)
public class ServletEdition extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEdition() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Edition");
		String idPlat = request.getParameter("idPlat");

		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			ArrayList<Groupe> grp = manager.getAllGroupe();
			request.setAttribute("groupes", grp);
			if(idPlat!=null){
				Plat p = manager.getPlat(Integer.parseInt(idPlat));
				request.setAttribute("plat", p);
			}
			request.getServletContext().getRequestDispatcher("/WEB-INF/Edition.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Edition");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String groupe = request.getParameter("groupe");
		String prix = request.getParameter("prix");
		String id = request.getParameter("id");
		
		InputStream inputStream = null; // input stream of the upload file
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("image");
		if (filePart != null) {
		    // prints out some information for debugging
		    System.out.println(filePart.getName());
		    System.out.println(filePart.getSize());
		    System.out.println(filePart.getContentType());
		     
		    // obtains input stream of the upload file
		    inputStream = filePart.getInputStream();
		}
	    byte[] bytes = IOUtils.toByteArray(inputStream);

	    
	    if(id.length()<1 && nom!=null && description!=null && groupe!=null && prix!=null && inputStream!=null){
	    	Plat p = new Plat();
			p.setNom(nom);
			p.setDescription(description);
			p.setGroupe(Integer.parseInt(groupe));
			p.setPrix(Float.parseFloat(prix));
			Photo ph = new Photo();
			ph.setImg(bytes);
			manager.createPlat(p,ph);
	    }else if(nom!=null || description!=null || groupe!=null || prix!=null){
			Plat p = new Plat();
			if(id.length()>0)p.setId(Integer.parseInt(id));
			p.setNom(nom);
			p.setDescription(description);
			if(groupe.length()>0)p.setGroupe(Integer.parseInt(groupe));
			if(prix.length()>0)p.setPrix(Float.parseFloat(prix));
			manager.updatePlat(p);
		}
		response.sendRedirect("GestionPlats");
		/*if(create!=null){
			System.out.println("Creation d'un plats");
			request.setAttribute("nomPlats", update);
			response.sendRedirect("Edition");
		}else if(update!=null){
			System.out.println("Modification du plats : "+update);
			request.setAttribute("nomPlats", update);
			response.sendRedirect("Edition");
		}else if(delete!=null){
			System.out.println("Suppression du plats : "+delete);
			manager.deletePlat(delete);
			doGet(request,response);
		}*/
	}
}
