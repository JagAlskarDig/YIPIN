package com.ypgly.yipin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ypgly.yipin.entity.GuestInfo;
import com.ypgly.yipin.mapper.GuestInfoMapper;
import com.ypgly.yipin.service.GuestInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzy
 */
@Service
public class GuestInfoServiceImpl implements GuestInfoService {

    private Logger log= LoggerFactory.getLogger(GuestInfoServiceImpl.class);

    @Resource
    GuestInfoMapper guestInfoMapper;
    @Override
    public Map<String, String> addGuestInfo(GuestInfo guestInfo) {
        Map<String, String> map=new HashMap<>(2);
        try {
            boolean checkUser=false;
            List<GuestInfo> userList=guestInfoMapper.getGuestInfoByUserNum(guestInfo.getUserNumber(),guestInfo.getDoormanName());
            if(userList.size()>0){
                map.put("flag","0");
                map.put("msg","该用户门客已经存在！");
            }else {
                guestInfoMapper.addGuestInfo(guestInfo);
                Integer userInfoId=guestInfo.getId();
                if(userInfoId!=null&&userInfoId>0){
                    map.put("flag","1");
                    map.put("msg","添加成功！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("新增门客信息出错！"+e.getMessage());
            map.put("flag","1");
            map.put("msg","新增门客信息出错，请稍后尝试！");
        }
        return map;
    }

    @Override
    public Map<String, Object> pageGuestInfo(GuestInfo guestInfo) {
        Map<String, Object> map=new HashMap<>();
        try {
            int pageNum=1;
            int pageSize=10;
            if(guestInfo.getPageNo()!=null&&!"".equals(guestInfo.getPageNo())) {
                pageNum=Integer.valueOf(guestInfo.getPageNo());
            }
            if(guestInfo.getSize()!=null&&!"".equals(guestInfo.getSize())) {
                pageSize=Integer.valueOf(guestInfo.getSize());
            }
            PageHelper.startPage(pageNum, pageSize);
            List<GuestInfo> list=new ArrayList<>();
            list=guestInfoMapper.pageGuestInfo(guestInfo);
            PageInfo<GuestInfo> pageInfo = new PageInfo<GuestInfo>(list);
            map.put("pageInfo",pageInfo);
            map.put("flag","1");
            map.put("msg","查询成功");
        }catch (Exception e){
            log.error("分页查询门客信息错误！"+e.getMessage());
            map.put("flag","0");
            map.put("msg","分页查询门客信息出错，请稍后尝试！");
        }
        return map;
    }

    @Override
    public Map<String, String> updateGuestInfo(GuestInfo guestInfo) {
        Map<String, String> map=new HashMap<>(2);
        try {
            guestInfoMapper.updateGuestInfo(guestInfo);
            map.put("flag","1");
            map.put("msg","修改成功");
        }catch (Exception e){
            log.error("修改门客信息失败！"+e.getMessage());
            map.put("flag","0");
            map.put("msg","修改门客信息失败");
        }
        return map;
    }

    @Override
    public GuestInfo getGuestInfoById(Integer id) {

        GuestInfo guestInfo=null;
        try{
            guestInfo=guestInfoMapper.getGuestInfoById(id);
        }catch (Exception e){
            log.error("根据用户id查询用户出错！"+e.getMessage());
        }
        return guestInfo;
    }

    @Override
    public Map<String, String> deleteGuestInfo(Integer id) {
        Map<String,String> map=new HashMap<>();
        try {
            int i = guestInfoMapper.deleteGuestInfo(id);
            if (i <= 0) {
                map.put("flag", "0");
                map.put("msg", "删除失败");
            } else {
                map.put("flag", "1");
                map.put("msg", "删除成功");
            }
        }catch (Exception e){
            map.put("flag","0");
            map.put("msg","删除失败");
            log.error("删除文本失败"+e.getMessage());
        }
        return map;
    }


}
