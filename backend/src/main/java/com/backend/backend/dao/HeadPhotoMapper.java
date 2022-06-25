package com.backend.backend.dao;

import com.backend.backend.entity.HeadPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface HeadPhotoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(HeadPhoto record);

    HeadPhoto selectByPrimaryKey(String userId);

    List<HeadPhoto> selectAll();

    int updateByPrimaryKey(HeadPhoto record);
}