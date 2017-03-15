package HTMLPages;
import Frames.*;
import HTMLControls.*;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.JSONString;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HomePage
{
    public HomePage()
    {

    }
    public MetroAccordion createHomePage()
    {
        MetroAccordion homeAccordion = new MetroAccordion();
        MetroLayout homeLayout = new MetroLayout();
        MetroHeading heading = new MetroHeading("Select A Version", "");
        MetroTile html5Tile = new MetroTile("loadHTML5Edition();", "cyan", "HTML5", "html5", "");
        MetroTile javaSwingTile = new MetroTile("loadSwingEdition();", "cyan", "Java Swing", "windows", "");
        MetroPopover chooseHTML5Popover = new MetroPopover("Choose HTML5", "cyan", "top");
        MetroPresenter presenter = new MetroPresenter();
        presenter.addSlide("cyan", "WeighBridge", "", "");
        presenter.addSlide("cyan", "Commodity Prices", "", "");
        presenter.addSlide("cyan", "Ration Calculator", "", "");
        homeLayout.addRow(heading);
        homeLayout.addEmptyRows(2);
        homeLayout.addRow(new ArrayList<>(Arrays.asList(html5Tile, javaSwingTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        homeLayout.addEmptyRows(2);
        homeLayout.addRow(chooseHTML5Popover, new ArrayList<>(Arrays.asList(1, 4, 7)));
        homeLayout.addEmptyRows(2);
        homeLayout.addRow(presenter);
        homeAccordion.addFrame("WeighBridge Portal", homeLayout, "home");
        return homeAccordion;
    }
    public JSONString getHomePage()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createHomePage().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public void launchSwingVersion()
    {
        SwingUtilities.invokeLater(() ->
        {
            new WeighBridgeFrame();
        });
    }
    public JSONString launchHTML5Version()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createMainMenu().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public MetroAccordion createMainMenu()
    {
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        MetroLayout mainMenuLayout = new MetroLayout();
        MetroHeading heading = new MetroHeading("Main Menu", "");
        MetroTile firstWeightTile = new MetroTile("selectWeighBridgeDocketType();", "cyan", "First Weight", "truck", "");
        MetroTile secondWeightTile = new MetroTile("getDocketsAwaitingSecondWeighing();", "cyan", "Second Weight",
        "truck", "");
        MetroTile logInTile = new MetroTile("getLogOnPage();", "cyan", "Log In", "enter", "");
        MetroTile registerTile = new MetroTile("getRegistrationPage();", "cyan", "Register", "user-plus", "");
        mainMenuLayout.addRow(heading);
        mainMenuLayout.addEmptyRows(2);
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(firstWeightTile, secondWeightTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        mainMenuLayout.addEmptyRows(2);
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(logInTile, registerTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        mainMenuLayout.addEmptyRows(2);
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        return mainMenuAccordion;
    }
    public void exit()
    {
        System.exit(0);
    }
}
