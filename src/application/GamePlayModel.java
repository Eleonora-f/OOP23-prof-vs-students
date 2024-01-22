package application;

import java.util.List;
import java.util.ArrayList;

public class GamePlayModel {

	    private int solarEnergy; // Energia solare
	    private int timeTot; // Tempo di gioco
	    private List<Student> studentList; // Lista di studenti presenti
	    private List<Professor> profList; // Lista di prof presenti
	    private int COST_OF_PROF = 1;
	    private int HEALTH_POINTS = 5;
	    
	    public GamePlayModel() {
	        this.solarEnergy = 0;
	        this.timeTot = 0;
	        this.studentList = new ArrayList<>();
	        this.profList = new ArrayList<>();
	    }

		public int getSolarEnergy() {
	        return solarEnergy;
	    }

	    public int getTimeTot() {
	        return timeTot;
	    }

	    public List<Student> getStudentList() {
	        return studentList;
	    }

	    public List<Professor> getProfList() {
	        return profList;
	    }

	    public void generateWave() {
	        // Creare una nuova ondata di zombie
	    }

	    public void generateNewProf() {
	        // Creare un nuovo professore
	    	for (int row=1; row<5; row++) {
	    		int col = 0;
	    		Professor newProf = new Professor(null, HEALTH_POINTS, col, row);
	    		profList.add(newProf);
	    	}
	    	
	    }

	    public void generateNewStudent() {
	        // Creare un nuovo studente
	    	// da implementare correttamente quando verrà implementata la classe Student
	    	Student student = new Student(1,1);
	    	studentList.add(student);
	    }

	    public void increaseSolarEnergy(int amount) {
	        solarEnergy += amount;
	    }

	    public void decreaseSolarEnergy(int amount) {
	        if (solarEnergy >= amount) {
	            solarEnergy -= amount;
	        } else {
	        	// Manca energia
	        }
	    }
	    
	    public void increaseTimeTot(int amount) {
	    	timeTot += amount;
	    }

	    public void decreaseTimeTot(int amount) {
	        if (timeTot >= amount) {
	        	timeTot -= amount;
	        } else {
	            // Manca tempo
	        }
	    }

	    public void profKilled(Professor prof) {
	        // Logica quando un professore viene ucciso
	    	// [prof.getTimeCost()] per ogni professore morto devo togliere un tot di tempo, 
	    	// sarebbe da togliere in base al tipo di prof, per ora lo metto un valore fisso
	    	decreaseTimeTot(COST_OF_PROF);
	        // Rimuovi il prof dalla lista
	        profList.remove(prof);
	    }

	    

	    // Definizione delle classi Student e Prof (da implementare)

	    public class Student {
	        public int col;
	        public int row;
	        
			public Student(int col, int row) {
				this.col = col;
				this.row = row;
			}

			public int getCol() {
				return col;
			}

			public void setCol(int col) {
				this.col = col;
			}

			public int getRow() {
				return row;
			}

			public void setRow(int row) {
				this.row = row;
			}
	        
	        // ...
	    }


}
