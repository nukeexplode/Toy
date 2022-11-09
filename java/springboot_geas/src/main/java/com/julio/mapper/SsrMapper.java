package com.julio.mapper;

import com.julio.domain.Species;
import com.julio.domain.ssr.Primer;
import com.julio.domain.ssr.CultivarSsr;
import com.julio.domain.ssr.SpeciesSsr;
import com.julio.mapper.sqlprovider.SsrSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Julio
 * @date 2020/3/14-12:57
 **/
@Repository
public interface SsrMapper {

    @SelectProvider(type = SsrSqlProvider.class,method = "querySpeciesSsrsSql")
    public List<SpeciesSsr> querySpeciesSsr(@Param("genus") String genus, @Param("type") int type, @Param("motif") String motif,
                                            @Param("conditions") List<String> conditions);//根据参数动态查询物种SSR

    @SelectProvider(type = SsrSqlProvider.class,method = "queryCultivarSsrsSql")
    public List<CultivarSsr> queryCultivarSsr(@Param("genus") String genus, @Param("species") String species, @Param("type") int type,
                                              @Param("motif") String motif, @Param("conditions") List<String> conditions);//根据参数动态查询品种SSR

    @Select("select distorganizm1,distorganizm2,distorganizm3,distorganizm4,distorganizm5," +
            "distorganizm6,distorganizm7,distorganizm8,distorganizm9,distorganizm10 from species_ssr where ssrid = #{ssrid} ")
    public SpeciesSsr querySpeciesDistBySsrid(@Param("ssrid") String ssrid);//根据id查ssr在不同物种分布情况

    @Select("select distcultivar1,distcultivar2,distcultivar3,distcultivar4,distcultivar5," +
            "distcultivar6,distcultivar7,distcultivar8,distcultivar9,distcultivar10 from cultivar_ssr where ssrid = #{ssrid} ")
    public CultivarSsr queryCultivarDistBySsrid(@Param("ssrid") String ssrid);//根据id查ssr在不同品种分布情况

    @Select("select genus from species_ssr where ssrid = #{ssrid}")
    public String queryGenusBySsrid(@Param("ssrid") String ssrid);//根据id查ssr的所属

    @Select("select genus,species AS specie from cultivar_ssr where ssrid = #{ssrid}")
    public Species querySpeciesBySsrid(@Param("ssrid") String ssrid);//根据品种ssrid查ssr的所属物种

    @Select("select species from genus where genus = #{genus}")
    public String queryDistOrganizm(@Param("genus") String genus);//根据属名查其有多少分布物种

    @Select("select cultivars from species where genus = #{genus} and specie = #{specie}")
    public String queryDistCultivar(String genus,String specie);//查看某个物种有哪些品种


    @Select("select * from species_primer where ssrid = #{ssrid}")
    @Results(id="spePrimerMap",value={
            @Result(column="ssrid",property = "ssrid"),
            @Result(column="genus",property = "genus"),
            @Result(column="primer_fw",property = "primerFw"),
            @Result(column="tm_fw",property = "tmFw"),
            @Result(column="size_fw",property = "sizeFw"),
            @Result(column="average_identity_fw",property = "identityFw"),
            @Result(column="average_coverage_fw",property = "coverageFw"),
            @Result(column="primer_re",property = "primerRe"),
            @Result(column="tm_re",property = "tmRe"),
            @Result(column="size_re",property = "sizeRe"),
            @Result(column="average_identity_re",property = "identityRe"),
            @Result(column="average_coverage_re",property = "coverageRe"),
            @Result(column="product_size",property = "produceSize"),
            @Result(column="start",property = "start"),
            @Result(column="end",property = "end"),
    })
    public List<Primer> querySpeciesPrimer(String ssrid);//根据ssrid查询引物

    @Select("select * from cultivar_primer where ssrid = #{ssrid}")
    @Results(id="CulPrimerMap",value={
            @Result(column="ssrid",property = "ssrid"),
            @Result(column="genus",property = "genus"),
            @Result(column="species",property = "species"),
            @Result(column="primer_fw",property = "primerFw"),
            @Result(column="tm_fw",property = "tmFw"),
            @Result(column="size_fw",property = "sizeFw"),
            @Result(column="average_identity_fw",property = "identityFw"),
            @Result(column="average_coverage_fw",property = "coverageFw"),
            @Result(column="primer_re",property = "primerRe"),
            @Result(column="tm_re",property = "tmRe"),
            @Result(column="size_re",property = "sizeRe"),
            @Result(column="average_identity_re",property = "identityRe"),
            @Result(column="average_coverage_re",property = "coverageRe"),
            @Result(column="product_size",property = "produceSize"),
            @Result(column="start",property = "start"),
            @Result(column="end",property = "end"),
    })
    public List<Primer> queryCultivarPrimer(String ssrid);//根据ssrid查品种SSR的引物

    @Select("SELECT * FROM species_primer WHERE genus = #{genus} ORDER BY (CONVERT(SUBSTR(ssrid,7),UNSIGNED))")
    @ResultMap(value = "spePrimerMap")
    public List<Primer> querySpeciesPrimerByGenusAsc(String genus);//根据属名查询物种SSR的引物,并按ssrid升序排序

    @Select("SELECT * FROM cultivar_primer WHERE genus = #{genus} AND species = #{species} ORDER BY (CONVERT(SUBSTR(ssrid,7),UNSIGNED))")
    @ResultMap(value = "CulPrimerMap")
    public List<Primer> queryCultivarPrimerByScinameAsc(String genus,String species);//根据属名、物种名查询品种SSR的引物,并按ssrid升序排序

    @Select("select * from species_ssr where ssrid = #{ssrid}")
    public List<SpeciesSsr> querySpecieSsrById(String ssrid);//根据SSRID查询物种SSR数据

    @Select("SELECT motif,count(1) AS total FROM species_ssr WHERE genus = #{genus} GROUP BY motif ORDER BY total DESC LIMIT #{num}")
    public List<Map<String,Integer>> querySpeciesMotifsCount(int num,String genus); //查询某个属的SSR的不同基序出现次数排序

    @Select("SELECT motif,count(1) AS total FROM cultivar_ssr WHERE genus = #{genus} AND species = #{species} GROUP BY motif ORDER BY total DESC LIMIT #{num};")
    public List<Map<String,Integer>> queryCultivarMotifsCount(int num,String genus,String species); //查询某个物种的SSR基序在其不同品种间的重复次数排序
}
