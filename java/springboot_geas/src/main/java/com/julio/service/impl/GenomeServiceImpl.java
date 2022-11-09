package com.julio.service.impl;

import com.julio.domain.Genome;
import com.julio.mapper.GenomeMapper;
import com.julio.service.IGenomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Julio
 * @date 2020/11/12-18:40
 **/
@Service
public class GenomeServiceImpl implements IGenomeService {

    @Autowired
    private GenomeMapper genomeMapper;

    @Override
    public List<Genome> queryGenomeByGenus(String genus) {
        return genomeMapper.queryGenomeByGenus(genus);
    }

    @Override
    public List<Genome> queryGenome(String scientificname) {
        return genomeMapper.queryGenome(scientificname);
    }

}
