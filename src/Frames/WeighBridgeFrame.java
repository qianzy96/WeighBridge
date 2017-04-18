package Frames;

import WeighBridge.*;
import Models.WeighBridge;
import Utilities.ObtainPhoto;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WeighBridgeFrame extends Components
{
    private JFrame frame;
    private JComponent component;
    /*private DocketType selectedDocketType;
    private Consignee selectedConsignee;
    private Driver selectedDriver;
    private Commodity selectedCommodity;
    private FirstWeight selectedFirstWeight;
    private SecondWeight selectedSecondWeight;*/
    private WeighBridge aWeighBridge;
    public WeighBridgeFrame()
    {
        try
        {
            UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
        aWeighBridge = new WeighBridge();
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
        JButton logOnTile = createTile("Log On", "", 4);
        logOnTile.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new LogOnFrame();
                frame.dispose();
            });
        });
        aPanel.add(logOnTile);
        JButton registrationTile = createTile("Registration", "", 4);
        registrationTile.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new RegistrationFrame();
                frame.dispose();
            });
        });
        aPanel.add(registrationTile);
        addComponent(aPanel);
    }
    public void selectWeightDocketType()
    {
        ArrayList<DocketType> docketTypesObjects = aWeighBridge.getDocketTypes();
        JPanel docketTypesPanel = new JPanel(new GridLayout(0, 3));
        docketTypesObjects.forEach(z ->
        {
            JButton aDocketType = createTile(z.toString(), "", docketTypesObjects.size());
            aDocketType.addActionListener((y) -> {aWeighBridge.setSelectedDocketType(z); selectConsignee();});
            docketTypesPanel.add(aDocketType);
        });
        addComponent(docketTypesPanel);
    }
    public void selectConsignee()
    {
        JPanel consigneesPanel = new JPanel(new GridLayout(0, 3));
        ArrayList<Consignee> consigneesObjects = new ArrayList<>();
        final int numberOfConsignees = consigneesObjects.size();
        if(aWeighBridge.getSelectedDocketType().getCode() == 1)
        {
            consigneesObjects = aWeighBridge.getConsignees("suppliers");
            JButton addNewSupplierTile = createTile("Add New Supplier", "", numberOfConsignees + 1);
            addNewSupplierTile.addActionListener((x) -> addNewSupplier());
            consigneesPanel.add(addNewSupplierTile);
        }
        else if(aWeighBridge.getSelectedDocketType().getCode() == 2)
        {
            consigneesObjects = aWeighBridge.getConsignees("customers");
            JButton addNewCustomerTile = createTile("Add New Customer", "", numberOfConsignees + 1);
            addNewCustomerTile.addActionListener((x) -> addNewCustomer());
            consigneesPanel.add(addNewCustomerTile);
        }
        consigneesObjects.forEach(x ->
        {
            JButton aConsignee = createTile(x.toString(), "", numberOfConsignees + 1);
            aConsignee.addActionListener(y -> {aWeighBridge.setSelectedConsignee(x); selectDriver();});
            consigneesPanel.add(aConsignee);
        });
        addComponent(consigneesPanel);
    }
    public void selectDriver()
    {
        ArrayList<Driver> driverObjects = aWeighBridge.getDrivers();
        JPanel driversPanel = new JPanel(new GridLayout(0, 3));
        JButton createNewDriver = createTile("Add New Driver", "", driverObjects.size() + 1);
        createNewDriver.addActionListener((x) -> addNewDriver());
        driversPanel.add(createNewDriver);
        driverObjects.forEach(x ->
        {
            JButton aDriver = createTile(x.toString(), "users/" + x.getCode() + ".png", driverObjects.size() + 1);
            aDriver.addActionListener((y) -> {aWeighBridge.setSelectedDriver(x); selectCommodity();});
            driversPanel.add(aDriver);
        });
        addComponent(driversPanel);
    }
    public void addNewDriver()
    {
        JPanel addNewDriversPanel = new JPanel(new GridLayout(5, 1));
        JTextField firstName = createTextField("");
        JTextField lastName = createTextField("");
        JButton addDriverButton = createButton("Add New Driver");
        addDriverButton.addActionListener((x) ->
        {
            if(firstName.getText().length() > 1 && lastName.getText().length() > 1)
            {
                Driver aDriver = aWeighBridge.insertNewDriver(firstName.getText(), lastName.getText());
                JPanel obtainPhotoPanel = new JPanel(new GridLayout(2, 1));
                JProgressBar obtainPhotoProgressBar = createProgressBar(0, 100);
                JLabel obtainPhotoLabel = createLabel("Initialising The WebCam");
                obtainPhotoPanel.add(obtainPhotoLabel);
                obtainPhotoPanel.add(obtainPhotoProgressBar);
                addComponent(obtainPhotoPanel);
                ObtainPhoto obtainPhoto = new ObtainPhoto(obtainPhotoLabel, obtainPhotoProgressBar, "images/users/" + aDriver.getCode() + ".png");
                obtainPhoto.execute();
                obtainPhoto.addPropertyChangeListener(y ->
                {
                    if(y.getNewValue().toString().contains("DONE") && y.getPropertyName().toString().contains("state"))
                        selectCommodity();
                });
            }
            else
                addNewDriver();
        });
        addNewDriversPanel.add(createLabel("Please enter the first name of the driver"));
        addNewDriversPanel.add(firstName);
        addNewDriversPanel.add(createLabel("Please enter the last name of the driver"));
        addNewDriversPanel.add(lastName);
        addNewDriversPanel.add(addDriverButton);
        addComponent(addNewDriversPanel);
    }
    public void addNewCommodity()
    {
        JPanel addNewCommodityPanel = new JPanel(new GridLayout(3, 1));
        JTextField commodity = createTextField("");
        JButton addCommodityButton = createButton("Add New Entities.Commodity");
        addCommodityButton.addActionListener((x) ->
        {
            if(commodity.getText().length() > 1)
            {
                aWeighBridge.insertNewCommodity(commodity.getText());
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
                aWeighBridge.insertNewConsignee(customerFirstName.getText(), customerLastName.getText(), "customers");
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
                aWeighBridge.insertNewConsignee(supplierFirstName.getText(), supplierLastName.getText(), "suppliers");
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
        ArrayList<Commodity> commoditiesObjects = aWeighBridge.getCommodities();
        JPanel commoditiesPanel = new JPanel(new GridLayout(0, 3));
        JButton addNewCommodityButton = createTile("Add New Commodity", "", commoditiesObjects.size() + 1);
        addNewCommodityButton.addActionListener(x -> addNewCommodity());
        commoditiesPanel.add(addNewCommodityButton);
        commoditiesObjects.forEach(x ->
        {
            JButton aCommodity = createTile(x.getTitle(), "commodities/" + x.getCode(), commoditiesObjects.size() + 1);
            aCommodity.addActionListener(y -> {aWeighBridge.setSelectedCommodity(x); selectFirstWeight();});
            commoditiesPanel.add(aCommodity);
        });
        addComponent(commoditiesPanel);
    }
    public void selectFirstWeight()
    {
        aWeighBridge.insertNewFirstWeight();
        selectWeightType();
    }
    public void selectSecondWeight()
    {
        ArrayList<ArrayList<String>> firstWeightsAwaitingSecondWeight = aWeighBridge.getDocketsAwaitingSecondWeightment();
        JPanel secondWeightsPanel = new JPanel(new GridLayout(0, 3));
        firstWeightsAwaitingSecondWeight.forEach(x ->
        {
            aWeighBridge.retrieveParametersForSelectedSecondWeight(x.get(1), x.get(2), x.get(5), x.get(6));
            JButton currentButton = createTile(aWeighBridge.getCaptionTitle(), "", firstWeightsAwaitingSecondWeight.size());
            currentButton.addActionListener((y) ->
            {
                aWeighBridge.insertNewSecondWeight(x.get(0), x.get(3), x.get(4));
                aWeighBridge.generateReport();
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
            aWeighBridge.printReport();
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
                if (emailAddressTextField.getText().length() > 2)
                {
                    aWeighBridge.emailReport(emailAddressTextField.getText());
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
