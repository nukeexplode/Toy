package com.julio.domain.dto;

/**
 * @author Julio
 * @date 2020/11/11-11:10
 * @Description 对每个物种的属、品种关系及数据量统计等进行封装的DTO类，供search、info页面以及热点物种区使用
 **/
public class SpeciesInfoDTO {
    private Integer id; //数据库id
    private String genus;  //属
    private String ref; //该属SSR的参考物种
    private String specie; //当前info展示的物种
    private String species; //该属的所有物种
    private String refcultivar; //该物种的参考品种
    private String cultivars;//该物种的所有品种
    private String spgenotype; //参考物种基因型
    private String cvgenotype; //参考品种基因型
    private String angiosperm; //单双子叶类型
    private int sperecord; //物种别的SSR记录数(属-物种)
    private int culrecord; //品种级别的SSR记录数(物种-品种)
    private String img; //物种图片的url
    private String spsite; //参考物种基因型的来源站点（站点logo）
    private String cvsite; //参考品种基因型的来源站点（站点logo）
    private String spsiteurl; //参考物种基因型的来源站点网址
    private String cvsiteurl; //参考品种基因型的来源站点网址
    private String des; //该属的描述

    public SpeciesInfoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpeciesInfoDTO{" +
                "id=" + id +
                ", genus='" + genus + '\'' +
                ", ref='" + ref + '\'' +
                ", specie='" + specie + '\'' +
                ", species='" + species + '\'' +
                ", refcultivar='" + refcultivar + '\'' +
                ", cultivars='" + cultivars + '\'' +
                ", spgenotype='" + spgenotype + '\'' +
                ", cvgenotype='" + cvgenotype + '\'' +
                ", angiosperm='" + angiosperm + '\'' +
                ", sperecord=" + sperecord +
                ", culrecord=" + culrecord +
                ", img='" + img + '\'' +
                ", spsite='" + spsite + '\'' +
                ", cvsite='" + cvsite + '\'' +
                ", spsiteurl='" + spsiteurl + '\'' +
                ", cvsiteurl='" + cvsiteurl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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


    public String getAngiosperm() {
        return angiosperm;
    }

    public void setAngiosperm(String angiosperm) {
        this.angiosperm = angiosperm;
    }

    public int getSperecord() {
        return sperecord;
    }

    public void setSperecord(int sperecord) {
        this.sperecord = sperecord;
    }

    public int getCulrecord() {
        return culrecord;
    }

    public void setCulrecord(int culrecord) {
        this.culrecord = culrecord;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSpgenotype() {
        return spgenotype;
    }

    public void setSpgenotype(String spgenotype) {
        this.spgenotype = spgenotype;
    }

    public String getCvgenotype() {
        return cvgenotype;
    }

    public void setCvgenotype(String cvgenotype) {
        this.cvgenotype = cvgenotype;
    }

    public String getSpsite() {
        return spsite;
    }

    public void setSpsite(String spsite) {
        this.spsite = spsite;
    }

    public String getCvsite() {
        return cvsite;
    }

    public void setCvsite(String cvsite) {
        this.cvsite = cvsite;
    }

    public String getSpsiteurl() {
        return spsiteurl;
    }

    public void setSpsiteurl(String spsiteurl) {
        this.spsiteurl = spsiteurl;
    }

    public String getCvsiteurl() {
        return cvsiteurl;
    }

    public void setCvsiteurl(String cvsiteurl) {
        this.cvsiteurl = cvsiteurl;
    }
}
