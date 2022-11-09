package com.julio.domain.ssr;

import java.io.Serializable;

/**
 * @author Julio
 * @date 2020/2/11-0:28
 * @Description 查询SSR提交的表单对象
 **/
public class SsrForm implements Serializable {
    private int type; //几核苷酸重复
    private String motif; //重复基序
    private String genus; //属名
    private String species; //物种名
    private String filterMR; //缺失率过滤器开关
    private String filterStd; //标准差过滤器开关
    private String filterTrans; //转移率过滤器开关
    private String missingType; //缺失率运算符
    private String stdType; //标准差运算符
    private String transType; //转移率运算符
    private String missingRate; //缺失值
    private String stddev; //标准差值
    private String transferability; //转移率值
    private Integer pn; //页码

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFilterMR() {
        return filterMR;
    }

    public void setFilterMR(String filterMR) {
        this.filterMR = filterMR;
    }

    public String getFilterStd() {
        return filterStd;
    }

    public void setFilterStd(String filterStd) {
        this.filterStd = filterStd;
    }

    public String getFilterTrans() {
        return filterTrans;
    }

    public void setFilterTrans(String filterTrans) {
        this.filterTrans = filterTrans;
    }

    public String getMissingType() {
        return missingType;
    }

    public void setMissingType(String missingType) {
        this.missingType = missingType;
    }

    public String getStdType() {
        return stdType;
    }

    public void setStdType(String stdType) {
        this.stdType = stdType;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getMissingRate() {
        return missingRate;
    }

    public void setMissingRate(String missingRate) {
        this.missingRate = missingRate;
    }

    public String getStddev() {
        return stddev;
    }

    public void setStddev(String stddev) {
        this.stddev = stddev;
    }

    public String getTransferability() {
        return transferability;
    }

    public void setTransferability(String transferability) {
        this.transferability = transferability;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    @Override
    public String toString() {
        return "SsrForm{" +
                "type='" + type + '\'' +
                ", motif='" + motif + '\'' +
                ", genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                ", filterMR='" + filterMR + '\'' +
                ", filterStd='" + filterStd + '\'' +
                ", filterTrans='" + filterTrans + '\'' +
                ", missingType='" + missingType + '\'' +
                ", stdType='" + stdType + '\'' +
                ", transType='" + transType + '\'' +
                ", missingRate='" + missingRate + '\'' +
                ", stddev='" + stddev + '\'' +
                ", transferability='" + transferability + '\'' +
                ", pn=" + pn +
                '}';
    }
}
