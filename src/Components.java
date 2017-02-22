import org.pushingpixels.flamingo.api.common.*;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import static java.lang.System.out;

public class Components
{
    private Font font;
    public Components()
    {
        Database database = new Database();
        HashMap<String, String> selectedRows = new HashMap<>();
        selectedRows.put("title", "Font Size");
        ArrayList<ArrayList<String>> fontSize = database.getTableRows("settings", selectedRows, new ArrayList<>(), "");
        selectedRows.replace("title", "Font Face");
        ArrayList<ArrayList<String>> fontFace = database.getTableRows("settings", selectedRows, new ArrayList<>(), "");
        if(fontSize.size() > 0 && fontFace.size() > 0)
            font = new Font(fontFace.get(0).get(2), Font.BOLD, Integer.parseInt(fontSize.get(0).get(2)));
        else
            font = new Font("Segoe UI", Font.BOLD, 30);
    }
    protected JFrame createFrame(String title)
    {
        JFrame aFrame = new JFrame();
        aFrame.setTitle(title);
        aFrame.pack();
        aFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        aFrame.setFocusable(true);
        return aFrame;
    }
    protected JRibbonFrame createRibbonFrame(String title)
    {
        JRibbonFrame aRibbonFrame = new JRibbonFrame();
        aRibbonFrame.setTitle(title);
        aRibbonFrame.pack();
        aRibbonFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        aRibbonFrame.setFocusable(true);
        return aRibbonFrame;
    }
    protected JScrollPane createScrollPane()
    {
        JScrollPane aScrollPane = new JScrollPane();
        aScrollPane.setFont(font);
        aScrollPane.setVisible(true);
        return aScrollPane;
    }
    protected JProgressBar createProgressBar(int minimum, int maximum)
    {
        JProgressBar aProgressBar = new JProgressBar();
        aProgressBar.setFont(font);
        aProgressBar.setMinimum(minimum);
        aProgressBar.setMaximum(maximum);
        return aProgressBar;
    }
    protected JTable createTable()
    {
        JTable aTable = new JTable();
        aTable.setFont(font);
        aTable.setCellSelectionEnabled(true);
        aTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return aTable;
    }
    protected JTextField createTextField(String text)
    {
        JTextField aTextField = new JTextField();
        aTextField.setText(text);
        aTextField.setFont(font);
        return aTextField;
    }
    protected JPasswordField createPasswordField(String text)
    {
        JPasswordField aPasswordField = new JPasswordField();
        aPasswordField.setText(text);
        aPasswordField.setFont(font);
        return aPasswordField;
    }
    protected JLabel createLabel(String text)
    {
        JLabel aLabel = new JLabel();
        aLabel.setHorizontalTextPosition(JLabel.CENTER);
        aLabel.setVerticalTextPosition(JLabel.CENTER);
        aLabel.setText(text);
        aLabel.setFont(font);
        return aLabel;
    }
    protected JButton createButton(String text)
    {
        JButton aButton = new JButton();
        aButton.setFont(font);
        aButton.setText(text);
        return aButton;
    }
    protected JComboBox createDropDown(ArrayList<String> items)
    {
        JComboBox aDropDown = new JComboBox();
        aDropDown.setFont(font);
        items.forEach(x -> aDropDown.addItem(x));
        return aDropDown;
    }
    protected JButton createTile(String caption, String imagePath, int totalNumberOfTiles)
    {
        JButton aTile = new JButton();
        aTile.setHorizontalTextPosition(JButton.CENTER);
        aTile.setVerticalTextPosition(JButton.CENTER);
        aTile.setFont(font);
        aTile.setText(caption);
        if(imagePath.length() > 0 && new File("images/" + imagePath).exists())
        {
            try
            {
                BufferedImage anImage = ImageIO.read(new File("images/" + imagePath));
                //img.getGraphics().setFont(img.getFont());
                /*anImage.getGraphics().setFont(new Font("Segoe UI", Font.BOLD, 20));
                anImage.getGraphics().drawString(caption, 200, 200);
                anImage.getGraphics().dispose();*/
                Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
                //ImageIcon anImage = new ImageIcon(anImage.getScaledInstance((int)(screenDimensions.getWidth() / 2),
                //(int)(screenDimensions.getHeight() / 2), Image.SCALE_SMOOTH));
                //Graphics g = anImage.getImage().getGraphics();
                //g.drawString("First Weight", 100, 100);
                //g.dispose();
                aTile.setIcon(new ImageIcon(anImage.getScaledInstance((int) (screenDimensions.getWidth() / 3),
                (int) (screenDimensions.getHeight() / (totalNumberOfTiles / 3)), Image.SCALE_SMOOTH)));
            }
            catch (Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        }
        return aTile;
    }
    protected ResizableIcon getIconFromPhoto(String resource)
    {
        return ImageWrapperResizableIcon.getIcon(JRibbonFrame.class.getClassLoader().getResource(resource), new Dimension(48, 48));
    }
    protected RibbonTask createRibbonTask(String title, JRibbonBand[] bands)
    {
        return new RibbonTask(title, bands);
    }
    protected JRibbonBand createRibbonBand(String title)
    {
        JRibbonBand aRibbonBand = new JRibbonBand(title, new EmptyResizableIcon(16));
        aRibbonBand.setResizePolicies(Arrays.asList(new CoreRibbonResizePolicies.None(aRibbonBand.getControlPanel()),
        new IconRibbonBandResizePolicy(aRibbonBand.getControlPanel())));
        return aRibbonBand;
    }
    protected JCommandToggleButton createCommandToggleButton(String title)
    {
        return new JCommandToggleButton(title, new EmptyResizableIcon(16));
    }
    protected JCommandButton createCommandButton(String title)
    {
        return new JCommandButton(title, new EmptyResizableIcon(16));
    }
    protected RibbonApplicationMenu createApplicationMenu()
    {
        RibbonApplicationMenu ribbonMainMenu = new RibbonApplicationMenu();
        /*RibbonApplicationMenuEntryPrimary mainMenuEntry = createApplicationMenuEntry("HELP");
        RibbonApplicationMenuEntrySecondary minorMenuEntry = createMinorApplicationMenuEntry("HELP");
        mainMenuEntry.addSecondaryMenuGroup("GROUP 1", new RibbonApplicationMenuEntrySecondary[]{minorMenuEntry, minorMenuEntry});
        mainMenuEntry.addSecondaryMenuGroup("GROUP 2", new RibbonApplicationMenuEntrySecondary[]{minorMenuEntry, minorMenuEntry});
        RibbonApplicationMenuEntryFooter footerMenuEntry = createFooterApplicationMenuEntry("HELP");
        for(int counter = 0; counter < 8; counter++)
            ribbonMainMenu.addMenuEntry(mainMenuEntry);
        for(int counter = 0; counter < 3; counter++)
            ribbonMainMenu.addFooterEntry(footerMenuEntry);*/
        return ribbonMainMenu;
    }
    protected RibbonApplicationMenuEntryPrimary createApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntryPrimary(new EmptyResizableIcon(16), menuTitle, aListener, JCommandButton.CommandButtonKind.ACTION_ONLY);
    }
    protected RibbonApplicationMenuEntrySecondary createMinorApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntrySecondary(new EmptyResizableIcon(16), menuTitle, aListener, JCommandButton.CommandButtonKind.ACTION_ONLY);
    }
    protected RibbonApplicationMenuEntryFooter createFooterApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntryFooter(new EmptyResizableIcon(16), menuTitle, aListener);
    }
}
