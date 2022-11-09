package com.julio.domain.ssr;

import java.util.List;

/**
 * @author Julio
 * @date 2020/2/13-22:01
 * @Description ssr在不同物种分布情况的封装实体类传输对象
 **/
public class DistributeTo {
    private String genus;
    private String specie;
    private List<String> organizmName; //所有物种名
    private List<String> cultivarName; //所有品种名
    private List<Float> distrubute; //分布次数
    private List<String> distField; //分布区域

    @Override
    public String toString() {
        return "DistributeTo{" +
                "genus='" + genus + '\'' +
                ", specie='" + specie + '\'' +
                ", organizmName=" + organizmName +
                ", cultivarName=" + cultivarName +
                ", distrubute=" + distrubute +
                ", distField=" + distField +
                '}';
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public List<String> getCultivarName() {
        return cultivarName;
    }

    public void setCultivarName(List<String> cultivarName) {
        this.cultivarName = cultivarName;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public List<String> getOrganizmName() {
        return organizmName;
    }

    public void setOrganizmName(List<String> organizmName) {
        this.organizmName = organizmName;
    }

    public List<Float> getDistrubute() {
        return distrubute;
    }

    public void setDistrubute(List<Float> distrubute) {
        this.distrubute = distrubute;
    }

    public List<String> getDistField() {
        return distField;
    }

    public void setDistField(List<String> distField) {
        this.distField = distField;
    }
}
