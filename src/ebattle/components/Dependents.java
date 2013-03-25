/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;

/**
 *
 * @author Martin
 */
public class Dependents extends Component {

    private Bag<Entity> directDependents = new Bag();

    public Bag<Entity> getDirectDependents() {
        return directDependents;
    }
}
