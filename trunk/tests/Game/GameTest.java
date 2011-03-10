/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Path.DiagonalPath;
import java.awt.Dimension;
import java.awt.Color;
import Unit.DefaultUnit;
import Unit.Player;
import java.util.ArrayList;
import Projectile.BasicProjectile;
import Projectile.Projectile;
import Unit.Enemy;
import Unit.Unit;
import Weapon.BasicWeapon;
import org.junit.*;

/**
 *
 * @author michal
 * @modified
 */
public class GameTest {

    Game g;
    Unit enemy;
    Unit player;
    Projectile proj;
    Projectile inF1;
    Projectile outF1;
    Unit inF2;
    Unit outF2;
    // the next 2 should be initialised so they overlap
    Unit collU;
    Projectile collP;
    // the next 2 should be initialised so they overlap
    Unit collU2;
    Projectile collP2;
    // initialised to overlap
    Unit crashUnit;
    Unit crashUnit2;
    // player to collide with an enemy
    Player crashPlayer;
    Enemy crashEnemy;
    // unit separate from colliding units
    Unit noColl;
    Projectile noHit;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     */
    @Before
    public void setUp() {
        g = new Game(); // game object
        // Enemy
        enemy = new Enemy(300, 100, new BasicWeapon(), new DiagonalPath(), 20, 10, 10, Color.red);
        // Player
        player = new Player(300, 200, new BasicWeapon(), 0, 40, 40, Color.green);
        // Projectile
        proj = new BasicProjectile(20, 20);
        // Projectile outside the bounds of a normal frame
        outF1 = new BasicProjectile(-300, -300);
        // Unit outside the bounds of a normal frame.
        outF2 = new DefaultUnit(-400, -400);
        // Projectile inside normal frame bounds.
        inF1 = new BasicProjectile(40, 40);
        // Unit inside normal frame bounds.
        inF2 = new DefaultUnit(60, 60);

        // Units and projectiles for collisions
        collP = new BasicProjectile(120, 120);
        collU = new DefaultUnit(118, 118);
        collP2 = new BasicProjectile(200, 200);
        collU2 = new DefaultUnit(202, 202);
        crashUnit = new DefaultUnit(400, 400);
        crashUnit2 = new DefaultUnit(401, 401);
        crashPlayer = new Player(10, 10, new BasicWeapon(), 10, 50, 50, Color.yellow);
        crashEnemy = new Enemy(10, 10, new BasicWeapon(), new DiagonalPath(), 40, 50, 50, Color.yellow);
        // shouldn't collide with anything
        noColl = new DefaultUnit(600, 600);
        noHit = new BasicProjectile(580, 580);
    }

    /**
     * Sets all values used to null, to ensure that we're not using objects that
     * were modified by previous tests.
     */
    @After
    public void tearDown() {
        g = null;
        enemy = null;
        proj = null;
        player = null;
        inF1 = null;
        outF1 = null;
        inF2 = null;
        outF2 = null;
    }

    /**
     * Array getter methods should be tested first, since if they don't work
     * properly, everything else is going to be broken as well, probably.
     */
    /**
     * Test of getUnitArray method, of class Game.
     * Tests that the initial array in the game class is the same as one created
     * here.
     */
    @Test
    public void testGetUnitArray() {
        ArrayList<Unit> expRes = new ArrayList<Unit>();
        org.junit.Assert.assertArrayEquals(expRes.toArray(), g.getUnitArray().toArray());
    }

    /**
     * Test of getProjectileArray method, of class Game.
     * Checks that the initial array created by the game class is the same as
     * one created in this class.
     */
    @Test
    public void testGetProjectileArray() {
        ArrayList<Projectile> expRes = new ArrayList<Projectile>();
        org.junit.Assert.assertArrayEquals(expRes.toArray(), g.getProjectileArray().toArray());
    }

    /**
     * Test of addUnitToArray method, of class Game.
     * Adds a unit to the array created here, and one to the array created in
     * the game class, and then checks that these arrays contain the same items.
     */
    @Test
    public void testAddUnitToArray() {
        ArrayList<Unit> expArray = new ArrayList<Unit>();
        expArray.add(player);
        g.addUnitToArray(player);
        org.junit.Assert.assertArrayEquals(expArray.toArray(), g.getUnitArray().toArray());
    }

    /**
     * Test of addProjectileToArray method, of class Game.
     * Adds a projectile to the array here, and to the array in game, and checks that
     * they are equal.
     */
    @Test
    public void testAddProjectileToArray() {
        ArrayList<Projectile> expArray = new ArrayList<Projectile>();
        expArray.add(proj);
        g.addProjectileToArray(proj);
        org.junit.Assert.assertArrayEquals(expArray.toArray(), g.getProjectileArray().toArray());
    }

    /**
     * Checks whether the prune units method works correctly. Adds two units to the
     * game array, one inside the bounding box, and one outside, prunes the array,
     * and then checks to see if it is the same as the one here, which contains only
     * the unit that is inside the bounds.
     */
    @Test
    public void testPruneUnitArray() {
        ArrayList<Unit> expUnit = new ArrayList<Unit>();
        expUnit.add(inF2);
        g.addUnitToArray(outF2); // adds a unit outside the bounds
        g.addUnitToArray(inF2); // adds a unit inside the bounds
        g.pruneEnemyArray(800, 600);
        org.junit.Assert.assertArrayEquals(expUnit.toArray(), g.getUnitArray().toArray());
    }

    /**
     * Checks whether the prune projectiles method works correctly. Adds two projectiles to the
     * game array, one inside the bounding box, and one outside, prunes the array,
     * and then checks to see if it is the same as the one here, which contains only
     * the projectile that is inside the bounds.
     */
    @Test
    public void testPruneProjectileArray() {
        ArrayList<Projectile> expProj = new ArrayList<Projectile>();
        expProj.add(inF1);
        g.addProjectileToArray(outF1); // adds a projectile outside the bounds
        g.addProjectileToArray(inF1); // adds a projectile inside the bounds
        g.pruneProjectileArray(800, 600);
        org.junit.Assert.assertArrayEquals(expProj.toArray(), g.getProjectileArray().toArray());
    }

    /**
     * Tests that the pruneArray method works correctly. Does essentially the same
     * stuff as the individual test methods, but adds to both arrays at once, and
     * ensures that they are both pruned by the method once it is called.
     */
    @Test
    public void testPruneArray() {
        ArrayList<Unit> expUnit = new ArrayList<Unit>();
        ArrayList<Projectile> expProj = new ArrayList<Projectile>();
        expProj.add(inF1);
        expUnit.add(inF2);
        g.addProjectileToArray(outF1); // adds a projectile outside the bounds
        g.addProjectileToArray(inF1); // adds a projectile inside the bounds
        g.addUnitToArray(outF2); // adds a unit outside the bounds
        g.addUnitToArray(inF2); // adds a unit inside the bounds
        g.pruneArrays(new Dimension(800, 600));
        org.junit.Assert.assertArrayEquals(expProj.toArray(), g.getProjectileArray().toArray());
        org.junit.Assert.assertArrayEquals(expUnit.toArray(), g.getUnitArray().toArray());
    }

    @Test
    public void testNaiveCollisionDetection() {
        ArrayList<Unit> expUnit = new ArrayList<Unit>();
        ArrayList<Projectile> expProj = new ArrayList<Projectile>();
        expProj.add(noHit);
        expUnit.add(noColl);
        g.addProjectileToArray(collP);
        g.addProjectileToArray(collP2);
        g.addProjectileToArray(noHit);
        g.addUnitToArray(collU);
        g.addUnitToArray(collU2);
        g.addUnitToArray(crashEnemy);
        g.addUnitToArray(crashPlayer);
//        g.addUnitToArray(crashUnit);
//        g.addUnitToArray(crashUnit2);
        g.doNaiveCollisionDetection();
        org.junit.Assert.assertArrayEquals(expProj.toArray(), g.getProjectileArray().toArray());
        org.junit.Assert.assertArrayEquals(expUnit.toArray(), g.getUnitArray().toArray());
    }
}
