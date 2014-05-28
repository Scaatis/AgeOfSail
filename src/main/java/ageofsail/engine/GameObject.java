package ageofsail.engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
    private GraphicsResource resource;
    private Point2D          position;
    private Scene            scene;

    public GameObject(GraphicsResource resource, Point2D position) {
        this.position = position;
        this.resource = resource;
    }

    public GameObject(Scene scene, GraphicsResource resource, Point2D position) {
        this(resource, position);
        this.scene = scene;
        scene.spawnObject(this);
    }

    public abstract void update(double delta);

    public void draw(Graphics g) {
        resource.draw(g, Util.toPoint(position));
    }

    public GraphicsResource getResource() {
        return resource;
    }

    public void setResource(GraphicsResource resource) {
        this.resource = resource;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        if (scene == this.scene) {
            return;
        }
        if (this.scene != null) {
            Scene old = this.scene;
            this.scene = null;
            old.despawnObject(this);
        }
        this.scene = scene;
        if (scene != null) {
            scene.spawnObject(this);
        }

    }
    
    public Rectangle2D getBounds() {
        Rectangle bounds = resource.getBounds();
        return new Rectangle2D.Double(position.getX() + bounds.x, position.getY() + bounds.y, bounds.width, bounds.height);
    }
}
