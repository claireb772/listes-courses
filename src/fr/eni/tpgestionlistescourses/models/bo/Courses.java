package fr.eni.tpgestionlistescourses.models.bo;

import java.util.ArrayList;
import java.util.List;

public class Courses {

	private int id;
	private String nom;
	private List<Article> article;

	public Courses() {
		this.article = new ArrayList<Article>();
	}

	public Courses(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Courses(String nom, List<Article> article) {
		this.nom = nom;
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Article> getArticle() {
		return article;
	}

}
