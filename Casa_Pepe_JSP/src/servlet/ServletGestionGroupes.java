package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import bean.Plat;
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
		ArrayList<Groupe> listGroupes = manager.getAllGroupe();
		System.out.println(listGroupes.size());
		System.out.println("GestionGroupes OK");
		request.setAttribute("listGroupes", listGroupes);
		request.getServletContext().getRequestDispatcher("/WEB-INF/GestionGroupes.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		/*if(manager.recupereAllPlatsServ()){
			System.out.println("GestionGroupes OK");
			response.sendRedirect("GestionGroupes.jsp");
			//passage de la session dans la request
			//request.setAttribute(request.getSession(), arg1);
		}else{
			System.out.println("GestionGroupes KO");
			doGet(request,response);
		}*/
	}
}
