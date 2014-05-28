package ageofsail.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Scene extends GameObject implements GraphicsResource {
    private Collection<GameObject> objects;
    private Queue<GameObject>      toSpawn;
    private Queue<GameObject>      toDeSpawn;
    private final Dimension        size;

    public Scene(Dimension size) {
        super(null, new Point());
        setResource(this);
        objects = new ArrayList<>();
        toSpawn = new LinkedList<>();
        toDeSpawn = new LinkedList<>();
        this.size = size;
    }

    public void spawnObject(GameObject object) {
        toSpawn.add(object);
        object.setScene(this);
    }

    public void despawnObject(GameObject object) {
        toDeSpawn.add(object);
        object.setScene(null);
    }

    @Override
    public void update(double delta) {
        doDeSpawning();
        doSpawning();
        // FIXME: Collisions!
        for (GameObject object : objects) {
            object.update(delta);
        }
        doDeSpawning();
        doSpawning();
    }

    private void doSpawning() {
        while (!toSpawn.isEmpty()) {
            objects.add(toSpawn.remove());
        }
    }

    private void doDeSpawning() {
        while (!toDeSpawn.isEmpty()) {
            objects.remove(toDeSpawn.remove());
        }
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.translate(p.x, p.y);
        for (GameObject object : objects) {
            object.draw(g);
        }
        g.translate(-p.x, -p.y);
    }

    @Override
    public Dimension getDimensions() {
        return new Dimension(size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(new Point(), getDimensions());
    }
}
