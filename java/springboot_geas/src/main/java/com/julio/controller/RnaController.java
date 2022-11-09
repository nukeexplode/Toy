package com.julio.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julio.domain.rna.Rna;
import com.julio.service.IRnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Julio
 * @date 2020/2/16-19:30
 **/
@Controller
@RequestMapping("/rna")
public class RnaController {
    @Autowired
    private IRnaService rnaService;

    @RequestMapping("/queryRnas")
    public ModelAndView queryRnas(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum, Rna rna) {
        ModelAndView mv=new ModelAndView();
        String angiosperm = rna.getAngiosperm();
        String genus = rna.getGenus();
        String scientificname = rna.getScientificname();
        PageHelper.startPage(pageNum,15);
        List<Rna> rnas = rnaService.queryRnas(angiosperm, genus, scientificname);
        PageInfo<Rna> info=new PageInfo<>(rnas);

//        if(rnas.size()==0){
//            RnaException e = new RnaException("Find No Records!");
//            throw e;
//        }
        mv.addObject("angiosperm",angiosperm);
        mv.addObject("genus",genus);
        mv.addObject("scientificname",scientificname);
        mv.addObject("rnainfo",info);
        mv.setViewName("rna");
        return mv;
    }
}
