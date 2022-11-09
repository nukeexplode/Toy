package com.julio.service;

import com.julio.domain.ssr.*;

import java.util.List;
import java.util.Map;

/**
 * @author Julio
 * @date 2020/2/10-18:54
 **/
public interface ISsrService {
    public List<SpeciesSsr> queryAllSsrs(SsrForm ssrForm);//根据表单查询所有物种SSR
    DistributeTo querySpeciesSsrDist(String ssrid);//查询物种ssr分布情况
    DistributeTo queryCultivarSsrDist(String ssrid);//查询品种级别ssr分布情况
    public List<SpeciesSsr> querySpecieSsrById(String ssrid);//根据id查SSR
    public List<CultivarSsr> querySpeciesSsrs(SsrForm ssrForm);//根据表单查询所有品种SSR

//    public int getTotalCount(String type,String genus,List<String> conditions);
//    public int getTotalPage(String type1, String organizm1, List<String> str, int pageSize);


//    public String queryGenusBySsrid(String ssrid);
//    public String queryDistOrganizm(String genus);


    //DistributeTO getDistribute(String SSRID);
    public List<Primer> querySpeciesPrimer(String ssrid);//根据ssrid查看引物
    public List<Primer> queryCultivarPrimer(String ssrid);//查询品种SSR的引物
    public List<Map<String,Integer>> querySpeciesMotifs(int num,String genus);//查询物种SSR的基序排序
    public List<Map<String,Integer>> queryCultivarMotifs(int num,String genus,String species);//查询品种SSR的基序排序
}
