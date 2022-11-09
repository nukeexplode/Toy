package com.julio.service.impl;

import com.julio.domain.rna.Rna;
import com.julio.mapper.RnaMapper;
import com.julio.service.IRnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Julio
 * @date 2020/2/16-20:13
 **/
@Service
public class RnaServiceImpl implements IRnaService {

    @Autowired
    private RnaMapper rnaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Rna> queryRnas(String angiosperm, String genus, String scientificname) {
        return rnaDao.queryRnas(angiosperm,genus,scientificname);
    }
}
