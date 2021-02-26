package org.jayjay.air.common.handler;

import org.jayjay.air.common.entity.ResultModel;
import org.jayjay.air.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: JayJay
 * @Date: 26/2/2021
 * @ClassName: CustomException
 * @Description: 异常控制处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler  {


    /**
     * 捕捉校验异常(BindException)
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultModel validException(BindException e) {
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return ResultModel.error(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }

    /**
     * 捕捉校验异常(MethodArgumentNotValidException)
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel validException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return ResultModel.error(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }

    /**
     * 捕捉其他所有自定义异常
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResultModel handle(CustomException e) {
        e.printStackTrace();
        return ResultModel.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 捕捉404异常
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultModel handle(NoHandlerFoundException e) {
        e.printStackTrace();
        return ResultModel.error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

//    /**
//     * 捕捉其他所有异常
//     * @param request
//     * @param ex
//     * @return
//     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ResultModel globalException(HttpServletRequest request, Throwable ex) {
//        ex.printStackTrace();
//        return ResultModel.error(this.getStatus(request).value(), ex.toString() + ": " + ex.getMessage());
//    }

    /**
     * 获取状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 获取校验错误信息
     * @param fieldErrors
     * @return
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> result = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage()).append(".");
        }
        result.put("errorList", errorList);
        result.put("errorMsg", errorMsg);
        return result;
    }
}
