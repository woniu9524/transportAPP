package com.backend.backend.security;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor

public class LoginUser implements UserDetails {
    private UserDto user;

    public LoginUser(UserDto user) {
        this.user = user;
    }

    //返回权限信息
    private List<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority
        if (authorities!=null){
            return authorities;
        }
        authorities=new ArrayList<>();
        for (String permission:user.getUserType()){
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    //是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
