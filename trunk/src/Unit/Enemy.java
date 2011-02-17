/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Weapon.Weapon;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

/**
 *
 * @author michal
 */
public class Enemy extends Unit {

    Shape shape;

    public Enemy(int health, int speed, Shape shape, Weapon weaponType, int pointValue, int xCoord, int yCoord, Color color) {
        super(health, speed, weaponType, pointValue, xCoord, yCoord, color);
        this.shape = shape;
        check();
    }

    public void check() {
        System.out.println(health);
     //   System.out.println(shape);
        System.out.println(speed);
        System.out.println(weaponType);
        System.out.println(pointValue);
        System.out.println(xCoord + ", " + yCoord);
        System.out.println(color);
    }

    public void draw(Graphics g) {
        Graphics2D g_ = (Graphics2D) g;
        g_.setColor(color);
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(xCoord, yCoord + 20);
        triangle.lineTo(xCoord - 10, yCoord - 10);
        triangle.lineTo(xCoord + 10, yCoord - 10);
        Area a = new Area(triangle);
        g_.fill(a);
    }

    public void setShape(Shape s){
        shape = s;
    }

    public Shape getShape(){
        return shape;
    }
}
