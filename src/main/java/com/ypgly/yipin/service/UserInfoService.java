package com.ypgly.yipin.service;

import com.ypgly.yipin.entity.UserInfo;

import java.util.Map;

/**
 * @author yzy
 */
public interface UserInfoService {
    Map<String, String> addUserInfo(UserInfo userInfo);

    Map<String, Object> pageUserInfo(UserInfo userInfo);

    Map<String, String> updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Integer valueOf);

    Map<String, String> deleteUserInfo(Integer valueOf);
}
