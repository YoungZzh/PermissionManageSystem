package com.debug.pmp.server.controller;

import com.debug.pmp.common.response.BaseResponse;
import com.debug.pmp.common.response.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:Young
 * Date:2020/6/23-23:07
 */
@RequestMapping("/base")
@Controller
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse info(String name){
        BaseResponse response = new BaseResponse(StatusCode.Success);

        if (StringUtils.isBlank(name)){
            name = "权限管理平台！";
        }
        response.setData(name);

        return response;
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public String page(String name, ModelMap modelMap){
        if (StringUtils.isBlank(name)){
            name = "权限管理平台！";
        }
        modelMap.put("name",name);
        modelMap.put("app",name);
        return "pageOne";
    }
}
