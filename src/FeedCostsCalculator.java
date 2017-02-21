import java.util.HashMap;
import java.util.Map;
public class FeedCostsCalculator
{
    private HashMap<FeedCosts, Double> feedCosts;
    private Double totalFreshIntake;
    public FeedCostsCalculator(HashMap<FeedCosts, Double> feedCosts)
    {
        this.feedCosts = feedCosts;
        this.totalFreshIntake = 0.0;
        calculateTotalFreshIntake();
    }
    public FeedCostsCalculator()
    {
        this.feedCosts = new HashMap<>();
        this.totalFreshIntake = 0.0;
        calculateTotalFreshIntake();
    }
    public HashMap<FeedCosts, Double> getFeedCosts()
    {
        return feedCosts;
    }
    public void setFeedCosts(HashMap<FeedCosts, Double> feedCosts)
    {
        this.feedCosts = feedCosts;
    }
    private void calculateTotalFreshIntake()
    {
        for(Map.Entry aFeedCost : feedCosts.entrySet())
            totalFreshIntake += (Double)aFeedCost.getValue();
    }
    public Double calculateDryMatter()
    {
        Double dryMatter = 0.0;
        for(Map.Entry aFeedCost : feedCosts.entrySet())
        {
            System.out.println("A FEED COST: " + aFeedCost);
            System.out.println("A FEED COST KEY: "  + aFeedCost.getKey());
            System.out.println("A FEED COST KEY DRY MATTER: " + ((FeedCosts)aFeedCost.getKey()).getDm());
            System.out.println("A FEED COST VALUE: " + aFeedCost.getValue());
            System.out.println("A TOTAL FRESH INTAKE: " + totalFreshIntake);
            dryMatter += Double.parseDouble(((FeedCosts) aFeedCost.getKey()).getDm()) * ((Double) aFeedCost.getValue() / totalFreshIntake);
        }
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
}