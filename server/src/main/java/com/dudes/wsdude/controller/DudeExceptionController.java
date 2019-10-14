package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DudeExceptionController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    public DudeExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = "error")
    @ResponseBody
    public ExceptionResponse error(WebRequest webRequest, HttpServletResponse response) {
        return new ExceptionResponse(response.getStatus(), getErrorAttributes(webRequest));
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.putAll(errorAttributes.getErrorAttributes(webRequest, true));
        return errorMap;
    }
}
