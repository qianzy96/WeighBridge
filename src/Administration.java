import javafx.scene.control.DatePicker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.StringValuePair;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.internal.ui.ribbon.JRibbonGallery;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import sun.management.Agent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Administration extends Components
{
    private JComponent component;
    private JRibbonFrame frame;
    public Administration()
    {

    }
    public void createRibbon()
    {
        frame = new JRibbonFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Administration Portal");
        createReportsTaskOnRibbon();
        createSpecifiedTaskOnRibbon();
        createTaskOnRibbon("Driver", "Drivers", "drivers");
        createTaskOnRibbon("Commodity", "Commodities", "commodities");
        createTaskOnRibbon("First Weight", "First Weights", "firstweights");
        createTaskOnRibbon("Second Weight", "Second Weights", "secondweights");
        createTaskOnRibbon("Administrator", "Administrators", "administrators");
        createTaskOnRibbon("Customer", "Customers", "customers");
        createTaskOnRibbon("Docket Type", "Docket Types", "dockettypes");
        createTaskOnRibbon("Supplier", "Suppliers", "suppliers");
        createTaskOnRibbon("Contract", "Contracts", "contracts");
        createTaskOnRibbon("Setting", "Settings", "settings");
        frame.getRibbon().setApplicationMenu(createApplicationMenu());
        frame.setVisible(true);
        createDashboard();
        //frame.add(ribbon, BorderLayout.NORTH);
    }
    protected RibbonApplicationMenu createApplicationMenu()
    {
        RibbonApplicationMenu aRibbonApplicationMenu = new RibbonApplicationMenu();
        aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", (x) -> {SwingUtilities.invokeLater(() ->
        {
            new WeighBridge();
            frame.dispose();
        });}));
        aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", (x) -> System.exit(0)));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Drivers", "Driver", "drivers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Commodities", "Commodity", "commodities"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("First Weights", "First Weight", "firstweights"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Second Weights", "Second Weight", "secondweights"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Administrators", "Administrator", "administrators"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Customers", "Customer", "customers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Docket Types", "Docket Type", "dockettypes"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Suppliers", "Supplier", "suppliers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Contracts", "Contract", "contracts"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Settings", "Setting", "settings"));
        return aRibbonApplicationMenu;
    }
    private RibbonApplicationMenuEntryPrimary createRibbonMenuEntry(String pluralWord, String singularWord, String tableName)
    {
        RibbonApplicationMenuEntrySecondary viewMenuEntry = createMinorApplicationMenuEntry("View " + pluralWord, (x) -> viewTableContents(tableName));
        RibbonApplicationMenuEntrySecondary addMenuEntry = createMinorApplicationMenuEntry("Add An " + singularWord, (x) -> addRowToTable(tableName));
        RibbonApplicationMenuEntrySecondary deleteMenuEntry = createMinorApplicationMenuEntry("Delete An " + singularWord, (x) -> deleteRowFromTable(tableName));
        RibbonApplicationMenuEntrySecondary updateMenuEntry = createMinorApplicationMenuEntry("Update An " + singularWord, (x) -> updateRowFromTable(tableName));
        RibbonApplicationMenuEntryPrimary aMenuEntry = createApplicationMenuEntry(pluralWord, (x) -> {});
        aMenuEntry.addSecondaryMenuGroup(pluralWord, new RibbonApplicationMenuEntrySecondary[]{viewMenuEntry, addMenuEntry, deleteMenuEntry, updateMenuEntry});
        return aMenuEntry;
    }
    private void createDashboard()
    {
        StringBuilder webPageContent = new StringBuilder();
        try
        {
            RetrieveContent noggersPage = new RetrieveContent("http://www.noggersblog.co.uk/prices/marketinfo/index2.htm");
            String webPage = noggersPage.getText();
            String desiredContent = webPage.substring(webPage.indexOf("<!--document.write(unescape('") + 29);
            String formattedResult = URLDecoder.decode(desiredContent.substring(0, desiredContent.length() - 32), "UTF-8");
            ArrayList<String> links = Utilities.extractAttributeOfTags(formattedResult, "<iframe src=\".*?\".*?></iframe>", "src");
            if(links.size() > 5)
                for(int index = 0; index < 5; index++)
                    links.remove(links.size() - 1);
            JPanel dashboardPanel = new JPanel(new GridLayout(0, 5));
            links.forEach(x ->
            {
                RetrieveContent aPage = new RetrieveContent(x);
                String pageContent = aPage.getText();
                ArrayList<String> pageContents = Utilities.extractTextBetweenTags(pageContent, "<font.*?>", "</font>");
                CommodityPrices aCommodityPrices = new CommodityPrices(pageContents.get(0), pageContents.get(1));
                for(int index = 2; index < 7; index++)
                    aCommodityPrices.addHeading(pageContents.get(index));
                for(int counter = 7; counter < pageContents.size(); counter = counter + 5)
                {
                    aCommodityPrices.addPrices(pageContents.get(counter), new ArrayList<>(Arrays.asList(pageContents.get(counter + 1), pageContents.get(counter + 2),
                    pageContents.get(counter + 3), pageContents.get(counter + 4))));
                }
                System.out.println(aCommodityPrices.toString());
                JButton aCommodityTile = createTile(aCommodityPrices.getCommodityTitle() + " " + aCommodityPrices.getDate(), "", links.size());
                aCommodityTile.addActionListener(c -> {});
                dashboardPanel.add(aCommodityTile);
            });
            /*Document aDocument = Jsoup.parse(formattedResult);
            Elements iframeTags = aDocument.getElementsByTag("iframe");
            if(iframeTags.size() >= 3)
            {
                iframeTags.remove(iframeTags.size() - 1);
                iframeTags.remove(iframeTags.size() - 1);
                iframeTags.remove(iframeTags.size() - 1);
            }
            iframeTags.forEach(x ->
            {
                //System.out.println("SRC: "  + x.attr("src"));
                RetrieveContent aPage = new RetrieveContent(x.attr("src"));
                String webPageContents = aPage.getText();
                System.out.println(webPageContents);
                Document currentDocument = Jsoup.parse(webPageContents);
                Elements fontTags = aDocument.getElementsByTag("font");
                fontTags.forEach(y ->
                {
                    String aFontTag = y.html();
                    //aFontTag = aFontTag.substring(aFontTag.indexOf('>') + 1);
                    //aFontTag = aFontTag.substring(0, aFontTag.indexOf("<"));
                    System.out.println(aFontTag);
                });
            });*/
            /*Pattern aPattern = Pattern.compile("<iframe>(\\S+)</iframe>");
            //Pattern aPattern = Pattern.compile("\"<([^\\\\s>/]+)\"");
            Matcher aMatcher = aPattern.matcher(formattedResult);
            if(aMatcher.find())
            {
                System.out.println("LOCATED A MATCH");
                for(int counter = 1; counter <= aMatcher.groupCount(); counter++)
                    System.out.println("A MATCH: " + aMatcher.group(counter));
            }*/
            //System.out.println(formattedResult);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    private void createReportsTaskOnRibbon()
    {
        JRibbonBand aBand = createRibbonBand("Reports");
        JRibbonGallery aGallery = new JRibbonGallery();
        ArrayList<StringValuePair<ArrayList<JCommandToggleButton>>> buttons = new ArrayList<>();
        Map<RibbonElementPriority, Integer> buttonPriorities = new HashMap<>();
        buttonPriorities.put(RibbonElementPriority.LOW, 3);
        buttonPriorities.put(RibbonElementPriority.MEDIUM, 3);
        buttonPriorities.put(RibbonElementPriority.TOP, 3);
        ArrayList<JCommandToggleButton> buttonsGroup = new ArrayList<>();
        for(int counter = 0; counter < 5; counter++)
            buttonsGroup.add(new JCommandToggleButton("A Button " + (counter + 1), new EmptyResizableIcon(16)));
        buttons.add(new StringValuePair<>("Group 1", buttonsGroup));
        buttons.add(new StringValuePair<>("Group 2", buttonsGroup));
        //aBand.addRibbonGallery("Start Date", buttons, buttonPriorities, 3, RibbonElementPriority.TOP);
    }
    private void createSpecifiedTaskOnRibbon()
    {
        JRibbonBand aBand = createRibbonBand("Joined Tables");
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers", "commodities", "dockettypes", "customers", "secondweights"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code", "firstweights.commodity", "commodities.code",
        "firstweights.dockettype", "dockettypes.code", "firstweights.consignee", "customers.code", "firstweights.code", "secondweights.firstweight"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
        "drivers.lastname", "commodities.title", "dockettypes.dockettype", "customers.firstname", "customers.lastname", "secondweights.code", "secondweights.weight",
        "secondweights.date"));
        ArrayList<String> columnTitles = new ArrayList<>(Arrays.asList("First Weight Code", "First Weight", "Date", "First Name", "Last Name", "Title", "DocketType",
        "First Name", "Last Name", "Second Weight Code", "Second Weight", "Second Weight Date"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        JCommandButton viewSecondWeightsCustomersButton = createCommandButton("View Customers Completed Weighings");
        viewSecondWeightsCustomersButton.addActionListener((x) ->
        {
            tableTitles.set(4, "customers");
            joinConditions.set(7, "customers.code");
            desiredColumns.set(7, "customers.firstname");
            desiredColumns.set(8, "customers.lastname");
            selectedValues.replace("dockettypes.code", "2");
            createTable(tableTitles, joinConditions, desiredColumns, selectedValues, columnTitles);
        });
        aBand.addCommandButton(viewSecondWeightsCustomersButton, RibbonElementPriority.TOP);
        JCommandButton viewSecondWeightsSuppliersButton = createCommandButton("View Suppliers Completed Weighings");
        viewSecondWeightsSuppliersButton.addActionListener((x) ->
        {
            tableTitles.set(4, "suppliers");
            joinConditions.set(7, "suppliers.code");
            desiredColumns.set(7, "suppliers.firstname");
            desiredColumns.set(8, "suppliers.lastname");
            selectedValues.replace("dockettypes.code", "1");
            createTable(tableTitles, joinConditions, desiredColumns, selectedValues, columnTitles);
        });
        aBand.addCommandButton(viewSecondWeightsSuppliersButton, RibbonElementPriority.TOP);
        ArrayList<String> revisedTableTitles = new ArrayList<>(tableTitles);
        ArrayList<String> revisedJoinConditions = new ArrayList<>(joinConditions);
        ArrayList<String> revisedDesiredColumns = new ArrayList<>(desiredColumns);
        ArrayList<String> revisedColumnTitles = new ArrayList<>(columnTitles);
        revisedTableTitles.remove(5);
        revisedJoinConditions.remove(8);
        revisedJoinConditions.remove(8);
        revisedDesiredColumns.remove(9);
        revisedDesiredColumns.remove(9);
        revisedDesiredColumns.remove(9);
        revisedColumnTitles.remove(9);
        revisedColumnTitles.remove(9);
        revisedColumnTitles.remove(9);
        JCommandButton viewFirstWeightsCustomersButton = createCommandButton("View Customers First Weighings");
        viewFirstWeightsCustomersButton.addActionListener((x) ->
        {
            revisedTableTitles.set(4, "customers");
            revisedJoinConditions.set(7, "customers.code");
            revisedDesiredColumns.set(7, "customers.firstname");
            revisedDesiredColumns.set(8, "customers.lastname");
            selectedValues.replace("dockettypes.code", "2");
            createTable(revisedTableTitles, revisedJoinConditions, revisedDesiredColumns, selectedValues, revisedColumnTitles);
        });
        aBand.addCommandButton(viewFirstWeightsCustomersButton, RibbonElementPriority.TOP);
        JCommandButton viewFirstWeightsSuppliersButton = createCommandButton("View Suppliers First Weighings");
        viewFirstWeightsSuppliersButton.addActionListener((x) ->
        {
            revisedTableTitles.set(4, "suppliers");
            revisedJoinConditions.set(7, "suppliers.code");
            revisedDesiredColumns.set(7, "suppliers.firstname");
            revisedDesiredColumns.set(8, "suppliers.lastname");
            selectedValues.replace("dockettypes.code", "1");
            createTable(revisedTableTitles, revisedJoinConditions, revisedDesiredColumns, selectedValues, revisedColumnTitles);
        });
        aBand.addCommandButton(viewFirstWeightsSuppliersButton, RibbonElementPriority.TOP);
        ArrayList<String> contractTableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities", "dockettypes", "customers"));
        ArrayList<String> contractJoinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code", "contracts.dockettype", "dockettypes.code",
        "contracts.consignee", "customers.code"));
        ArrayList<String> contractDesiredColumns = new ArrayList<>(Arrays.asList("commodities.title", "contracts.total", "contracts.price", "dockettypes.dockettype",
        "customers.firstname", "customers.lastname"));
        ArrayList<String> contractColumnTitles = new ArrayList<>(Arrays.asList("Commodity", "Total Quantity", "Total Price", "Docket Type", "Customer First Name",
        "Customer Last Name"));
        HashMap<String, String> contractSelectedValues  = new HashMap<>();
        contractSelectedValues.put("dockettypes.code", "2");
        JCommandButton viewCustomerContractsButton = createCommandButton("View Customer Contracts");
        viewCustomerContractsButton.addActionListener((x) ->
        {
            contractTableTitles.set(3, "customers");
            contractJoinConditions.set(5, "customers.code");
            contractDesiredColumns.set(4, "customers.firstname");
            contractDesiredColumns.set(5, "customers.lastname");
            contractColumnTitles.set(4, "Customer First Name");
            contractColumnTitles.set(5, "Customer Last Name");
            contractSelectedValues.replace("dockettypes.code", "2");
            createTable(contractTableTitles, contractJoinConditions, contractDesiredColumns, contractSelectedValues, contractColumnTitles);
        });
        JCommandButton viewSupplierContractsButton = createCommandButton("View Supplier Contracts");
        viewSupplierContractsButton.addActionListener((x) ->
        {
            contractTableTitles.set(3, "suppliers");
            contractJoinConditions.set(5, "suppliers.code");
            contractDesiredColumns.set(4, "suppliers.firstname");
            contractDesiredColumns.set(5, "suppliers.lastname");
            contractSelectedValues.replace("dockettypes.code", "1");
            contractColumnTitles.set(4, "Supplier First Name");
            contractColumnTitles.set(5, "Supplier Last Name");
            createTable(contractTableTitles, contractJoinConditions, contractDesiredColumns, contractSelectedValues, contractColumnTitles);
        });
        aBand.addCommandButton(viewCustomerContractsButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(viewSupplierContractsButton, RibbonElementPriority.TOP);
        RibbonTask aTask = createRibbonTask("Joined Tables", new JRibbonBand[]{aBand});
        frame.getRibbon().addTask(aTask);
    }
    private void createTaskOnRibbon(String singularWord, String pluralWord, String tableName)
    {
        JRibbonBand aBand = createRibbonBand(pluralWord);
        JCommandButton viewButton = createCommandButton("View " + pluralWord);
        viewButton.addActionListener((x) -> viewTableContents(tableName));
        JCommandButton addButton = createCommandButton("Add An " + singularWord);
        addButton.addActionListener((x) -> addRowToTable(tableName));
        JCommandButton deleteButton = createCommandButton("Delete An " + singularWord);
        deleteButton.addActionListener((x) -> deleteRowFromTable(tableName));
        JCommandButton updateButton = createCommandButton("Update An " + singularWord);
        updateButton.addActionListener((x) -> updateRowFromTable(tableName));
        aBand.addCommandButton(viewButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(addButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(deleteButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(updateButton, RibbonElementPriority.TOP);
        RibbonTask aTask = createRibbonTask(pluralWord, new JRibbonBand[]{aBand});
        frame.getRibbon().addTask(aTask);
    }
    private JTable createTable(ArrayList<String> tableNames, ArrayList<String> tableJoinConditions, ArrayList<String> desiredColumns,
                               HashMap<String, String> selectedValues, ArrayList<String> columnTitles)
    {
        Database main = new Database();
        ArrayList<ArrayList<String>> rowContents = main.getJoinedTableRows(tableNames, tableJoinConditions, selectedValues, desiredColumns, "");
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        columnTitles.forEach(x -> aModel.addColumn(x));
        rowContents.forEach(x -> aModel.addRow(x.toArray()));
        addComponent(aTable);
        return aTable;
    }
    private JTable createTable(String tableName)
    {
        Database main = new Database();
        ArrayList<String> columnTitles = main.getColumnTitles(tableName);
        ArrayList<ArrayList<String>> rowContents = main.getTableRows(tableName, new HashMap<>(), new ArrayList<>(), "");
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        columnTitles.forEach(x -> aModel.addColumn(x));
        rowContents.forEach(x -> aModel.addRow(x.toArray()));
        addComponent(aTable);
        return aTable;
    }
    public void viewTableContents(String tableName)
    {
        createTable(tableName);
    }
    public void updateRowFromTable(String tableName)
    {
        JTable aTable = createTable(tableName);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            JPanel aPanel = new JPanel(new GridLayout(0, 1));
            Database main = new Database();
            ArrayList<String> columnTitles = main.getColumnTitles(tableName);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("code", ((DefaultTableModel)aTable.getModel()).getValueAt(aTable.getSelectedRow(), 0).toString());
            ArrayList<ArrayList<String>> selectedRowContents = main.getTableRows(tableName, parameters, new ArrayList<>(), "");
            ArrayList<JTextField> textFields = new ArrayList<>();
            for(int counter = 0; counter < columnTitles.size(); counter++)
            {
                aPanel.add(createLabel("Please enter a value for " + columnTitles.get(counter)));
                JTextField aTextField = createTextField(selectedRowContents.get(0).get(counter));
                if(counter == 0)
                    aTextField.setEnabled(false);
                textFields.add(aTextField);
                aPanel.add(aTextField);
            }
            JButton aButton = createButton("Update Selected Row");
            aButton.addActionListener(y ->
            {
                HashMap<String, String> selectedParameters = new HashMap<>();
                selectedParameters.put("code", textFields.get(0).getText());
                HashMap<String, String> updatedParameters = new HashMap<>();
                columnTitles.forEach(z ->
                {
                    int currentPosition = columnTitles.indexOf(z);
                    if(currentPosition > 0)
                        updatedParameters.put(z, textFields.get(currentPosition).getText());
                });
                main.updateTableRow(tableName, updatedParameters, selectedParameters);
                updateRowFromTable(tableName);
            });
            aPanel.add(aButton);
            addComponent(aPanel);
        });
    }
    public void deleteRowFromTable(String tableName)
    {
        JTable aTable = createTable(tableName);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                Database main = new Database();
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("code", aTable.getModel().getValueAt(aTable.getSelectedRow(), 0).toString());
                main.removeTableRow(tableName, parameters);
                deleteRowFromTable(tableName);
            }
        });
    }
    public void addRowToTable(String tableName)
    {
        Database main = new Database();
        ArrayList<String> columnTitles = main.getColumnTitles(tableName);
        ArrayList<JTextField> textFields = new ArrayList<>();
        JPanel aPanel = new JPanel(new GridLayout(0, 1));
        columnTitles.forEach(x ->
        {
            JTextField aTextField = createTextField("");
            textFields.add(aTextField);
            aPanel.add(createLabel("Please enter a valid value for " + x));
            aPanel.add(aTextField);
        });
        JButton aButton = createButton("Add New Row");
        aButton.addActionListener((x) ->
        {
            ArrayList<String> textEntered = new ArrayList<>();
            textFields.forEach(y -> textEntered.add(y.getText()));
            main.insertTableRow(tableName, textEntered);
            createTable(tableName);
        });
        aPanel.add(aButton);
        addComponent(aPanel);
    }
    private void addComponent(JComponent aComponent)
    {
        if(component != null)
            frame.remove(component);
        component = aComponent;
        frame.add(component, BorderLayout.CENTER);
        frame.invalidate();
        frame.revalidate();
    }
}
