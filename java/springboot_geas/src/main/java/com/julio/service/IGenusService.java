package com.julio.service;

import com.julio.domain.dto.SpeciesInfoDTO;

import java.util.List;

/**
 * @author Julio
 * @date 2020/10/27-19:06
 **/
public interface IGenusService {
    public List<String> queryAllOrganizm();//查询所有物种
    public List<SpeciesInfoDTO> queryHotSpecies(); //查询热点物种
    public SpeciesInfoDTO querySpeicesInfo(String genus, String species); //查询物种详细信息
    public List<SpeciesInfoDTO> queryGenusFamily(String genus); //查询属的关系
    public List<SpeciesInfoDTO> searchInfoByKeyWord(String keyword); //关键字检索物种信息
    public List<SpeciesInfoDTO> searchDownloadSSRInfoByKeyword(String keyword); //关键字检索可供下载的SSR数据
}
