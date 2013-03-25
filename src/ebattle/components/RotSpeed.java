/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;

/**
 *
 * @author Martin
 */
public class RotSpeed extends Component {

    private double rotSpd;

    public RotSpeed() {
    }

    public RotSpeed(double rotSpd) {
        this.rotSpd = rotSpd;
    }

    public double getRotSpd() {
        return rotSpd;
    }

    public void setRotSpd(double rotSpd) {
        this.rotSpd = rotSpd;
    }
}
