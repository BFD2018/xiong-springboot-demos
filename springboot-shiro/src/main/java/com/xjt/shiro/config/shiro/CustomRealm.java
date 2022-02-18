package com.xjt.shiro.config.shiro;

import com.xjt.shiro.domain.TUser;
import com.xjt.shiro.service.TUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private TUserService tUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("======授权doGetAuthenticationInfo=======");
        //1、获取用户名
        String principal = principals.getPrimaryPrincipal().toString();

        //2、通过用户名查询所有的权限表达式
        Set<String> permissions = tUserService.getPermissionsByUsername(principal);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("======doGetAuthenticationInfo=======");

        //从传过来的token获取到的用户名（也可以直接使用 token.getPrincipal()获取用户名）
        //AuthenticationToken是UsernamePasswordToken的父类
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        System.out.println("====用户名===="+username);

        //从数据库查询
        TUser tUser = tUserService.findByUsername(username);

        if (!ObjectUtils.isEmpty(tUser)){
            //1、用户名为username的用户存在
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    username,
                    tUser.getPassword(),
                    new SimpleByteSource(tUser.getSalt()),
                    this.getName());

            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("USER_SESSION",tUser);

            return simpleAuthenticationInfo;
        }else{
            //2、用户不存在
            return null;
        }
    }
}

