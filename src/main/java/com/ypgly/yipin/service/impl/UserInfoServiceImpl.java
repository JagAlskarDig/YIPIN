package com.ypgly.yipin.service.impl;

import com.ypgly.yipin.entity.UserInfo;
import com.ypgly.yipin.mapper.UserInfoMapper;
import com.ypgly.yipin.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzy
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private Logger log= LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public Map<String, String> addUserInfo(UserInfo userInfo) {

        Map<String, String> map=new HashMap<>(2);
        try {
            boolean checkUser=false;
            List<UserInfo> userList=userInfoMapper.getUserInfoByUserNum(userInfo.getUserNumber(),userInfo.getUserDistrict());
            if(userList.size()>0){
                map.put("flag","0");
                map.put("msg","该用户已经存在！");
            }else {
                userInfoMapper.addUserInfo(userInfo);
                Integer userInfoId=userInfo.getId();
                if(userInfoId!=null&&userInfoId>0){
                    map.put("flag","1");
                    map.put("msg","新增用户信息！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("新增用户信息出错！"+e.getMessage());
            map.put("flag","1");
            map.put("msg","新增用户信息出错，请稍后尝试！");
        }
        return map;
    }
}