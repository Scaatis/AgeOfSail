package ageofsail.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ageofsail.TextResource;

public class FontSpriteSheet extends SpriteSheet {

    public FontSpriteSheet(BufferedImage image, Point origin, int colums, int rows) {
        super(image, origin, colums, rows);
    }

    public void drawChar(Graphics g, char ch, Point location) {
        drawFrame(g, (int) ch, location);
    }

    public class RasterTextResource implements TextResource {
        private String text;

        public RasterTextResource(String text) {
            this.text = text;
        }

        @Override
        public void draw(Graphics g, Point p) {
            int w = getSingleDimensions().width;
            int x = p.x;
            for (int i = 0; i < text.length(); i++) {
                try {
                    drawChar(g, text.charAt(i), new Point(x, p.y));
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Unsupported characters are simply skipped.
                }
                x += w;
            }
        }

        @Override
        public Dimension getDimensions() {
            Dimension singleChar = getSingleDimensions();
            return new Dimension(text.length() * singleChar.width, singleChar.height);
        }

        @Override
        public Rectangle getBounds() {
            Point center = new Point(-getOrigin().x, -getOrigin().y);
            return new Rectangle(center, getDimensions());
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            // Reusing the object by changing the text and not just making a new
            // one? That is not the java way!
            // I will allow it though.
        }
    }
}
