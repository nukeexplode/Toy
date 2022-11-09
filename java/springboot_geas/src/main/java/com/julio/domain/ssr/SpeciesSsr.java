package com.julio.domain.ssr;

import java.io.Serializable;

/**
 * @author Julio
 * @date 2020/2/10-18:49
 * @Description 物种SSR实体类
 **/
public class SpeciesSsr implements Serializable {
    private String ssrid; //SSR编号
    private String motif; //基序
    private Integer repeats; //重复次数
    private String ref; //参考物种SSR区域
    private String stddev; //标准偏差
    private String missingrate; //缺失率
    private String transferability; //转移率
    private String genus; //属名
    private String upstream; //上游序列
    private String downstream; //下游序列
    //该SSR在其它十个物种的分布
    private String distorganizm1;
    private String distorganizm2;
    private String distorganizm3;
    private String distorganizm4;
    private String distorganizm5;
    private String distorganizm6;
    private String distorganizm7;
    private String distorganizm8;
    private String distorganizm9;
    private String distorganizm10;
    //将不同物种的分布区域重写为字符串
    public String distToString() {
        return  distorganizm1 + ',' + distorganizm2 + ',' + distorganizm3 + ',' +
                distorganizm4 + ',' + distorganizm5 + ',' + distorganizm6 + ',' +
                distorganizm7 + ',' + distorganizm8 + ',' + distorganizm9 + ',' +
                distorganizm10;
    }

    public SpeciesSsr() {
    }

    @Override
    public String toString() {
        return "Ssr{" +
                "ssrid='" + ssrid + '\'' +
                ", motif='" + motif + '\'' +
                ", repeats=" + repeats +
                ", ref='" + ref + '\'' +
                ", stddev='" + stddev + '\'' +
                ", missingrate='" + missingrate + '\'' +
                ", transferability='" + transferability + '\'' +
                ", genus='" + genus + '\'' +
                ", upstream='" + upstream + '\'' +
                ", downstream='" + downstream + '\'' +
                ", distorganizm1='" + distorganizm1 + '\'' +
                ", distorganizm2='" + distorganizm2 + '\'' +
                ", distorganizm3='" + distorganizm3 + '\'' +
                ", distorganizm4='" + distorganizm4 + '\'' +
                ", distorganizm5='" + distorganizm5 + '\'' +
                ", distorganizm6='" + distorganizm6 + '\'' +
                ", distorganizm7='" + distorganizm7 + '\'' +
                ", distorganizm8='" + distorganizm8 + '\'' +
                ", distorganizm9='" + distorganizm9 + '\'' +
                ", distorganizm10='" + distorganizm10 + '\'' +
                '}';
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

    public int getRepeats() {
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


    public String getDistorganizm1() {
        return distorganizm1;
    }

    public void setDistorganizm1(String distorganizm1) {
        this.distorganizm1 = distorganizm1;
    }

    public String getDistorganizm2() {
        return distorganizm2;
    }

    public void setDistorganizm2(String distorganizm2) {
        this.distorganizm2 = distorganizm2;
    }

    public String getDistorganizm3() {
        return distorganizm3;
    }

    public void setDistorganizm3(String distorganizm3) {
        this.distorganizm3 = distorganizm3;
    }

    public String getDistorganizm4() {
        return distorganizm4;
    }

    public void setDistorganizm4(String distorganizm4) {
        this.distorganizm4 = distorganizm4;
    }

    public String getDistorganizm5() {
        return distorganizm5;
    }

    public void setDistorganizm5(String distorganizm5) {
        this.distorganizm5 = distorganizm5;
    }

    public String getDistorganizm6() {
        return distorganizm6;
    }

    public void setDistorganizm6(String distorganizm6) {
        this.distorganizm6 = distorganizm6;
    }

    public String getDistorganizm7() {
        return distorganizm7;
    }

    public void setDistorganizm7(String distorganizm7) {
        this.distorganizm7 = distorganizm7;
    }

    public String getDistorganizm8() {
        return distorganizm8;
    }

    public void setDistorganizm8(String distorganizm8) {
        this.distorganizm8 = distorganizm8;
    }

    public String getDistorganizm9() {
        return distorganizm9;
    }

    public void setDistorganizm9(String distorganizm9) {
        this.distorganizm9 = distorganizm9;
    }

    public String getDistorganizm10() {
        return distorganizm10;
    }

    public void setDistorganizm10(String distorganizm10) {
        this.distorganizm10 = distorganizm10;
    }
}
