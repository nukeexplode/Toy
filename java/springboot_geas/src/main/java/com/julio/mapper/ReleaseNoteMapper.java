package com.julio.mapper;

import com.julio.domain.note.ReleaseNote;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julio
 * @date 2021/1/21-13:05
 * @Description 操作新闻公告
 **/
@Repository
public interface ReleaseNoteMapper {

    @Select("SELECT * FROM releasenote")
    public List<ReleaseNote> queryAllReleaseNote();
}
