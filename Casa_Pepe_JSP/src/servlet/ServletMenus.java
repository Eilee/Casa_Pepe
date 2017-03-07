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

@WebServlet("/Menus")
public class ServletMenus extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMenus() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Menus");
		/*Manager manager = (Manager) request.getSession().getAttribute("Manager");
		ArrayList<Menu> listMenus = manager.getAllMenus();
		System.out.println("Menus OK");
		request.setAttribute("listMenus", listMenus);*/
		request.getServletContext().getRequestDispatcher("/WEB-INF/Menus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		/*if(manager.recupereAllPlatsServ()){
			System.out.println("Récup OK");
			response.sendRedirect("Menus.jsp");
			//passage de la session dans la request
			//request.setAttribute(request.getSession(), arg1);
		}else{
			System.out.println("Menus KO");
			doGet(request,response);
		}*/
	}
}
