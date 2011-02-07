/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trunk.src.GUIComponents;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Seems like somewhere in here we need to have the keylisteners and
 * mouselisteners, since the frame is what is focused when you're clicking and
 * pressing keys. This means that the mousecontrol and keyboardcontrol classes
 * should be instances of keylistener and mouselistener so that we can just
 * pass those to the addlistener methods. Of couse, this is only guesswork, since
 * i've not done it before. Many problems here. I suggest for the time being we do
 * it the simple way and just make listeners in here to listen for things.
 * But there's another problem! If we're using this frame as the main thing, and
 * we're swapping panels, then listeners have to do different things based on game
 * state - might be solvable if we reference the panel and get the game state.
 * 
 * @author michal
 */
public class BaseFrame {

    JFrame bFrame;
    Dimension windowSize;

    public BaseFrame(Dimension windowSize) {
        this.windowSize = windowSize;
        initFrame();
    }

    /*
     * Initialises the frame. Sets the default close operation to exit.
     * Sets the width and height of the frame to the value passed in to
     * the constructor. Sets the frame to be enabled and sets it to be visible.
     */
    private void initFrame() {
        bFrame = new JFrame("ShootyThing");
        bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bFrame.setSize(windowSize);

        /*
         * Adds a gamepanel to the frame. Better way of doing this needed.
         */
        addPanel(new GamePanel());

        bFrame.setEnabled(true);
        bFrame.setVisible(true);
    }

    /**
     * Adds a panel to the frame.
     * @param addPanel Panel to add to the frame.
     */
    public void addPanel(JPanel addPanel) {
        bFrame.add(addPanel);
    }
    /**
     * Replaces the current panel in the frame with a new panel.
     * @param repPanel Panel to put into the frame.
     */
    public void replacePanel(JPanel repPanel) {
        bFrame.removeAll();
        bFrame.add(repPanel);
    }

}
