package com.julio.service;

import com.julio.domain.Genome;

import java.util.List;

/**
 * @author Julio
 * @date 2020/11/12-18:38
 **/
public interface IGenomeService {
    public List<Genome> queryGenomeByGenus(String genus);
    public List<Genome> queryGenome(String scientificname);
}
