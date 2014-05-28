package ageofsail.objects;

import java.awt.geom.Point2D;

import ageofsail.engine.DummyResource;
import ageofsail.engine.GameObject;
import ageofsail.engine.Scene;

public class LootChest extends GameObject {
    
    private int value;
    
    public LootChest(Scene scene, Point2D position, int value) {
        super(scene, new DummyResource(), position);
        this.value = value;
    }
    
    @Override
    public void update(double delta) {
        // FIXME: collisions
    }
    
    private void collideWith(PirateShip other) {
        other.addLoot(value);
        destroy();
    }

}
