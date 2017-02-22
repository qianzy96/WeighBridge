import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends Components
{
    private JRibbonFrame frame;
    private JComponent component;
    private HashMap<FeedCosts, Double> feedCostsWithQuantities;
    private String title;
    public Calculator()
    {
        feedCostsWithQuantities = new HashMap<>();
        title = "";
        createRibbon();
    }
    private void createRibbon()
    {
        frame = createRibbonFrame("Calculator");
        JRibbonBand rationsBand = createRibbonBand("Rations");
        JCommandButton createNewRationButton = createCommandButton("Create New Ration");
        createNewRationButton.addActionListener(x -> {createCalculatorDialogBox();});
        rationsBand.addCommandButton(createNewRationButton, RibbonElementPriority.TOP);
        JCommandButton viewRationsButton = createCommandButton("View Rations");
        rationsBand.addCommandButton(viewRationsButton, RibbonElementPriority.TOP);
        JCommandButton deleteRationButton = createCommandButton("Delete Ration");
        rationsBand.addCommandButton(deleteRationButton, RibbonElementPriority.TOP);
        RibbonTask rationsTask = createRibbonTask("Rations", new JRibbonBand[]{rationsBand});
        JRibbonBand commoditiesBand = createRibbonBand("Commodities");
        JCommandButton createNewCommodityButton = createCommandButton("Create New Commodity");
        commoditiesBand.addCommandButton(createNewCommodityButton, RibbonElementPriority.TOP);
        JCommandButton viewCommoditiesButton = createCommandButton("View Commodities");
        commoditiesBand.addCommandButton(viewCommoditiesButton, RibbonElementPriority.TOP);
        JCommandButton deleteCommodityButton = createCommandButton("Delete Commodity");
        commoditiesBand.addCommandButton(deleteCommodityButton, RibbonElementPriority.TOP);
        RibbonTask commoditiesTask = createRibbonTask("Commodities", new JRibbonBand[]{commoditiesBand});
        JRibbonBand quickActionsBand = createRibbonBand("Quick Actions");
        JCommandButton generatePDFFileButton = createCommandButton("Generate PDF File");
        generatePDFFileButton.addActionListener(x -> generatePDFFile());
        quickActionsBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton emailPDFFileButton = createCommandButton("Email PDF File");
        emailPDFFileButton.addActionListener(x -> emailPDFFile());
        quickActionsBand.addCommandButton(emailPDFFileButton, RibbonElementPriority.TOP);
        JCommandButton printPDFFileButton = createCommandButton("Print PDF File");
        printPDFFileButton.addActionListener(x -> printPDFFile());
        quickActionsBand.addCommandButton(printPDFFileButton, RibbonElementPriority.TOP);
        RibbonTask quickActionsTask = createRibbonTask("Quick Actions", new JRibbonBand[]{quickActionsBand});
        frame.getRibbon().addTask(rationsTask);
        frame.getRibbon().addTask(commoditiesTask);
        frame.getRibbon().addTask(quickActionsTask);
        RibbonApplicationMenu anApplicationMenu = new RibbonApplicationMenu();
        for(int counter = 0; counter < 7; counter++)
        {
            RibbonApplicationMenuEntryPrimary anApplicationMenuEntry = createApplicationMenuEntry("Primary", x -> {});
            RibbonApplicationMenuEntrySecondary aSecondaryApplicationMenuEntry = createMinorApplicationMenuEntry("Secondary", x -> {});
            anApplicationMenuEntry.addSecondaryMenuGroup("Group", new RibbonApplicationMenuEntrySecondary[]{aSecondaryApplicationMenuEntry,
            aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry});
            anApplicationMenu.addMenuEntry(anApplicationMenuEntry);
        }
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
    }
    public void createCalculatorDialogBox()
    {
        JPanel mainPanel = new JPanel(new GridLayout(3, 0));
        JPanel textBoxPanel = new JPanel(new GridLayout(3, 0));
        JPanel tilesPanel = new JPanel(new GridLayout(5, 0));
        Database main = new Database();
        ArrayList<FeedCosts> availableFeedCosts = new ArrayList<>();
        ArrayList<ArrayList<String>> availableFeeds = main.getTableRows("feedcosts", new HashMap<>(), new ArrayList<>(), "");
        availableFeeds.forEach(x -> availableFeedCosts.add(new FeedCosts(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7),
        x.get(8), x.get(9), x.get(10), x.get(11), x.get(12), x.get(13))));
        JLabel titleLabel = createLabel("Please enter the title of the ration");
        JTextField titleTextField = createTextField("");
        JComboBox aDropDownBox = createDropDown(new ArrayList<>());
        availableFeedCosts.forEach(x -> aDropDownBox.addItem(x));
        aDropDownBox.addItemListener((x) ->
        {
            while(textBoxPanel.getComponents().length > 0)
                textBoxPanel.remove(0);
            JLabel aLabel = createLabel("The kg per day fresh weight for " + x.getItem());
            JTextField aTextField = createTextField("");
            textBoxPanel.add(aLabel);
            textBoxPanel.add(aTextField);
            JButton aButton = createTile("Save Feed", "", 8);
            aButton.addActionListener((y) ->
            {
                JButton aTile = createTile(aDropDownBox.getSelectedItem() + " " + aTextField.getText() + " kg", "", 8);
                aDropDownBox.removeItem(x.getItem());
                tilesPanel.add(aTile);
                feedCostsWithQuantities.put((FeedCosts) x.getItem(), Double.parseDouble(aTextField.getText()));
                frame.invalidate();
                frame.revalidate();
            });
            textBoxPanel.add(aButton);
            frame.invalidate();
            frame.revalidate();
        });
        JButton calculateYourRationTile = createTile("Calculate Your Ration", "", 1);
        calculateYourRationTile.addActionListener(x ->
        {
            if(feedCostsWithQuantities.size() > 0 && titleTextField.getText().length() > 0)
            {
                title = titleTextField.getText();
                createResultsDialogBox();
            }
        });
        mainPanel.add(titleLabel);
        mainPanel.add(titleTextField);
        mainPanel.add(aDropDownBox);
        mainPanel.add(textBoxPanel);
        mainPanel.add(tilesPanel);
        mainPanel.add(calculateYourRationTile);
        addComponent(mainPanel);
    }
    public void createResultsDialogBox()
    {
        FeedCostsCalculator aCalculator = new FeedCostsCalculator(feedCostsWithQuantities, title);
        JPanel resultsPanel = new JPanel(new GridLayout(4, 2));
        JButton dryMatterTile = createTile("Dry Matter: " + aCalculator.calculateDryMatter(), "", 7);
        JButton crudeProteinTile = createTile("Crude Protein: " + aCalculator.calculateCrudeProtein(), "", 7);
        JButton ndfTile = createTile("NDF: " + aCalculator.calculateNDF(), "", 7);
        JButton starchAndSugarsTile = createTile("Starch & Sugars: " + aCalculator.calculateStarchAndSugars(), "", 7);
        JButton oilTile = createTile("Oil: " + aCalculator.calculateOil(), "", 7);
        JButton rationCostDryMatterTile = createTile("Dry Matter: £" + aCalculator.calculateRationCostDryMatter(), "", 7);
        JButton rationCostFreshWeightTile = createTile("Fresh Weight: £" + aCalculator.calculateRationCostFreshWeight(), "", 7);
        resultsPanel.add(dryMatterTile);
        resultsPanel.add(crudeProteinTile);
        resultsPanel.add(ndfTile);
        resultsPanel.add(starchAndSugarsTile);
        resultsPanel.add(oilTile);
        resultsPanel.add(rationCostDryMatterTile);
        resultsPanel.add(rationCostFreshWeightTile);
        addComponent(resultsPanel);
    }
    public void generatePDFFile()
    {
        FeedCostsCalculator aCalculator = new FeedCostsCalculator(feedCostsWithQuantities, title);
        Report aReport = new Report("rations/1.pdf");
        ArrayList<String> reportContent = new ArrayList<>();
        reportContent.add("Title: " + aCalculator.getTitle());
        reportContent.add("Date: " + new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(aCalculator.getDate()));
        reportContent.add("Printed  By: " + "Stephen Cullinan");
        reportContent.add("Email Address: " + "stephencullinan1991@gmail.com");
        reportContent.add("Phone Number: " + "(087)1033684");
        for(Map.Entry<FeedCosts, Double> aFeedCost : feedCostsWithQuantities.entrySet())
            reportContent.add(aFeedCost.getKey().getFeed() + " " + aFeedCost.getValue());
        reportContent.add("Total Fresh Intake: " + aCalculator.getTotalFreshIntake());
        reportContent.add("Total Dry Matter Intake: " + aCalculator.getTotalFreshIntake() * (aCalculator.calculateDryMatter() / 100));
        reportContent.addAll(Arrays.asList("Dry Matter: " + aCalculator.calculateDryMatter(), "Crude Protein: " + aCalculator.calculateCrudeProtein(),
        "NDF: " + aCalculator.calculateNDF(), "Starch & Sugars: " + aCalculator.calculateStarchAndSugars(), "Oil: " + aCalculator.calculateOil(), "Dry Matter: £" +
        aCalculator.calculateRationCostDryMatter(), "Fresh Weight: £" + aCalculator.calculateRationCostFreshWeight()));
        aReport.addContent(reportContent);
    }
    public void emailPDFFile()
    {
        if(!new File("rations/1.pdf").exists())
            generatePDFFile();
        Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
        Object anEmailAddress = JOptionPane.showInputDialog(null, "Please enter your email address", "Your Email Address",
        JOptionPane.INFORMATION_MESSAGE);
        if(anEmailAddress.toString().length() > 1)
            anEmail.sendMessage(anEmailAddress.toString(), "Ration Report", "Dear Sir/Madam,\n\nPlease find attached the ration report as calculated " +
            "on the application.\n\nYours faithfully,\nStephen Cullinan", "rations/1.pdf", "Ration Report.pdf");
        else
            emailPDFFile();
    }
    public void printPDFFile()
    {
        if(!new File("rations/1.pdf").exists())
            generatePDFFile();
        new Printer("rations/1.pdf");
    }
    private void addComponent(JComponent newComponent)
    {
        if(component != null)
            frame.remove(component);
        component = newComponent;
        frame.add(component);
        frame.invalidate();
        frame.revalidate();
    }
    private void refreshComponent()
    {
        frame.invalidate();
        frame.revalidate();
    }
}