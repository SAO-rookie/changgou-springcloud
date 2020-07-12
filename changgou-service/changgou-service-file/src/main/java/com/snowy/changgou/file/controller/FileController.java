package com.snowy.changgou.file.controller;

import com.snowy.changgou.file.entity.FastDFSFile;
import com.snowy.changgou.file.until.FastDFSUntil;
import com.snowy.tool.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @auther snowy
 * @date 2020/7/11 - 22:51
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

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
}
