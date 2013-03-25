/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import math.Vector2d;

/**
 *
 * @author Martin
 */
public class Position extends Component {

    private Vector2d pos = new Vector2d(0, 0);

    public Position() {
    }

    public Position(double x, double y) {
        pos = new Vector2d(x, y);
    }

    public Position(Vector2d pos) {
        this.pos = pos;
    }

    public Vector2d getPos() {
        return pos;
    }

    public void setPos(Vector2d pos) {
        this.pos = pos;
    }

    public double getX() {
        return pos.getX();
    }

    public float getXf() {
        return (float) pos.getX();
    }

    public double getY() {
        return pos.getY();
    }

    public float getYf() {
        return (float) pos.getY();
    }
}
