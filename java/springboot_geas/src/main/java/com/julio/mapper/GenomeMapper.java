package com.julio.mapper;

import com.julio.domain.Genome;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julio
 * @date 2020/11/11-15:58
 **/
@Repository
public interface GenomeMapper {

    @Select("SELECT * FROM genome WHERE scientificname LIKE '%${scientificname}%'")
    public List<Genome> queryGenome(String scientificname);

    @Select("SELECT * FROM genome WHERE genus = #{genus}")
    public List<Genome> queryGenomeByGenus(String genus);
}
