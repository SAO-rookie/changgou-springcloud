package com.snowy.changgou.file.controller;

import com.snowy.changgou.file.entity.FastDFSFile;
import com.snowy.changgou.file.until.FastDFSUntil;
import com.snowy.changgou.content.tool.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @auther snowy
 * @date 2020/7/11 - 22:51
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController{

    /**
     * 上传文件
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [file]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );
        String[] paths = FastDFSUntil.fileUpload(fastDFSFile);
        String path  ="http://182.92.208.221:8080/"+paths[0]+"/"+paths[1];
        return Result.ok(path);
    }

    /**
     *
     * 下载文件
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [path, response]
     * @return: void
     */
    @GetMapping
    public void download(@RequestParam("path") String path, HttpServletResponse response){
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-disposition", "attachement;filename="+System.currentTimeMillis()+".png");
        String substringOne = path.substring(27);
        String groupName = substringOne.substring(0, 6);
        String remoteFileName = substringOne.substring(7);
        InputStream inputStream = FastDFSUntil.downFile(groupName, remoteFileName);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) != -1){
                outputStream.write(temp, 0, len);
            }
            // 刷新缓存
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
