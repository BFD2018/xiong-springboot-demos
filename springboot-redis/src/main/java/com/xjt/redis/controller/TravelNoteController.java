package com.xjt.redis.controller;

import com.xjt.redis.domain.TTravelNote;
import com.xjt.redis.service.TravelNoteService;
import com.xjt.redis.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("travelnote")
public class TravelNoteController {
    @Autowired
    private TravelNoteService travelNoteService;

    @GetMapping("/all")
    private RespBean getAllTravelNote() {
        List<TTravelNote> noteList = travelNoteService.getAllTravelNote();

        return RespBean.success("ok", noteList);
    }
}
