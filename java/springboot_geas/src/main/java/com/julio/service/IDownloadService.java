package com.julio.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio
 * @date 2021/1/7-18:06
 **/
public interface IDownloadService {
    public void batchDownloadSpecieSsrExcel(String genus, HttpServletResponse response);
    public void batchDownloadCultivarSsrExcel(String genus,String specie,HttpServletResponse response);
    public void batchDownloadSpeciesPrimerExcel(String genus,HttpServletResponse response);
    public void batchDownloadCultivarPrimerExcel(String genus,String species,HttpServletResponse response);
}
