package by.makedon.graphicseditor.tool.impl;

import by.makedon.graphicseditor.manager.DrawManager;
import by.makedon.graphicseditor.tool.AbstractTool;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author Yahor Makedon
 */
public class Line extends AbstractTool {
    private boolean isMousePressed;
    private int x1, x2, y1, y2;
    private BufferedImage savedBufferedImage;

    @Override
    public void init() {
        isMousePressed = false;
        x1 = x2 = y1 = y2 = 0;
        savedBufferedImage = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawLine(DrawManager.getInstance().getGraphics());
        DrawManager.getInstance().paint();

        init();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //TODO cursor drawPanel.setCursor(cursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //TODO cursor drawPanel.setCursor(cursor);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        if (!isMousePressed) {
            isMousePressed = true;

            savedBufferedImage = DrawManager.getInstance().createNewBufferedImage();

            x1 = x2;
            y1 = y2;
        }

        paint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //TODO cursor drawPanel.setCursor(cursor);
    }

    private void paint() {
        DrawManager.getInstance().copyOriginalImageTo(savedBufferedImage);
        drawLine(DrawManager.getInstance().getGraphics(savedBufferedImage));
        DrawManager.getInstance().paint(savedBufferedImage);
    }

    private void drawLine(Graphics2D g) {
        g.drawLine(x1, y1, x2, y2);
    }
}
