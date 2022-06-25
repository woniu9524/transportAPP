package com.backend.backend.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
public class CommonUtil {

    //解决权限我写的不一致
    public List<String> getUserPermissions(Integer i){
        //因为表我设计成了123那个，就加一条
        //用户类型：司机0 车主1 车主+司机2 货主3
        List<String> permissions=new ArrayList<>();
        if (Objects.isNull(i)){
            permissions.add("error");
            return permissions;
        }
        switch (i){
            case 0:{
                permissions.add("driver");
                return permissions;
            }
            case 1:{
                permissions.add("owner");
                return permissions;
            }
            case 2:{
                permissions.add("driver");
                permissions.add("owner");
                return permissions;
            }
            case 3:{
                permissions.add("cargoOwner");
                return permissions;
            }
        }
        return permissions;
    }
}
