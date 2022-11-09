package com.julio.service;

import com.julio.domain.note.NewsNote;
import com.julio.domain.note.ReleaseNote;

import java.util.List;

/**
 * @author Julio
 * @date 2021/1/21-13:24
 * @Description
 **/
public interface INotesService {
    public List<NewsNote> queryLimitNewsNote(int num); //查询最近的6个news
    public List<ReleaseNote> queryAllReleaseNote();
}
