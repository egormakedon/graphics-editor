package tools;

import window.DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Text {

    private Cursor cursor;
    private JPanel drawPanel;
    private JColorChooser colorChooser;
    private BufferedImage bufferedImage;
    private DrawManager drawManager;

    private int x1, y1;
    private String str;
    private BufferedImage transparentBufImg;
    private BufferedImage secondBufferedImg;

    public Text(DrawManager drawManager) {
        cursor = new Cursor(Cursor.TEXT_CURSOR);
        drawPanel = drawManager.getDrawPanel();
        colorChooser = drawManager.getColorChooser();
        bufferedImage = drawManager.getBufferedImage();

        this.drawManager = drawManager;
    }

    private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            drawPanel.setCursor(cursor);
        }
    };

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            transparentBufImg = new BufferedImage((int) screenSize.getWidth(), (int) screenSize.getHeight(), BufferedImage.TYPE_INT_RGB);
            transparentBufImg.getGraphics().drawImage(bufferedImage,0,0, drawPanel);

            secondBufferedImg = new BufferedImage((int) screenSize.getWidth(), (int) screenSize.getHeight(), BufferedImage.TYPE_INT_RGB);
            secondBufferedImg.getGraphics().drawImage(bufferedImage,0,0, drawPanel);

            x1 = e.getX();
            y1 = e.getY();
            str = "";

            Graphics2D g = (Graphics2D) transparentBufImg.getGraphics();
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(1.0f));
            g.drawRect(x1, y1,200,19);
            drawPanel.getGraphics().drawImage(transparentBufImg,0,0, drawPanel);

            drawPanel.removeKeyListener(keyListener);
            paint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            drawPanel.setCursor(cursor);
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            if ((drawManager.getThickness() == 1 && str.length() < 14) ||
                    (drawManager.getThickness() == 2 && str.length() < 12) ||
                    (drawManager.getThickness() == 3 && str.length() < 10)) {

                bufferedImage.getGraphics().drawImage(secondBufferedImg,0,0, drawPanel);
                Graphics2D g = (Graphics2D) bufferedImage.getGraphics();

                g.setColor(colorChooser.getColor());
                g.setFont(new Font("", Font.PLAIN, drawManager.getThickness() * 3 + 15));
                str += e.getKeyText(e.getKeyCode());
                g.drawString(str, x1 + 4, y1 + 19);

                transparentBufImg.getGraphics().drawImage(bufferedImage, 0, 0, drawPanel);
                g = (Graphics2D) transparentBufImg.getGraphics();
                g.setColor(Color.black);
                g.setStroke(new BasicStroke(1.0f));
                g.drawRect(x1, y1, 200, 18 + drawManager.getThickness());

                drawPanel.getGraphics().drawImage(transparentBufImg, 0, 0, drawPanel);
            }
        }
    };

    public void paint() {
        drawPanel.requestFocus();
        drawPanel.addKeyListener(keyListener);
    }

    public MouseMotionListener getMouseMotionListener() { return mouseMotionListener; }
    public MouseListener getMouseListener() { return mouseListener; }
}