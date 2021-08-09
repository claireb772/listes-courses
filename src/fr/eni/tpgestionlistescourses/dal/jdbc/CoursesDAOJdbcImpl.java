package fr.eni.tpgestionlistescourses.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tpgestionlistescourses.dal.CoursesDAO;
import fr.eni.tpgestionlistescourses.models.bo.Article;
import fr.eni.tpgestionlistescourses.models.bo.Courses;

public class CoursesDAOJdbcImpl implements CoursesDAO {

	private static final String SELECT = "select id, nom from LISTES";
	private static final String DELETE = "delete from LISTES where id =?";
	private static final String INSERT = "insert into LISTES (nom) values (?)";
	private static final String INSERTARTICLE = "insert into ARTICLES(nom, id_liste, coche) values (?,?,?)";
	private static final String SELECTARTICLE = "select nom from ARTICLES";
	private static final String SELECTALL = "select listes.id, listes.nom, articles.id as id_article, articles.nom as nom_article, id_liste, coche from listes inner join ARTICLES on listes.id = ARTICLES.id_liste";

	private PreparedStatement pstmt;

	@Override
	public Courses insert(Courses courses) throws Exception {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, courses.getNom());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				courses.setId(rs.getInt(1));
			}

			for (Article article : courses.getArticle()) {
				PreparedStatement rqtArticle = cnx.prepareStatement(INSERTARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				rqtArticle.setString(1, article.getNom());
				rqtArticle.setInt(2, courses.getId());
				rqtArticle.setBoolean(3, article.isEstCoche());
				rqtArticle.executeUpdate();
				ResultSet rsArticle = rqtArticle.getGeneratedKeys();
				if (rsArticle.next()) {
					article.setId(rsArticle.getInt(1));
				}
			}
			cnx.commit();
		} catch (SQLException e) {
			cnx.rollback();
			throw new Exception("Problème lors de l'ajout de la liste. [" + e.getMessage() + "]");
		}

		return courses;
	}

	@Override
	public void supprime(int id) throws Exception {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Problème lors de la suppression d'une liste de la base. Cause : " + e.getMessage());

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Courses> select() throws Exception {
		List<Courses> lesCourses = new ArrayList<Courses>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement rqt = cnx.createStatement();) {
			ResultSet rs = rqt.executeQuery(SELECTALL);

			int idCurrentListe = 0;
			Courses listeCourante = null;

			while (rs.next()) {
				if (idCurrentListe != rs.getInt("id")) {

					listeCourante = new Courses();
					listeCourante.setId(rs.getInt("id"));
					listeCourante.setNom(rs.getString("nom"));
					idCurrentListe = rs.getInt("id");

					// associe l'article à la liste

					Article article = new Article(rs.getInt("id_article"), rs.getString("nom_article"),
							rs.getBoolean("coche"));
					listeCourante.getArticle().add(article);

					lesCourses.add(listeCourante);

				} else {
					Article article = new Article(rs.getInt("id_article"), rs.getString("nom_article"),
							rs.getBoolean("coche"));
					listeCourante.getArticle().add(article);
				}

			}

		} catch (SQLException e) {
			throw new Exception("Problème d'extraction des courses de la base. Cause : " + e.getMessage());

		}

		return lesCourses;
	}

	/*
	 * @Override public List<Courses> select() throws Exception { List<Courses>
	 * lesCourses = new ArrayList<Courses>(); Connection cnx = null; Statement rqt =
	 * null; try { cnx = ConnectionProvider.getConnection(); rqt =
	 * cnx.createStatement(); ResultSet rs = rqt.executeQuery(SELECT); int
	 * idCurrentListe = 0; Courses listeCourante = null;
	 * 
	 * while (rs.next()) { listeCourante = new Courses();
	 * listeCourante.setId(rs.getInt("id"));
	 * listeCourante.setNom(rs.getString("nom")); idCurrentListe = rs.getInt("id");
	 * lesCourses.add(listeCourante); }
	 * 
	 * } catch (SQLException e) { throw new
	 * Exception("Problème d'extraction des courses de la base. Cause : " +
	 * e.getMessage());
	 * 
	 * } finally { try { if (rqt != null) { rqt.close(); }
	 * 
	 * if (cnx != null) { cnx.close(); } } catch (SQLException e) {
	 * e.printStackTrace(); } } return lesCourses; }
	 */
}
