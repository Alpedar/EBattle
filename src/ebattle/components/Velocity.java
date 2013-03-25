/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import math.Position;
import math.Vector2d;

/**
 *
 * @author Martin
 */
public class Velocity extends Component {

    private Vector2d vel = new Vector2d(0, 0);

    public Velocity() {
    }

    public Velocity(double x, double y) {
        this.vel = new Vector2d(x, y);
    }

    public Velocity(Vector2d pos) {
        this.vel = pos;
    }

    public Vector2d getVel() {
        return vel;
    }

    public void setVel(Vector2d vel) {
        this.vel = vel;
    }

    public double getX() {
        return vel.getX();
    }

    public double getY() {
        return vel.getY();
    }

    public double getDir() {
        return vel.getDir();
    }

    public double getDirTo(Position v) {
        return vel.getDirTo(v);
    }

    public double getDirDeg() {
        return vel.getDirDeg();
    }
}
