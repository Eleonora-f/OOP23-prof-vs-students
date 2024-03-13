package _OOP_develop_gradle.model;

import _OOP_develop_gradle.Elements;
import _OOP_develop_gradle.view.ProfessorView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Professor {
	private Pane pane; // Riferimento al pannello che contiene l'immagine del professore

	private ProfessorView ProfessorView;
	private int costProfessor;
	private int damage;
	private final Elements<Integer, Integer> position;
	private double healthPoints;
	public String pathImgP;
	private boolean hitted = false;
	public int id;

	public Professor(int damage, double healthPoints, Elements<Integer, Integer> position, String pathImgP, int costProfessor, int id) {
		this.damage = damage;
		this.healthPoints = healthPoints;
		this.position = position;
		this.pathImgP = pathImgP;
		this.costProfessor = costProfessor;
		this.id = id;
	}

	/*
	 * private ImageView createImageView() { ImageView image = new ImageView(new
	 * Image(path)); image.setFitWidth(50); // Imposta la larghezza desiderata
	 * image.setFitHeight(50); // Imposta l'altezza desiderata return image; }
	 */
	// Metodo per ottenere l'ImageView della pianta
	/*
	 * public ProfessorView getImageProf(Professor prof) { return new
	 * ProfessorView(pathImg); }
	 */
	
	public ImageView getImageProf(Professor prof) {
    	ImageView profImg = new ImageView(); // se crea già metodo --> stud.getImg() anche direttamente sotto nel setConstraints() ??
    	profImg.setImage(new Image(getClass().getResource(prof.getPathImgProf()).toString()));
		return profImg;
    }

	public String getPathImgProf() {
		return pathImgP;
	}
	
	public void setPathImgProf(String pathImg) {
		this.pathImgP = pathImg;
	}

	public boolean performAttack(Pane pane) {
		// Implementa la logica di attacco qui
		return false;
		
	}

	public double getHealthPointsProf() {
		return healthPoints;
	}

	
	 public void setHealthPointsProf(int healthPoints) 
	 { 
		 this.healthPoints = healthPoints; 
	 }
	 

	/*
	 * private void removeProfessorFromGrid() { // Implementa la logica di rimozione
	 * dalla griglia qui imageView.setVisible(false); imageView.setDisable(true); }
	 */
	public int getcostProfessor() {
		return costProfessor;
	}
	
	public void setcostProfessor(int costProfessor) {
		this.costProfessor = costProfessor;
	}


	/*
	 * Ti restituisce danno che il prf causa 
	 */
	public int getDamageProf() {
		return damage;
	}
	
	public void setDamagProf (int damage) {
		this.damage = damage;
	}

	public Elements<Integer, Integer> getPositionProf() {
		return position;
	}

	public boolean isAliveProf() {
		return healthPoints > 0;
	}
	
	/*
	 * Quando prof riceve un danno
	 */
	public void receiveDamageProf(double damageReceived) {
		healthPoints -= damageReceived;
		if (healthPoints <= 0) {
			destroyProf();
		}
	}
	
	public boolean hittedProfessor() {
		if (hitted == true) {
			return true;
		}
		return false;
	}

	public void setPane(Pane pane) {
        this.pane = pane;
    }
	
	public void destroyProf() {
		// Rimuovi l'immagine del professore dalla griglia o dal pannello
        //pane.getChildren().remove(getImageProf(this));
		if (pane != null) {
	        // Rimuovi l'immagine del professore dal pannello
	        pane.getChildren().remove(getImageProf(this));
	    }
	}

	public int getIDProf() {
		return id;
	}

}
