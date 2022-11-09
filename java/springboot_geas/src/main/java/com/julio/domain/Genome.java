package com.julio.domain;

/** 侦测SSR时用到的基因组数据
 * @author Julio
 * @date 2020/11/11-15:35
 **/
public class Genome {
    private Integer id;
    private String scientificname;//生物学名
    private String genotype; //基因型
    private String source; //数据来源网址
    private String reference; //参考文献
    private String size;//数据大小
    private String download; //下载地址

    public Genome() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScientificname() {
        return scientificname;
    }

    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
