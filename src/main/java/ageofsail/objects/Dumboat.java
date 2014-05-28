package ageofsail.objects;

import ageofsail.Sea;
import ageofsail.Ship;

public class Dumboat extends Ship {

    public static final int MAX_HEALTH = 20;
    
    public Dumboat(int id, Sea world) {
        super(id, world, MAX_HEALTH, 1);
        // FIXME: Spawning and heading setting stuff
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public void goToPort() {
        // FIXME: Disconnect from the spanish crown
        getWorld().despawnObject(this);
    }
}
