package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
	 * En fonction des valeurs récupérées cette fonction va éxecuter la création ou modification d'un plat avec sa photo
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Edition");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		if(manager==null)response.sendRedirect("Accueil");
		
		Plat p = null;
		Photo ph = null;
		Groupe grp = null;
		InputStream inputStream = null;
		Part filePart = null;
		byte[] bytes = null;
		
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String groupe = request.getParameter("groupe");
		String prix = request.getParameter("prix");
		String id = request.getParameter("id");
		filePart = request.getPart("image");

		if(groupe.equals("")){
			
		}
		
		ph = new Photo();
		inputStream = filePart.getInputStream();
		bytes = IOUtils.toByteArray(inputStream);
		if(bytes.length>0){
			ph.setImg(bytes);
		}
		
	    if(id.equals("") && !nom.equals("") && !description.equals("") && !groupe.equals("") && !prix.equals("") && !inputStream.equals("")){
	    	p = new Plat();
			p.setNom(nom);
			p.setDescription(description);
			grp = new Groupe();
			grp.setId(Integer.parseInt(groupe));
			p.setGroupe(grp);
			p.setPrix(Float.parseFloat(prix));
			manager.createPlat(p,ph);
			response.sendRedirect("GestionPlats");
	    }else if(!id.equals("") && !nom.equals("") && !description.equals("") && !groupe.equals("") && !prix.equals("") && !inputStream.equals("")){
			p = new Plat();
			p.setId(Integer.parseInt(id));
			p.setNom(nom);
			p.setDescription(description);
			if(groupe.length()>0){
				grp = new Groupe();
				grp.setId(Integer.parseInt(groupe));
				p.setGroupe(grp);
			}
			if(prix.length()>0)p.setPrix(Float.parseFloat(prix));
			manager.updatePlat(p,ph);
			response.sendRedirect("GestionPlats");
		}else{
			request.setAttribute("error",2);
			doGet(request,response);
		}
	}
}
