package com.julio.mapper.sqlprovider;

import java.util.Map;

/**
 * @author Julio
 * @date 2020/2/16-19:58
 **/
public class RnaSqlProvider {
//    public String queryRnasSql(Map<String,String> map) {
//        return new SQL() {
//            {
//                SELECT("*");
//                FROM("mrna");
//                if (!"All".equals(map.get("angiosperm"))) {
//                    WHERE("angiosperm like \"%\"#{angiosperm}\"%\"");
//                }
//                if (map.get("genus") != null && !"".equals(map.get("genus"))) {
//                    WHERE("genus like \"%\"#{genus}\"%\"");
//                }
//                if (map.get("scientificname") != null && !"".equals(map.get("scientificname"))) {
//                WHERE("scientificname like \"%\"#{scientificname}\"%\"");
//            }
//            }
//        }.toString();
//    }
        public String queryRnasSql(Map<String,String> map) {
            String sql = "select * from mrna where 1=1 ";
            StringBuilder sb = new StringBuilder(sql);
            String angiosperm= map.get("angiosperm");
            String genus= map.get("genus");
            String scientificname= map.get("scientificname");

            if (!"All".equals(angiosperm) && !"".equals(angiosperm) && angiosperm != null) {//判断是否有值
                sb.append(" and angiosperm like '" + angiosperm + "'");
            }
            if (genus != null && !"".equals(genus)) {
                sb.append(" and genus like '%" + genus + "%'");
            }
            if (scientificname != null && !"".equals(scientificname)) {
                sb.append(" and scientificname like '%" + scientificname + "%'");
            }
            sql=sb.toString();
            return sql;
        }
}
