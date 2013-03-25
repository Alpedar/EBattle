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
import ebattle.components.Orientation;
import ebattle.components.RotSpeed;
import ebattle.components.Velocity;
import math.Vector2d;

public class RotateIndependentsSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<Orientation> om;
    @Mapper
    ComponentMapper<RotSpeed> rsm;
    @Mapper
    ComponentMapper<Velocity> vm;

    public RotateIndependentsSystem() {
        super(Aspect.getAspectForAll(Orientation.class, RotSpeed.class, Independent.class));
    }

    @Override
    protected void process(Entity e) {
        Orientation ori = om.get(e);
        RotSpeed rspd = rsm.get(e);
        double nDir = ori.getDir() + rspd.getRotSpd() * world.getDelta();
        ori.setDir(nDir);
        Velocity vel = vm.getSafe(e);
        if (vel != null) {
            Vector2d v = vel.getVel();
            vel.setVel(Vector2d.createAngular(-nDir, v.getSize()));
        }
    }
}