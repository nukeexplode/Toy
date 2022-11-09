package com.julio.domain.note;

import java.util.Date;

/**
 * @author Julio
 * @date 2021/1/21-13:07
 * @Description 新闻公告
 **/
public class NewsNote {
    private Integer id;
    private String title;
    private String content;
    private Date createdate;

    @Override
    public String toString() {
        return "NewsNote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdate=" + createdate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
