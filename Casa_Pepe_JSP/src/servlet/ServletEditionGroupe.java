package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import manager.Manager;

@WebServlet("/EditionGroupe")
@MultipartConfig(maxFileSize = 16177215)
public class ServletEditionGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditionGroupe() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Edition Groupe");
		String idGroupe = request.getParameter("idGroupe");

		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			if(idGroupe!=null){
				Groupe grp = manager.getGroupe(Integer.parseInt(idGroupe));
				request.setAttribute("groupe",grp);
			}
			request.getServletContext().getRequestDispatcher("/WEB-INF/EditionGroupe.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Edition");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		if(manager==null)response.sendRedirect("Accueil");
		
		Groupe grp = null;
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
	    if(id.length()<1 && nom!=null){
	    	grp = new Groupe();
			grp.setNom(nom);
			manager.createGroupe(grp);
	    }else if(nom!=null){
	    	grp = new Groupe();
	    	grp.setId(Integer.parseInt(id));
			grp.setNom(nom);
			manager.updateGroupe(grp);
		}
		response.sendRedirect("GestionGroupes");
	}
}