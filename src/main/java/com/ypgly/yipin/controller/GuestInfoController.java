package com.ypgly.yipin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ypgly.yipin.common.GeneralResponse;
import com.ypgly.yipin.entity.GuestInfo;
import com.ypgly.yipin.entity.UserInfo;
import com.ypgly.yipin.service.impl.GuestInfoServiceImpl;
import com.ypgly.yipin.utils.RepEnchUtilImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzy
 */
@RestController
@Api(value = "门客信息管理")
public class GuestInfoController {
    private static final Logger log= LoggerFactory.getLogger(GuestInfoController.class);

    @Resource
    GuestInfoServiceImpl guestInfoService;

    @PostMapping(value = "/guestInfo/addGuestInfo")
    @ApiOperation("新增门客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号",required = true),
            @ApiImplicitParam(paramType = "query", name = "doormanName", value = "门客名称",  required = true),
            @ApiImplicitParam(paramType = "query", name = "qualification", value = "门客资质",required = true),
            @ApiImplicitParam(paramType = "query", name = "bloodNum", value = "门客血量",required = true),
            @ApiImplicitParam(paramType = "query", name = "isSuit", value = "是否套装门客",required = true)
    }
    )
    public GeneralResponse addGuestInfo(GuestInfo guestInfo){
        log.info("进入新增门客");
        GeneralResponse response=null;
        Map<String,String> map=new HashMap<>();
        map=guestInfoService.addGuestInfo(guestInfo);
        if(map!=null){
            response=new GeneralResponse(map.get("flag"),map.get("msg"),"","");
        }else{
            response=new GeneralResponse("0","新增门客信息出错，请稍后重试！","","");
        }
        log.info("结束新增门客");
        return response;
    }

    @PostMapping(value = "/guestInfo/pageGuestInfo")
    @ApiOperation("分页查询门客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号"),
            @ApiImplicitParam(paramType = "query", name = "doormanName", value = "门客名称"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "size", value = "分页大小",required = true),
    }
    )
    public GeneralResponse pageUserInfo(GuestInfo guestInfo){
        log.info("进入分页查询门客信息");
        GeneralResponse response=null;
        Map<String,Object> map=new HashMap<>();

        map=guestInfoService.pageGuestInfo(guestInfo);
        if(map!=null){
            if("1".equals(map.get("flag").toString())){
                String s= JSONObject.toJSONString(map.get("pageInfo"));
                JSONObject allObject = JSONObject.parseObject(s);
                JSONObject outObject=new JSONObject();
                outObject.put("total",allObject.getString("total"));
                outObject.put("pages",allObject.getString("pages"));
                outObject.put("pageNum",allObject.getString("pageNum"));
                outObject.put("pageSize",allObject.getString("pageSize"));
                outObject.put("list",allObject.getJSONArray("list"));
                response=new GeneralResponse("1","查询成功","",outObject);
            }else{
                response=new GeneralResponse("0",map.get("msg").toString(),"","");
            }
        }else{
            response=new GeneralResponse("0","分页推荐热词出错","","");
        }
        log.info("结束分页查询门客信息");
        return response;
    }

    @PutMapping(value = "/guestInfo/updateGuestInfo")
    @ApiOperation("修改门客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "门客id",required = true),
            @ApiImplicitParam(paramType = "query", name = "qualification", value = "门客资质"),
            @ApiImplicitParam(paramType = "query", name = "bloodNum", value = "门客血量"),
            @ApiImplicitParam(paramType = "query", name = "isSuit", value = "是否套装门客")
    }
    )
    public GeneralResponse updateUserInfo(GuestInfo guestInfo){
        log.info("进入修改门客信息");
        GeneralResponse response=null;
        Map<String,String> map=new HashMap<>();
        map=guestInfoService.updateGuestInfo(guestInfo);
        if(map!=null){
            if("1".equals(map.get("flag").toString())){
                response=new GeneralResponse("1","修改成功","","");
            }else{
                response=new GeneralResponse("0",map.get("msg").toString(),"","");
            }
        }else{
            response=new GeneralResponse("0","修改信息出错","","");
        }
        log.info("结束修改门客信息");
        return response;
    }

    @DeleteMapping(value = "/guestInfo/deleteGuestInfo")
    @ApiOperation("删除门客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "ids", value = "门客id",required = true),
    }
    )
    public GeneralResponse deleteUserInfo(String ids){
        log.info("删除门客信息");
        GeneralResponse response=null;
        if(ids==null||"".equals(ids)){
            return response=new GeneralResponse("0","请选择您要删除的门客！","","");
        }
        ids= RepEnchUtilImpl.replace1(ids);
        List<Map<String,String>> list=new ArrayList<>();
        String[] isDelIds=ids.split(",");
        int errorsize=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<isDelIds.length;i++){
            Map<String,String> map1 = new HashMap<String, String>();
            try{
                GuestInfo userInfo= guestInfoService.getGuestInfoById(Integer.valueOf(isDelIds[i]));
                if(userInfo!=null){
                    map1= guestInfoService.deleteGuestInfo(Integer.valueOf(isDelIds[i]));
                    if("0".equals(map1.get("flag"))){
                        map1.put("error","id号为"+isDelIds[i]+"门客删除失败！");
                        errorsize++;
                        list.add(map1);
                    }
                }else{
                    map1.put("error","id号为"+isDelIds[i]+"用户不存在！");
                    errorsize++;
                    list.add(map1);
                }
            }catch (Exception e){
                map1.put("error","id号为"+isDelIds[i]+"门客删除异常！");
                errorsize++;
                list.add(map1);
            }
        }
        if(list.size()<=0){
            map.put("flag","1");
            map.put("msg","门客删除成功");
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"","");
        }else if(list.size()==isDelIds.length){
            map.put("flag","0");
            map.put("msg","所有门客删除失败，请稍后尝试");
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"","");
        }else if(list.size()>0&&list.size()<isDelIds.length){
            map.put("flag","1");
            map.put("msg","部分门客删除失败！");
            map.put("errorList",list);
            map.put("errorSize",errorsize);
            JSONObject data=new JSONObject();
            JSONArray errorList= JSON.parseArray(list.toString());
            data.put("errorSize",map.get("errorSize"));
            data.put("errorList",errorList);
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"",data);
        }
        log.info("结束门客删除！");
        return response;
    }
}
