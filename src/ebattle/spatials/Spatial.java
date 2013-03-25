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
import org.newdawn.slick.Graphics;

/**
 *
 * @author Martin
 */
public abstract class Spatial {
    protected final World world;
    protected final Entity owner;
    protected final float xSize;
    protected final float ySize;
    protected Orientation orientation;
    protected Position position;

    public Spatial(World world, Entity owner, float xSize, float ySize) {
        this.world = world;
        this.owner = owner;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void initalize() {
        ComponentMapper<Orientation> oriM = world.getMapper(Orientation.class);
        orientation = oriM.getSafe(owner);
        if (orientation == null) {
            orientation = new Orientation(0);
        }
        ComponentMapper<Position> posiM = world.getMapper(Position.class);
        position = posiM.get(owner);
    }

    public abstract void render(Graphics g);
}
