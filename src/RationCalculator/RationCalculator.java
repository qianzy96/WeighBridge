package RationCalculator;

import java.util.*;

public class RationCalculator
{
    private HashMap<Ration, Double> feedCosts;
    private Double totalFreshIntake;
    private String title;
    private Date date;
    private int numberOfPlaces;
    private String currencySymbol;
    public RationCalculator(HashMap<Ration, Double> feedCosts, String title)
    {
        this.feedCosts = feedCosts;
        this.totalFreshIntake = 0.0;
        this.title = title;
        this.date = new Date();
        calculateTotalFreshIntake();
        initialiseVariables();
    }
    public RationCalculator(String title)
    {
        this.feedCosts = new HashMap<>();
        this.totalFreshIntake = 0.0;
        this.title = title;
        this.date = new Date();
        calculateTotalFreshIntake();
        initialiseVariables();
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public HashMap<Ration, Double> getFeedCosts()
    {
        return feedCosts;
    }
    public void setFeedCosts(HashMap<Ration, Double> feedCosts)
    {
        this.feedCosts = feedCosts;
        calculateTotalFreshIntake();
    }
    private void initialiseVariables()
    {
        Database.Database database = new Database.Database();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("title", "Number Of Places");
        ArrayList<ArrayList<String>> selectedTableRows = database.getTableRows("settings", parameters, new ArrayList<>(Collections.singletonList("value")),
        "");
        if(selectedTableRows.size() > 0)
            numberOfPlaces = Integer.parseInt(selectedTableRows.get(0).get(0));
        parameters.put("title", "Currency Symbol");
        selectedTableRows = database.getTableRows("settings", parameters, new ArrayList<>(Collections.singletonList("value")),"");
        if(selectedTableRows.size() > 0)
            currencySymbol = selectedTableRows.get(0).get(0);
    }
    private void calculateTotalFreshIntake()
    {
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            totalFreshIntake += (Double)aFeedCost.getValue();
    }
    public Double getTotalFreshIntake()
    {
        return totalFreshIntake;
    }
    public Double calculateDryMatter()
    {
        Double dryMatter = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            dryMatter += Double.parseDouble(((Ration) aFeedCost.getKey()).getDm()) * ((Double) aFeedCost.getValue() / totalFreshIntake);
        return dryMatter;
    }
    public Double calculateCrudeProtein()
    {
        Double crudeProtein = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            crudeProtein += Double.parseDouble(((Ration)aFeedCost.getKey()).getCp()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return crudeProtein;
    }
    public Double calculateNDF()
    {
        Double ndf = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            ndf += Double.parseDouble(((Ration)aFeedCost.getKey()).getNdf()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return ndf;
    }
    public Double calculateStarchAndSugars()
    {
        Double starchAndSugars = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            starchAndSugars += (Double.parseDouble(((Ration)aFeedCost.getKey()).getStarch()) + Double.parseDouble(((Ration)aFeedCost.getKey()).getSugar())) *
            ((Double)aFeedCost.getValue() / totalFreshIntake);
        return starchAndSugars;
    }
    public Double calculateOil()
    {
        Double oil = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            oil += Double.parseDouble(((Ration)aFeedCost.getKey()).getOil()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return oil;
    }
    public Double calculateRationCostDryMatter()
    {
        Double rationCostDryMatter = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            rationCostDryMatter += Double.parseDouble(((Ration)aFeedCost.getKey()).getCostDryMatter().substring(1)) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return rationCostDryMatter;
    }
    public Double calculateRationCostFreshWeight()
    {
        Double rationCostFreshWeight = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            rationCostFreshWeight += Double.parseDouble(((Ration)aFeedCost.getKey()).getCostFreshWeight().substring(1)) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return rationCostFreshWeight;
    }
}