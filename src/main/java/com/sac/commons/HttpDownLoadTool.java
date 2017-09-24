package com.sac.commons;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * http下载帮助类
 * Created by EAISON on 2017/9/22.
 */
public class HttpDownLoadTool {


    /**
     * 获取下载文件的字节流
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> GetDownLoadBytes(HttpServletRequest request, File file)
            throws IOException {
        String fileName=file.getName();
        // 设置响应头头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", HttpFileEncodeTool.toUtf8String(request, fileName));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 返回流
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
                HttpStatus.OK);
        return responseEntity;
    }

}
