package _OOP_develop_gradle.view;

import javafx.scene.layout.GridPane;

/**
 * A view class for displaying an image of a normal professor.
 */

public class NormalProfView extends ElementView{

	/**
     * Constructor for NormalProfView.
     * @param gridPane The GridPane where the normal professor view will be added.
     */
	public NormalProfView(GridPane gridPane) {
		super(gridPane);
	}

	/**
     * Retrieves the path to the image file representing the normal professor.
     * @return The path to the image file.
     */
	@Override
    protected String getImagePath() {
        return "_OOP_develop_gradle/img/professoressa2Nobg.png"; 
    }
}
