package Weapon;

import Projectile.Projectile;
import java.io.Serializable;

/**
 *
 * @author Jere
 */
public abstract class Weapon implements Serializable {

    // range = pixels*10. So 1 range is 10 pixels moved up.
    protected int range;
    protected Projectile projectile;
    String imageReference;
//    protected BufferedImage texture;

    public Weapon(Projectile projectile, int range, String imageReference) {
        System.out.println(imageReference);
        this.projectile = projectile;
        this.range = range;
        this.imageReference = imageReference;
    }

    public Weapon(int range) {
        this.range = range;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setWeaponType(String imageReference) {
        this.imageReference = imageReference;
    }

    // Accessory methods
    public int getRange() {
        return range;
    }

    public int getDamage() {
        return projectile.getDamage();
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile proj) {
        this.projectile = proj;
    }
//    public BufferedImage getTexture() {
//        return texture;
//    }
//    public void setTexture(BufferedImage texture) {
//        this.texture = texture;
//
//    }
//
//    // Abstract methods.
//    public abstract void fire(Graphics2D g, int x, int y);
//
//    public void setTextureFromFile(String filename) {
//        try {
//            texture = ImageIO.read(new File(filename));
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//            System.err.println("Error with loading texture to unit.");
//        }
//    }
}
