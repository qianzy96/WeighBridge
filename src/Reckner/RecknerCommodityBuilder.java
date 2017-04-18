package Reckner;

public class RecknerCommodityBuilder {
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

    public RecknerCommodityBuilder setCode(int code) {
        this.code = code;
        return this;
    }

    public RecknerCommodityBuilder setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
        return this;
    }

    public RecknerCommodityBuilder setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
        return this;
    }

    public RecknerCommodityBuilder setDm(Double dm) {
        this.dm = dm;
        return this;
    }

    public RecknerCommodityBuilder setAsh(Double ash) {
        this.ash = ash;
        return this;
    }

    public RecknerCommodityBuilder setCrudeProtein(Double crudeProtein) {
        this.crudeProtein = crudeProtein;
        return this;
    }

    public RecknerCommodityBuilder setCrudeFibre(Double crudeFibre) {
        this.crudeFibre = crudeFibre;
        return this;
    }

    public RecknerCommodityBuilder setOil(Double oil) {
        this.oil = oil;
        return this;
    }

    public RecknerCommodityBuilder setNdf(Double ndf) {
        this.ndf = ndf;
        return this;
    }

    public RecknerCommodityBuilder setAdf(Double adf) {
        this.adf = adf;
        return this;
    }

    public RecknerCommodityBuilder setEffectiveNdf(Double effectiveNdf) {
        this.effectiveNdf = effectiveNdf;
        return this;
    }

    public RecknerCommodityBuilder setOmd(Double omd) {
        this.omd = omd;
        return this;
    }

    public RecknerCommodityBuilder setPdia(Double pdia) {
        this.pdia = pdia;
        return this;
    }

    public RecknerCommodityBuilder setPdin(Double pdin) {
        this.pdin = pdin;
        return this;
    }

    public RecknerCommodityBuilder setPdie(Double pdie) {
        this.pdie = pdie;
        return this;
    }

    public RecknerCommodityBuilder setStarch(Double starch) {
        this.starch = starch;
        return this;
    }

    public RecknerCommodityBuilder setSugar(Double sugar) {
        this.sugar = sugar;
        return this;
    }

    public RecknerCommodityBuilder setUfl(Double ufl) {
        this.ufl = ufl;
        return this;
    }

    public RecknerCommodityBuilder setUfv(Double ufv) {
        this.ufv = ufv;
        return this;
    }

    public RecknerCommodityBuilder setLysdi(Double lysdi) {
        this.lysdi = lysdi;
        return this;
    }

    public RecknerCommodityBuilder setMethdi(Double methdi) {
        this.methdi = methdi;
        return this;
    }

    public RecknerCommodityBuilder setCa(Double ca) {
        this.ca = ca;
        return this;
    }

    public RecknerCommodityBuilder setP(Double p) {
        this.p = p;
        return this;
    }

    public RecknerCommodityBuilder setMg(Double mg) {
        this.mg = mg;
        return this;
    }

    public RecknerCommodityBuilder setNa(Double na) {
        this.na = na;
        return this;
    }

    public RecknerCommodityBuilder setCu(Double cu) {
        this.cu = cu;
        return this;
    }

    public RecknerCommodityBuilder setZn(Double zn) {
        this.zn = zn;
        return this;
    }

    public RecknerCommodityBuilder setMn(Double mn) {
        this.mn = mn;
        return this;
    }

    public RecknerCommodityBuilder setCo(Double co) {
        this.co = co;
        return this;
    }

    public RecknerCommodityBuilder setSe(Double se) {
        this.se = se;
        return this;
    }

    public RecknerCommodityBuilder setI(Double i) {
        this.i = i;
        return this;
    }

    public RecknerCommodityBuilder setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
        return this;
    }

    public RecknerCommodityBuilder setVitaminD(Double vitaminD) {
        this.vitaminD = vitaminD;
        return this;
    }

    public RecknerCommodityBuilder setVitaminE(Double vitaminE) {
        this.vitaminE = vitaminE;
        return this;
    }

    public RecknerCommodityBuilder setPal(Double pal) {
        this.pal = pal;
        return this;
    }

    public RecknerCommodityBuilder setMe(Double me) {
        this.me = me;
        return this;
    }

    public RecknerCommodity createRecknerCommodity() {
        return new RecknerCommodity(code, commodityTitle, commodityPrice, dm, ash, crudeProtein, crudeFibre, oil, ndf, adf, effectiveNdf, omd, pdia, pdin, pdie, starch, sugar, ufl, ufv, lysdi, methdi, ca, p, mg, na, cu, zn, mn, co, se, i, vitaminA, vitaminD, vitaminE, pal, me);
    }
}