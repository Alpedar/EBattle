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
import ebattle.components.Independent;
import ebattle.components.Position;
import ebattle.components.Velocity;
import math.Vector2d;

public class MovementSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Velocity> vm;
    @Mapper
    ComponentMapper<Independent> im;

    public MovementSystem() {
        super(Aspect.getAspectForAll(Position.class, Velocity.class, Independent.class));
    }

    @Override
    protected void process(Entity e) {
        // Get the components from the entity using component mappers.
        Position position = pm.get(e);
        Velocity velocity = vm.get(e);
        // Update the position.
        Vector2d pos = position.getPos();
        pos.addLocal(velocity.getX() * world.getDelta(),
                velocity.getY() * world.getDelta());

    }
}