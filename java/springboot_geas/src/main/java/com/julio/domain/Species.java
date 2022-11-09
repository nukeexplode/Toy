package com.julio.domain;

/**
 * @author Julio
 * @date 2020/11/26-19:11
 * @Description 物种-品种映射实体类
 **/
public class Species {
    private Integer id; //数据库id
    private String genus; //属名
    private String specie; //物种名
    private String refcultivar; //参考品种名
    private String cultivars; //该物种的所有品种
    private String angiosperm;  //被子植物类型
    private String site;    //基因组数据来源站点
    private String siteurl; //站点网址
    private String genotype; //参考基因组

    public Species() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getRefcultivar() {
        return refcultivar;
    }

    public void setRefcultivar(String refcultivar) {
        this.refcultivar = refcultivar;
    }

    public String getCultivars() {
        return cultivars;
    }

    public void setCultivars(String cultivars) {
        this.cultivars = cultivars;
    }

    public String getAngiosperm() {
        return angiosperm;
    }

    public void setAngiosperm(String angiosperm) {
        this.angiosperm = angiosperm;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", genus='" + genus + '\'' +
                ", specie='" + specie + '\'' +
                ", refcultivar='" + refcultivar + '\'' +
                ", cultivars='" + cultivars + '\'' +
                ", angiosperm='" + angiosperm + '\'' +
                ", site='" + site + '\'' +
                ", siteurl='" + siteurl + '\'' +
                ", genotype='" + genotype + '\'' +
                '}';
    }
}
