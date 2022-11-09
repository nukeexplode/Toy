package com.julio.service.impl;

import com.julio.domain.DataExcel;
import com.julio.domain.ssr.CultivarSsr;
import com.julio.domain.ssr.Primer;
import com.julio.domain.ssr.SpeciesSsr;
import com.julio.mapper.SsrMapper;
import com.julio.service.IDownloadService;
import com.julio.utils.Jutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Julio
 * @date 2021/1/7-18:09
 **/
@Service
public class DownloadServiceImpl implements IDownloadService {

    @Autowired
    private SsrMapper ssrMapper;

    @Override
    public void batchDownloadSpecieSsrExcel(String genus, HttpServletResponse response) {
        List<SpeciesSsr> speciesSsrs = ssrMapper.querySpeciesSsr(genus, 0, null, new ArrayList<>());
        String distSpecies= ssrMapper.queryDistOrganizm(genus);//查询该属有哪些分布物种
        //定义列名
        String[] baseRow=new String[]{"SSRID","Motif","Repeats","Ref","Std_Dev","MissingRate","Transferability","UpStream(100bp)","DownStream(100bp)", "genus"};
        //"distorganizm1","distorganizm2","distorganizm3","distorganizm4","distorganizm5","distorganizm6","distorganizm7","distorganizm8","distorganizm9","distorganizm10"
        String[] speciesRow = null;
        if (distSpecies.contains("|")){
            speciesRow=distSpecies.split("\\|");//将查询到的分布物种名划分为数组
        }
        String[]rowName = Jutils.mergeArray(baseRow,speciesRow);//合并列名数组

        //定义数据内容
        List<Object[]> dataList=new ArrayList<>();
        //当有多行数据时需要分别定义每一行
        Object[] obj=null;
        for(int i = 0; i< speciesSsrs.size(); i++){
            obj=new Object[baseRow.length+10];
            //将查询到的ssr集合的值分别存在每行的obj数组中
            obj[0]= speciesSsrs.get(i).getSsrid();
            obj[1]= speciesSsrs.get(i).getMotif();
            obj[2]= speciesSsrs.get(i).getRepeats();
            obj[3]= speciesSsrs.get(i).getRef();
            obj[4]= speciesSsrs.get(i).getStddev();
            obj[5]= speciesSsrs.get(i).getMissingrate();
            obj[6]= speciesSsrs.get(i).getTransferability();
            obj[7]= speciesSsrs.get(i).getUpstream();
            obj[8]= speciesSsrs.get(i).getDownstream();
            obj[9]= speciesSsrs.get(i).getGenus();
            obj[10]= speciesSsrs.get(i).getDistorganizm1();
            obj[11]= speciesSsrs.get(i).getDistorganizm2();
            obj[12]= speciesSsrs.get(i).getDistorganizm3();
            obj[13]= speciesSsrs.get(i).getDistorganizm4();
            obj[14]= speciesSsrs.get(i).getDistorganizm5();
            obj[15]= speciesSsrs.get(i).getDistorganizm6();
            obj[16]= speciesSsrs.get(i).getDistorganizm7();
            obj[17]= speciesSsrs.get(i).getDistorganizm8();
            obj[18]= speciesSsrs.get(i).getDistorganizm9();
            obj[19]= speciesSsrs.get(i).getDistorganizm10();
            dataList.add(obj);//obj数组的值保存在集合中
        }
        //定义文件名
        String fileName=genus+String.valueOf(System.currentTimeMillis())+".xls";
        //创建excel对象
        DataExcel excel= new DataExcel(fileName,rowName,dataList,response);
        excel.downloadExcel();
    }

    @Override
    public void batchDownloadCultivarSsrExcel(String genus, String specie, HttpServletResponse response) {
        List<CultivarSsr> cultivarSsrs = ssrMapper.queryCultivarSsr(genus, specie, 0, null, new ArrayList<>());
        String distCultivars = ssrMapper.queryDistCultivar(genus, specie);//查询该物种有哪些品种
        //定义列名
        String[] baseRow=new String[]{"SSRID","Motif","Repeats","Ref","Std_Dev","MissingRate","Transferability","UpStream(100bp)","DownStream(100bp)", "genus","species"};
        //"distorganizm1","distorganizm2","distorganizm3","distorganizm4","distorganizm5","distorganizm6","distorganizm7","distorganizm8","distorganizm9","distorganizm10"
        String[] cultivarRow = null;
        if (distCultivars.contains("|")){
            cultivarRow=distCultivars.split("\\|");//将查询到的分布物种名划分为数组
        }
        String[]rowName = Jutils.mergeArray(baseRow,cultivarRow);//合并列名数组

        //定义数据内容
        List<Object[]> dataList=new ArrayList<>();
        //当有多行数据时需要分别定义每一行
        Object[] obj=null;
        for(int i = 0; i< cultivarSsrs.size(); i++){
            obj=new Object[baseRow.length+10];
            //将查询到的ssr集合的值分别存在每行的obj数组中
            obj[0]= cultivarSsrs.get(i).getSsrid();
            obj[1]= cultivarSsrs.get(i).getMotif();
            obj[2]= cultivarSsrs.get(i).getRepeats();
            obj[3]= cultivarSsrs.get(i).getRef();
            obj[4]= cultivarSsrs.get(i).getStddev();
            obj[5]= cultivarSsrs.get(i).getMissingrate();
            obj[6]= cultivarSsrs.get(i).getTransferability();
            obj[7]= cultivarSsrs.get(i).getUpstream();
            obj[8]= cultivarSsrs.get(i).getDownstream();
            obj[9]= cultivarSsrs.get(i).getGenus();
            obj[10]= cultivarSsrs.get(i).getSpecies();
            obj[11]= cultivarSsrs.get(i).getDistcultivar1();
            obj[12]= cultivarSsrs.get(i).getDistcultivar2();
            obj[13]= cultivarSsrs.get(i).getDistcultivar3();
            obj[14]= cultivarSsrs.get(i).getDistcultivar4();
            obj[15]= cultivarSsrs.get(i).getDistcultivar5();
            obj[16]= cultivarSsrs.get(i).getDistcultivar6();
            obj[17]= cultivarSsrs.get(i).getDistcultivar7();
            obj[18]= cultivarSsrs.get(i).getDistcultivar8();
            obj[19]= cultivarSsrs.get(i).getDistcultivar9();
            obj[20]= cultivarSsrs.get(i).getDistcultivar10();
            dataList.add(obj);//obj数组的值保存在集合中
        }
        //定义文件名
        String fileName=genus+"_"+specie+String.valueOf(System.currentTimeMillis())+".xls";
        //创建excel对象
        DataExcel excel= new DataExcel(fileName,rowName,dataList,response);
        excel.downloadExcel();
    }

    @Override
    public void batchDownloadSpeciesPrimerExcel(String genus, HttpServletResponse response) {
        List<Primer> primerList = ssrMapper.querySpeciesPrimerByGenusAsc(genus);
        String[] rowName=new String[]{"ssrid","genus","primerFw1","tmFw1","sizeFw1","identityFw1","coverageFw1",
                "primerRe1", "tmRe1","sizeRe1","identityRe1","coverageRe1","produceSize1","start","end",
                "primerFw2","tmFw2","sizeFw2","identityFw2","coverageFw2",
                "primerRe2", "tmRe2","sizeRe2","identityRe2","coverageRe2","produceSize2","start","end",
                "primerFw3","tmFw3","sizeFw3","identityFw3","coverageFw3",
                "primerRe3", "tmRe3","sizeRe3","identityRe3","coverageRe3","produceSize3","start","end",};
        //定义数据内容
        List<Object[]> dataList=new ArrayList<>();

        //将ssrid相等的primer对象分为一组
        Map<String, List<Primer>> map = primerList.stream().collect(Collectors.groupingBy(Primer::getSsrid));
        map.forEach((ssrid,trimerPrimers)->{
            Object[] data = new Object[rowName.length];
            data[0] = ssrid;
            data[1] = genus;
            //循环遍历，分别将三对引物信息写入到数据数组中（一行表格）
            for (int i = 0; i < 3; i++) {
                data[2+i*13]=trimerPrimers.get(i).getPrimerFw();
                data[3+i*13]=trimerPrimers.get(i).getTmFw();
                data[4+i*13]=trimerPrimers.get(i).getSizeFw();
                data[5+i*13]=trimerPrimers.get(i).getIdentityFw();
                data[6+i*13]=trimerPrimers.get(i).getCoverageFw();
                data[7+i*13]=trimerPrimers.get(i).getPrimerRe();
                data[8+i*13]=trimerPrimers.get(i).getTmRe();
                data[9+i*13]=trimerPrimers.get(i).getSizeRe();
                data[10+i*13]=trimerPrimers.get(i).getIdentityRe();
                data[11+i*13]=trimerPrimers.get(i).getCoverageRe();
                data[12+i*13]=trimerPrimers.get(i).getProduceSize();
                data[13+i*13]=trimerPrimers.get(i).getStart();
                data[14+i*13]=trimerPrimers.get(i).getEnd();
            }
            dataList.add(data);
        });
        //定义文件名
        String fileName=genus+"Primer"+String.valueOf(System.currentTimeMillis())+".xls";
        //创建excel对象
        DataExcel excel= new DataExcel(fileName,rowName,dataList,response);
        excel.downloadExcel();
    }

    @Override
    public void batchDownloadCultivarPrimerExcel(String genus, String species, HttpServletResponse response) {
        List<Primer> primerList = ssrMapper.queryCultivarPrimerByScinameAsc(genus,species);
        String[] rowName=new String[]{"ssrid","genus","species","primerFw1","tmFw1","sizeFw1","identityFw1","coverageFw1",
                "primerRe1", "tmRe1","sizeRe1","identityRe1","coverageRe1","produceSize1","start","end",
                "primerFw2","tmFw2","sizeFw2","identityFw2","coverageFw2",
                "primerRe2", "tmRe2","sizeRe2","identityRe2","coverageRe2","produceSize2","start","end",
                "primerFw3","tmFw3","sizeFw3","identityFw3","coverageFw3",
                "primerRe3", "tmRe3","sizeRe3","identityRe3","coverageRe3","produceSize3","start","end",};
        //定义数据内容
        List<Object[]> dataList=new ArrayList<>();

        //将ssrid相等的primer对象分为一组
        Map<String, List<Primer>> map = primerList.stream().collect(Collectors.groupingBy(Primer::getSsrid));
        map.forEach((ssrid,trimerPrimers)->{
            Object[] data = new Object[rowName.length];
            data[0] = ssrid;
            data[1] = genus;
            data[2] = species;
            //循环遍历，分别将三对引物信息写入到数据数组中（一行表格）
            for (int i = 0; i < 3; i++) {
                data[3+i*13]=trimerPrimers.get(i).getPrimerFw();
                data[4+i*13]=trimerPrimers.get(i).getTmFw();
                data[5+i*13]=trimerPrimers.get(i).getSizeFw();
                data[6+i*13]=trimerPrimers.get(i).getIdentityFw();
                data[7+i*13]=trimerPrimers.get(i).getCoverageFw();
                data[8+i*13]=trimerPrimers.get(i).getPrimerRe();
                data[9+i*13]=trimerPrimers.get(i).getTmRe();
                data[10+i*13]=trimerPrimers.get(i).getSizeRe();
                data[11+i*13]=trimerPrimers.get(i).getIdentityRe();
                data[12+i*13]=trimerPrimers.get(i).getCoverageRe();
                data[13+i*13]=trimerPrimers.get(i).getProduceSize();
                data[14+i*13]=trimerPrimers.get(i).getStart();
                data[15+i*13]=trimerPrimers.get(i).getEnd();
            }
            dataList.add(data);
        });
        //定义文件名
        String fileName=genus+species+"Primer"+String.valueOf(System.currentTimeMillis())+".xls";
        //创建excel对象
        DataExcel excel= new DataExcel(fileName,rowName,dataList,response);
        excel.downloadExcel();
    }

}
