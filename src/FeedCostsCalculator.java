import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FeedCostsCalculator
{
    private HashMap<FeedCosts, Double> feedCosts;
    private Double totalFreshIntake;
    private String title;
    private Date date;
    public FeedCostsCalculator(HashMap<FeedCosts, Double> feedCosts, String title)
    {
        this.feedCosts = feedCosts;
        this.totalFreshIntake = 0.0;
        this.title = title;
        this.date = new Date();
        calculateTotalFreshIntake();
    }
    public FeedCostsCalculator(String title)
    {
        this.feedCosts = new HashMap<>();
        this.totalFreshIntake = 0.0;
        this.title = title;
        this.date = new Date();
        calculateTotalFreshIntake();
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
    public HashMap<FeedCosts, Double> getFeedCosts()
    {
        return feedCosts;
    }
    public void setFeedCosts(HashMap<FeedCosts, Double> feedCosts)
    {
        this.feedCosts = feedCosts;
        calculateTotalFreshIntake();
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
            dryMatter += Double.parseDouble(((FeedCosts) aFeedCost.getKey()).getDm()) * ((Double) aFeedCost.getValue() / totalFreshIntake);
        return dryMatter;
    }
    public Double calculateCrudeProtein()
    {
        Double crudeProtein = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            crudeProtein += Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getCp()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return crudeProtein;
    }
    public Double calculateNDF()
    {
        Double ndf = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            ndf += Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getNdf()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return ndf;
    }
    public Double calculateStarchAndSugars()
    {
        Double starchAndSugars = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            starchAndSugars += (Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getStarch()) + Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getSugar())) *
            ((Double)aFeedCost.getValue() / totalFreshIntake);
        return starchAndSugars;
    }
    public Double calculateOil()
    {
        Double oil = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            oil += Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getOil()) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return oil;
    }
    public Double calculateRationCostDryMatter()
    {
        Double rationCostDryMatter = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            rationCostDryMatter += Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getCostDryMatter().substring(1)) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return rationCostDryMatter;
    }
    public Double calculateRationCostFreshWeight()
    {
        Double rationCostFreshWeight = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            rationCostFreshWeight += Double.parseDouble(((FeedCosts)aFeedCost.getKey()).getCostFreshWeight().substring(1)) * ((Double)aFeedCost.getValue() / totalFreshIntake);
        return rationCostFreshWeight;
    }
}