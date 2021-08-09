package fr.eni.tpgestionlistescourses.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tpgestionlistescourses.models.bll.CoursesManager;

/**
 * Servlet implementation class SupprimerCoursesServlet
 */
@WebServlet("/supprimer/courses")
public class SupprimerCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerCoursesServlet() {
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
		String coursesId = request.getParameter("idCourses");
		int idCourses = Integer.parseInt(coursesId);

		CoursesManager mgr = new CoursesManager();

		try {
			mgr.supprimerCourses(idCourses);
			String message = "La liste a été supprimée";
			request.setAttribute("msg", message);
		} catch (Exception e) {
			request.setAttribute("erreur", e);
			e.printStackTrace();
		} finally {

			RequestDispatcher rd = request.getRequestDispatcher("/listescourses");
			if (rd != null) {
				rd.forward(request, response);
			}
		}
	}

}
