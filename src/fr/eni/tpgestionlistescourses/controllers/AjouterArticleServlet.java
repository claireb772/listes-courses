package fr.eni.tpgestionlistescourses.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tpgestionlistescourses.models.bll.CoursesManager;
import fr.eni.tpgestionlistescourses.models.bo.Courses;

/**
 * Servlet implementation class AjouterArticleServlet
 */
@WebServlet("/ajout/article")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleListe.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// placement d'un cookie sur la liste
		String nomListe = request.getParameter("nom");

		CoursesManager mgr = new CoursesManager();

		String nomArticle = request.getParameter("article");

		try {
			mgr.ajouterCourses(nomListe, nomArticle);
			List<Courses> courses = mgr.afficherListeDeCourse();

			request.setAttribute("articles", courses);
		} catch (Exception e) {
			request.setAttribute("erreur", e);
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
