package ageofsail.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import ageofsail.TextResource;

public class VectorTextResource implements TextResource {

    private Font   font;
    private String text;

    public VectorTextResource(Font font, String text) {
        this.font = font;
        this.text = text;
    }

    @Override
    public void draw(Graphics g, Point p) {
        Font f = g.getFont();
        g.setFont(font);
        g.drawString(text, p.x, p.y);
        g.setFont(f);
    }

    @Override
    public Dimension getDimensions() {
        return getBounds().getSize();
    }

    @Override
    public Rectangle getBounds() {
        // Fun times incoming
        FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
        TextLayout layout = new TextLayout(text, font, frc);
        Rectangle2D bounds = layout.getBounds();
        return new Rectangle((int) bounds.getX(), (int) bounds.getY(),
                (int) bounds.getWidth(), (int) bounds.getHeight());
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}
