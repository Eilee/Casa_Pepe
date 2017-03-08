package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Plat;
import manager.Manager;

@WebServlet("/GestionPlats")
public class ServletGestionPlats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionPlats() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Gestion Plats");
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			ArrayList<Plat> listPlats = manager.getAllPlat();
			request.setAttribute("listPlats", listPlats);
			request.getServletContext().getRequestDispatcher("/WEB-INF/GestionPlats.jsp").forward(request, response);
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
		if(create!=null){
			System.out.println("Creation d'un plat");
			response.sendRedirect("Edition");
		}else if(update!=null){
			System.out.println("Modification du plat : "+update);
			response.sendRedirect("Edition?idPlat="+update);
		}else if(delete!=null){
			System.out.println("Suppression du plat : "+delete);
			manager.deletePlat(Integer.parseUnsignedInt(delete));
			doGet(request,response);
		}
	}
}
