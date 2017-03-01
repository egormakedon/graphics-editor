import java.awt.*;

public class Cursors {

    private Toolkit toolkit;

    private Image mainCursorImage;
    private Cursor mainCursor;

    private Image pencilCursorImage;
    private Cursor pencilCursor;

    private Image lineCursorImage;
    private Cursor lineCursor;

    public Cursors() {
        toolkit = Toolkit.getDefaultToolkit();

        mainCursorImage = toolkit.getImage("images/mainCursor.png");
        mainCursor = toolkit.createCustomCursor(mainCursorImage, new Point(), "mainCursor");

        pencilCursorImage = toolkit.getImage("images/pencilCursor.png");
        pencilCursor = toolkit.createCustomCursor(pencilCursorImage, new Point(), "pencilCursor");

        lineCursorImage = toolkit.getImage("images/lineCursor.png");
        lineCursor = toolkit.createCustomCursor(lineCursorImage, new Point(), "lineCursor");
    }

    public Cursor getMainCursor() { return mainCursor; }

    public Cursor getPencilCursor() { return pencilCursor; }

    public Cursor getLineCursorCursor() { return lineCursor; }
}