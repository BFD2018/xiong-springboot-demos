package com.xjt.filesupload.service;

import com.xjt.filesupload.domain.TUserFile;
import com.xjt.filesupload.utils.RespBean;

public interface TUserFilesService {
    Integer uploadFile(TUserFile userFile);

    RespBean getAllFilesByUserId(String user_id);
}
