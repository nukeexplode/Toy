package com.julio.service.impl;

import com.julio.domain.Species;
import com.julio.domain.ssr.*;
import com.julio.mapper.SsrMapper;
import com.julio.service.ISsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Julio
 * @date 2020/2/10-18:54
 **/
@Service("ssrService")
public class SsrServiceImpl implements ISsrService {

    @Autowired
    private SsrMapper ssrMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpeciesSsr> queryAllSsrs(SsrForm ssrForm) {
        String genus = ssrForm.getGenus();
        int type = ssrForm.getType();
        String motif = ssrForm.getMotif();
        String missingType = ssrForm.getMissingType();
        String stdType = ssrForm.getStdType();
        String transType = ssrForm.getTransType();
        String missingRate = ssrForm.getMissingRate();
        String stddev = ssrForm.getStddev();
        String transferability = ssrForm.getTransferability();

        String filterMR = ssrForm.getFilterMR();
        String filterStd = ssrForm.getFilterStd();
        String filterTrans = ssrForm.getFilterTrans();

        List<String> conditions = new ArrayList<>();
        if (filterMR != null &&  !"".equals(missingRate)) {
            conditions.add("missingrate" + missingType + missingRate);

        }
        if (filterStd != null && !"".equals(stddev )) {
            conditions.add("stddev" + stdType + stddev);

        }
        if (filterTrans != null && !"".equals(transferability)) {
            conditions.add("transferability" + transType + transferability);
        }

        return ssrMapper.querySpeciesSsr(genus,type,motif,conditions);
    }

    @Override
    @Transactional(readOnly = true)
    public DistributeTo querySpeciesSsrDist(String ssrid) {
        DistributeTo distribute=new DistributeTo();
        List<String> organizmName=new ArrayList<>();//对应物种名
        List<Float> distTimes=new ArrayList<>();//分布次数
        List<String> distField=new ArrayList<>();//分布区域

        String genusName = ssrMapper.queryGenusBySsrid(ssrid);
        String organizm = ssrMapper.queryDistOrganizm(genusName);
        //1.先获取分布物种名
        if(organizm.contains("|")){
            StringTokenizer stringTokenizer = new StringTokenizer(organizm,"|");//规定分隔符
            while(stringTokenizer.hasMoreTokens()){
                organizmName.add(stringTokenizer.nextToken());//按照分隔符依次取出物种名
            }
        }else{
            organizmName.add("null");
        }
        distribute.setGenus(genusName);
        distribute.setOrganizmName(organizmName);


       //2.获取分布次数和分布区域
        SpeciesSsr speciesSsrDist = ssrMapper.querySpeciesDistBySsrid(ssrid);
        String s= speciesSsrDist.distToString();//转为字符串不排除空格（.replace(" ","")）
        String[] strings = s.split(",");//分割ssr的分布值例如：2|CjaTrnas081961:1508-1515|-    3|CniTrans118454:200-211|+


        for(int i=0;i<strings.length;i++){
            //String regex="[0-9.]";
            //Pattern pattern=Pattern.compile(regex);pattern.matcher(strings[i]).matches()
            if(strings[i].contains("|")){
                String[] split = strings[i].split("\\|");//把每个分布值切割成次数和区域两部分
                distTimes.add(Float.parseFloat(split[0]));
                distField.add(split[1]);
            }else{
                distTimes.add(0F);
                distField.add("null");
            }
        }
        distribute.setDistrubute(distTimes);//set分布次数
        distribute.setDistField(distField);//set分布区域

        return distribute;
    }

    //查询物种-品种SSR多态性分布
    @Override
    public DistributeTo queryCultivarSsrDist(String ssrid) {
        DistributeTo distribute=new DistributeTo();
        List<String> cultivarName=new ArrayList<>();//对应品种名
        List<Float> distTimes=new ArrayList<>();//分布次数
        List<String> distField=new ArrayList<>();//分布区域

        Species species = ssrMapper.querySpeciesBySsrid(ssrid);
        System.out.println("查询"+species.toString());
        String genusName = species.getGenus();
        String specieName = species.getSpecie();
        String cultivar = ssrMapper.queryDistCultivar(genusName,specieName);
        System.out.println("查询"+cultivar);
        //1.先获取分布品种名
        if(cultivar.contains("|")){
            StringTokenizer stringTokenizer = new StringTokenizer(cultivar,"|");//规定分隔符
            while(stringTokenizer.hasMoreTokens()){
                cultivarName.add(stringTokenizer.nextToken());//按照分隔符依次取出物种名
            }
        }else{
            cultivarName.add("null");
        }
        distribute.setGenus(genusName);
        distribute.setSpecie(specieName);
        distribute.setCultivarName(cultivarName);


        //2.获取分布次数和分布区域
        CultivarSsr ssrDist = ssrMapper.queryCultivarDistBySsrid(ssrid);
        String s=ssrDist.distToString();//转为字符串不排除空格（.replace(" ","")）
        String[] strings = s.split(",");//分割ssr的分布值例如：2|CjaTrnas081961:1508-1515|-    3|CniTrans118454:200-211|+


        for(int i=0;i<strings.length;i++){
            //String regex="[0-9.]";
            //Pattern pattern=Pattern.compile(regex);pattern.matcher(strings[i]).matches()
            if(strings[i].contains("|")){
                String[] split = strings[i].split("\\|");//把每个分布值切割成次数和区域两部分
                distTimes.add(Float.parseFloat(split[0]));
                distField.add(split[1]);
            }else{
                distTimes.add(0F);
                distField.add("null");
            }
        }
        distribute.setDistrubute(distTimes);//set分布次数
        distribute.setDistField(distField);//set分布区域

        return distribute;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpeciesSsr> querySpecieSsrById(String ssrid) {
        return ssrMapper.querySpecieSsrById(ssrid);
    }

    @Override
    public List<CultivarSsr> querySpeciesSsrs(SsrForm ssrForm) {
        String genus = ssrForm.getGenus();
        String species = ssrForm.getSpecies();
        int type = ssrForm.getType();
        String motif = ssrForm.getMotif();
        String missingType = ssrForm.getMissingType();
        String stdType = ssrForm.getStdType();
        String transType = ssrForm.getTransType();
        String missingRate = ssrForm.getMissingRate();
        String stddev = ssrForm.getStddev();
        String transferability = ssrForm.getTransferability();

        String filterMR = ssrForm.getFilterMR();
        String filterStd = ssrForm.getFilterStd();
        String filterTrans = ssrForm.getFilterTrans();

        List<String> conditions = new ArrayList<>();
        if (filterMR != null &&  !"".equals(missingRate)) {
            conditions.add("missingrate" + missingType + missingRate);

        }
        if (filterStd != null && !"".equals(stddev )) {
            conditions.add("stddev" + stdType + stddev);

        }
        if (filterTrans != null && !"".equals(transferability)) {
            conditions.add("transferability" + transType + transferability);
        }

        return ssrMapper.queryCultivarSsr(genus,species,type,motif,conditions);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Primer> querySpeciesPrimer(String ssrid) {
        return ssrMapper.querySpeciesPrimer(ssrid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Primer> queryCultivarPrimer(String ssrid) {
        return ssrMapper.queryCultivarPrimer(ssrid);
    }

    @Override
    public List<Map<String, Integer>> querySpeciesMotifs(int num,String genus) {
        return ssrMapper.querySpeciesMotifsCount(num,genus);
    }

    @Override
    public List<Map<String, Integer>> queryCultivarMotifs(int num, String genus, String species) {
        return ssrMapper.queryCultivarMotifsCount(num,genus,species);
    }


}
