package com.julio.domain;

/**
 * @author Julio
 * @date 2020/10/27-19:02
 * @Description 属-物种关系映射表的实体类
 **/
public class Genus {
    private Integer id; //数据库id号
    private String genus; //属名
    private String ref; //该属的参考物种
    private String species; //所有物种名
    private String angiosperm;  //被子植物类型
    private String img; //图片地址
    private String site;    //基因组数据来源站点
    private String siteurl; //站点网址
    private String genotype; //参考基因组型
    private String hot; //是否为热点物种
    private String des; //该属的科普描述

    public Genus() {
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAngiosperm() {
        return angiosperm;
    }

    public void setAngiosperm(String angiosperm) {
        this.angiosperm = angiosperm;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }


    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
