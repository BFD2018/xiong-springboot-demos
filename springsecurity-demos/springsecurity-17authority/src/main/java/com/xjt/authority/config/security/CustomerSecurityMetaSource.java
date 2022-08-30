package com.xjt.authority.config.security;

import com.xjt.authority.entity.TMenu;
import com.xjt.authority.entity.TRole;
import com.xjt.authority.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomerSecurityMetaSource implements FilterInvocationSecurityMetadataSource {
//    @Autowired
//    private TMenuService menuService;

    private final TMenuService menuService;

    @Autowired
    public CustomerSecurityMetaSource(TMenuService menuService) {
        this.menuService = menuService;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        //1.当前请求对象
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        //2.查询角色对应的所有菜单
        List<TMenu> allMenu = menuService.getAllMenu();

        for (TMenu menu : allMenu) {
            if (antPathMatcher.match(menu.getPattern(), requestURI)) {
                String[] roles = menu.getRoleList().stream().map(TRole::getName).toArray(String[]::new);
                List<ConfigAttribute> attributeList = SecurityConfig.createList(roles);
                return attributeList;
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
