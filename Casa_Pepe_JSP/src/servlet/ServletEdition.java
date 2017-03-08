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

@WebServlet("/Edition")
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
		}else if(idPlat!=null){
			Plat p = manager.getPlat(Integer.parseInt(idPlat));
			request.setAttribute("plat", p);
		}
		ArrayList<Groupe> grp = manager.getAllGroupe();
		request.setAttribute("groupes", grp);
		request.getServletContext().getRequestDispatcher("/WEB-INF/Edition.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Edition");
		/*Manager manager = (Manager) request.getSession().getAttribute("Manager");
		String create = request.getParameter("create");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		if(create!=null){
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
