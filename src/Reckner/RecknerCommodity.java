package Reckner;

import java.util.ArrayList;
import java.util.Arrays;

public class RecknerCommodity
{
    private int code;
    private String commodityTitle;
    private Double commodityPrice;
    private Double dm;
    private Double ash;
    private Double crudeProtein;
    private Double crudeFibre;
    private Double oil;
    private Double ndf;
    private Double adf;
    private Double effectiveNdf;
    private Double omd;
    private Double pdia;
    private Double pdin;
    private Double pdie;
    private Double starch;
    private Double sugar;
    private Double ufl;
    private Double ufv;
    private Double lysdi;
    private Double methdi;
    private Double ca;
    private Double p;
    private Double mg;
    private Double na;
    private Double cu;
    private Double zn;
    private Double mn;
    private Double co;
    private Double se;
    private Double i;
    private Double vitaminA;
    private Double vitaminD;
    private Double vitaminE;
    private Double pal;
    private Double me;

    public RecknerCommodity(int code, String commodityTitle, Double commodityPrice, Double dm, Double ash, Double crudeProtein, Double crudeFibre, Double oil,
                            Double ndf, Double adf, Double effectiveNdf, Double omd, Double pdia, Double pdin, Double pdie, Double starch, Double sugar, Double ufl,
                            Double ufv, Double lysdi, Double methdi, Double ca, Double p, Double mg, Double na, Double cu, Double zn, Double mn, Double co, Double se,
                            Double i, Double vitaminA, Double vitaminD, Double vitaminE, Double pal, Double me)
    {
        this.code = code;
        this.commodityTitle = commodityTitle;
        this.commodityPrice = commodityPrice;
        this.dm = dm;
        this.ash = ash;
        this.crudeProtein = crudeProtein;
        this.crudeFibre = crudeFibre;
        this.oil = oil;
        this.ndf = ndf;
        this.adf = adf;
        this.effectiveNdf = effectiveNdf;
        this.omd = omd;
        this.pdia = pdia;
        this.pdin = pdin;
        this.pdie = pdie;
        this.starch = starch;
        this.sugar = sugar;
        this.ufl = ufl;
        this.ufv = ufv;
        this.lysdi = lysdi;
        this.methdi = methdi;
        this.ca = ca;
        this.p = p;
        this.mg = mg;
        this.na = na;
        this.cu = cu;
        this.zn = zn;
        this.mn = mn;
        this.co = co;
        this.se = se;
        this.i = i;
        this.vitaminA = vitaminA;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.pal = pal;
        this.me = me;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getCommodityTitle()
    {
        return commodityTitle;
    }
    public void setCommodityTitle(String commodityTitle)
    {
        this.commodityTitle = commodityTitle;
    }
    public Double getCommodityPrice()
    {
        return commodityPrice;
    }
    public void setCommodityPrice(Double commodityPrice)
    {
        this.commodityPrice = commodityPrice;
    }
    public Double getDm()
    {
        return dm;
    }
    public void setDm(Double dm)
    {
        this.dm = dm;
    }
    public Double getAsh()
    {
        return ash;
    }
    public void setAsh(Double ash)
    {
        this.ash = ash;
    }
    public Double getCrudeProtein()
    {
        return crudeProtein;
    }
    public void setCrudeProtein(Double crudeProtein)
    {
        this.crudeProtein = crudeProtein;
    }
    public Double getCrudeFibre()
    {
        return crudeFibre;
    }
    public void setCrudeFibre(Double crudeFibre)
    {
        this.crudeFibre = crudeFibre;
    }
    public Double getOil()
    {
        return oil;
    }
    public void setOil(Double oil)
    {
        this.oil = oil;
    }
    public Double getNdf()
    {
        return ndf;
    }
    public void setNdf(Double ndf)
    {
        this.ndf = ndf;
    }
    public Double getAdf()
    {
        return adf;
    }
    public void setAdf(Double adf)
    {
        this.adf = adf;
    }
    public Double getEffectiveNdf()
    {
        return effectiveNdf;
    }
    public void setEffectiveNdf(Double effectiveNdf)
    {
        this.effectiveNdf = effectiveNdf;
    }
    public Double getOmd()
    {
        return omd;
    }
    public void setOmd(Double omd)
    {
        this.omd = omd;
    }
    public Double getPdia()
    {
        return pdia;
    }
    public void setPdia(Double pdia)
    {
        this.pdia = pdia;
    }
    public Double getPdin()
    {
        return pdin;
    }
    public void setPdin(Double pdin)
    {
        this.pdin = pdin;
    }
    public Double getPdie()
    {
        return pdie;
    }
    public void setPdie(Double pdie)
    {
        this.pdie = pdie;
    }
    public Double getStarch()
    {
        return starch;
    }
    public void setStarch(Double starch)
    {
        this.starch = starch;
    }
    public Double getSugar()
    {
        return sugar;
    }
    public void setSugar(Double sugar)
    {
        this.sugar = sugar;
    }
    public Double getUfl()
    {
        return ufl;
    }
    public void setUfl(Double ufl)
    {
        this.ufl = ufl;
    }
    public Double getUfv()
    {
        return ufv;
    }
    public void setUfv(Double ufv)
    {
        this.ufv = ufv;
    }
    public Double getLysdi()
    {
        return lysdi;
    }
    public void setLysdi(Double lysdi)
    {
        this.lysdi = lysdi;
    }
    public Double getMethdi()
    {
        return methdi;
    }
    public void setMethdi(Double methdi)
    {
        this.methdi = methdi;
    }
    public Double getCa()
    {
        return ca;
    }
    public void setCa(Double ca)
    {
        this.ca = ca;
    }
    public Double getP()
    {
        return p;
    }
    public void setP(Double p)
    {
        this.p = p;
    }
    public Double getMg()
    {
        return mg;
    }
    public void setMg(Double mg)
    {
        this.mg = mg;
    }
    public Double getNa()
    {
        return na;
    }
    public void setNa(Double na)
    {
        this.na = na;
    }
    public Double getCu()
    {
        return cu;
    }
    public void setCu(Double cu)
    {
        this.cu = cu;
    }
    public Double getZn()
    {
        return zn;
    }
    public void setZn(Double zn)
    {
        this.zn = zn;
    }
    public Double getMn()
    {
        return mn;
    }
    public void setMn(Double mn)
    {
        this.mn = mn;
    }
    public Double getCo()
    {
        return co;
    }
    public void setCo(Double co)
    {
        this.co = co;
    }
    public Double getSe()
    {
        return se;
    }
    public void setSe(Double se)
    {
        this.se = se;
    }
    public Double getI()
    {
        return i;
    }
    public void setI(Double i)
    {
        this.i = i;
    }
    public Double getVitaminA()
    {
        return vitaminA;
    }
    public void setVitaminA(Double vitaminA)
    {
        this.vitaminA = vitaminA;
    }
    public Double getVitaminD()
    {
        return vitaminD;
    }
    public void setVitaminD(Double vitaminD)
    {
        this.vitaminD = vitaminD;
    }
    public Double getVitaminE()
    {
        return vitaminE;
    }
    public void setVitaminE(Double vitaminE)
    {
        this.vitaminE = vitaminE;
    }
    public Double getPal()
    {
        return pal;
    }
    public void setPal(Double pal)
    {
        this.pal = pal;
    }
    public Double getMe()
    {
        return me;
    }
    public void setMe(Double me)
    {
        this.me = me;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", commodityTitle, commodityPrice + "", dm + "", ash + "", crudeProtein + "", crudeFibre + "", oil + "", ndf + "",
        adf + "", effectiveNdf + "", omd + "", pdia + "", pdin + "", pdie + "", starch + "", sugar + "", ufl + "", ufv + "", lysdi + "", methdi + "", ca + "", p + "",
        mg + "", na + "", cu + "", zn + "", mn + "", co + "", se + "", i + "", vitaminA + "", vitaminD + "", vitaminE + "", pal + "", me + ""));
    }
}
