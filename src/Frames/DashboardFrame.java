package Frames;
import Prices.CommodityPrices;
import Models.Dashboard;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class DashboardFrame extends Components
{
    private JRibbonFrame frame;
    private JComponent component;
    private Dashboard aDashboard;
    public DashboardFrame(String userID)
    {
        this.aDashboard = new Dashboard(userID);
        addMenu();
        aDashboard.addDashboard();
        viewComponent("barChart");
    }
    private void addMenu()
    {
        frame = createRibbonFrame("Prices");
        JRibbonBand pricesBand = createRibbonBand("Prices");
        JCommandButton viewBarChartButton = createCommandButton("View Prices In BarChart Format");
        viewBarChartButton.addActionListener(x -> viewComponent("barChart"));
        pricesBand.addCommandButton(viewBarChartButton, RibbonElementPriority.TOP);
        JCommandButton viewLineChartButton = createCommandButton("View Prices In LineChart Format");
        viewLineChartButton.addActionListener(x -> viewComponent("lineChart"));
        pricesBand.addCommandButton(viewLineChartButton, RibbonElementPriority.TOP);
        JCommandButton viewTableButton = createCommandButton("View Prices In Tabular Format");
        viewTableButton.addActionListener(x -> viewComponent("table"));
        pricesBand.addCommandButton(viewTableButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Prices", new JRibbonBand[]{pricesBand}));
        JRibbonBand quickActionsBand = createRibbonBand("Quick Actions");
        JCommandButton generatePDFFileButton = createCommandButton("Generate PDF File");
        generatePDFFileButton.addActionListener(x -> aDashboard.generatePDFFile());
        quickActionsBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton emailPDFFileButton = createCommandButton("Email PDF File");
        emailPDFFileButton.addActionListener(x -> emailPDFFile());
        quickActionsBand.addCommandButton(emailPDFFileButton, RibbonElementPriority.TOP);
        JCommandButton printPDFFileButton = createCommandButton("Print PDF File");
        printPDFFileButton.addActionListener(x -> aDashboard.printPDFFile());
        quickActionsBand.addCommandButton(printPDFFileButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Quick Actions", new JRibbonBand[]{quickActionsBand}));
        RibbonApplicationMenu anApplicationMenu = new RibbonApplicationMenu();
        RibbonApplicationMenuEntryPrimary anEntry = createApplicationMenuEntry("Entry", x -> {});
        for(int counter = 0; counter < 5; counter++)
            anEntry.addSecondaryMenuGroup("Group", createMinorApplicationMenuEntry("Title", x -> {}));
        for(int counter = 0; counter < 5; counter++)
            anApplicationMenu.addMenuEntry(anEntry);
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Return To Main Menu",
        x -> {new PortalFrame(aDashboard.getUserID() + "");frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {new WeighBridgeFrame();frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
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
        for(Map.Entry<String, CommodityPrices> aCommodity: aDashboard.getCommodityPrices().entrySet())
        {
            JButton aCommodityTile = createTile(aCommodity.getValue().getCommodityTitle(), "", aDashboard.getCommodityPrices().size());
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
        ArrayList<ArrayList<String>> formattedCommodityPrices = aDashboard.getListOfCommodityPricesForSelectedCommodity(aCommodity, false);
        formattedCommodityPrices.forEach(x -> aModel.addRow(x.toArray()));
        return aTable;
    }
    private ChartPanel viewLineChart(CommodityPrices aCommodity)
    {
        JFreeChart aLineChart = ChartFactory.createLineChart(aCommodity.getCommodityTitle(), "Period", aCommodity.getCommodityTitle(),
        aDashboard.generateDataForChart(aCommodity), PlotOrientation.VERTICAL, true, true, false);
        return new ChartPanel(aLineChart);
    }
    private ChartPanel viewBarChart(CommodityPrices aCommodity)
    {
        JFreeChart aBarChart = ChartFactory.createBarChart(aCommodity.getCommodityTitle(), "Period", aCommodity.getCommodityTitle(),
        aDashboard.generateDataForChart(aCommodity), PlotOrientation.VERTICAL, true, true, false);
        return new ChartPanel(aBarChart);
    }
    private void emailPDFFile()
    {
        String emailAddress = JOptionPane.showInputDialog(null, "What is your email address?", "Email Address", JOptionPane.QUESTION_MESSAGE);
        if(emailAddress.length() > 1)
           aDashboard.emailPDFFile(emailAddress);
        else
            emailPDFFile();
    }
}
