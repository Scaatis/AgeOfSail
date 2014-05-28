package ageofsail.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite implements GraphicsResource {
    private BufferedImage image;
    private Point         origin;

    public Sprite(BufferedImage image, Point origin) {
        this.image = image;
        this.origin = origin;
    }

    public Sprite(BufferedImage image) {
        this(image, new Point());
    }

    public Dimension getDimensions() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    public Rectangle getBounds() {
        return new Rectangle(-origin.x, -origin.y, image.getWidth(), image.getHeight());
    }

    public BufferedImage getImage() {
        return image;
    }

    public Point getOrigin() {
        return new Point(origin);
    }

    @Override
    public void draw(Graphics g, Point location) {
        g.translate(-origin.x, -origin.y);
        g.drawImage(image, location.x, location.y, null);
        g.translate(origin.x, origin.y);
    }
}
