/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.spatials;

import com.artemis.Entity;
import com.artemis.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Martin
 */
public class Picture extends Spatial {

    private String fileName;
    Image img;
    private int iw;
    private int ih;

    public Picture(String fileName, World world, Entity owner, float xSize, float ySize) {
        super(world, owner, xSize, ySize);
        this.fileName = fileName;
    }

    @Override
    public void initalize() {
        super.initalize();
        try {
            Image imgL = new Image(fileName);
            img = imgL.getScaledCopy((int) xSize, (int) ySize);
        } catch (SlickException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void render(Graphics g) {
        img.setRotation(orientation.getDirDegf() + 90);
        img.drawCentered(position.getXf(), position.getYf());

    }
}
