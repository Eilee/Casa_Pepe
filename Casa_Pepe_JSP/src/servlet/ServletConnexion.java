package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Manager;

@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Simple chargement de la page , aucun traitement
		System.out.println("doGet Connexion");
Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			request.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ident = request.getParameter("ident");
		String mdp = request.getParameter("mdp");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		if(manager.isIdentificationValid(ident, mdp)){
			System.out.println("Identification OK");
			response.sendRedirect("Accueil");
			//passage de la session dans la request
			//request.setAttribute("session", request.getSession());
		}else{
			System.out.println("Identification KO");
			request.setAttribute("ident", ident);
			request.setAttribute("mdp", mdp);
			request.setAttribute("error", 2);
			doGet(request,response);
		}
	}
}
