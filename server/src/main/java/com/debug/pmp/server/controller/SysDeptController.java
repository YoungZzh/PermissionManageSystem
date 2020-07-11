package com.debug.pmp.server.controller;

import com.debug.pmp.common.response.BaseResponse;
import com.debug.pmp.common.response.StatusCode;
import com.debug.pmp.common.utils.ValidatorUtil;
import com.debug.pmp.model.entity.SysDeptEntity;
import com.debug.pmp.server.service.SysDeptService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Author:Young
 * Date:2020/7/3-21:56
 */
@Controller
@ResponseBody
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController{

    @Autowired
    private SysDeptService sysDeptService;

    //获取一级部门/顶级部门的deptId
    @RequestMapping("/info")
    public BaseResponse info(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap = Maps.newHashMap();
        Long deptId = 0L;
        try {


        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        resMap.put("deptId",deptId);
        response.setData(resMap);
        return response;
    }

    //部门列表
    @RequestMapping("/list")
    public List<SysDeptEntity> list(){
        return sysDeptService.queryAll(Maps.newHashMap());
    }

    //获取部门树
    @RequestMapping("/select")
    public BaseResponse select(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap = Maps.newHashMap();

        List<SysDeptEntity> deptList = Lists.newLinkedList();
        try {
            deptList = sysDeptService.queryAll(Maps.newHashMap());

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        resMap.put("deptList",deptList);
        response.setData(resMap);

        return response;
    }

    //新增
    @RequestMapping(value = "/save",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse save(@RequestBody @Validated SysDeptEntity entity, BindingResult result){
        String res = ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }

        BaseResponse response = new BaseResponse((StatusCode.Success));
        try {
            log.info("新增部门~接收到数据：{}",entity);

            sysDeptService.save(entity);
        }catch (Exception e){
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //获取详情
    @RequestMapping("/detail/{deptId}")
    @ResponseBody
    public BaseResponse detail(@PathVariable Long deptId){
        /*if (deptId == null && deptId < 0){
            return new BaseResponse(StatusCode.InvalidParams);
        }*/
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap = Maps.newHashMap();
        try {
            //log.info("");

            resMap.put("dept",sysDeptService.getById(deptId));
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);

        return response;
    }

    //修改
    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse update(@RequestBody @Validated SysDeptEntity entity,BindingResult result){
        String res = ValidatorUtil.checkResult(result);
        if(StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }
        if (entity.getDeptId() == null || entity.getDeptId() <= 0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            log.info("修改部门~接收到数据：{}",entity);

            sysDeptService.updateById(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //删除
    @RequestMapping(value = "/delete")
    public BaseResponse delete(Long deptId){
        if (deptId == null || deptId <= 0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            log.info("删除部门~接收到数据：{}",deptId);

            //如果当前部门有子部门，则需要要求先删除下面的所有子部门，再删除当前部门
            List<Long> subIds = sysDeptService.queryDeptIds(deptId);
            if (subIds != null && !subIds.isEmpty()){
                return new BaseResponse(StatusCode.DeptHasSubDeptCanNotBeDelete);
            }
            sysDeptService.removeById(deptId);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
