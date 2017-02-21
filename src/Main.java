import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.out;

public class Main
{
    public static void main(String[] args)
    {
        /*Database mainDatabase = new Database();
        ArrayList<ArrayList<String>> mainDetails = mainDatabase.getTableRows("drivers", new HashMap<>(), new ArrayList<>());
        mainDetails.forEach(x -> x.forEach(y -> System.out.println(y)));
        mainDatabase.insertTableRow("drivers", new ArrayList<String>(Arrays.asList(new String[]{"4", "Simon", "Conway"})));
        ArrayList<String> tableTitles = mainDatabase.getColumnTitles("commodities");
        System.out.println("TABLE TITLES");
        tableTitles.forEach(x -> System.out.println(x));*/
        /*SwingUtilities.invokeLater(() ->
        {
            Administration anAdministration = new Administration();
            anAdministration.createRibbon();
            //aFrame.add(aComponent.createTable(""));
        });*/
        /*SwingUtilities.invokeLater(() ->
        {
            AdministratorLogOn anAdministrationLogOn = new AdministratorLogOn();
            anAdministrationLogOn.createLogOnDialogBox();
        });*/
        SwingUtilities.invokeLater(() ->
        {
            WeighBridge aWeighBridge = new WeighBridge();
            //aWeighBridge.selectWeightType();
        });
    }
}
