package com.julio.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.julio.domain.DataExcel;
import com.julio.domain.ssr.*;
import com.julio.exception.InputException;
import com.julio.service.IDownloadService;
import com.julio.service.IGenusService;
import com.julio.service.ISsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julio
 * @date 2020/2/10-20:06
 **/
@Controller
@RequestMapping("/ssr")
public class SsrController {

    @Autowired
    private ISsrService ssrService;

    @Autowired
    private IDownloadService downloadService;

    @Autowired
    private IGenusService genusService;

    //pagehelper的分页查询，pageNum是当前页数
    @RequestMapping("/speciesSsr")
    public ModelAndView querySpeciesSsr(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum, SsrForm ssrform) {
        //System.out.println(ssrform);
            ModelAndView mv = new ModelAndView();
            if(pageNum!=1){
                pageNum = ssrform.getPn();
            }
            try{
                PageHelper.startPage(pageNum, 15);
                List<SpeciesSsr> speciesSsrs = ssrService.queryAllSsrs(ssrform);
                //pageInfo中有很多功能,传入第二个参数则是连续分页的页码数
                PageInfo<SpeciesSsr> info = new PageInfo<>(speciesSsrs);
                mv.addObject("ssrinfo", info);
                mv.addObject("param",ssrform);
                mv.setViewName("ssr/speciesSsr");
                return mv;
            }catch (Exception e){
                throw new InputException("请输入合法的查询条件！");
            }
    }

    @RequestMapping("/cultivarSsr")
    public ModelAndView queryCultivarSsrs(SsrForm ssrForm,
                                         @RequestParam(value = "pn", defaultValue = "1") Integer pageNum){
        ModelAndView mv = new ModelAndView();
        if(pageNum!=1){
            pageNum = ssrForm.getPn();
        }
        PageHelper.startPage(pageNum,15);
        List<CultivarSsr> cultivarSsrs = ssrService.querySpeciesSsrs(ssrForm);
        PageInfo<CultivarSsr> ssrInfo = new PageInfo<>(cultivarSsrs);
        mv.addObject("ssrInfo",ssrInfo);
        mv.addObject("param",ssrForm);
        mv.setViewName("ssr/cultivarSsr");
        return mv;
    }

    //属-物种间多态性分布
    @RequestMapping("/getSpeciesDistribute")
    public @ResponseBody DistributeTo getSsrDistribute(String ssrid){
        DistributeTo ssrDistribute = ssrService.querySpeciesSsrDist(ssrid);
        return ssrDistribute;
    }

    //物种-品种间多态性分布
    @RequestMapping("/getCultivarDistribute")
    public @ResponseBody DistributeTo getCultivarDistribute(String ssrid){
        DistributeTo ssrDistribute = ssrService.queryCultivarSsrDist(ssrid);
        return ssrDistribute;
    }


    //查询物种SSR的引物,第二个参数用来判断查询的是物种SSR还是品种SSR的引物
    @RequestMapping("/queryPrimer")
    public @ResponseBody List<Primer> querySpeciesPrimer(String ssrid,boolean isSpecies){
        List<Primer> primers = null;
        if (isSpecies){
            //查询物种SSR引物
            primers = ssrService.querySpeciesPrimer(ssrid);
        }else{
            //查询品种SSR的引物
            primers = ssrService.queryCultivarPrimer(ssrid);
        }
        return primers;
    }

    //查询品种SSR的引物
    @RequestMapping("/queryCultivarPrimer")
    public @ResponseBody List<Primer> queryCultivarPrimer(String ssrid){
        List<Primer> primers = ssrService.queryCultivarPrimer(ssrid);
        return primers;
    }

    //下载单条SSR记录
    @RequestMapping("/downLoadSsrExcel")
    public void downLoadSsrExcel(@RequestParam(value = "ssrid") String ssrid, HttpServletResponse response){
        List<SpeciesSsr> speciesSsrs = ssrService.querySpecieSsrById(ssrid);
        System.out.println(speciesSsrs.size());
        for (SpeciesSsr speciesSsr : speciesSsrs) {
        }
        //定义列名
        String[] rowName=new String[]{"SSRID","Type","Repeats","Ref","std_dev","MissingRate","Transferability","UpStream(100bp)","DownStream(100bp)"};
        //定义数据内容
        List<Object[]> dataList=new ArrayList<>();
        //当有多行数据时需要分别定义每一行
        Object[] obj=null;
        for(int i = 0; i< speciesSsrs.size(); i++){
            obj=new Object[rowName.length];
            //将查询到的ssr集合的值分别存在每行的obj数组中，这里我们实际只有一行数据
            obj[0]= speciesSsrs.get(i).getSsrid();
            obj[1]= speciesSsrs.get(i).getMotif();
            obj[2]= speciesSsrs.get(i).getRepeats();
            obj[3]= speciesSsrs.get(i).getRef();
            obj[4]= speciesSsrs.get(i).getStddev();
            obj[5]= speciesSsrs.get(i).getMissingrate();
            obj[6]= speciesSsrs.get(i).getTransferability();
            obj[7]= speciesSsrs.get(i).getUpstream();
            obj[8]= speciesSsrs.get(i).getDownstream();
            dataList.add(obj);//obj数组的值保存在集合中
        }

        //定义文件名
        String fileName=String.valueOf(System.currentTimeMillis())+".xls";

        //创建excel对象
        DataExcel excel= new DataExcel(fileName,rowName,dataList,response);
        excel.downloadExcel();
    }

    //批量下载物种SSR数据
    @RequestMapping("/batchDownSpecieSsr")
    public void batchDownloadSpecieSsrExcel(String genus,HttpServletResponse response){
        downloadService.batchDownloadSpecieSsrExcel(genus,response);
    }

    //批量下载品种SSR数据
    @RequestMapping("/batchDownCultivarSsr")
    public void batchDownloadCultivarSsrExcel(String genus,String species,HttpServletResponse response){
        downloadService.batchDownloadCultivarSsrExcel(genus,species,response);
    }

    //批量下载物种SSR引物数据
    @RequestMapping("/batchDownSpePrimer")
    public void batchDownloadSpeciesPrimersExcel(String genus,HttpServletResponse response){
        downloadService.batchDownloadSpeciesPrimerExcel(genus,response);
    }

    //批量下载品种SSR引物数据
    @RequestMapping("/batchDownCulPrimer")
    public void batchDownloadCultivarPrimersExcel(String genus,String species,HttpServletResponse response){
        downloadService.batchDownloadCultivarPrimerExcel(genus,species,response);
    }
    //返回数据库所有物种名
    @RequestMapping("/genusMap")
    public @ResponseBody List<String> queryGenusMap(){
        return genusService.queryAllOrganizm();
    }


}