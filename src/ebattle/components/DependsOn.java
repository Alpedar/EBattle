/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import com.artemis.Entity;
import math.Vector2d;

/**
 *
 * @author Martin
 */
public class DependsOn extends Component {

    private Entity parent;
    private Vector2d relPos;
    private double relDir;

    public DependsOn(Entity parent, Vector2d relPos, double relDir) {
        this.parent = parent;
        this.relPos = relPos;
        this.relDir = relDir;
    }

    public DependsOn(Entity parent, Vector2d relPos) {
        this.parent = parent;
        this.relPos = relPos;
        this.relDir = Double.NaN;
    }

    public Entity getParent() {
        return parent;
    }

    public Vector2d getRelPos() {
        return relPos;
    }

    public double getRelDir() {
        return relDir;
    }

    public boolean haveRelDir() {
        return !Double.isNaN(relDir);
    }
}
