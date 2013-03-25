/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebattle.components;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import ebattle.spatials.Oval;
import ebattle.spatials.Picture;
import ebattle.spatials.Spatial;
import ebattle.spatials.SpatialType;
import ebattle.spatials.Triangle;
import ebattle.spatials.Unknown;

/**
 *
 * @author Martin
 */
public class SpatialDescriptor extends Component {

    private SpatialType type;
    private String fileName;
    private float xSize;
    private float ySize;

    public SpatialDescriptor(SpatialType type, String fileName, float xSize, float ySize) {
        this.type = type;
        this.fileName = fileName;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public SpatialType getType() {
        return type;
    }

    public Spatial createSpatial(World world, Entity owner) {
        switch (type) {
            case OVAL:
                return new Oval(world, owner, xSize, ySize);
            case TRIANGLE:
                return new Triangle(world, owner, xSize, ySize);
            case PICTURE:
                return new Picture(fileName, world, owner, xSize, ySize);
            default:
                return new Unknown(world, owner, xSize, ySize);
        }
    }
}
