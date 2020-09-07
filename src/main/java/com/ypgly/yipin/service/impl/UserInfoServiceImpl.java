package com.ypgly.yipin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ypgly.yipin.entity.UserInfo;
import com.ypgly.yipin.mapper.UserInfoMapper;
import com.ypgly.yipin.service.UserInfoService;
import com.ypgly.yipin.utils.file.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

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
                }else {
                    map.put("flag","0");
                    map.put("msg","新增玩家信息失败！");
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

    @Override
    public Map<String, Object> pageUserInfo(UserInfo userInfo) {
        Map<String, Object> map=new HashMap<>();
        try {
            int pageNum=1;
            int pageSize=10;
            if(userInfo.getPageNo()!=null&&!"".equals(userInfo.getPageNo())) {
                pageNum=Integer.valueOf(userInfo.getPageNo());
            }
            if(userInfo.getSize()!=null&&!"".equals(userInfo.getSize())) {
                pageSize=Integer.valueOf(userInfo.getSize());
            }
            PageHelper.startPage(pageNum, pageSize);
            List<UserInfo> list=new ArrayList<>();
            list=userInfoMapper.pageUserInfo(userInfo);
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
            map.put("pageInfo",pageInfo);
            map.put("flag","1");
            map.put("msg","查询成功");
        }catch (Exception e){
            log.error("分页查询用户信息错误！"+e.getMessage());
            map.put("flag","0");
            map.put("msg","分页查询用户信息出错，请稍后尝试！");
        }
        return map;
    }

    @Override
    public Map<String, String> updateUserInfo(UserInfo userInfo) {
        Map<String, String> map=new HashMap<>(2);
        try {
            userInfoMapper.updateUserInfo(userInfo);
            map.put("flag","1");
            map.put("msg","修改成功");
        }catch (Exception e){
            log.error("修改用户信息失败！"+e.getMessage());
            map.put("flag","0");
            map.put("msg","修改用户信息失败");
        }
        return map;
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {
        UserInfo userInfo=null;
        try{
            userInfo=userInfoMapper.getUserInfoById(id);
        }catch (Exception e){
            log.error("根据用户id查询用户出错！"+e.getMessage());
        }
        return userInfo;
    }

    @Override
    public Map<String, String> deleteUserInfo(Integer id) {

        Map<String,String> map=new HashMap<>();
        try {
            int i = userInfoMapper.deleteUserInfo(id);
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

    @Override
    public Map<String, String> batchAddUserInfo(MultipartFile file) {

        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        File excelFile=null;
        List<UserInfo> list=null;
        List<Map<String, String>> listMap = null;
        String cellData = null;
        Map<String,String> map=new HashMap<>();
        try{
            excelFile=FileUtils.multipartFileToFile(file);
            String filePath = excelFile.getPath();
            wb=FileUtils.readExcel(filePath);
            String columns[] = { "玩家编号", "玩家游戏名称", "玩家所在区服", "玩家总势力", "玩家vip等级", "玩家门客数量", "玩家所在联盟名称", "玩家联盟等级"};
            if (wb != null) {
                // 用来存放表中数据
                list = new ArrayList<>();
                // 获取第一个sheet
                sheet = wb.getSheetAt(0);
                // 获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                // 获取第二行
                row = sheet.getRow(1);
                // 获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                for (int i = 1; i < rownum; i++) {
                    UserInfo tem=new UserInfo();
                    row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < colnum; j++) {
                            cellData = (String) FileUtils.getCellFormatValue(row.getCell(j));
                            tem=setData(tem,columns[j],cellData);
                        }
                    } else {
                        break;
                    }
                    list.add(tem);
                }
            }
            int errSize=0;
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    Map<String,String> temMap=new HashMap<>();
                    UserInfo userInfo=list.get(i);
                    temMap=addUserInfo(userInfo);
                    if("0".equals(temMap.get("flag"))){
                        errSize++;
                    }
                }
                if(errSize==0){
                    map.put("flag","1");
                    map.put("msg","添加成功！");
                }else if(errSize>0&&errSize<list.size()){
                    map.put("flag","2");
                    map.put("msg","部分数据添加失败");
                    map.put("errsize",errSize+"");
                }else {
                    map.put("flag","0");
                    map.put("msg","全部新增失败！");
                }
            }else {
                map.put("flag","0");
                map.put("msg","读取excel数据为空！");
            }
        }catch (Exception e){
            log.error("解析excel出错!");
            map.put("flag","0");
            map.put("msg","解析excel出错！");
        }finally {
            if(excelFile.exists()){
                excelFile.delete();
            }
        }
        return map;
    }

    private UserInfo setData(UserInfo tem, String column, String cellData) {
        if("玩家编号".equals(column)){
            tem.setUserNumber(cellData);
        }else if("玩家游戏名称".equals(column)){
            tem.setUserName(cellData);
        }else if("玩家所在区服".equals(column)){
            tem.setUserDistrict(cellData);
        }else if("玩家总势力".equals(column)){
            tem.setPowerNum(cellData);
        }else if("玩家vip等级".equals(column)){
            tem.setUserVipLevel(cellData);
        }else if("玩家门客数量".equals(column)){
            tem.setDoormanNum(cellData);
        }else if("玩家所在联盟名称".equals(column)){
            tem.setUnionName(cellData);
        }else if("玩家联盟等级".equals(column)){
            tem.setUnionLevel(cellData);
        }
        return tem;
    }
}
