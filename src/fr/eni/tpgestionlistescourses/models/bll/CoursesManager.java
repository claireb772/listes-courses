package fr.eni.tpgestionlistescourses.models.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.tpgestionlistescourses.dal.jdbc.CoursesDAOJdbcImpl;
import fr.eni.tpgestionlistescourses.models.bo.Article;
import fr.eni.tpgestionlistescourses.models.bo.Courses;

public class CoursesManager {

	private CoursesDAOJdbcImpl coursesDAO = new CoursesDAOJdbcImpl();

	public void supprimerCourses(int id) throws Exception {

		try {
			coursesDAO.supprime(id); // supprime également les articles associés grâce au on delete cascade
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("erreur lors de la suppression de l'article", e);
		}
	}

	public List<Courses> afficherListeDeCourse() throws Exception {
		List<Courses> courses = coursesDAO.select();
		return courses;
	}

	public void ajouterCourses(String nom, String article) throws Exception {
		List<Article> articlesList = new ArrayList<Article>();

		articlesList.add(new Article(article, false));

		// delègue à la DAL l'accès à la source de données
		Courses courses = new Courses(nom, articlesList);

		try {
			coursesDAO.insert(courses);

		} catch (Exception e) {
			throw new Exception("problème dans l'insertion d'une liste de courses." + e.getMessage());
		}

	}
}
