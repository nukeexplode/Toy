package com.julio.mapper;

import com.julio.domain.Genus;
import com.julio.domain.dto.SpeciesInfoDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julio
 * @date 2020/10/27-19:04
 **/
@Repository
public interface GenusMapper {

    @Select("SELECT * FROM genus")
    public List<Genus> queryAllGenus();//查询所有物种

    @Select("SELECT\n" +
            "\tg.id,\n" +
            "\tg.genus,\n" +
            "\tg.ref AS specie,\n" +
            "\tg.species,\n" +
            "\ts.cultivars,\n" +
            "\ts.refcultivar,\n" +
            "\tg.angiosperm,\n" +
            "\tg.img,\n" +
            "\tg.site AS spsite,\n" +
            "\tg.siteurl AS spsiteurl,\n" +
            "\tg.genotype AS spgenotype,\n" +
            "\t(SELECT count(1) FROM species_ssr where species_ssr.genus=g.genus) as sperecord,\n" +
            "\t(SELECT count(1) FROM cultivar_ssr cs WHERE cs.genus=g.genus AND cs.species = g.ref) as culrecord\n" +
            "FROM\n" +
            "\tgenus g LEFT JOIN species s ON (g.genus=s.genus AND g.ref=s.specie)\n" +
            "WHERE\n" +
            "\tg.hot = 'y'")
    public List<SpeciesInfoDTO> queryHotSpecies(); //查询热点物种

    @Select("SELECT\n" +
            "\tg.id,\n" +
            "\tg.genus,\n" +
            "\tg.ref AS specie,\n" +
            "\tg.angiosperm,\n" +
            "\tg.img,\n" +
            "\tg.site AS spsite,\n" +
            "\tg.siteurl AS spsiteurl,\n" +
            "\tg.genotype AS spgenotype,\n" +
            "\ts.site AS cvsite,\n" +
            "\ts.siteurl AS cvsiteurl,\n" +
            "\ts.genotype AS cvgenotype,\n" +
            "\tg.des,\n" +
            "\t(SELECT count(1) FROM species_ssr where species_ssr.genus=g.genus) as sperecord,\n" +
            "\t(SELECT count(1) FROM cultivar_ssr cs WHERE cs.genus=g.genus AND cs.species = #{ref}) as culrecord\n" +
            "FROM\n" +
            "\tgenus g LEFT JOIN species s ON (g.genus=s.genus AND g.ref=s.specie)\n" +
            "WHERE\n" +
            "\tg.genus = #{genus}")
    public SpeciesInfoDTO querySpeciesInfo(@Param("genus") String genus, @Param("ref") String species); //查看物种详情(仅仅使用属名进行查询)

    @Select("SELECT\n" +
            "\tgenus.genus,\n" +
            "\tgenus.ref,\n" +
            "\tspecies.specie,\n" +
            "\tspecies.refcultivar,\n" +
            "\tgenus.species,\n" +
            "\tspecies.cultivars\n" +
            "FROM\n" +
            "\tgenus\n" +
            "LEFT JOIN species ON genus.genus = species.genus\n" +
            "WHERE\n" +
            "\tgenus.genus = #{genus}")
    public List<SpeciesInfoDTO> queryGenusFamily(String genus); //查看属的家族关系图

    @Select("SELECT\n" +
            "\tg.genus,\n" +
            "\tg.ref,\n" +
            "\ts.specie,\n" +
            "  g.genotype AS spgenotype,\n" +
            "\tg.siteurl AS spsiteurl,\n" +
            "  (SELECT count(1) FROM species_ssr ss WHERE ss.genus = g.genus) AS sperecord,\n" +
            "\t(SELECT count(1) FROM cultivar_ssr cs WHERE cs.genus = g.genus AND cs.species = g.ref) AS culrecord\n" +
            "FROM\n" +
            "\tgenus g\n" +
            "LEFT JOIN species s ON g.genus = s.genus\n" +
            "WHERE\n" +
            "\tg.genus LIKE '%${key}%' OR g.ref LIKE '%${key}%' OR g.species LIKE '%${key}%' OR s.refcultivar LIKE '%${key}%' OR s.cultivars LIKE '%${key}%'")
    public List<SpeciesInfoDTO> searchInfoByKeyword(@Param("key") String keyword); //根据用户输入的关键字进行泛检索


    @Select("SELECT\n" +
            "\tg.genus,\n" +
            "\tg.ref,\n" +
            "\ts.specie,\n" +
            "  g.species,\n" +
            "\ts.refcultivar,\n" +
            "\ts.cultivars,\n" +
            "  (SELECT count(1) FROM species_ssr WHERE species_ssr.genus = g.genus) AS sperecord,\n" +
            "\t(SELECT count(1) FROM cultivar_ssr cs WHERE cs.genus = g.genus AND cs.species = g.ref) AS culrecord\n" +
            "FROM\n" +
            "\tgenus g\n" +
            "LEFT JOIN species s ON g.genus = s.genus\n" +
            "WHERE\n" +
            "\tg.genus LIKE '%${key}%' OR g.ref LIKE '%${key}%' OR g.species LIKE '%${key}%' OR s.refcultivar LIKE '%${key}%' OR s.cultivars LIKE '%${key}%'")
    public List<SpeciesInfoDTO> searchDownloadSSRInfoByKeyword(@Param("key") String keyword);//检索可供下载的SSR信息
}
