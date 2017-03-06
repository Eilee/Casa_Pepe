package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Plat;
import validation.Validation;

public class ServletPlat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPlat() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Simple chargement de la page , aucun traitement
		System.out.println("doGet");
		request.getServletContext().getRequestDispatcher("/WEB-INF/Menus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Validation validation = new Validation();
		validation.nonVide(Plat.class,"nom",request.getParameter("nom"));
		validation.nonVide(Plat.class,"description",request.getParameter("description"));
		if(validation.isValide()){
			//si le resulat est valide , on redirige vers une autres vue
			request.setAttribute("mess", "Le livre a été enregistré");
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
		}else{
			//Sinon on redirige sur la même page
			request.setAttribute("validation", validation);
			request.getServletContext().getRequestDispatcher("/WEB-INF/Menus.jsp").forward(request, response);
		}
		//doGet(request,response);
	}
}
