package com.ypgly.yipin.mapper;

import com.ypgly.yipin.entity.GuestInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yzy
 */
@Mapper
public interface GuestInfoMapper {
    List<GuestInfo> getGuestInfoByUserNum(String userNumber, String doormanName);

    void addGuestInfo(GuestInfo guestInfo);

    List<GuestInfo> pageGuestInfo(GuestInfo guestInfo);

    void updateGuestInfo(GuestInfo guestInfo);

    GuestInfo getGuestInfoById(Integer id);

    int deleteGuestInfo(Integer id);
}
