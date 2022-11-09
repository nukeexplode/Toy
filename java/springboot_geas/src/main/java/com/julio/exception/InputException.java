package com.julio.exception;

/**处理用户的非正常输入导致的异常
 * @author Julio
 * @date 2020/3/21-22:09
 **/
public class InputException extends RuntimeException{
    public InputException(String message) {
        super(message);
    }
}
