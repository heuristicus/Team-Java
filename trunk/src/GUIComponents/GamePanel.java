package GUIComponents;

import Controls.Controls;
import Game.Game;
import Projectile.BasicProjectile;
import Projectile.Projectile;
import Spawn.Spawn;
import Unit.*;
import Unit.Player;
import Weapon.BasicWeapon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jere
 */
public class GamePanel extends JPanel {

    /*
     *  The panel needs to have somewhere to get data from, and logically this is
     * the game that is currently taking place. Perhaps have this as a constructor
     * parameter, or have it passed in using another method.
     */
    Game shootGame;
    JPanel gPanel;
    Color bgColor = Color.BLACK;
    Player one;
    Spawn sp;
    BasicWeapon base;
    int player1_x = 500;
    int player1_y = 500;
    Controls a;
    Boolean mouse; //if mouse is being used or not
    int width;
    int height;
    Timer timer;
    ArrayList<Unit> spawns;
    int counter;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        initialize();
    }

    /**
     * Most important method here. Draws all objects that need to be drawn.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        counter++;
        counter = counter % 400;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.blue);
//        g2.draw(new Line2D.Double(30, 30, 500, 500));
//        try{
//        shootGame.getProjectileArray().get(shootGame.getProjectileArray().size()-1).draw(g2);
//        }catch(Exception e){
//        }
        logic();

        render(g2);
    }

    // Initialization
    private void initialize() {
        timer = new Timer(20, new ActionListener() { //60 fps

            public void actionPerformed(ActionEvent e) {

                System.out.println("action");
                repaint();
            }
        });
        timer.start();
        sp = new Spawn();
        spawns = new ArrayList();
        base = new BasicWeapon();
        shootGame = new Game();
        one = new Player(200, 200, base, 200, player1_x, player1_y, Color.WHITE);
        a = new Controls();
        //gPanel = new JPanel();
        setBackground(bgColor);
        shootGame.pruneArrays(this.getSize());
        // height = this.getSize().height;
        // width = this.getSize().width;
        one.setLocation(new Point(player1_x, player1_y));
        mouse = a.isMouse();
        shootGame.addUnitToArray(one);
//        /*
//         * TODO: Implement automatic calling of spawn classes
//         * Below code generates 5 enemies at random position.
//         */
//        int type = 1;
//        assert (type >= 0 && type <= 2);
//        spawns = sp.spawnN(5, type);
//        for (int i = 0; i < spawns.size(); i++) {
//            shootGame.addUnitToArray(spawns.get(i));
//        }
        setFocusable(true);
        addMouseListener(a);
        addKeyListener(a);
        addMouseMotionListener(a);
        hideMouse();
    }

    // Logic methods
    private void logic() {
        mouse = a.isMouse();
        movement();
        shootGame.pruneArrays(new Dimension(width, height));
        shootGame.moveEnemies();
        shootGame.moveProjectiles();
        shootGame.doNaiveCollisionDetection();
    }

    // Rendering methods
    private void render(Graphics2D g2) {
        super.paintComponent(g2);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-aliasing
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        for (Projectile p : shootGame.getProjectileArray()) {
//            p.draw(g2);
////            System.out.println("x= " + p.getX());
////            System.out.println("y= " + p.getY());
//            p.doMove();
//
//        }

        drawShips(g2);
        drawProjectiles(g2);
    }

    private void drawShips(Graphics2D g2) {
//        shootGame.removeUnitFromArray(one);
        one.setLocation(new Point(player1_x, player1_y));
//        shootGame.addUnitToArray(one);
        ArrayList<Unit> units = shootGame.getUnitArray();
        for (Unit unit : units) {
            unit.draw(g2);
        }
        System.out.println(counter);
        if(counter == 399){
                    /*
         * TODO: Implement automatic calling of spawn classes
         * Below code generates 5 enemies at random position.
         */
        int type = 1;
        assert (type >= 0 && type <= 2);
        spawns = sp.spawnN(5, type);
        for (int i = 0; i < spawns.size(); i++) {
            shootGame.addUnitToArray(spawns.get(i));
        }
        }
        //shootGame.getUnitArray().get(shootGame.getUnitArrayLength() - 1).draw(g2);
    }

    private void drawProjectiles(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        ArrayList<Projectile> projectiles = shootGame.getProjectileArray();
        for (Projectile projectile : projectiles) {
            projectile.draw(g2);
        }
        
//        try{
//        shootGame.getProjectileArray().get(shootGame.getProjectileArray().size()-1).draw(g2);
//        }catch(Exception e){
//
//        }
//        for (Projectile p : shootGame.getProjectileArray()) {
//            p.draw(g2);
//            p.doMove();
//
//        }
    }

    /**
     * used to control the movement
     */
    private void movement() {
        if (mouse) {
            player1_x = a.getMouseX();
            player1_y = a.getMouseY();
        } else {
            if (a.isUp() && player1_y > 0) {
                player1_y -= 5;
            }
            if (a.isDown() && player1_y < height) {
                player1_y += 5;
            }
            if (a.isLeft() && player1_x > 0) {
                player1_x -= 5;
            }
            if (a.isRight()&& player1_x < width) {
                player1_x += 5;
            }

        }
        if (a.isSpace()) {
            //testPro = new BasicProjectile(one.getX(), one.getY());
            shootGame.addProjectileToArray(new BasicProjectile(one.getX(), one.getY() - 15));
        }
    }

    private void hideMouse() {
        ImageIcon invisi = new ImageIcon(new byte[0]);
        Cursor invisible = getToolkit().createCustomCursor(
                invisi.getImage(), new Point(0, 0), "Hiding");
        this.setCursor(invisible);
    }
}
