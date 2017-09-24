package com.sac.web.system;

import com.sac.commons.HttpDownLoadTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by EAISON on 2017/9/24.
 */
@CrossOrigin(origins = "*")
@RequestMapping("/File")
@Controller
public class FileController {

    /**
     * 文件下载的保存地址
     */
    @Value("#{configProperties['downLoadPath']}")
    private String downLoadPath;

    /**
     * @param request
     * @param fileName 文件名
     * @param fileType 文件类型，也就是文件后缀
     * @return
     */
    @RequestMapping(value = "/DownLoad/{fileName}/{fileType}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downLoadExcel(HttpServletRequest request,
                                                @PathVariable String fileName,
                                                @PathVariable String fileType) {
        try {
            //写linux的目录
            File downLoadFile = new File(downLoadPath + "//" + fileName + "." + fileType);
            ResponseEntity<byte[]> responseEntity = HttpDownLoadTool.GetDownLoadBytes(request, downLoadFile);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upLoad", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus upLoad(MultipartFile file, HttpServletRequest request) {
        if (file != null) {
            File dirPath = new File(downLoadPath);
            if (!dirPath.exists()) {
                // 文件夹不存在就创建一个文件夹
                dirPath.mkdirs();
            }
            // 文件名
            String fileName = file.getOriginalFilename();
            // 文件保存的路径路径
            File dir = new File(downLoadPath, fileName);
            // 保存文件到服务器
            try {
                file.transferTo(dir);
                return  HttpStatus.OK;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return HttpStatus.BAD_REQUEST;
    }
}
