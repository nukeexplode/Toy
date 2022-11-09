package com.julio.service.impl;

import com.julio.domain.note.NewsNote;
import com.julio.domain.note.ReleaseNote;
import com.julio.mapper.NewsNoteMapper;
import com.julio.mapper.ReleaseNoteMapper;
import com.julio.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Julio
 * @date 2021/1/21-13:25
 * @Description
 **/
@Service
public class NotesServiceImpl implements INotesService {

    @Autowired
    private NewsNoteMapper newsMapper;

    @Autowired
    private ReleaseNoteMapper releaseMapper;

    @Override
    @Cacheable("news")
    public List<NewsNote> queryLimitNewsNote(int num) {
        return newsMapper.queryLimtNewsDesc(num);
    }

    @Override
    @Cacheable(cacheNames = "release")
    public List<ReleaseNote> queryAllReleaseNote() {
        List<ReleaseNote> releaseList = releaseMapper.queryAllReleaseNote();
        Collections.sort(releaseList, Comparator.nullsFirst(Comparator.comparing(ReleaseNote::getCreatedate,Comparator.nullsFirst((r1, r2)-> r2.compareTo(r1))))); //按时间先后降序排序
        return releaseList;
    }
}
