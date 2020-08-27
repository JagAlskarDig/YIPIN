package com.ypgly.yipin.mapper;

import com.ypgly.yipin.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yzy
 */
@Mapper
public interface UserInfoMapper {
    void addUserInfo(UserInfo userInfo);

    List<UserInfo> getUserInfoByUserNum(String userNumber, String userDistrict);
}
