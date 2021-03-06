package by.makedon.graphicseditor.window.menubar;

import by.makedon.graphicseditor.util.Constants;
import by.makedon.graphicseditor.util.ResourceUtil;
import by.makedon.graphicseditor.window.menubar.colormenu.ColorMenu;
import by.makedon.graphicseditor.window.menubar.mainmenu.MainMenu;
import by.makedon.graphicseditor.window.menubar.thicknessmenu.ThicknessMenu;
import by.makedon.graphicseditor.window.menubar.toolsmenu.ToolsMenu;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yahor Makedon
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {
        String menuBarColor = ResourceUtil.getPropertyValue(Constants.MENUBAR_COLOR);
        int menuBarWidth = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.MENUBAR_WIDTH));
        int menuBarHeight = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.MENUBAR_HEIGHT));

        setBackground(Color.decode(menuBarColor));
        setPreferredSize(new Dimension(menuBarWidth, menuBarHeight));
        setBorder(BorderFactory.createEtchedBorder());

        add(new MainMenu());
        add(new ToolsMenu());
        add(new ColorMenu());
        add(new ThicknessMenu());
    }
}
