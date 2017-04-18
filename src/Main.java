import Frames.BrowserFrame;
import Frames.WeighBridgeFrame;
import Models.WeighBridge;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        /*Database.Database mainDatabase = new Database.Database();
        ArrayList<ArrayList<String>> mainDetails = mainDatabase.getTableRows("drivers", new HashMap<>(), new ArrayList<>());
        mainDetails.forEach(x -> x.forEach(y -> System.out.println(y)));
        mainDatabase.insertTableRow("drivers", new ArrayList<String>(Arrays.asList(new String[]{"4", "Simon", "Conway"})));
        ArrayList<String> tableTitles = mainDatabase.getColumnTitles("commodities");
        System.out.println("TABLE TITLES");
        tableTitles.forEach(x -> System.out.println(x));*/
        /*SwingUtilities.invokeLater(() ->
        {
            Models.AdministrationPage anAdministration = new Models.AdministrationPage();
            anAdministration.createRibbon();
            //aFrame.add(aComponent.createTable(""));
        });*/
        /*SwingUtilities.invokeLater(() ->
        {
            AdministratorLogOn anAdministrationLogOn = new AdministratorLogOn();
            anAdministrationLogOn.createLogOnDialogBox();
        });*/
        /*SwingUtilities.invokeLater(() ->
        {
            new WeighBridgeFrame();
            //WeighBridge aWeighBridge = new WeighBridge();
            //aWeighBridge.selectWeightType();
        });*/
        SwingUtilities.invokeLater(() ->
        {
            new BrowserFrame();
        });


        /*SwingUtilities.invokeLater(() ->
        {
            new WeighBridgeFrame();
        });*/
    }
}
