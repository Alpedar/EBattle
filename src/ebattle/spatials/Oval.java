/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.spatials;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import ebattle.components.Orientation;
import ebattle.components.Position;
import math.Constants;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Martin
 */
public class Oval extends Spatial {

    private Shape shape;

    public Oval(World world, Entity owner, float xSize, float ySize) {
        super(world, owner, xSize, ySize);
    }


    @Override
    public void initalize() {      
        Ellipse el = new Ellipse(0, 0, xSize, ySize);
        shape = el;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.setAntiAlias(true);
        Shape t = shape.transform(Transform.createRotateTransform(orientation.getDirf()));
        t.setLocation(position.getXf(), position.getYf());
        g.draw(t);
    }
}
