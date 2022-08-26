package com.xjt.authority;

import com.xjt.authority.entity.TMenu;
import com.xjt.authority.service.TMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestDemo {
    @Autowired
    TMenuService menuService;

    @Test
    public void test01(){
        List<TMenu> allMenu = menuService.getAllMenu();
        System.out.println(allMenu);
    }

}
