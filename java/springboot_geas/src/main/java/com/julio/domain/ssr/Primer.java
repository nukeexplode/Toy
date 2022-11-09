package com.julio.domain.ssr;

import java.io.Serializable;

/**
 * @author Julio
 * @date 2020/2/15-18:28
 * @Description 引物实体类
 **/
public class Primer implements Serializable {
    private String ssrid; //SSR编号
    private String genus; //属名
    private String species; //物种名
    private String primerFw; //前段引物序列
    private float tmFw; //前段温度
    private int sizeFw; //前段大小
    private float identityFw; //前端引物一致性
    private float coverageFw; //前端引物覆盖率

    private String primerRe; //后段引物序列
    private float tmRe; //后段温度
    private int sizeRe; //后段大小
    private float identityRe; //后段引物一致性
    private float coverageRe; //后段引物覆盖率

    private int produceSize; //产物大小
    private int start;
    private int end;

    @Override
    public String toString() {
        return "Primer{" +
                "ssrid='" + ssrid + '\'' +
                ", genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                ", primerFw='" + primerFw + '\'' +
                ", tmFw=" + tmFw +
                ", sizeFw=" + sizeFw +
                ", identityFw=" + identityFw +
                ", coverageFw=" + coverageFw +
                ", primerRe='" + primerRe + '\'' +
                ", tmRe=" + tmRe +
                ", sizeRe=" + sizeRe +
                ", identityRe=" + identityRe +
                ", coverageRe=" + coverageRe +
                ", produceSize=" + produceSize +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Primer() {
    }

    public String getSsrid() {
        return ssrid;
    }

    public void setSsrid(String ssrid) {
        this.ssrid = ssrid;
    }

    public String getPrimerFw() {
        return primerFw;
    }

    public void setPrimerFw(String primerFw) {
        this.primerFw = primerFw;
    }

    public float getTmFw() {
        return tmFw;
    }

    public void setTmFw(float tmFw) {
        this.tmFw = tmFw;
    }

    public int getSizeFw() {
        return sizeFw;
    }

    public void setSizeFw(int sizeFw) {
        this.sizeFw = sizeFw;
    }

    public float getIdentityFw() {
        return identityFw;
    }

    public void setIdentityFw(float identityFw) {
        this.identityFw = identityFw;
    }

    public float getCoverageFw() {
        return coverageFw;
    }

    public void setCoverageFw(float coverageFw) {
        this.coverageFw = coverageFw;
    }

    public String getPrimerRe() {
        return primerRe;
    }

    public void setPrimerRe(String primerRe) {
        this.primerRe = primerRe;
    }

    public float getTmRe() {
        return tmRe;
    }

    public void setTmRe(float tmRe) {
        this.tmRe = tmRe;
    }

    public int getSizeRe() {
        return sizeRe;
    }

    public void setSizeRe(int sizeRe) {
        this.sizeRe = sizeRe;
    }

    public float getIdentityRe() {
        return identityRe;
    }

    public void setIdentityRe(float identityRe) {
        this.identityRe = identityRe;
    }

    public float getCoverageRe() {
        return coverageRe;
    }

    public void setCoverageRe(float coverageRe) {
        this.coverageRe = coverageRe;
    }

    public int getProduceSize() {
        return produceSize;
    }

    public void setProduceSize(int produceSize) {
        this.produceSize = produceSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
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
}
