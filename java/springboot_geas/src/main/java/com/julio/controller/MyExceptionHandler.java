package com.julio.controller;

import com.julio.exception.InputException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**异常处理器
 * @author Julio
 * @date 2020/3/21-22:13
 **/
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @Author Julio
     * @Description 处理查询条件输入异常
     * @return String str
     */
    @ExceptionHandler(InputException.class)
    public String handleInputException(Exception e, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        request.setAttribute("javax.servlet.error.status_code",500);
        request.setAttribute("error",map);
        return "forward:/error";//交给spring自动异常处理流程
    }
}
