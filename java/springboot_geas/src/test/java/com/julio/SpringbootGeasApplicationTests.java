package com.julio;

import com.julio.domain.Genus;
import com.julio.domain.dto.SpeciesInfoDTO;
import com.julio.domain.rna.Rna;
import com.julio.mapper.GenomeMapper;
import com.julio.mapper.GenusMapper;
import com.julio.mapper.RnaMapper;
import com.julio.mapper.SsrMapper;
import com.julio.utils.Jutils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootGeasApplication.class)
class SpringbootGeasApplicationTests {

    @Autowired
    private SsrMapper ssrMapper;
    @Autowired
    private RnaMapper rnadao;


    @Autowired
    private GenusMapper genusMapper;

    @Autowired
    private GenomeMapper genomeMapper;
    @Test
    void contextLoads() {
        String angiosperm="All";
        String genus="vitis";
        String scientificname=null;

        List<Rna> rnas = rnadao.queryRnas(angiosperm, genus, scientificname);
        for (Rna rna : rnas) {
            System.out.println(rna);
        }
    }

    @Test
    void test(){
        String vmName = ManagementFactory.getRuntimeMXBean().getVmName();
        //System.out.println(Runtime.getRuntime().totalMemory()/1024/1024/1024);
        System.out.println(vmName);
    }

    @Test
    void testStream(){
        ArrayList<String> organizms = new ArrayList<>(); //新建集合储存所有物种名
        List<Genus> genumaps = genusMapper.queryAllGenus();
        for (Genus genusmap : genumaps) {
            String[] organArray = genusmap.getSpecies().split("\\|"); //切分数组
            String genus = genusmap.getGenus();
            String ref = genusmap.getRef();
            organizms.add(genus+" "+ref);
            for (int i=0;i<organArray.length;i++){
                organizms.add(genus+" "+organArray[i]);
            }
        }
        System.out.println(organizms);

    }

    @Test
    void queryGenome(){
//        List<Genome> vitis = genomeMapper.queryGenome("vitis");
//        System.out.println(vitis.size());
        String param="Vitis Vinifera";
        String[] split = param.split("\\s+");
        SpeciesInfoDTO infoDTO = genusMapper.querySpeciesInfo(split[0], split[1]);
        System.out.println(infoDTO.toString());
    }

    @Test
    void queryMotifs(){
    }

    @Test
    void searchInfoByOrganizm(){
        List<SpeciesInfoDTO> vitis = genusMapper.searchInfoByKeyword("viti");
        vitis.stream().map((v)->{
            if (!Jutils.nullOrEmpty(v.getSpecies())){
                v.setSpecies(v.getSpecies().replace("|",","));
            }
            if (!Jutils.nullOrEmpty(v.getCultivars())){
                v.setCultivars(v.getCultivars().replace("|",","));
            }
            return v;
        }).collect(Collectors.toList());
        System.out.println(vitis.toString());
    }

    @Test
    void testMergeArray(){
        String[] str1=new String[]{"java","python","js"};
        String[] str2=new String[]{"css","html","jquery"};
        String[] strings = Jutils.mergeArray(str1, str2);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
        System.out.println(strings.toString());
    }

}