package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import manager.Manager;

@WebServlet("/GestionGroupes")
public class ServletGestionGroupes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionGroupes() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Gestion Groupes");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			ArrayList<Groupe> listGroupes = manager.getAllGroupe();
			request.setAttribute("listGroupes", listGroupes);
			request.getServletContext().getRequestDispatcher("/WEB-INF/GestionGroupes.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Gestion Plats");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		String create = request.getParameter("create");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String pdf = request.getParameter("pdf");
		if(create!=null){
			System.out.println("Creation d'un plat");
			response.sendRedirect("EditionGroupe");
		}else if(update!=null){
			System.out.println("Modification d'un groupe : "+update);
			response.sendRedirect("EditionGroupe?idGroupe="+update);
		}else if(delete!=null){
			System.out.println("Suppression d'un groupe : "+delete);
			manager.deleteGroupe(Integer.parseUnsignedInt(delete));
			doGet(request,response);
		}else if(pdf!=null){
			System.out.println("Production du pdf : "+pdf);
			
			doGet(request,response);
		}
	}
}
