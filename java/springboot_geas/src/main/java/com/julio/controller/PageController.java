package com.julio.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julio.domain.dto.SpeciesInfoDTO;
import com.julio.domain.note.NewsNote;
import com.julio.domain.note.ReleaseNote;
import com.julio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julio
 * @date 2020/10/22-21:01
 **/
@Controller
public class PageController {

    @Autowired
    private IGenusService genusService;

    @Autowired
    private IRnaService rnaService;

    @Autowired
    private ISsrService ssrService;

    @Autowired
    private INotesService notesService;

    @Autowired
    private IGenomeService genomeService;

    @RequestMapping({"/index","index.html","/"})
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView();
        List<NewsNote> newsNotes = notesService.queryLimitNewsNote(6);//查询6条新闻
        mv.addObject("newsNotes",newsNotes);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView toSearch(@RequestParam("keyword") String keyword){
        ModelAndView mv = new ModelAndView();
        List<SpeciesInfoDTO> infos = genusService.searchInfoByKeyWord(keyword);
        mv.addObject("infos",infos);
        mv.addObject("keyword",keyword);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping("/info")
    public ModelAndView toInfo(@RequestParam("scientificname") String scientificname){
        ModelAndView mv = new ModelAndView();
        String[] param = scientificname.split("\\s+"); //生物学名分别拆分为属名和物种名
        SpeciesInfoDTO infoDTO = genusService.querySpeicesInfo(param[0], param[1]);

        //List<Genome> genomeList = genomeService.queryGenomeByGenus(param[0]);

        mv.addObject("speciesInfo",infoDTO);
       // mv.addObject("genomes",genomeList);
        mv.addObject("scientificname",scientificname);
        mv.setViewName("info");
        return mv;
    }

    //获取基序统计及该属的家族图谱
    @RequestMapping("/motifs")
    public @ResponseBody Map<String,Object> queryMotifs(String scientificname){
        String[] sciname = scientificname.split("\\s+"); //根据空格将生物学名切分为属名和物种名
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Integer>> speciesMotifs = ssrService.querySpeciesMotifs(50,sciname[0]);
        List<Map<String, Integer>> cultivarMotifs = ssrService.queryCultivarMotifs(50,sciname[0],sciname[1]);
        List<SpeciesInfoDTO> genusFamily = genusService.queryGenusFamily(sciname[0]);
        map.put("speciesMotifs",speciesMotifs);
        map.put("cultivarMotifs",cultivarMotifs);
        map.put("genusFamily",genusFamily);
        return map;
    }

    @RequestMapping("/ssrDownloadInfo")
    public ModelAndView toSsrDownload(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                      String keyword){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(pageNum,15);
        List<SpeciesInfoDTO> infoDTOS = genusService.searchDownloadSSRInfoByKeyword(keyword);
        PageInfo<SpeciesInfoDTO> ssrDownloadInfo = new PageInfo<>(infoDTOS);
        mv.addObject("downloadInfo",ssrDownloadInfo);
        mv.addObject("keyword",keyword);
        mv.setViewName("download/ssrDownload");
        return mv;
    }

    @RequestMapping("/notes")
    public ModelAndView toReleaseNotes(){
        ModelAndView mv = new ModelAndView();
        List<ReleaseNote> releaseNotes = notesService.queryAllReleaseNote();
        mv.addObject("releaseNotes",releaseNotes);
        mv.setViewName("notes");
        return mv;
    }

    @RequestMapping("/help")
    public String toHelp(){
        return "help";
    }
}
