package Frames;
import HTMLControls.ChromiumBrowser;
import HTMLControls.MetroPage;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
public class BrowserFrame extends Components
{
    private JFrame frame;
    private static ChromiumBrowser browser;
    public BrowserFrame()
    {
        MetroPage aPage = new MetroPage();
        browser = new ChromiumBrowser(aPage.getHtmlLocation(), "");
        addMenu();
    }
    private void addMenu()
    {
        frame = createFrame("WeighBridge Portal");
        frame.add(browser.getBrowserView(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static Browser getBrowser()
    {
        return browser.getBrowser();
    }
}
