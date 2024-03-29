package _OOP_develop_gradle.model;

public class Rector extends Professor{

	private Elements<Integer, Integer> currentPosition;
	
	public static final int RECTOR_HIT_DAMAGE = 50; 
	public static final int RECTOR_BUY_ENERGY = 30;
	public static final int RECTOR_HEALTHPOINTS = 150;
	public Bullet rectorBullet;
	private int bulletSpeed=1;
	
	public String pathImgR;
	public static final int rectorName = 3;
	
	
	/**
     * Constructor for creating a Rector object.
     *
     * @param col The column position of the Rector.
     * @param row The row position of the Rector.
     */
	public Rector(int col, int row) {
		super(RECTOR_HIT_DAMAGE, RECTOR_HEALTHPOINTS, new Elements<Integer, Integer>(col, row), RECTOR_BUY_ENERGY);
		this.currentPosition = new Elements<Integer, Integer>(col, row);
		int bulletCol = currentPosition.getX() + bulletSpeed;
	    int bulletRow = currentPosition.getY() + bulletSpeed;
	    rectorBullet = new Bullet(bulletSpeed, RECTOR_HIT_DAMAGE, new Elements<>(bulletCol, bulletRow));
	    
	}
	
	/**
     * Gets the bullet used by the Rector.
     *
     * @return The bullet used by the Rector.
     */
	public Bullet getRectorBullet() {
		return rectorBullet;
	}
	
	/**
     * Sets the bullet used by the Rector.
     *
     * @param rectorBullet The bullet to be used by the Rector.
     */
	public void setRectorBullet(Bullet rectorBullet) {
		this.rectorBullet = rectorBullet;
	}
	
	/**
     * Gets the speed of the bullet used by the Rector.
     *
     * @return The speed of the bullet used by the Rector.
     */
	public int getBulletSpeed() {
		return bulletSpeed;
	}
	
	/**
     * Sets the speed of the bullet used by the Rector.
     *
     * @param bulletSpeed The speed of the bullet used by the Rector.
     */
	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
}
