package com.backend.backend.service.impl;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.dao.HeadPhotoMapper;
import com.backend.backend.entity.HeadPhoto;
import com.backend.backend.service.FileService;
import com.backend.backend.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    private HeadPhotoMapper headPhotoMapper;

    @Value("${Qiniu.baseUrl}")
    private String baseUrl;
    @Value("${Qiniu.bucket}")
    private String bucket;

    @Override
    public CommonResult<?> saveFile(MultipartFile uploadFile, String userId) {
        String imageUrl = uploadUtil.uploadFile(uploadFile, bucket, baseUrl);
        if (Objects.isNull(imageUrl)) {
            return CommonResult.failed("上传失败");
        }

        HeadPhoto headPhoto = new HeadPhoto();
        headPhoto.setUserId(userId);
        headPhoto.setPhoto(imageUrl);
        //查询是否已经有头像
        if (Objects.isNull(headPhotoMapper.selectByPrimaryKey(userId))) {
            //没有，插入
            headPhotoMapper.insert(headPhoto);
        } else {
            //有，更新
            headPhotoMapper.updateByPrimaryKey(headPhoto);
        }
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("imageUrl", imageUrl);
        return CommonResult.success(urlMap);

    }

    @Override
    public CommonResult<?> loadImage(String userId) {
        HeadPhoto headPhoto = null;
        try {
            headPhoto = headPhotoMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            return CommonResult.failed("加载失败");
        }
        if (Objects.isNull(headPhoto))
            return CommonResult.failed("加载失败");

        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", headPhoto.getPhoto());
        return CommonResult.success(map);
    }

}
