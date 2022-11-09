package com.julio.domain.ssr;

/**
 * @author Julio
 * @date 2020/11/25-21:37
 * @Description 品种的SSR
 **/
public class CultivarSsr {
    private String ssrid; //SSR编号
    private String motif; //基序
    private Integer repeats; //重复次数
    private String ref; //参考物种SSR区域
    private String stddev; //标准偏差
    private String missingrate; //缺失率
    private String transferability; //转移率
    private String genus; //属名
    private String species; //物种名
    private String upstream; //上游序列
    private String downstream; //下游序列
    //SSR在其它十个品种的分布区域
    private String distcultivar1;
    private String distcultivar2;
    private String distcultivar3;
    private String distcultivar4;
    private String distcultivar5;
    private String distcultivar6;
    private String distcultivar7;
    private String distcultivar8;
    private String distcultivar9;
    private String distcultivar10;

    public CultivarSsr() {
    }

    //在不同品种分布情况，重写为字符串
    public String distToString() {
        return  distcultivar1 + ',' + distcultivar2 + ',' + distcultivar3 + ',' +
                distcultivar4 + ',' + distcultivar5 + ',' + distcultivar6 + ',' +
                distcultivar7 + ',' + distcultivar8 + ',' + distcultivar9 + ',' +
                distcultivar10;
    }

    public String getSsrid() {
        return ssrid;
    }

    public void setSsrid(String ssrid) {
        this.ssrid = ssrid;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Integer getRepeats() {
        return repeats;
    }

    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getStddev() {
        return stddev;
    }

    public void setStddev(String stddev) {
        this.stddev = stddev;
    }

    public String getMissingrate() {
        return missingrate;
    }

    public void setMissingrate(String missingrate) {
        this.missingrate = missingrate;
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

    public String getUpstream() {
        return upstream;
    }

    public void setUpstream(String upstream) {
        this.upstream = upstream;
    }

    public String getDownstream() {
        return downstream;
    }

    public void setDownstream(String downstream) {
        this.downstream = downstream;
    }

    public String getDistcultivar1() {
        return distcultivar1;
    }

    public void setDistcultivar1(String distcultivar1) {
        this.distcultivar1 = distcultivar1;
    }

    public String getDistcultivar2() {
        return distcultivar2;
    }

    public void setDistcultivar2(String distcultivar2) {
        this.distcultivar2 = distcultivar2;
    }

    public String getDistcultivar3() {
        return distcultivar3;
    }

    public void setDistcultivar3(String distcultivar3) {
        this.distcultivar3 = distcultivar3;
    }

    public String getDistcultivar4() {
        return distcultivar4;
    }

    public void setDistcultivar4(String distcultivar4) {
        this.distcultivar4 = distcultivar4;
    }

    public String getDistcultivar5() {
        return distcultivar5;
    }

    public void setDistcultivar5(String distcultivar5) {
        this.distcultivar5 = distcultivar5;
    }

    public String getDistcultivar6() {
        return distcultivar6;
    }

    public void setDistcultivar6(String distcultivar6) {
        this.distcultivar6 = distcultivar6;
    }

    public String getDistcultivar7() {
        return distcultivar7;
    }

    public void setDistcultivar7(String distcultivar7) {
        this.distcultivar7 = distcultivar7;
    }

    public String getDistcultivar8() {
        return distcultivar8;
    }

    public void setDistcultivar8(String distcultivar8) {
        this.distcultivar8 = distcultivar8;
    }

    public String getDistcultivar9() {
        return distcultivar9;
    }

    public void setDistcultivar9(String distcultivar9) {
        this.distcultivar9 = distcultivar9;
    }

    public String getDistcultivar10() {
        return distcultivar10;
    }

    public void setDistcultivar10(String distcultivar10) {
        this.distcultivar10 = distcultivar10;
    }
}
