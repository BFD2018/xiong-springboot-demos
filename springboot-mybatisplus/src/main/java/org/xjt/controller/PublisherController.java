package org.xjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjt.bean.TPublisher;
import org.xjt.service.PublisherService;
import org.xjt.utils.R;

import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("list")
    public R getAll() {
        List<TPublisher> publisherList = publisherService.list();
        return R.ok().put("data", publisherList);
    }
}
















