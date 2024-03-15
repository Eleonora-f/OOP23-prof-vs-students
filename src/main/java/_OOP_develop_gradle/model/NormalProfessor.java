package _OOP_develop_gradle.model;

import _OOP_develop_gradle.model.Professor;

public class NormalProfessor extends Professor{

	public static final int NORMALPROF_HIT_DAMAGE = 50; //DANNO CHE INFLIGGO ALLO STUDENTE
	public static final int NORMALPROF_BUY_COST = 20;
	public static final int NORMALPROF_HEALTHPOINTS = 150;
	public static final String NORMALPROF_IMG_PATH = "img/professor.png";
	public static final String normalProfBulletName = "normalProfBullet";
	public static final int NormalProfName = 2;
	public Bullet normalProfBullet;
	public int bulletSpeed=2;
	private String pathImgNP;
	
	/**
	 * Constructor for creating a NormalProfessor object.
	 *
	 * @param col The column position of the NormalProfessor.
	 * @param row The row position of the NormalProfessor.
	 */
	public NormalProfessor(int col, int row) {
		super(NORMALPROF_HIT_DAMAGE, NORMALPROF_HEALTHPOINTS, new Elements<Integer, Integer>(col, row), NORMALPROF_IMG_PATH, NORMALPROF_BUY_COST, NormalProfName);
		normalProfBullet = new Bullet(bulletSpeed, NORMALPROF_HIT_DAMAGE, new Elements<Integer, Integer>(col, row), normalProfBulletName, pathImgNP);
	}

	/**
     * Gets the bullet used by the NormalProfessor.
     *
     * @return The bullet used by the NormalProfessor.
     */
	public Bullet getNormalProfBullet() {
		return normalProfBullet;
	}

	/**
     * Sets the bullet used by the NormalProfessor.
     *
     * @param normalProfBullet The bullet to be used by the NormalProfessor.
     */
	public void setNormalProfBullet(Bullet normalProfBullet) {
		this.normalProfBullet = normalProfBullet;
	}

	/**
     * Gets the speed of the bullet used by the NormalProfessor.
     *
     * @return The speed of the bullet used by the NormalProfessor.
     */
	public int getBulletSpeed() {
		return bulletSpeed;
	}

	/**
     * Sets the speed of the bullet used by the NormalProfessor.
     *
     * @param bulletSpeed The speed of the bullet used by the NormalProfessor.
     */
	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	/**
     * Gets the name of the bullet used by the NormalProfessor.
     *
     * @return The name of the bullet used by the NormalProfessor.
     */
	public static String getNormalprofbulletname() {
		return normalProfBulletName;
	}
}