package com.xjt.authority.service.impl;

import com.xjt.authority.entity.TMenu;
import com.xjt.authority.mapper.TMenuMapper;
import com.xjt.authority.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TMenuServiceImpl implements TMenuService {

    @Autowired
    private TMenuMapper menuMapper;

    @Override
    public List<TMenu> getAllMenu() {
        return null;
    }
}
