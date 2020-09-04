package com.ypgly.yipin.mapper;

import com.ypgly.yipin.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yzy
 */
@Mapper
public interface UserInfoMapper {
    void addUserInfo(UserInfo userInfo);

    List<UserInfo> getUserInfoByUserNum(String userNumber, String userDistrict);

    List<UserInfo> pageUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(@Param("id") Integer id);

    int deleteUserInfo(@Param("id") Integer id);
}
