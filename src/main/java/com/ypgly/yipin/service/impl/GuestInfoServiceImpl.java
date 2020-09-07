package com.ypgly.yipin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ypgly.yipin.entity.GuestInfo;
import com.ypgly.yipin.mapper.GuestInfoMapper;
import com.ypgly.yipin.service.GuestInfoService;
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

    @Override
    public Map<String, String> batchAddGuestInfo(MultipartFile file) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        File excelFile=null;
        List<GuestInfo> list=null;
        List<Map<String, String>> listMap = null;
        String cellData = null;
        Map<String,String> map=new HashMap<>();
        try{
            excelFile= FileUtils.multipartFileToFile(file);
            String filePath = excelFile.getPath();
            wb=FileUtils.readExcel(filePath);
            String columns[] = { "玩家编号", "门客名称", "门客资质", "门客血量", "是否是套装门客"};
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
                    GuestInfo tem=new GuestInfo();
                    row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < colnum; j++) {
                            cellData = (String) FileUtils.getCellFormatValue(row.getCell(j));
                            tem=setData(tem,j,cellData);
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
                    GuestInfo userInfo=list.get(i);
                    temMap=addGuestInfo(userInfo);
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

    private GuestInfo setData(GuestInfo tem, int j, String cellData) {
        if(j==0){
            tem.setUserNumber(cellData);
        }else if(j==1){
            tem.setDoormanName(cellData);
        }else if(j==2){
            tem.setQualification(cellData);
        }else if(j==3){
            tem.setBloodNum(cellData);
        }else if(j==4){
            //五虎、2四奸、3五义、4四杰、5红颜门客、6其他
            if("五虎".equals(cellData)){
                tem.setIsSuit("1");
            }else if("四奸".equals(cellData)){
                tem.setIsSuit("2");
            }else if("五义".equals(cellData)){
                tem.setIsSuit("3");
            }else if("四杰".equals(cellData)){
                tem.setIsSuit("4");
            }else if("红颜门客".equals(cellData)){
                tem.setIsSuit("5");
            }else {
                tem.setIsSuit("6");
            }
        }
        return tem;
    }


}
