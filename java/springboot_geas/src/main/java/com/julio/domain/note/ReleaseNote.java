package com.julio.domain.note;

import java.util.Date;

/**
 * @author Julio
 * @date 2021/1/21-13:07
 * @Description 版本公告
 **/
public class ReleaseNote {
    private Integer id;
    private String vertitle;
    private String vercontent;
    private Date createdate;

    @Override
    public String toString() {
        return "ReleaseNote{" +
                "id=" + id +
                ", vertitle='" + vertitle + '\'' +
                ", vercontent='" + vercontent + '\'' +
                ", createdate=" + createdate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVertitle() {
        return vertitle;
    }

    public void setVertitle(String vertitle) {
        this.vertitle = vertitle;
    }

    public String getVercontent() {
        return vercontent;
    }

    public void setVercontent(String vercontent) {
        this.vercontent = vercontent;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
