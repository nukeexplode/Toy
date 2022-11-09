package com.julio.domain.rna;

import java.io.Serializable;

/**
 * @author Julio
 * @date 2020/2/10-18:50
 * @Description 挖掘SSR时用到的转录组RunInfo数据
 **/
public class Rna implements Serializable {
    private String scientificname; //生物学名
    private String genus; //属名
    private String experiment; //实验编号
    private String bioproject; //项目编号
    private String biosample; //标本编号
    private String run; //NCBI的Run编号
    private String reference; //参考文献
    private String angiosperm; //被子植物类型
    private int pubmed; //文献PubMed编号
    private String download; //NCBI下载地址

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getAngiosperm() {
        return angiosperm;
    }

    public void setAngiosperm(String angiosperm) {
        this.angiosperm = angiosperm;
    }

    @Override
    public String toString() {
        return "Rna{" +
                "scientificname='" + scientificname + '\'' +
                ", genus='" + genus + '\'' +
                ", experiment='" + experiment + '\'' +
                ", bioproject='" + bioproject + '\'' +
                ", biosample='" + biosample + '\'' +
                ", run='" + run + '\'' +
                ", reference='" + reference + '\'' +
                ", angiosperm='" + angiosperm + '\'' +
                ", pubmed=" + pubmed +
                ", download='" + download + '\'' +
                '}';
    }

    public String getScientificname() {
        return scientificname;
    }

    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    public String getExperiment() {
        return experiment;
    }

    public void setExperiment(String experiment) {
        this.experiment = experiment;
    }

    public String getBioproject() {
        return bioproject;
    }

    public void setBioproject(String bioproject) {
        this.bioproject = bioproject;
    }

    public String getBiosample() {
        return biosample;
    }

    public void setBiosample(String biosample) {
        this.biosample = biosample;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getPubmed() {
        return pubmed;
    }

    public void setPubmed(int pubmed) {
        this.pubmed = pubmed;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
