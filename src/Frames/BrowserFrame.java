package Frames;
import HTMLControls.ChromiumBrowser;
import HTMLControls.MetroPage;

import javax.swing.*;
import java.awt.*;
public class BrowserFrame extends Components
{
    private JFrame frame;
    private ChromiumBrowser browser;
    public BrowserFrame()
    {
        //browser = new ChromiumBrowser("", aPage.toString());
        MetroPage aPage = new MetroPage();
        browser = new ChromiumBrowser("file:///C:/Users/user/IdeaProjects/WeighBridge/index.html", "");
        addMenu();
    }
    private void addMenu()
    {
        frame = createFrame("WeighBridge Portal");
        frame.add(browser.getBrowser(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
