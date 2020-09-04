package com.ypgly.yipin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ypgly.yipin.common.GeneralResponse;
import com.ypgly.yipin.entity.UserInfo;
import com.ypgly.yipin.service.impl.UserInfoServiceImpl;
import com.ypgly.yipin.utils.RepEnchUtilImpl;
import com.ypgly.yipin.utils.json.JackJsonUtils;
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
@Api(value = "用户信息管理")
public class UserInfoController {

    private static final Logger log= LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    UserInfoServiceImpl userInfoService;

    @PostMapping(value = "/userInfo/addUserInfo")
    @ApiOperation("新增用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号",required = true),
            @ApiImplicitParam(paramType = "query", name = "userName", value = "用户昵称",  required = true),
            @ApiImplicitParam(paramType = "query", name = "userDistrict", value = "用户所在区服",required = true),
            @ApiImplicitParam(paramType = "query", name = "powerNum", value = "用户总势力",required = true),
            @ApiImplicitParam(paramType = "query", name = "userVipLevel", value = "用户vip等级"),
            @ApiImplicitParam(paramType = "query", name = "doormanNum", value = "用户门客数量",required = true),
            @ApiImplicitParam(paramType = "query", name = "unionName", value = "用户所在联盟名称"),
            @ApiImplicitParam(paramType = "query", name = "unionLevel", value = "用户联盟等级"),
        }
    )
    public GeneralResponse addUserInfo(UserInfo userInfo){

        log.info("进入新增用户");
        GeneralResponse response=null;
        Map<String,String> map=new HashMap<>();
        System.out.println(JackJsonUtils.toJson(userInfo));
        map=userInfoService.addUserInfo(userInfo);
        if(map!=null){
            response=new GeneralResponse(map.get("flag"),map.get("msg"),"","");
        }else{
            response=new GeneralResponse("0","新增用户信息出错，请稍后重试！","","");
        }
        log.info("结束新增用户");
        return response;
    }

    @PostMapping(value = "/userInfo/pageUserInfo")
    @ApiOperation("分页查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号"),
            @ApiImplicitParam(paramType = "query", name = "userDistrict", value = "用户所在区服"),
            @ApiImplicitParam(paramType = "query", name = "doormanNum", value = "用户门客数量"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "size", value = "分页大小",required = true),
    }
    )
    public GeneralResponse pageUserInfo(UserInfo userInfo){
        log.info("进入分页查询用户信息");
        GeneralResponse response=null;
        Map<String,Object> map=new HashMap<>();

        map=userInfoService.pageUserInfo(userInfo);
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
        log.info("结束分页查询用户信息");
        return response;
    }

    @PutMapping(value = "/userInfo/updateUserInfo")
    @ApiOperation("修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id",required = true),
            @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号"),
            @ApiImplicitParam(paramType = "query", name = "userName", value = "用户昵称"),
            @ApiImplicitParam(paramType = "query", name = "userDistrict", value = "用户所在区服"),
            @ApiImplicitParam(paramType = "query", name = "powerNum", value = "用户总势力"),
            @ApiImplicitParam(paramType = "query", name = "userVipLevel", value = "用户vip等级"),
            @ApiImplicitParam(paramType = "query", name = "doormanNum", value = "用户门客数量"),
            @ApiImplicitParam(paramType = "query", name = "unionName", value = "用户所在联盟名称"),
            @ApiImplicitParam(paramType = "query", name = "unionLevel", value = "用户联盟等级"),
    }
    )
    public GeneralResponse updateUserInfo(UserInfo userInfo){
        log.info("进入修改用户信息");
        GeneralResponse response=null;
        Map<String,String> map=new HashMap<>();
        map=userInfoService.updateUserInfo(userInfo);
        if(map!=null){
            if("1".equals(map.get("flag").toString())){
                response=new GeneralResponse("1","修改成功","","");
            }else{
                response=new GeneralResponse("0",map.get("msg").toString(),"","");
            }
        }else{
            response=new GeneralResponse("0","修改信息出错","","");
        }
        return response;
    }

    @DeleteMapping(value = "/userInfo/deleteUserInfo")
    @ApiOperation("删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "ids", value = "用户id",required = true),
    }
    )
    public GeneralResponse deleteUserInfo(String ids){
        log.info("删除用户信息");
        GeneralResponse response=null;
        if(ids==null||"".equals(ids)){
            return response=new GeneralResponse("0","请选择您要删除的用户！","","");
        }
        ids=RepEnchUtilImpl.replace1(ids);
        List<Map<String,String>> list=new ArrayList<>();
        String[] isDelIds=ids.split(",");
        int errorsize=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<isDelIds.length;i++){
            Map<String,String> map1 = new HashMap<String, String>();
            try{
                UserInfo userInfo= userInfoService.getUserInfoById(Integer.valueOf(isDelIds[i]));
                if(userInfo!=null){
                    map1= userInfoService.deleteUserInfo(Integer.valueOf(isDelIds[i]));
                    if("0".equals(map1.get("flag"))){
                        map1.put("error","id号为"+isDelIds[i]+"用户删除失败，请联系管理员！");
                        errorsize++;
                        list.add(map1);
                    }
                }else{
                    map1.put("error","id号为"+isDelIds[i]+"用户不存在！");
                    errorsize++;
                    list.add(map1);
                }
            }catch (Exception e){
                map1.put("error","id号为"+isDelIds[i]+"用户删除异常！");
                errorsize++;
                list.add(map1);
            }
        }
        if(list.size()<=0){
            map.put("flag","1");
            map.put("msg","用户删除成功");
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"","");
        }else if(list.size()==isDelIds.length){
            map.put("flag","0");
            map.put("msg","所有用户删除失败，请稍后尝试");
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"","");
        }else if(list.size()>0&&list.size()<isDelIds.length){
            map.put("flag","1");
            map.put("msg","部分用户删除失败！");
            map.put("errorList",list);
            map.put("errorSize",errorsize);
            JSONObject data=new JSONObject();
            JSONArray errorList= JSON.parseArray(list.toString());
            data.put("errorSize",map.get("errorSize"));
            data.put("errorList",errorList);
            response=new GeneralResponse(map.get("flag").toString(),map.get("msg").toString(),"",data);
        }
        log.info("结束用户删除！");
        return response;
    }

}
