package com.julio.controller;

import com.julio.domain.dto.SpeciesInfoDTO;
import com.julio.service.IGenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Julio
 * @date 2020/11/4-19:37
 **/
@Controller
public class GenusController {
    @Autowired
    private IGenusService genusService;

    @RequestMapping("/HotSpecies")
    public @ResponseBody List<SpeciesInfoDTO> queryHotSpecies(){
        return genusService.queryHotSpecies();
    }
}
