package com.julio.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**自定义异常消息，存放公共异常属性
 * @author Julio
 * @date 2020/3/21-22:20
 **/
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map=super.getErrorAttributes(webRequest, includeStackTrace);
        //在原有的异常异常基础上添加如下属性
        map.put("group","com.julio");
        //从前面自定义的MyExceptionHandler中获取放在request中的错误信息，并设定作用域。0是request；1是session域
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("error", 0);
        map.put("error",ext);//再统一放到map
        return map;
    }
}
