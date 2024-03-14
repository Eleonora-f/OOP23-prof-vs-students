package _OOP_develop_gradle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import _OOP_develop_gradle.Elements;

public class RectorTest {

    private Rector rector;

    public void setUp() {
        rector = new Rector(0, 0);
    }

    @Test
    public void testGetRectorBullet() {
        assertNotNull(rector.getRectorBullet());
    }

    @Test
    public void testSetRectorBullet() {
        Bullet bullet = new Bullet(3, 25, new Elements<>(1, 1), "bulletName", "bulletPath");
        rector.setRectorBullet(bullet);
        assertEquals(bullet, rector.getRectorBullet());
    }

    @Test
    public void testGetBulletSpeed() {
        assertEquals(3, rector.getBulletSpeed());
    }

    @Test
    public void testSetBulletSpeed() {
        rector.setBulletSpeed(5);
        assertEquals(5, rector.getBulletSpeed());
    }

    @Test
    public void testGetRectorbulletname() {
        assertEquals("rectorBullet", Rector.getRectorbulletname());
    }

    @Test
    public void testShootDiagonal() {
        int initialCol = rector.getPositionProf().getX();
        int initialRow = rector.getPositionProf().getY();
        rector.shootDiagonal(10, 10);
        //assertEquals(initialCol + rector.getBulletSpeed(), rector.getRectorBullet().getPosition().getX());
        //assertEquals(initialRow + rector.getBulletSpeed(), rector.getRectorBullet().getPosition().getY());
    }
}
