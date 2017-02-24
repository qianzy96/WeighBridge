import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dashboard extends Components
{
    private String userID;
    private String fileName;
    private JRibbonFrame frame;
    private JComponent component;
    private HashMap<String, CommodityPrices> commodityPrices;
    public Dashboard(String userID)
    {
        this.userID = userID;
        this.fileName = "";
        this.commodityPrices = new HashMap<>();
        addMenu();
        addDashboard();
        viewComponent("barChart");
    }
    private void addMenu()
    {
        frame = createRibbonFrame("Prices");
        JRibbonBand pricesBand = createRibbonBand("Prices");
        JCommandButton viewBarChartButton = createCommandButton("View Prices In BarChart Format");
        viewBarChartButton.addActionListener(x ->
        {
            viewComponent("barChart");
        });
        pricesBand.addCommandButton(viewBarChartButton, RibbonElementPriority.TOP);
        JCommandButton viewLineChartButton = createCommandButton("View Prices In LineChart Format");
        viewLineChartButton.addActionListener(x ->
        {
            viewComponent("lineChart");
        });
        pricesBand.addCommandButton(viewLineChartButton, RibbonElementPriority.TOP);
        JCommandButton viewTableButton = createCommandButton("View Prices In Tabular Format");
        viewTableButton.addActionListener(x ->
        {
            viewComponent("table");
        });
        pricesBand.addCommandButton(viewTableButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Prices", new JRibbonBand[]{pricesBand}));
        JRibbonBand quickActionsBand = createRibbonBand("Quick Actions");
        JCommandButton generatePDFFileButton = createCommandButton("Generate PDF File");
        generatePDFFileButton.addActionListener(x ->
        {
            generatePDFFile();
        });
        quickActionsBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton emailPDFFileButton = createCommandButton("Email PDF File");
        emailPDFFileButton.addActionListener(x ->
        {
            emailPDFFile();
        });
        quickActionsBand.addCommandButton(emailPDFFileButton, RibbonElementPriority.TOP);
        JCommandButton printPDFFileButton = createCommandButton("Print PDF File");
        printPDFFileButton.addActionListener(x ->
        {
            printPDFFile();
        });
        quickActionsBand.addCommandButton(printPDFFileButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Quick Actions", new JRibbonBand[]{quickActionsBand}));
        RibbonApplicationMenu anApplicationMenu = new RibbonApplicationMenu();
        RibbonApplicationMenuEntryPrimary anEntry = createApplicationMenuEntry("Entry", x -> {});
        for(int counter = 0; counter < 5; counter++)
            anEntry.addSecondaryMenuGroup("Group", createMinorApplicationMenuEntry("Title", x -> {}));
        for(int counter = 0; counter < 5; counter++)
            anApplicationMenu.addMenuEntry(anEntry);
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Return To Main Menu", x -> {new Portal(userID);frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {new WeighBridge();frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
    }
    private void addDashboard()
    {
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
            links.forEach(x ->
            {
                RetrieveContent aPage = new RetrieveContent(x);
                String pageContent = aPage.getText();
                ArrayList<String> pageContents = Utilities.extractTextBetweenTags(pageContent, "<font.*?>", "</font>");
                CommodityPrices aCommodityPrices = new CommodityPrices(pageContents.get(0), pageContents.get(1));
                for (int index = 2; index < 7; index++)
                    aCommodityPrices.addHeading(pageContents.get(index));
                for (int counter = 7; counter < pageContents.size(); counter = counter + 5)
                {
                    aCommodityPrices.addPrices(pageContents.get(counter), new ArrayList<>(Arrays.asList(pageContents.get(counter + 1), pageContents.get(counter + 2),
                    pageContents.get(counter + 3), pageContents.get(counter + 4))));
                }
                commodityPrices.put(aCommodityPrices.getCommodityTitle(), aCommodityPrices);
            });
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
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
    private void viewComponent(String componentTitle)
    {
        JPanel dashboardPanel = new JPanel(new GridLayout(4, 4));
        for(Map.Entry<String, CommodityPrices> aCommodity: commodityPrices.entrySet())
        {
            JButton aCommodityTile = createTile(aCommodity.getValue().getCommodityTitle(), "", commodityPrices.size());
            aCommodityTile.addActionListener(d ->
            {
                JPanel individualItemPanel = new JPanel(new GridLayout(1, 1));
                if(componentTitle.equals("table"))
                    individualItemPanel.add(createTable(aCommodity.getValue()));
                else if(componentTitle.equals("barChart"))
                    individualItemPanel.add(viewBarChart(aCommodity.getValue()));
                else if(componentTitle.equals("lineChart"))
                    individualItemPanel.add(viewLineChart(aCommodity.getValue()));
                addComponent(individualItemPanel);
            });
            dashboardPanel.add(aCommodityTile);
        }
        addComponent(dashboardPanel);
    }
    private JTable createTable(CommodityPrices aCommodity)
    {
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        aCommodity.getHeadings().forEach(x -> aModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedCommodityPrices = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> aCommodityPrices : aCommodity.getPrices().entrySet())
        {
            ArrayList<String> commodityPrices = new ArrayList<>();
            commodityPrices.add(aCommodityPrices.getKey());
            for(String aPrice : aCommodityPrices.getValue())
                commodityPrices.add(aPrice);
            formattedCommodityPrices.add(commodityPrices);
        }
        formattedCommodityPrices.forEach(x -> aModel.addRow(x.toArray()));
        return aTable;
    }
    private ChartPanel viewLineChart(CommodityPrices aCommodity)
    {
        JFreeChart aLineChart = ChartFactory.createLineChart(aCommodity.getCommodityTitle(), "Period", aCommodity.getCommodityTitle(),
        generateDataForChart(aCommodity), PlotOrientation.VERTICAL, true, true, false);
        return new ChartPanel(aLineChart);
    }
    private ChartPanel viewBarChart(CommodityPrices aCommodity)
    {
        JFreeChart aBarChart = ChartFactory.createBarChart(aCommodity.getCommodityTitle(), "Period", aCommodity.getCommodityTitle(),
        generateDataForChart(aCommodity), PlotOrientation.VERTICAL, true, true, false);
        return new ChartPanel(aBarChart);
    }
    private DefaultCategoryDataset generateDataForChart(CommodityPrices aCommodity)
    {
        DefaultCategoryDataset dataPoints = new DefaultCategoryDataset();
        for(Map.Entry<String, ArrayList<String>> aDataPoint : aCommodity.getPrices().entrySet())
        {
            int counter = 1;
            for(String aPrice : aDataPoint.getValue())
            {
                dataPoints.addValue(Double.parseDouble(formatPrice(aPrice)), "Commodities " + counter, aDataPoint.getKey());
                counter++;
            }
        }
        return dataPoints;
    }
    private void generatePDFFile()
    {
        fileName = "Prices/Prices_" + new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date()) + ".pdf";
        Report aReport = new Report(fileName);
        ArrayList<String> paragraphs = new ArrayList<>();
        Database main = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", userID);
        ArrayList<ArrayList<String>> userDetails = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        if(userDetails.size() == 1)
            paragraphs.add("Created By " + userDetails.get(0).get(3) + " " + userDetails.get(0).get(4));
        paragraphs.add("Created On " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        for(Map.Entry<String, CommodityPrices> aCommodityPrice: commodityPrices.entrySet())
        {
            paragraphs.add(aCommodityPrice.getValue().getCommodityTitle() + " " + aCommodityPrice.getValue().getDate() + "\n\n");
            for(String aHeading: aCommodityPrice.getValue().getHeadings())
                paragraphs.add(aHeading + "\t");
            paragraphs.add("\n");
            ArrayList<ArrayList<String>> prices = new ArrayList<>();
            for(Map.Entry<String, ArrayList<String>> aPrice : aCommodityPrice.getValue().getPrices().entrySet())
            {
                ArrayList<String> price = new ArrayList<>();
                price.add(aPrice.getKey());
                aPrice.getValue().forEach(x -> price.add(x));
                prices.add(price);
            }
            for(ArrayList<String> aPrice : prices)
            {
                aPrice.forEach(x -> paragraphs.add(x + "\t"));
                paragraphs.add("\n");
            }
        }
        aReport.addContent(paragraphs);
    }
    private void emailPDFFile()
    {
        if(fileName.length() == 0)
            generatePDFFile();
        String emailAddress = JOptionPane.showInputDialog(null, "What is your email address?", "Email Address",
        JOptionPane.QUESTION_MESSAGE);
        if(emailAddress.length() > 1)
        {
            Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
            anEmail.sendMessage(emailAddress, "Prices Report",
            "Dear Sir/Madam, Please find attached the report on the prices. If you have any further questions please do not hesitate to contact me." +
            "Yours sincerely, Stephen Cullinan", "prices/" + fileName, fileName);
        }
        else
            emailPDFFile();
    }
    private void printPDFFile()
    {
        if(fileName.length() == 0)
            generatePDFFile();
        new Printer("prices/" + fileName);
    }
    private String formatPrice(String aPrice)
    {
        aPrice = aPrice.replace("'", ".");
        aPrice = aPrice.replace("y", "");
        aPrice = aPrice.replace("s", "");
        aPrice = aPrice.replace("n/a", "0.0");
        if (aPrice.length() == 0)
            aPrice = "0.0";
        return aPrice;
    }
}