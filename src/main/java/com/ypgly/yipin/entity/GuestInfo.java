package com.ypgly.yipin.entity;

/**
 * @author yzy
 */
public class GuestInfo {
    /**
     * id id号
     * userNumber 用户编号
     * doormanName 门客名称
     * qualification 门客资质
     * bloodNum 门客血量
     * isSuit 是否是套装门客（1五虎、2四奸、3五义、4四杰、5红颜门客）
     */
    private Integer id;
    private String userNumber;
    private String doormanName;
    private String qualification;
    private String bloodNum;
    private String isSuit;

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

    public String getDoormanName() {
        return doormanName;
    }

    public void setDoormanName(String doormanName) {
        this.doormanName = doormanName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getBloodNum() {
        return bloodNum;
    }

    public void setBloodNum(String bloodNum) {
        this.bloodNum = bloodNum;
    }

    public String getIsSuit() {
        return isSuit;
    }

    public void setIsSuit(String isSuit) {
        this.isSuit = isSuit;
    }
}
