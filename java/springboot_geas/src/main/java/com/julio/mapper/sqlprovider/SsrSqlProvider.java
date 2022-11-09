package com.julio.mapper.sqlprovider;

import com.julio.utils.Jutils;

import java.util.List;
import java.util.Map;

/**
 * ssr的sql语句帮助类
 *
 * @author Julio
 * @date 2020/2/10-19:03
 **/
public class SsrSqlProvider {

    public String querySpeciesSsrsSql(Map<String, Object> params) {
        String sql = "select * from species_ssr where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        String motif= (String) params.get("motif");
        String genus= (String) params.get("genus");
        int type= (Integer) params.get("type");

        if (!Jutils.nullOrEmpty(motif)) {//判断是否有值
            sb.append(" AND motif LIKE '" + motif + "'");
        }
        if (!Jutils.nullOrEmpty(genus)) {
            sb.append(" and genus like '%" + genus + "%'");
        }
        if (type > 0 && Jutils.nullOrEmpty(motif)){ //当用户没有输入motif时才生效
            sb.append(" AND LENGTH(motif) = "+type);
        }
        List<String> conditions = (List<String>) params.get("conditions");
        for (String condition : conditions) {
            if(condition!=null&&!"".equals(condition)){
                //有值，则拼接sql语句
                sb.append(" and "+condition);
            }
        }
        sql=sb.toString();
        return sql;
    }

    public String queryCultivarSsrsSql(Map<String,Object> params){
        String sql = "select * from cultivar_ssr where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        String genus= (String) params.get("genus");
        String species= (String) params.get("species");
        int type= (Integer) params.get("type");
        String motif= (String) params.get("motif");
        //获取form1的值
        if (species != null && !"".equals(species)) {//判断是否有值
            sb.append(" and species like '" + species + "'");
        }
        if (genus != null && !"".equals(genus)) {
            sb.append(" and genus like '%" + genus + "%'");
        }
        if (type > 0 && Jutils.nullOrEmpty(motif)){ //当用户没有输入motif时才生效
            sb.append(" AND LENGTH(motif) = "+type);
        }
        if (!Jutils.nullOrEmpty(motif)){
            sb.append(" AND motif LIKE '" + motif + "'");
        }
        List<String> conditions = (List<String>) params.get("conditions");
        for (String condition : conditions) {
            if(condition!=null&&!"".equals(condition)){
                //有值，则拼接sql语句
                sb.append(" and "+condition);
            }
        }
        sql=sb.toString();
        return sql;
    }

}
