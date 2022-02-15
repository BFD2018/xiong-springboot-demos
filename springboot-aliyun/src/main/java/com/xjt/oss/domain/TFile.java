package com.xjt.oss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TFile {
    private String id;
    private String fileKey;
    private String fileSize;
    private Date updateTime;
}
