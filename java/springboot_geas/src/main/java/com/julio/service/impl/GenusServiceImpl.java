package com.julio.service.impl;

import com.julio.domain.Genus;
import com.julio.domain.dto.SpeciesInfoDTO;
import com.julio.mapper.GenusMapper;
import com.julio.service.IGenusService;
import com.julio.utils.Jutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julio
 * @date 2020/10/27-19:10
 **/
@Service
public class GenusServiceImpl implements IGenusService {
    @Autowired
    private GenusMapper genusMapper;

    @Override
    @Cacheable("allSpecies")
    public List<String> queryAllOrganizm() {
        ArrayList<String> species = new ArrayList<>(); //新建集合储存所有物种名
        List<Genus> genumaps = genusMapper.queryAllGenus();
        for (Genus genusmap : genumaps) {
            String[] organArray = genusmap.getSpecies().split("\\|"); //切分数组
            String genus = genusmap.getGenus();
            String ref = genusmap.getRef();
                species.add(genus+" "+ref);
            for (int i=0;i<organArray.length;i++){
                species.add(genus+" "+organArray[i]);
            }
        }
        return species;
    }

    @Override
    @Cacheable("hotSpecies")
    public List<SpeciesInfoDTO> queryHotSpecies() {
        return genusMapper.queryHotSpecies();
    }

    @Override
    public SpeciesInfoDTO querySpeicesInfo(String genus, String species) {
        return genusMapper.querySpeciesInfo(genus,species);
    }

    @Override
    public List<SpeciesInfoDTO> queryGenusFamily(String genus) {
        return genusMapper.queryGenusFamily(genus);
    }

    @Override
    public List<SpeciesInfoDTO> searchInfoByKeyWord(String keyword) {
        List<SpeciesInfoDTO> infos = genusMapper.searchInfoByKeyword(keyword);
        infos.stream().map((info)->{
            if (!Jutils.nullOrEmpty(info.getSpecies())){
                info.setSpecies(info.getSpecies().replace("|",","));
            }
            if (!Jutils.nullOrEmpty(info.getCultivars())){
                info.setCultivars(info.getCultivars().replace("|",","));
            }
            return info;
        }).collect(Collectors.toList());
        return infos;
    }

    @Override
    public List<SpeciesInfoDTO> searchDownloadSSRInfoByKeyword(String keyword) {
        List<SpeciesInfoDTO> infos = genusMapper.searchDownloadSSRInfoByKeyword(keyword);
        infos.stream().map((info)->{
            //分别取出ref和所有物种名拼成字符串
            if (!Jutils.nullOrEmpty(info.getSpecies())){
                if (!Jutils.nullOrEmpty(info.getRef())){
                    info.setSpecies(info.getRef()+","+info.getSpecies().replace("|",","));
                }else{
                    info.setSpecies(info.getSpecies().replace("|",","));
                }
            }
            if (!Jutils.nullOrEmpty(info.getCultivars())){
                if (!Jutils.nullOrEmpty(info.getRefcultivar())){
                    info.setCultivars(info.getRefcultivar()+","+info.getCultivars().replace("|",","));
                }else{
                    info.setCultivars(info.getCultivars().replace("|",","));
                }
            }
            return info;
        }).collect(Collectors.toList());
        return infos;
    }
}
