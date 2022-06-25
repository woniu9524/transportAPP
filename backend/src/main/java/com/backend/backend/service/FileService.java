package com.backend.backend.service;

import com.backend.backend.common.api.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    CommonResult<?> saveFile(MultipartFile uploadFile,String userId);

    CommonResult<?> loadImage(String userId);
}
