package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PdfCreator;
import bean.Plat;
import manager.Manager;

@WebServlet("/PdfFile")
public class ServletPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPdf() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet PDF");
		String filename = request.getServletContext().getRealPath("")+"/pdfFile.pdf";
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		
		if(manager==null){
			response.sendRedirect("Accueil");
		}else{
			PdfCreator pdfFile = new PdfCreator();
			int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
			ArrayList<Plat> list = manager.getPlatsDuGroupe(idGroupe);
			pdfFile.createPdf(filename,list);
			request.getServletContext().getRequestDispatcher("/WEB-INF/PdfFile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
