package com.julio.service;

import com.julio.domain.rna.Rna;

import java.util.List;

/**
 * @author Julio
 * @date 2020/2/16-20:13
 **/
public interface IRnaService {
    public List<Rna> queryRnas(String angiosperm, String genus, String scientificname);
}
