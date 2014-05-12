package ageofsail.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ageofsail.GraphicsResource;

public class SpriteSheet {
    private BufferedImage image;
    private Point         origin;
    private int           colums;
    private int           rows;

    public SpriteSheet(BufferedImage image, Point origin, int colums, int rows) {
        this.image = image;
        this.origin = origin;
        this.colums = colums;
        this.rows = rows;
    }

    public int getLength() {
        return colums * rows;
    }

    private void checkFrame(int frame) {
        if (frame >= getLength() || frame < 0) {
            throw new ArrayIndexOutOfBoundsException(frame);
        }
    }

    public Dimension getSingleDimensions() {
        return new Dimension(image.getWidth() / colums, image.getHeight() / rows);
    }

    public Dimension getDimensions() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    public Rectangle getFrameBounds(int frame) {
        checkFrame(frame);
        Dimension singleDimension = getSingleDimensions();
        Point center = new Point(frame % colums * singleDimension.width,
                frame / colums * singleDimension.height);
        return new Rectangle(center, singleDimension);
    }

    public Rectangle getSingleBounds() {
        Point center = new Point(-origin.x, -origin.y);
        return new Rectangle(center, getSingleDimensions());
    }

    public void drawFrame(Graphics g, int frame, Point location) {
        Rectangle bounds = getFrameBounds(frame);
        g.translate(-origin.x, -origin.y);
        g.drawImage(image, location.x, location.y, location.x + bounds.width, location.y + bounds.height,
                bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, null);
        g.translate(origin.x, origin.y);
    }

    public Point getOrigin() {
        return new Point(origin);
    }

    public class SpriteSheetResource implements GraphicsResource {
        private int frame;

        public SpriteSheetResource(int frame) {
            checkFrame(frame);
            this.frame = frame;
        }

        @Override
        public void draw(Graphics g, Point p) {
            drawFrame(g, frame, p);
        }

        @Override
        public Dimension getDimensions() {
            return getSingleDimensions();
        }

        @Override
        public Rectangle getBounds() {
            return getSingleBounds();
        }

        public int getFrame() {
            return frame;
        }

        public void setFrame(int frame) {
            checkFrame(frame);
            this.frame = frame;
        }

        public void advanceFrame() {
            frame = (frame + 1) % getLength();
        }
        
        public int getFrames() {
            return getLength();
        }
    }
}
