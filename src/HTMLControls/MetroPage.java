package HTMLControls;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MetroPage extends MetroComponent
{
    public MetroPage()
    {
        createPage();
    }
    private void createPage()
    {
        Document aDocument = new Document(DocumentType.HTMLTransitional);
        aDocument.head.setLang("en");
        aDocument.head.appendChild(createMetaTagForCharset());
        aDocument.head.appendChild(createMetaTagForHTTPEquivalent());
        aDocument.head.appendChild(createMetaTagForViewport());
        aDocument.head.appendChild(createTitleTag("WeighBridge"));
        aDocument.head.appendChild(createLinkToStyleSheet("css/metro.css"));
        aDocument.head.appendChild(createLinkToStyleSheet("css/metro-icons.css"));
        aDocument.head.appendChild(createLinkToStyleSheet("css/metro-responsive.css"));
        aDocument.head.appendChild(createLinkToStyleSheet("css/metro-schemes.css"));
        aDocument.head.appendChild(createLinkToScript("js/jquery-2.1.3.min.js"));
        aDocument.head.appendChild(createLinkToScript("js/metro.js"));
        aDocument.head.appendChild(createLinkToScript("js/jquery.dataTables.min.js"));
        aDocument.head.appendChild(createLinkToScript("js/index.js"));
        MetroAccordion anAccordion = new MetroAccordion();
        MetroTextField usernameTextField = new MetroTextField("Your username goes here", "user", "text", "username");
        MetroTextField passwordTextField = new MetroTextField("Your password goes here", "lock", "password", "password");
        MetroCommandButton logInButton = new MetroCommandButton("Log In", "Click Here To Securely Log In", "enter",
        "processUserLogOn();", "success");
        MetroCommandButton cancelButton = new MetroCommandButton("Cancel", "Return To Main Menu", "exit",
        "alert('HI');", "danger");
        MetroLayout aLayout = new MetroLayout();
        aLayout.addRow(new ArrayList<>(Collections.singletonList(usernameTextField)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        aLayout.addRow(new ArrayList<>(Collections.singletonList(passwordTextField)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        aLayout.addRow(new ArrayList<>(Arrays.asList(logInButton, cancelButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        anAccordion.addFrame("Log On", aLayout, "enter");
        /*MetroFluentMenu aFluentMenu = new MetroFluentMenu("About", new ArrayList<>(Arrays.asList("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")));
        MetroFluentButton aFluentButton = new MetroFluentButton("A Button", "database", "alert('Button Clicked');");
        ArrayList<MetroFluentButton> buttons = new ArrayList<>();
        for(int counter = 0; counter < 5; counter++)
            buttons.add(aFluentButton);
        MetroFluentMenuPanelGroup aFluentMenuPanelGroup = new MetroFluentMenuPanelGroup("A Panel", buttons);
        ArrayList<MetroFluentMenuPanelGroup> groups = new ArrayList<>();
        for(int counter = 0; counter < 3; counter++)
            groups.add(aFluentMenuPanelGroup);
        for(int counter = 0; counter < 5; counter++)
        {
            MetroFluentMenuPanel aFluentMenuPanel = new MetroFluentMenuPanel("tabs_" + counter, groups);
            aFluentMenu.addPanel(aFluentMenuPanel);
        }
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("Column 1", "Column 2", "Column 3", "Column 4"));
        ArrayList<ArrayList<String>> tableCells = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList("Row 1", "Row 2", "Row 3", "Row 4")),
        new ArrayList<>(Arrays.asList("Row 1", "Row 2", "Row 3", "Row 4"))));
        MetroDataTable aDataTable = new MetroDataTable("aDataTable", tableTitles, tableCells);
        MetroLayout mainLayout = new MetroLayout();
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aDataTable)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(anAccordion)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroPanel aMetroPanel = new MetroPanel("Title", "Content", "home");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aMetroPanel)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroPopover aMetroPopover = new MetroPopover("This is a sample popover", "cyan", "top");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aMetroPopover)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroProgressBar aMetroProgressBar = new MetroProgressBar(100, "cyan");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aMetroProgressBar)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroSideBar aSideBar = new MetroSideBar("navy");
        aSideBar.addItem("Item 1", "Item 1", "home");
        aSideBar.addItem("Item 2", "Item 2", "home");
        aSideBar.addItem("Item 3", "Item 3", "home");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aSideBar)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroHeading aHeading = new MetroHeading("Sample", "Sample Text");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aHeading)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroTile aFirstTile = new MetroTile("alert('HI');", "cyan", "Home", "home", "1");
        MetroTile aSecondTile = new MetroTile("alert('HI');", "cyan", "Home", "home", "2");
        MetroTile aThirdTile = new MetroTile("alert('HI');", "cyan", "Home", "home", "3");
        mainLayout.addRow(new ArrayList<>(Arrays.asList(aFirstTile, aSecondTile, aThirdTile)), new ArrayList<>(Arrays.asList(0, 3, 1, 0, 3, 1, 0, 3, 1)));
        MetroTileWithControl aFourthTile = new MetroTileWithControl("alert('HI');", aSideBar, "");
        MetroTileWithControl aFifthTile = new MetroTileWithControl("alert('HI');", aSideBar, "");
        MetroTileWithControl aSixthTile = new MetroTileWithControl("alert('HI');", aMetroPanel, "");
        mainLayout.addRow(new ArrayList<>(Arrays.asList(aFourthTile, aFifthTile, aSixthTile)), new ArrayList<>(Arrays.asList(0, 3, 1, 0, 3, 1, 0, 3, 1)));
        MetroTab aTabs = new MetroTab("aTabs", new ArrayList<>(Arrays.asList("Tab 1", "Tab 2", "Tab 3", "Tab 4")));
        for(int counter = 0; counter < 4; counter++)
        {
            MetroLayout tabsLayout = new MetroLayout();
            tabsLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroHeading("Sample", (counter + 1) + ""))),
            new ArrayList<>(Arrays.asList(0, 12, 0)));
            aTabs.addTab(tabsLayout);
        }
        MetroListView aListView = new MetroListView();
        ArrayList<ArrayList<String>> items = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList("Title 1", "Sub Title 1", "Remark 1")), new ArrayList<>(
        Arrays.asList("Title 2", "Sub Title 2", "Remark 2"))));
        aListView.addItem("Item 1", items);
        aListView.addItem("Item 2", items);
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aTabs)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aListView)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroAppBar anAppBar = new MetroAppBar();
        for(int counter = 0; counter < 5; counter++)
            anAppBar.addItem("Home", "home", "alert('HOME PRESSED');");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(anAppBar)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroBreadCrumbs aBreadCrumbs = new MetroBreadCrumbs(new ArrayList<>(Arrays.asList("Item 1", "Item 2", "Item 3")));
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aBreadCrumbs)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroShortcutButton aButton = new MetroShortcutButton("Shortcut", "rocket", "10");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aButton)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroPreloader aPreLoader = new MetroPreloader();
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aPreLoader)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroSwitch aSwitch = new MetroSwitch(true);
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aSwitch)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroPresenter aPresenter = new MetroPresenter();
        aPresenter.addSlide("cyan", "Test", "A Line", "A Second Line");
        aPresenter.addSlide("cyan", "Test 2", "A Line", "A Second Line");
        mainLayout.addRow(new ArrayList<>(Collections.singletonList(aPresenter)), new ArrayList<>(Arrays.asList(0, 12, 0)));
        MetroWizard aWizard = new MetroWizard();
        MetroLayout aWizardLayout = new MetroLayout();
        aWizardLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroProgressBar(50, "cyan"))), new ArrayList<>(Arrays.asList(0, 12, 0)));
        aWizardLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroHeading("Sample", "Sample Heading"))),
        new ArrayList<>(Arrays.asList(0, 12, 0)));
        aWizard.addStep(aWizardLayout);
        aWizard.addStep(aWizardLayout);
        aWizard.addStep(aWizardLayout);
        aDocument.body.appendChild(aFluentMenu.toHTML());
        aDocument.body.appendChild(aDataTable.toHTML());
        aDocument.body.appendChild(mainLayout.toHTML());
        aDocument.body.appendChild(aWizard.toHTML());*/
        aDocument.body.appendChild(anAccordion.toHTML());
        try
        {
            BufferedWriter aWriter = new BufferedWriter(new FileWriter("index.html"));
            aWriter.write(aDocument.write());
            aWriter.flush();
            aWriter.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}