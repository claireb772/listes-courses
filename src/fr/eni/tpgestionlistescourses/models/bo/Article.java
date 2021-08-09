package fr.eni.tpgestionlistescourses.models.bo;

public class Article {

	private int id;
	private String nom;
	private boolean estCoche;

	public Article() {
	}

	public Article(String nom, boolean estCoche) {
		this.nom = nom;
		this.estCoche = estCoche;
	}

	public Article(int id, String nom, boolean estCoche) {
		super();
		this.id = id;
		this.nom = nom;
		this.estCoche = estCoche;
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

	public boolean isEstCoche() {
		return estCoche;
	}

	public void setEstCoche(boolean estCoche) {
		this.estCoche = estCoche;
	}

}
