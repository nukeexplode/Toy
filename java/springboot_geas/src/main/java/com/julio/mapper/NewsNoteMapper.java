package com.julio.mapper;

import com.julio.domain.note.NewsNote;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julio
 * @date 2021/1/21-13:05
 * @Description 操作版本信息公告
 **/
@Repository
public interface NewsNoteMapper {

    @Select("SELECT * FROM newsnote ORDER BY createdate DESC LIMIT #{num};")
    public List<NewsNote> queryLimtNewsDesc(@Param("num") int num);//按创建日期降序查询指定数量的news
}
