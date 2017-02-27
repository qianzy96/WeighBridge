package Models;
import Entities.CommodityPrices;
import Frames.Components;
import Utilities.*;
import org.jfree.data.category.DefaultCategoryDataset;
import Database.*;
import javax.swing.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dashboard extends Components
{
    private HashMap<String, CommodityPrices> commodityPrices;
    private String fileName;
    private String userID;
    public Dashboard(String userID)
    {
        this.commodityPrices = new HashMap<>();
        this.fileName = "";
        this.userID = userID;
    }
    public HashMap<String, CommodityPrices> getCommodityPrices()
    {
        return commodityPrices;
    }
    public String getUserID()
    {
        return userID;
    }
    public void addDashboard()
    {
        try
        {
            RetrieveContent noggersPage = new RetrieveContent("http://www.noggersblog.co.uk/prices/marketinfo/index2.htm");
            String webPage = noggersPage.getText();
            String desiredContent = webPage.substring(webPage.indexOf("<!--document.write(unescape('") + 29);
            String formattedResult = URLDecoder.decode(desiredContent.substring(0, desiredContent.length() - 32), "UTF-8");
            ArrayList<String> links = Utilities.extractAttributeOfTags(formattedResult, "<iframe src=\".*?\".*?></iframe>", "src");
            if (links.size() > 5)
                for (int index = 0; index < 5; index++)
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
        catch (Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public DefaultCategoryDataset generateDataForChart(CommodityPrices aCommodity)
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
    public void generatePDFFile()
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
    public void emailPDFFile(String emailAddress)
    {
        if(fileName.length() == 0)
            generatePDFFile();
        Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
        anEmail.sendMessage(emailAddress, "Prices Report","Dear Sir/Madam, Please find attached the report on the prices. If you have any further " +
        "questions please do not hesitate to contact me. Yours sincerely, Stephen Cullinan", "prices/" + fileName, fileName);
    }
    public void printPDFFile()
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