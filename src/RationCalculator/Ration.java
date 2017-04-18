package RationCalculator;
import java.util.*;
public class Ration
{
    private int code;
    private String feed;
    private String costFreshWeight;
    private String costDryMatter;
    private String pMj;
    private String pcp;
    private String dm;
    private String me;
    private String cp;
    private String oil;
    private String ndf;
    private String starch;
    private String sugar;
    private String feedingNotes;
    public Ration(int code, String feed, String costFreshWeight, String costDryMatter, String pMj, String pcp, String dm, String me, String cp, String oil,
                     String ndf, String starch, String sugar, String feedingNotes)
    {
        this.code = code;
        this.feed = feed.trim();
        this.costFreshWeight = costFreshWeight;
        this.costDryMatter = costDryMatter;
        this.pMj = pMj;
        this.pcp = pcp;
        this.dm = dm;
        this.me = me;
        this.cp = cp;
        this.oil = oil;
        this.ndf = ndf;
        this.starch = starch;
        this.sugar = sugar;
        this.feedingNotes = feedingNotes;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", feed, costFreshWeight, costDryMatter, pMj, pcp, dm, me, cp, oil, ndf, starch, sugar, feedingNotes));
    }
    public LinkedHashMap<String, String> toMap()
    {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("Code", code + "");
        parameters.put("Feed", feed);
        parameters.put("Cost Fresh Weight", costFreshWeight);
        parameters.put("Cost Dry Matter", costDryMatter);
        parameters.put("PMJ", pMj);
        parameters.put("PCP", pcp);
        parameters.put("DM", dm);
        parameters.put("ME", me);
        parameters.put("CP", cp);
        parameters.put("Oil", oil);
        parameters.put("NDF", ndf);
        parameters.put("Starch", starch);
        parameters.put("Sugar", sugar);
        parameters.put("Feeding Notes", feedingNotes);
        return parameters;
    }
    public String toString()
    {
        return this.feed;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getFeed()
    {
        return feed;
    }
    public void setFeed(String feed)
    {
        this.feed = feed;
    }
    public String getCostFreshWeight()
    {
        return costFreshWeight;
    }
    public void setCostFreshWeight(String costFreshWeight)
    {
        this.costFreshWeight = costFreshWeight;
    }
    public String getCostDryMatter()
    {
        return costDryMatter;
    }
    public void setCostDryMatter(String costDryMatter)
    {
        this.costDryMatter = costDryMatter;
    }
    public String getpMj()
    {
        return pMj;
    }
    public void setpMj(String pMj)
    {
        this.pMj = pMj;
    }
    public String getPcp()
    {
        return pcp;
    }
    public void setPcp(String pcp)
    {
        this.pcp = pcp;
    }
    public String getDm()
    {
        return dm;
    }
    public void setDm(String dm)
    {
        this.dm = dm;
    }
    public String getMe()
    {
        return me;
    }
    public void setMe(String me)
    {
        this.me = me;
    }
    public String getCp()
    {
        return cp;
    }
    public void setCp(String cp)
    {
        this.cp = cp;
    }
    public String getOil()
    {
        return oil;
    }
    public void setOil(String oil)
    {
        this.oil = oil;
    }
    public String getNdf()
    {
        return ndf;
    }
    public void setNdf(String ndf)
    {
        this.ndf = ndf;
    }
    public String getStarch()
    {
        return starch;
    }
    public void setStarch(String starch)
    {
        this.starch = starch;
    }
    public String getSugar()
    {
        return sugar;
    }
    public void setSugar(String sugar)
    {
        this.sugar = sugar;
    }
    public String getFeedingNotes()
    {
        return feedingNotes;
    }
    public void setFeedingNotes(String feedingNotes)
    {
        this.feedingNotes = feedingNotes;
    }
}