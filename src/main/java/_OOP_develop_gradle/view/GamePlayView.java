package _OOP_develop_gradle.view;

import java.util.List;

import java.util.Iterator;

import _OOP_develop_gradle.controller.GamePlayController;
import _OOP_develop_gradle.controller.MenuController;
import _OOP_develop_gradle.model.Bullet;
import _OOP_develop_gradle.model.GamePlayModel;
import _OOP_develop_gradle.model.NormalProfessor;
import _OOP_develop_gradle.model.Professor;
import _OOP_develop_gradle.model.Rector;
import _OOP_develop_gradle.model.Student;
import _OOP_develop_gradle.model.Tutor;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class GamePlayView {
	// per gestire la griglia di gioco, popolarla con le immagini degli studenti, professori, ecc.,
	// e di rispondere agli eventi di clic sulla griglia --> per rispettare meglio il pattern MVC
	
	@FXML
    private AnchorPane GamePlayRoot;
    
    @FXML
    private ImageView lawnImage;
    
    @FXML
    private ImageView sunCountImage;
    
    @FXML
    private Label matchScoreLabel;
    
    @FXML
    private Label energyLabel;
    
    @FXML
    private ImageView gameMenuButton;
    
    @FXML
    private GridPane lawn_grid;
    
    @FXML
    private Label timeLabel;

    public GamePlayController gameController;
    public GamePlayModel gamePlayModel;
    
    private List<Tutor> tutorInGrid = new ArrayList<>(); // Lista di tutor presenti
    private List<NormalProfessor> normalProfInGrid = new ArrayList<>(); // Lista di normal p presenti
    private List<Rector> rectorInGrid = new ArrayList<>(); // Lista di rector presenti
    private List<Student> studentInGrid = new ArrayList<>(); // Lista di studenti presenti
    private List<Bullet>  bulletInGrid = new ArrayList<>();
    List<List<? extends Professor>> profsInGrid = new ArrayList<>();
    public boolean firstProfPicked;
    private List<StudentView> studentViewList = new ArrayList<>();
    private List<TutorView> tutorViewList = new ArrayList<>();
    private List<NormalProfView> normalProfessorViewList = new ArrayList<>();
    private List<RectorView> rectorViewList = new ArrayList<>();
    private List<BulletView>  bulletViewList = new ArrayList<>();
    
    
    public AnimationTimer  timer;
    public int timeTot ;// Partiamo da 2 minuti, quindi 120 secondi
    public int matchScore ;
    public long lastTimeUpdate = 0;
    public long ONE_SECOND = 1_000_000_000;
    public boolean timerStop = false;
    public int profChoosen;
    
    public List<StudentView> getStudentViewList() {
		return studentViewList;
	}
	public void setStudentViewList(List<StudentView> studentViewList) {
		this.studentViewList = studentViewList;
	}
	public List<TutorView> getTutorViewList() {
		return tutorViewList;
	}
	public void setTutorViewList(List<TutorView> tutorViewList) {
		this.tutorViewList = tutorViewList;
	}
	public List<NormalProfView> getNormalProfessorViewList() {
		return normalProfessorViewList;
	}
	public void setNormalProfessorViewList(List<NormalProfView> normalProfessorViewList) {
		this.normalProfessorViewList = normalProfessorViewList;
	}
	public List<RectorView> getRectorViewList() {
		return rectorViewList;
	}
	public void setRectorViewList(List<RectorView> rectorViewList) {
		this.rectorViewList = rectorViewList;
	}
	public List<BulletView> getBulletViewList() {
		return bulletViewList;
	}
	public void setBulletViewList(List<BulletView> bulletViewList) {
		this.bulletViewList = bulletViewList;
	}
	public void setController(GamePlayController gameController) {
        this.gameController = gameController;
    }
	public boolean isFirstProfPicked() {
		return firstProfPicked;
	}

	public void setFirstProfPicked(boolean firstProfPicked) {
		this.firstProfPicked = firstProfPicked;
	}
    @FXML
    public void initialize() {
    	
        try {
        	gameController = new GamePlayController();
            gameController.initData(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initializeView() {
    	
    	profChoosen=-1;
    	gamePlayModel = GamePlayController.getGameModel();
		timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (!timerStop) {
                    if (now - lastTimeUpdate >= ONE_SECOND) {
                    	timeTot = gamePlayModel.getTimeTot();
                    	if (timeTot > 0) {
                    		updateTempoLabel();
                    		updateEnergyLabel();
                    		updateMatchScoreLabel();
                            lastTimeUpdate = now;
                    	}else {
                        	timer.stop();
                    	}
                    }
            	}else{
            		timer.stop();
            	}
           }
        };
        timer.start();
    }
    
    public boolean isTimerStop() {
		return timerStop;
	}
    
	public void setTimerStop(boolean timerStop) {
		this.timerStop = timerStop;
	}
	
	private void updateTempoLabel() {
    	// funzione che mi aggiorna il tempo che scorre
    	timeTot--;
        int min = timeTot / 60;
        int sec = timeTot % 60;
        
        timeLabel.setText(String.format("%02d:%02d", min, sec));
        gamePlayModel.setTimeTot(timeTot);
        
	}
	
	public void updateEnergyLabel() {
		energyLabel.setText(String.format("%d", gamePlayModel.getEnergy()));
	}
    
	public void updateMatchScoreLabel() {
		matchScoreLabel.setText(String.format("Score: %d", gamePlayModel.getScoreMacth()));
		
	}
	public int getMatchScore() {
		return matchScore;
	}
	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}
	public void updatePositions(List<Student> studentList, List<List<? extends Professor>> profList, List<Bullet> bulletListNormal, List<Bullet> bulletList){
		Platform.runLater(() -> {
			
			removeImageViews();
	        updateStudentPositions(studentList);
	        updateProfessorPositions(profList);
	        updateBulletPositions(bulletList);
	        updateBulletPositions(bulletListNormal);
	    });
    }
	
	private void removeImageViews() {
	    lawn_grid.getChildren().removeIf(node -> node instanceof ImageView);
	}
	
	private void updateStudentPositions(List<Student> studentList) {
		studentInGrid =studentList;
	    List<Student> studentInGridCopy = new ArrayList<>(studentInGrid);

	    // Utilizzo un iteratore per iterare sulla copia della lista
	    Iterator<Student> iterator = studentInGridCopy.iterator();
	    while (iterator.hasNext()) {
	        Student student = iterator.next();

	        // Creo e aggiungo la vista dello studente alla lista studentViewList
	        StudentView studView = new StudentView(lawn_grid);
	        studentViewList.add(studView);
	        studView.displayElement(student.getPosition());
	    }
	}

	
	public void updateProfessorPositions(List<List<? extends Professor>> profsList) {
		 // Rimuovi le liste obsolete da profsInGrid
	    List<List<? extends Professor>> professorsToRemove = new ArrayList<>();

	    for (List<? extends Professor> professors : profsInGrid) {
	        if (!profsList.contains(professors)) {
	            professorsToRemove.add(professors);
	        }
	    }

	    profsInGrid.removeAll(professorsToRemove);
	    
	    for (List<? extends Professor> professors : profsInGrid) {
	        Iterator<? extends Professor> iterator = professors.iterator();
	        while (iterator.hasNext() && !professors.isEmpty()) {
	            Professor prof = iterator.next();
	            // Prendi le coordinate del professore e metti la foto corrispondente sulla griglia
	            if (prof instanceof Tutor) {
	                Tutor tutor = (Tutor) prof;
	                TutorView tutorView = new TutorView(lawn_grid);
	                tutorViewList.add(tutorView);
	                tutorView.displayElement(tutor.getPositionProf());
	            } else if (prof instanceof NormalProfessor) {
	                NormalProfessor normalProfessor = (NormalProfessor) prof;
	                NormalProfView normalProfessorView = new NormalProfView(lawn_grid);
	                normalProfessorViewList.add(normalProfessorView);
	                normalProfessorView.displayElement(normalProfessor.getPositionProf());
	            } else if (prof instanceof Rector) {
	                Rector rector = (Rector) prof;
	                RectorView rectorView = new RectorView(lawn_grid);
	                rectorViewList.add(rectorView);
	                rectorView.displayElement(rector.getPositionProf());
	            }
	        }
	    }
	}
	
	public void updateBulletPositions(List<Bullet> bulletList) {
	    bulletInGrid = bulletList;
	    
	    // Creo una copia della lista bulletInGrid
	    List<Bullet> bulletInGridCopy = new ArrayList<>(bulletInGrid);

	    // Utilizzo un iteratore per iterare sulla copia della lista
	    Iterator<Bullet> iterator = bulletInGridCopy.iterator();
	    while (iterator.hasNext() && !bulletInGridCopy.isEmpty()) {
	        Bullet bullet = iterator.next();

	        // Creo e aggiungo la vista del proiettile alla lista bulletViewList
	        BulletView bulletView = new BulletView(lawn_grid);
	        bulletViewList.add(bulletView);
	        bulletView.displayElement(bullet.getPosition());
	    }
	}



	public void removePosition(List<? extends ElementView> elementsToRemove) {
	    Platform.runLater(() -> {
	        for (ElementView elem : elementsToRemove) {
	            elem.removeElement();
	        }
	    });
	}
	
	/**
     * Function that handle the choice of a professor type and put it on the lawn grid
     * @param event
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
    	
        Integer columnIndex = GridPane.getColumnIndex((Region) event.getSource());
        Integer rowIndex = GridPane.getRowIndex((Region) event.getSource());

        System.out.println("Clicked at column " + columnIndex + " and row " + rowIndex);
        
	    if(profChoosen != -1) {
	    	if(columnIndex!=null && rowIndex!=null && !isProfInCell(columnIndex, rowIndex)) {
	    		setFirstProfPicked(true);
	    		switch(profChoosen) {
	    			case 1:
	    				Tutor tutornew = new Tutor(columnIndex, rowIndex);
	    				Bullet tutorBullet = tutornew.tutorBullet;
	    				
	    				if(tutornew.getEnergyProfessor() <= gamePlayModel.getEnergy()) {
	    					gamePlayModel.getTutorList().add(tutornew);
	    					tutorInGrid.add(tutornew);
	    					gamePlayModel.getBulletListNormal().add(tutorBullet);
	    					
	    					profsInGrid.add(gamePlayModel.getTutorList());
	    					profsInGrid.add(gamePlayModel.getNormalProfList());
	    					profsInGrid.add(gamePlayModel.getRectorList());
	    					
	    	    			updatePositions(gamePlayModel.getStudentList(), profsInGrid,gamePlayModel.getBulletListNormal(), gamePlayModel.getBulletListDiagonal());
	    	    			
	    	    			gamePlayModel.decreaseEnergy(tutornew.getEnergyProfessor());
	    	    		}
	    				break;
	    			case 2:
	    				NormalProfessor normalProfNew = new NormalProfessor(columnIndex, rowIndex);
	    				Bullet nProfBullet = normalProfNew.normalProfBullet;
	    				
	    				if(normalProfNew.getEnergyProfessor() <= gamePlayModel.getEnergy()) {
	    					gamePlayModel.getNormalProfList().add(normalProfNew);
	    					normalProfInGrid.add(normalProfNew);
	    					gamePlayModel.getBulletListNormal().add(nProfBullet); 
	    					
	    					profsInGrid.add(gamePlayModel.getTutorList());
	    					profsInGrid.add(gamePlayModel.getNormalProfList());
	    					profsInGrid.add(gamePlayModel.getRectorList());
	    					
	    	    			updatePositions(gamePlayModel.getStudentList(), profsInGrid,gamePlayModel.getBulletListNormal(), gamePlayModel.getBulletListDiagonal());
	    	    			
	    	    			
	    	    			gamePlayModel.decreaseEnergy(normalProfNew.getEnergyProfessor());
	    	    		}
	    				break;
	    			case 3:
	    				Rector rectornew = new Rector(columnIndex, rowIndex);
	    				Bullet rectorBullet = rectornew.rectorBullet;
	    				
	    				if(rectornew.getEnergyProfessor() <= gamePlayModel.getEnergy()) {
	    					gamePlayModel.getRectorList().add(rectornew);
	    					rectorInGrid.add(rectornew);
	    					gamePlayModel.getBulletListDiagonal().add(rectorBullet);
	    					
	    					profsInGrid.add(gamePlayModel.getTutorList());
	    					profsInGrid.add(gamePlayModel.getNormalProfList());
	    					profsInGrid.add(gamePlayModel.getRectorList());
	    					
	    	    			updatePositions(gamePlayModel.getStudentList(), profsInGrid,gamePlayModel.getBulletListNormal(), gamePlayModel.getBulletListDiagonal());
	    	    			
	    	    			// diminuisco la moneta tot 
	    	    			gamePlayModel.decreaseEnergy(rectornew.getEnergyProfessor());
	    	    		}
	    				break;
	    			default:
	    		        System.out.println("Il numero non è né 1, né 2, né 3");
	    		        break;
	    		 }
	    	}
	    }
    }
    
    /**
     * Function that opens the Game Menu 
     * @param event
     * @throws IOException
     */
    @FXML
    void GameMenu(MouseEvent event) throws IOException {
    	gameController.setGameStatus(false);
    	timerStop = true;// stoppo il timer quando apro il menù
    	GamePlayController.getInstance().setGameStatus(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
        Parent gameMenu = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(gameMenu));
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData();
        
        stage.show();
    }

    @FXML
    public void handleTutorCardClick(){
    	profChoosen=1;
    }
    
    @FXML
    public void handleNormalCardClick(){
    	profChoosen=2;
    }
    
    @FXML
    public void handleRectorCardClick(){
    	profChoosen=3;
    }
    /**
     * Check if the professor is in the cell with columnIndex and rowIndex
     * @param columnIndex
     * @param rowIndex
     * @return TRUE if prof is in the cell otherwise FALSE
     */
    // TODO da controllare e da mettere per tutte le tipologie di professori e se non c'è già uno studente
	private boolean isProfInCell(int columnIndex, int rowIndex) {
		return rectorInGrid.stream().anyMatch(p -> p.getPositionProf().getX() == columnIndex && p.getPositionProf().getY() == rowIndex);
	}

    
}
