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
public class Unknown extends Spatial {

    private Shape shape;

    public Unknown(World world, Entity owner, float xSize, float ySize) {
        super(world, owner, xSize, ySize);
    }


    @Override
    public void initalize() {
        super.initalize();
        Polygon p = new Polygon();
        Polygon pol = new Polygon();
        final float x2 = xSize / 2;
        final float y2 = ySize / 2;  
        pol.addPoint(-x2, -y2);
        pol.addPoint(+x2, -y2);
        pol.addPoint(+x2, +y2);
        pol.addPoint(-x2, +y2);
        pol.setClosed(true);
        shape = pol;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.setAntiAlias(true);
        Shape t = shape.transform(Transform.createRotateTransform(orientation.getDirf()));
        t.setLocation(position.getXf(), position.getYf());
//        g.fill(t);
        g.draw(t);
    }
}
