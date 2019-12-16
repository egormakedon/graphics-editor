package by.makedon.graphicseditor.manager;

import by.makedon.graphicseditor.tool.Tool;
import by.makedon.graphicseditor.tool.ToolManager;
import by.makedon.graphicseditor.tool.impl.Pencil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * @author Yahor Makedon
 */
public final class DrawManager {
    private static DrawManager instance;

    private DrawPanel drawPanel;
    private Tool tool;
    private BufferedImage bufferedImage;
    private Color color;
    private int thickness;

    private DrawManager() {
        drawPanel = new DrawPanel();
        tool = ToolManager.getInstance().getTool(Pencil.class);
        bufferedImage = createNewBufferedImage();
        color = Color.BLACK;
        //TODO thickness start value
    }

    public static DrawManager getInstance() {
        if (instance == null) {
            instance = new DrawManager();
        }
        return instance;
    }

    public void paint() {
        paint(bufferedImage);
    }

    public void paint(BufferedImage bufferedImage) {
        drawPanel.getGraphics().drawImage(bufferedImage, 0, 0, drawPanel);
    }

    public void clearDrawPanel() {
        bufferedImage = createNewBufferedImage();
        paint();
    }

    public Graphics2D getGraphics() {
        return getGraphics(bufferedImage);
    }

    public Graphics2D getGraphics(BufferedImage bufferedImage) {
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(getColor());
        g.setStroke(getStroke());
        return g;
    }

    public void copyOriginalImageTo(BufferedImage bufferedImage) {
        bufferedImage.getGraphics()
                     .drawImage(this.bufferedImage, 0, 0, null);
    }

    public BufferedImage createNewBufferedImage() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        BufferedImage bufferedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.fillRect(0, 0, screenWidth, screenHeight);
        return bufferedImage;
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tool getTool() {
        return tool;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color getColor() {
        return color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    private Stroke getStroke() {
        return new BasicStroke(thickness);
    }

    private final class DrawPanel extends JComponent {
        private DrawPanel() {
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tool.mouseClicked(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    tool.mousePressed(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    tool.mouseReleased(e);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    tool.mouseEntered(e);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    tool.mouseExited(e);
                }
            });

            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    tool.mouseDragged(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    tool.mouseMoved(e);
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bufferedImage, 0, 0, this);
        }
    }
}
