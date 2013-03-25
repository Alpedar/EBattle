/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import ebattle.components.Dependents;
import ebattle.components.DependsOn;
import ebattle.components.Independent;
import ebattle.components.Orientation;
import ebattle.components.Position;
import ebattle.components.RotSpeed;
import ebattle.components.SpatialDescriptor;
import ebattle.components.Velocity;
import ebattle.spatials.SpatialType;
import ebattle.systems.MoveDependentsSystem;
import ebattle.systems.MovementSystem;
import ebattle.systems.RenderSystem;
import ebattle.systems.RotateIndependentsSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import math.Constants;
import math.Vector2d;
import org.newdawn.slick.*;

public class HelloWorld extends BasicGame {

    private World world;
    private GameContainer container;
    private EntitySystem renderSystem;

    public HelloWorld() {
        super("Hello World");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.container = gc;

        world = new World();
        world.setManager(new GroupManager());
        world.setManager(new TagManager());

        world.setSystem(new RotateIndependentsSystem());
        world.setSystem(new MovementSystem());
        world.setSystem(new MoveDependentsSystem());

        renderSystem = world.setSystem(new RenderSystem(container), true);

        world.initialize();
        initSpatials();
    }

    private void initSpatials() {
        Random r = new Random(0);
        for (int i = 0; 10 > i; i++) {
//            createEnemyShip(world, r.nextInt(container.getWidth()), -50, 0f, 0.05f).addToWorld();
            float vx = (float) (r.nextDouble() * 0.1);
            float vy = (float) (r.nextDouble() * 0.1);
            List<Entity> el = new ArrayList<>();
            createEnemyShip(world, r.nextInt(container.getWidth()), 150, vx, vy, el);
            for (Entity e : el) {
                e.addToWorld();
            }
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        world.setDelta(delta);
        world.process();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawString("Hello World", 100, 100);
        renderSystem.process();

    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new HelloWorld());

        app.setDisplayMode(800, 600, false);
        app.start();
    }

    private void createEnemyShip(World world, int x, int y, float vx, float vy,
            List<Entity> createdEntities) {
        Entity e = world.createEntity();
        createdEntities.add(e);
        e.addComponent(new Position(x, y));
        //        e.addComponent(new Orientation(0));
        vx = 0;
        vy = 0.01f;
        Velocity v = new Velocity(vx, vy);
        e.addComponent(v);
        Orientation o = new Orientation(v.getDir());
        e.addComponent(o);
        e.addComponent(new Independent());
        e.addComponent(new RotSpeed(0.0001));
//        e.addComponent(new SpatialDescriptor("Oval"));
//        e.addComponent(new SpatialDescriptor("Picture","art/trup.png",200,200));
        e.addComponent(new SpatialDescriptor(SpatialType.PICTURE, "art/trup.png", 200, 200));

        Dependents deps = new Dependents();
        e.addComponent(deps);
        world.getManager(GroupManager.class).add(e, "ENEMY_SHIPS");
        float xOff = 0;
        float yOff = 10;
        {
            Entity turret = createShipComponent(world, createdEntities);
            deps.getDirectDependents().add(turret);
            turret.addComponent(new DependsOn(e, new Vector2d(xOff, yOff), Constants.DEG2RAD * 0));
        }
        {
            Entity turret = createShipComponent(world, createdEntities);
            deps.getDirectDependents().add(turret);
            turret.addComponent(new DependsOn(e, new Vector2d(xOff, -yOff), Constants.DEG2RAD * 0));
        }
//        System.out.println("y = " + y);
//        if (y % 2 == 0) {
//            createEnemyShip(world, x, y + 51, vx, vy * 2);
//        }
    }

    private Entity createShipComponent(World world, List<Entity> createdEntities) {
        Entity e = world.createEntity();
        createdEntities.add(e);
        e.addComponent(new Position());
        e.addComponent(new Orientation());
//        e.addComponent(new SpatialDescriptor(SpatialType.TRIANGLE, null, 10, 10));
        e.addComponent(new SpatialDescriptor(SpatialType.PICTURE, "art/vez.png", 20, 20));

        world.getManager(GroupManager.class).add(e, "COMPONENT");
        return e;
    }
}
