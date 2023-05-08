package com.xjt.fdfs.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("fastdfs_file")
public class FastdfsFile {
    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String originalName;
    private String newName;
    private String ext;
    private String url;
    private String subUrl;
    private String groupName;
    private int status;
    private Long filesize;
    private Date createTime;
    private Date updateTime;
}
