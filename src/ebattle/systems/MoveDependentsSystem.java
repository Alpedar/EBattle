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
import ebattle.components.Dependents;
import ebattle.components.DependsOn;
import ebattle.components.Independent;
import ebattle.components.Orientation;
import ebattle.components.Position;
import math.Vector2d;

public class MoveDependentsSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Orientation> om;
    @Mapper
    ComponentMapper<DependsOn> dm;
    @Mapper
    ComponentMapper<Dependents> dsm;

    public MoveDependentsSystem() {
        super(Aspect.getAspectForAll(Position.class, Independent.class, Dependents.class));
    }

    @Override
    protected void process(Entity e) {
        // Get the components from the entity using component mappers.
        Position position = pm.get(e);
        Dependents deps = dsm.get(e);
        Bag<Entity> dds = deps.getDirectDependents();
        Orientation orientation = om.getSafe(e);
        for (int i = 0; i < dds.size(); i++) {
            Entity de = dds.get(i);
            processDependentEntity(de, position, orientation);
        }
    }

    private void processDependentEntity(Entity de, Position pPos, Orientation pOri) {
        Position dPos = pm.getSafe(de);
        if (dPos == null) {
            return;
        }
        DependsOn don = dm.getSafe(de);
        Vector2d relPos = don.getRelPos();        
        Orientation nextOri = null;
        if (pOri != null) {
            Orientation ori = om.getSafe(de);
            relPos = relPos.rotate(pOri.getDirf());
            if (ori != null) {
                ori.setDir(pOri.getDir() + don.getRelDir());
                nextOri = ori;
            } else {
                nextOri = pOri;
            }
        }
        Vector2d nPos = pPos.getPos().add(relPos);
        dPos.setPos(nPos);
        Dependents dependents = dsm.getSafe(de);
        if (dependents != null) {
            Bag<Entity> dds = dependents.getDirectDependents();
            for (int i = 0; i < dds.size(); i++) {
                Entity dde = dds.get(i);
                processDependentEntity(dde, dPos, nextOri);
            }
        }
    }
}