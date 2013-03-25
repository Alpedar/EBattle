/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import math.Constants;
import math.MathUtil;

/**
 *
 * @author Martin
 */
public class Orientation extends Component {

    private double dir;

    public static Orientation inDeg(double deg) {
        return new Orientation(Constants.DEG2RAD * deg);
    }

    public Orientation() {
    }

    public Orientation(double dir) {
        setDir(dir);
    }

    public double getDir() {
        return dir;
    }

    public float getDirf() {
        return (float) dir;
    }

    public double getDirDeg() {
        return dir * Constants.RAD2DEG;
    }

    public float getDirDegf() {
        return (float) getDirDeg();
    }

    public final void setDir(double dir) {
        this.dir = MathUtil.normalizeAngleRad0(dir);
    }
}
