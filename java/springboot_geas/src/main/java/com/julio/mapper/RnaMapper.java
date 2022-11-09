package com.julio.mapper;

import com.julio.domain.rna.Rna;
import com.julio.mapper.sqlprovider.RnaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julio
 * @date 2020/3/14-13:09
 **/
@Repository
public interface RnaMapper {
    @SelectProvider(type = RnaSqlProvider.class,method = "queryRnasSql")
    public List<Rna> queryRnas(@Param("angiosperm") String angiosperm, @Param("genus") String genus,
                               @Param("scientificname") String scientificname);

}
