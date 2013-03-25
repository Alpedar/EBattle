/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.Bag;
import ebattle.components.Orientation;
import ebattle.components.Position;
import ebattle.components.SpatialDescriptor;
import ebattle.spatials.Spatial;
import ebattle.spatials.Triangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class RenderSystem extends EntityProcessingSystem {

    private Graphics graphics;
    private Bag<Spatial> spatials;
    private ComponentMapper<Position> posMapper;
    private ComponentMapper<Orientation> oriMapper;
    private ComponentMapper<SpatialDescriptor> spdMapper;
    private GameContainer container;

    public RenderSystem(GameContainer container) {
        super(Aspect.getAspectForAll(Position.class, Orientation.class));
        this.container = container;
        this.graphics = container.getGraphics();

        spatials = new Bag<>();
    }

    @Override
    public void initialize() {
        posMapper = world.getMapper(Position.class);
        oriMapper = world.getMapper(Orientation.class);
        spdMapper = world.getMapper(SpatialDescriptor.class);
    }

    @Override
    protected void process(Entity e) {
        Spatial spatial = spatials.get(e.getId());
        Position position = posMapper.get(e);
        Orientation orientation = oriMapper.get(e);
        if (position.getX() >= 0 && position.getY() >= 0
                && position.getX() < container.getWidth()
                && position.getY() < container.getHeight()
                && spatial != null) {
            spatial.render(graphics);
        }
    }

    @Override
    protected void end() {
        graphics.setColor(Color.white);
        graphics.drawString("Active entities: " + world.getEntityManager().getActiveEntityCount(), 10, 25);
        graphics.drawString("Total added to world since start: " + world.getEntityManager().getTotalAdded(), 10, 40);
        graphics.drawString("Total created in world since start: " + world.getEntityManager().getTotalCreated(), 10, 55);
        graphics.drawString("Total deleted from world since start: " + world.getEntityManager().getTotalDeleted(), 10, 70);
//        graphics.drawString("Keys: A and D for movement, spacebar to shoot.", 10, 100);
//        graphics.drawString("Enable/Disable player ship entity: Q and W.", 10, 115);
    }

    @Override
    protected void inserted(Entity e) {
        Spatial spatial = createSpatial(e);
        System.out.println("Creted " + spatial);
        if (spatial != null) {
            spatial.initalize();
            spatials.set(e.getId(), spatial);
        }
    }

    @Override
    protected void removed(Entity e) {
        spatials.set(e.getId(), null);
    }

    private Spatial createSpatial(Entity e) {
//                return new Triangle(world, e);
        SpatialDescriptor spd = spdMapper.getSafe(e);
        if (spd == null) {
            return new Triangle(world, e, 10, 10);
        }
        return spd.createSpatial(world, e);
    }
}
