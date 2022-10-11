package com.xjt.redis.service;

import com.xjt.redis.domain.TTravelNote;
import com.xjt.redis.utils.RespBean;

import java.util.List;

public interface TravelNoteService {
    List<TTravelNote> getAllTravelNote();

}
