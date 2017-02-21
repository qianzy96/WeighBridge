import com.github.sarxos.webcam.Webcam;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontEncoding;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import jssc.SerialPort;
import org.omg.CORBA.Environment;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import com.itextpdf.*;

public class WeighBridge extends Components
{
    private JFrame frame;
    private JComponent component;
    private DocketType selectedDocketType;
    private Consignee selectedConsignee;
    private Driver selectedDriver;
    private Commodity selectedCommodity;
    //private String selectedFirstWeight;
    //private String selectedSecondWeight;
    private FirstWeight selectedFirstWeight;
    private SecondWeight selectedSecondWeight;
    public WeighBridge()
    {
        try
        {
            UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
        frame = createFrame("WeighBridge Portal");
        selectWeightType();
        frame.setVisible(true);
    }
    public void selectWeightType()
    {
        JPanel aPanel = new JPanel(new GridLayout(2, 2));
        JButton firstWeightTile = createTile("First Weight", "", 4);
        firstWeightTile.addActionListener(x -> selectWeightDocketType());
        aPanel.add(firstWeightTile);
        JButton secondWeightTile = createTile("Second Weight", "", 4);
        secondWeightTile.addActionListener(x -> selectSecondWeight());
        aPanel.add(secondWeightTile);
        JButton calculatorTile = createTile("Calculator", "", 4);
        calculatorTile.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                Calculator aCalculator = new Calculator();
                aCalculator.createCalculatorDialogBox();
                frame.dispose();
            });
        });
        aPanel.add(calculatorTile);
        JButton administratorLogOnTile = createTile("Administrator Log On", "", 4);
        administratorLogOnTile.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                AdministratorLogOn anAdministratorLogOn = new AdministratorLogOn();
                anAdministratorLogOn.createLogOnDialogBox();
                frame.dispose();
            });
        });
        aPanel.add(administratorLogOnTile);
        addComponent(aPanel);
    }
    public void selectWeightDocketType()
    {
        Database main = new Database();
        ArrayList<ArrayList<String>> docketTypes = main.getTableRows("dockettypes", new HashMap<>(), new ArrayList<>(), "dockettype");
        ArrayList<DocketType> docketTypesObjects = new ArrayList<>();
        docketTypes.forEach(x -> docketTypesObjects.add(new DocketType(Integer.parseInt(x.get(0)), x.get(1))));
        JPanel docketTypesPanel = new JPanel(new GridLayout(0, 3));
        docketTypesObjects.forEach(z ->
        {
            JButton aDocketType = createTile(z.toString(), "", docketTypes.size());
            aDocketType.addActionListener((y) -> {selectedDocketType = z;selectConsignee();});
            docketTypesPanel.add(aDocketType);
        });
        addComponent(docketTypesPanel);
    }
    public void selectConsignee()
    {
        JPanel consigneesPanel = new JPanel(new GridLayout(0, 3));
        Database main = new Database();
        ArrayList<Consignee> consigneesObjects = new ArrayList<>();
        final int numberOfConsignees = consigneesObjects.size();
        if(selectedDocketType.getCode() == 1)
        {
            ArrayList<ArrayList<String>> consignees = main.getTableRows("suppliers", new HashMap<>(), new ArrayList<>(), "lastname");
            consignees.forEach(y -> consigneesObjects.add(new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2))));
            JButton addNewSupplierTile = createTile("Add New Supplier", "", numberOfConsignees + 1);
            addNewSupplierTile.addActionListener((x) -> addNewSupplier());
            consigneesPanel.add(addNewSupplierTile);
        }
        else if(selectedDocketType.getCode() == 2)
        {
            ArrayList<ArrayList<String>> consignees = main.getTableRows("customers", new HashMap<>(), new ArrayList<>(), "lastname");
            consignees.forEach(y -> consigneesObjects.add(new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2))));
            JButton addNewCustomerTile = createTile("Add New Customer", "", numberOfConsignees + 1);
            addNewCustomerTile.addActionListener((x) -> addNewCustomer());
            consigneesPanel.add(addNewCustomerTile);
        }
        consigneesObjects.forEach(x ->
        {
            JButton aConsignee = createTile(x.toString(), "", numberOfConsignees + 1);
            if(selectedDocketType.getCode() == 1)
                aConsignee.addActionListener((y) -> {selectedConsignee = x;selectDriver();});
            else if(selectedDocketType.getCode() == 2)
                aConsignee.addActionListener((y) -> {selectedConsignee = x;selectDriver();});
            consigneesPanel.add(aConsignee);
        });
        addComponent(consigneesPanel);
    }
    public void selectDriver()
    {
        Database main = new Database();
        ArrayList<Driver> driverObjects = new ArrayList<>();
        ArrayList<ArrayList<String>> drivers = main.getTableRows("drivers", new HashMap<>(), new ArrayList<>(), "lastname");
        drivers.forEach(x -> driverObjects.add(new Driver(Integer.parseInt(x.get(0)), x.get(1), x.get(2))));
        JPanel driversPanel = new JPanel(new GridLayout(0, 3));
        JButton createNewDriver = createTile("Add New Driver", "", drivers.size() + 1);
        createNewDriver.addActionListener((x) -> addNewDriver());
        driversPanel.add(createNewDriver);
        driverObjects.forEach(x ->
        {
            JButton aDriver = createTile(x.toString(), "users/" + x.getCode() + ".png", drivers.size() + 1);
            aDriver.addActionListener((y) -> {selectedDriver = x; selectCommodity();});
            driversPanel.add(aDriver);
        });
        addComponent(driversPanel);
    }
    public void addNewDriver()
    {
        JPanel addNewDriversPanel = new JPanel(new GridLayout(5, 1));
        JLabel firstNameLabel = createLabel("Please enter the first name of the driver");
        JTextField firstName = createTextField("");
        JLabel lastNameLabel = createLabel("Please enter the last name of the driver");
        JTextField lastName = createTextField("");
        JButton addDriverButton = createButton("Add New Driver");
        addDriverButton.addActionListener((x) ->
        {
            if(firstName.getText().length() > 1 && lastName.getText().length() > 1)
            {
                Database main = new Database();
                int maximumValue = main.getMaxValueOfColumn("drivers", "code");
                ObtainPhoto obtainPhoto = new ObtainPhoto(frame, "images/users/" + (maximumValue + 1) + ".png");
                Driver aDriver = new Driver(maximumValue + 1, firstName.getText(), lastName.getText());
                main.insertTableRow("drivers", aDriver.toList());
                selectedDriver = aDriver;
                selectCommodity();
            }
            else
                addNewDriver();
        });
        addNewDriversPanel.add(firstNameLabel);
        addNewDriversPanel.add(firstName);
        addNewDriversPanel.add(lastNameLabel);
        addNewDriversPanel.add(lastName);
        addNewDriversPanel.add(addDriverButton);
        addComponent(addNewDriversPanel);
    }
    public void addNewCommodity()
    {
        JPanel addNewCommodityPanel = new JPanel(new GridLayout(3, 1));
        JTextField commodity = createTextField("");
        JButton addCommodityButton = createButton("Add New Commodity");
        addCommodityButton.addActionListener((x) ->
        {
            if(commodity.getText().length() > 1)
            {
                Database main = new Database();
                int maximumValue = main.getMaxValueOfColumn("commodities", "code");
                Commodity aCommodity = new Commodity(maximumValue + 1, commodity.getText());
                main.insertTableRow("commodities", aCommodity.toList());
                selectedCommodity = aCommodity;
                selectFirstWeight();
            }
            else
                addNewCommodity();
        });
        addNewCommodityPanel.add(createLabel("Please enter the name of the commodity"));
        addNewCommodityPanel.add(commodity);
        addNewCommodityPanel.add(addCommodityButton);
        addComponent(addNewCommodityPanel);
    }
    public void addNewCustomer()
    {
        JPanel addNewCustomerPanel = new JPanel(new GridLayout(5, 1));
        JTextField customerFirstName = createTextField("");
        JTextField customerLastName = createTextField("");
        JButton addCustomerButton = createButton("Add New Customer");
        addCustomerButton.addActionListener((x) ->
        {
            if(customerFirstName.getText().length() > 1 && customerLastName.getText().length() > 1)
            {
                Database main = new Database();
                int maximumValue = main.getMaxValueOfColumn("customers", "code");
                Consignee aConsignee = new Consignee(maximumValue + 1, customerFirstName.getText(), customerLastName.getText());
                main.insertTableRow("customers", aConsignee.toList());
                selectedConsignee = aConsignee;
                selectDriver();
            }
            else
                addNewCustomer();
        });
        addNewCustomerPanel.add(createLabel("Please enter the first name of the customer"));
        addNewCustomerPanel.add(customerFirstName);
        addNewCustomerPanel.add(createLabel("Please enter the last name of the customer"));
        addNewCustomerPanel.add(customerLastName);
        addNewCustomerPanel.add(addCustomerButton);
        addComponent(addNewCustomerPanel);
    }
    public void addNewSupplier()
    {
        JPanel addNewSupplierPanel = new JPanel(new GridLayout(5, 1));
        JTextField supplierFirstName = createTextField("");
        JTextField supplierLastName = createTextField("");
        JButton addSupplierButton = createButton("Add New Supplier");
        addSupplierButton.addActionListener((x) ->
        {
            if(supplierFirstName.getText().length() > 1 && supplierLastName.getText().length() > 1)
            {
                Database main = new Database();
                int maximumValue = main.getMaxValueOfColumn("suppliers", "code");
                Consignee aConsignee = new Consignee(maximumValue + 1, supplierFirstName.getText(), supplierLastName.getText());
                main.insertTableRow("suppliers", aConsignee.toList());
                selectedConsignee = aConsignee;
                selectDriver();
            }
            else
                addNewSupplier();
        });
        addNewSupplierPanel.add(createLabel("Please enter the first name of the supplier"));
        addNewSupplierPanel.add(supplierFirstName);
        addNewSupplierPanel.add(createLabel("Please enter the last name of the supplier"));
        addNewSupplierPanel.add(supplierLastName);
        addNewSupplierPanel.add(addSupplierButton);
        addComponent(addNewSupplierPanel);
    }
    public void selectCommodity()
    {
        Database main = new Database();
        ArrayList<ArrayList<String>> commodities = main.getTableRows("commodities", new HashMap<>(), new ArrayList<>(), "title");
        ArrayList<Commodity> commoditiesObjects = new ArrayList<>();
        commodities.forEach(x -> commoditiesObjects.add(new Commodity(Integer.parseInt(x.get(0)), x.get(1))));
        ArrayList<JButton> commodityTiles = new ArrayList<>();
        JPanel commoditiesPanel = new JPanel(new GridLayout(0, 3));
        JButton addNewCommodityButton = createTile("Add New Commodity", "", commodities.size() + 1);
        addNewCommodityButton.addActionListener((x) -> addNewCommodity());
        commoditiesPanel.add(addNewCommodityButton);
        commoditiesObjects.forEach(x ->
        {
            JButton aCommodity = createTile(x.getTitle(), "commodities/" + x.getCode(), commodities.size() + 1);
            aCommodity.addActionListener((y) -> {selectedCommodity = x; selectFirstWeight();});
            commodityTiles.add(aCommodity);
        });
        commodityTiles.forEach(y -> commoditiesPanel.add(y));
        addComponent(commoditiesPanel);
    }
    public void selectFirstWeight()
    {
        Database main = new Database();
        int maxValue = main.getMaxValueOfColumn("firstweights", "code");
        selectedFirstWeight = new FirstWeight(maxValue + 1, selectedDriver, selectedCommodity, 45000, new Date(), selectedDocketType, selectedConsignee);
        main.insertTableRow("firstweights", selectedFirstWeight.toList());
        selectWeightType();
    }
    public void selectSecondWeight()
    {
        Database main = new Database();
        ArrayList<ArrayList<String>> firstWeights = main.getTableRows("firstweights", new HashMap<>(), new ArrayList<>(), "");
        ArrayList<ArrayList<String>> secondWeights = main.getTableRows("secondweights", new HashMap<>(), new ArrayList<>(), "");
        ArrayList<ArrayList<String>> firstWeightsAwaitingSecondWeight = new ArrayList<>();
        for(int counter = 0; counter < firstWeights.size(); counter++)
        {
            Boolean weightLocated = false;
            for(int index = 0; index < secondWeights.size() && !weightLocated; index++)
                if(secondWeights.get(index).get(3).equals(firstWeights.get(counter).get(0)))
                    weightLocated = true;
            if(!weightLocated)
                firstWeightsAwaitingSecondWeight.add(firstWeights.get(counter));
        }
        JPanel secondWeightsPanel = new JPanel(new GridLayout(0, 3));
        firstWeightsAwaitingSecondWeight.forEach(x ->
        {
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("code", x.get(1));
            ArrayList<ArrayList<String>> driverDetails = main.getTableRows("drivers", selectedParameters, new ArrayList<>(), "");
            driverDetails.forEach(y -> selectedDriver = new Driver(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
            selectedParameters.put("code", x.get(2));
            ArrayList<ArrayList<String>> commodityDetails = main.getTableRows("commodities", selectedParameters, new ArrayList<>(), "");
            commodityDetails.forEach(y -> selectedCommodity = new Commodity(Integer.parseInt(y.get(0)), y.get(1)));
            selectedParameters.put("code", x.get(5));
            ArrayList<ArrayList<String>> docketTypeDetails = main.getTableRows("dockettypes", selectedParameters, new ArrayList<>(), "");
            docketTypeDetails.forEach(y -> selectedDocketType = new DocketType(Integer.parseInt(y.get(0)), y.get(1)));
            selectedParameters.put("code", x.get(6));
            if(selectedDocketType.getCode() == 1)
            {
                ArrayList<ArrayList<String>> supplierDetails = main.getTableRows("suppliers", selectedParameters, new ArrayList<>(), "");
                supplierDetails.forEach(y -> selectedConsignee = new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
            }
            else if(selectedDocketType.getCode() == 2)
            {
                ArrayList<ArrayList<String>> customerDetails = main.getTableRows("customers", selectedParameters, new ArrayList<>(), "");
                customerDetails.forEach(y -> selectedConsignee = new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
            }
            String captionTitle = selectedDriver.toString() + " " + selectedCommodity.toString();
            JButton currentButton = createTile(captionTitle, "", firstWeightsAwaitingSecondWeight.size());
            currentButton.addActionListener((y) ->
            {
                selectedFirstWeight = new FirstWeight(Integer.parseInt(x.get(0)), selectedDriver, selectedCommodity, Double.parseDouble(x.get(3)),
                Utilities.createDate(x.get(4),"yyyy/MM/dd HH:mm:ss"), selectedDocketType, selectedConsignee);
                int maxValueOfColumn = main.getMaxValueOfColumn("secondweights", "code");
                selectedSecondWeight = new SecondWeight(maxValueOfColumn + 1, 15000, new Date(), selectedFirstWeight);
                main.insertTableRow("secondweights", selectedSecondWeight.toList());
                Report aReport = new Report("dockets/" + selectedSecondWeight.getCode() + ".pdf");
                ArrayList<String> reportContent = new ArrayList<>(Arrays.asList("Weight Type: " + selectedDocketType.getDocketType(),
                "Consignee: " + selectedConsignee.toString(), "Driver: " + selectedDriver.toString(), "Commodity: " + selectedCommodity.toString(),
                "First Weight Sequence Number: " + selectedFirstWeight.getCode(), "First Weight Actual Weight: " + selectedFirstWeight.getWeight(),
                "First Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedFirstWeight.getDate()),
                "Second Weight Sequence Number: " + selectedSecondWeight.getCode(), "Second Weight Actual Weight: " + selectedSecondWeight.getWeight(),
                "Second Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedSecondWeight.getDate())));
                if(selectedDocketType.getCode() == 1)
                    reportContent.add("Total Weight: " + (selectedFirstWeight.getWeight() - selectedSecondWeight.getWeight()));
                else if(selectedDocketType.getCode() == 2)
                    reportContent.add("Total Weight: " + (selectedSecondWeight.getWeight() - selectedFirstWeight.getWeight()));
                aReport.addContent(reportContent);
                selectDocket();
            });
            secondWeightsPanel.add(currentButton);
        });
        if(firstWeightsAwaitingSecondWeight.size() == 0)
        {
            JButton returnToMainMenuTile = createTile("Return To Main Menu", "", 1);
            returnToMainMenuTile.addActionListener(((y) -> selectWeightType()));
            secondWeightsPanel.add(returnToMainMenuTile);
        }
        addComponent(secondWeightsPanel);
    }
    public void selectDocket()
    {
        JPanel processDocketPanel = new JPanel(new GridLayout(0, 3));
        JButton printDocketTile = createTile("Print Docket", "", 3);
        printDocketTile.addActionListener((x) ->
        {
            new Printer("dockets/" + selectedSecondWeight.getCode() + ".pdf");
            selectWeightType();
        });
        JButton emailDocketTile = createTile("Email Docket", "", 3);
        emailDocketTile.addActionListener((x) ->
        {
            JPanel emailDocketPanel = new JPanel(new GridLayout(3, 1));
            JTextField emailAddressTextField = createTextField("");
            emailDocketPanel.add(createLabel("Please enter your email address"));
            emailDocketPanel.add(emailAddressTextField);
            JButton emailDocketButton = createButton("Submit Your Email Address");
            emailDocketButton.addActionListener((y) ->
            {
                if (emailAddressTextField.getText().length() > 2) {
                    Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
                    anEmail.sendMessage(emailAddressTextField.getText(), "Weight Docket " + selectedSecondWeight.getCode(),
                    "Dear Sir/Madam\n\nPlease find attached the weight docket for " + selectedSecondWeight.getCode() + ".\n\nYours sincerely,\nS Cullinan",
                    "dockets/" + selectedSecondWeight.getCode() + ".pdf", selectedSecondWeight.getCode() + ".pdf");
                    selectWeightType();
                }
            });
            emailDocketPanel.add(emailDocketButton);
            addComponent(emailDocketPanel);
        });
        JButton returnToMainMenuTile = createTile("Return To Main Menu", "", 3);
        returnToMainMenuTile.addActionListener((x) ->
        {
            selectWeightType();
        });
        processDocketPanel.add(printDocketTile);
        processDocketPanel.add(emailDocketTile);
        processDocketPanel.add(returnToMainMenuTile);
        addComponent(processDocketPanel);
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
