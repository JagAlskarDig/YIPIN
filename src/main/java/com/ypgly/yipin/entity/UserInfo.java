package com.ypgly.yipin.entity;

/**
 * @author yzy
 */
public class UserInfo {
    /**
     *id id号
     * userNumber 用户编号
     * userName 用户昵称
     * userDistrict 用户所在区服
     * powerNum 用户总势力
     * userVipLevel 用户vip等级
     * doormanNum 用户门客数量
     * unionName 用户所在联盟名称
     * unionLevel 用户联盟等级
     * pageNo 页码
     * size 分页大小
     */
    private Integer id;
    private String userNumber;
    private String userName;
    private String userDistrict;
    private String powerNum;
    private String userVipLevel;
    private String doormanNum;
    private String unionName;
    private String unionLevel;
    private String pageNo;
    private String size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDistrict() {
        return userDistrict;
    }

    public void setUserDistrict(String userDistrict) {
        this.userDistrict = userDistrict;
    }

    public String getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(String powerNum) {
        this.powerNum = powerNum;
    }

    public String getUserVipLevel() {
        return userVipLevel;
    }

    public void setUserVipLevel(String userVipLevel) {
        this.userVipLevel = userVipLevel;
    }

    public String getDoormanNum() {
        return doormanNum;
    }

    public void setDoormanNum(String doormanNum) {
        this.doormanNum = doormanNum;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionLevel() {
        return unionLevel;
    }

    public void setUnionLevel(String unionLevel) {
        this.unionLevel = unionLevel;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
