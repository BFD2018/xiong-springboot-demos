package com.xjt.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2022-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TUser对象", description="")
@TableName("t_user")
public class TUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Boolean enabled;

    @TableField("accountNonExpired")
    private Boolean accountnonexpired;

    @TableField("accountNonLocked")
    private Boolean accountnonlocked;

    @TableField("credentialsNonExpired")
    private Boolean credentialsnonexpired;

    @TableField(exist = false)
    private List<TRole> roles = new ArrayList<>();       //关系属性 用来存储当前用户所有角色信息


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(simpleGrantedAuthority);
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountnonexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountnonlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsnonexpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
