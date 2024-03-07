package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GamePlayModel2 {

	    private int solarEnergy; // Energia solare
	    private int timeTot; // Tempo di gioco
	    private List<Student> studentList; // Lista di studenti presenti
	    private List<Professor> profList; // Lista di prof presenti
	    private int COST_OF_PROF = 1;
	    private int HEALTH_POINTS = 5;
	    
	    public GamePlayModel2() {
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
	        // Creare una nuova ondata di studenti
	    	Random rand = new Random();

	        // num di studenti per ondata
	        int waveSize = rand.nextInt(4) + 1;// posso cambiare, messo per prova meglio se tolgo il numero e metto una variabile

	        for (int i = 0; i < waveSize; i++) {
	            int randomRow = rand.nextInt(5); // numero casuale tra 0 e 5 per le righe
	            int col = 8; // gli studenti partono dall ultima colonna e poi avanzano

	            generateNewStudent(col, randomRow);
	        }
	    	
	    }

	    public Professor generateNewProf(int col, int row) {
	        // Creare un nuovo professore
	    	
    		col = 0;
    		Professor newProf = new Professor("", HEALTH_POINTS, col, row, null);
    		profList.add(newProf);
    		return newProf;
	    }

	    public void generateNewStudent(int col, int row) {
	        // Creare un nuovo studente
	    	// da implementare correttamente quando verrà implementata la classe Student
	    	Student student = new Student(col,row);
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

	    

	    // Definizione classe Student (da implementare)

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

	    public void handleStudentKilled(Student student) {
	        increaseTimeTot(getTimeTot());
	        getStudentList().remove(student);
	        //gestire la view //penso che si debba gestire nel controller
	        //gameView.update(gameModel); ???

	    }

	    public void handleProfKilled(Professor prof) {
	        decreaseTimeTot(getTimeTot());
	        getProfList().remove(prof);
	        //gestire la view //penso che si debba gestire nel controller
	        //update(gameModel);

	    }

}