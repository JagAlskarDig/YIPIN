package com.ypgly.yipin.service;

import com.ypgly.yipin.entity.GuestInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author yzy
 */
public interface GuestInfoService {
    Map<String, String> addGuestInfo(GuestInfo guestInfo);

    Map<String, Object> pageGuestInfo(GuestInfo guestInfo);

    Map<String, String> updateGuestInfo(GuestInfo guestInfo);

    GuestInfo getGuestInfoById(Integer valueOf);

    Map<String, String> deleteGuestInfo(Integer valueOf);

    Map<String, String> batchAddGuestInfo(MultipartFile file);
}
