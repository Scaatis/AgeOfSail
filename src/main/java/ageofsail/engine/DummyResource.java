package ageofsail.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class DummyResource implements GraphicsResource {

    @Override
    public void draw(Graphics g, Point p) {
        return;
    }

    @Override
    public Dimension getDimensions() {
        return new Dimension();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

}
