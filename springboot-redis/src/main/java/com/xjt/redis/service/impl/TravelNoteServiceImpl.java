package com.xjt.redis.service.impl;

import com.xjt.redis.domain.TTravelNote;
import com.xjt.redis.mapper.TravelNoteMapper;
import com.xjt.redis.service.TravelNoteService;
import com.xjt.redis.utils.RedisUtils;
import com.xjt.redis.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TravelNoteServiceImpl implements TravelNoteService {
    @Autowired
    private TravelNoteMapper travelNoteMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public RespBean getAllTravelNote() {
        List<TTravelNote> noteList = null;
        boolean exists = redisUtils.exists("notelist");
        if(exists){
            System.out.println("------------------>从redis中读取数据");
            noteList = (List<TTravelNote>)redisUtils.get("notelist");
        }else if(ObjectUtils.isEmpty(noteList)){
            System.out.println("==================>从数据库中读取数据");
            noteList = travelNoteMapper.selectList(null);
            redisUtils.set("notelist",noteList);
        }

        return RespBean.success("ok",noteList);
    }
}
