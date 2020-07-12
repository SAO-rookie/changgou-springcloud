package com.snowy.changgou.file.until;

import com.snowy.changgou.file.entity.FastDFSFile;
import jdk.internal.dynalink.beans.StaticClass;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @auther snowy
 * @date 2020/7/11 - 22:29
 */
public class FastDFSUntil {
    /*
    *  根据配置文件初始化连接
    * */
    static {
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    /*
    *   获取TrackerServer并且获取Storage里的信息
    * */
    private static TrackerServer getTrackerClient() throws IOException {
        //创建TrackerClient客户端对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient对象获取TrackerServer信息
        return trackerClient.getConnection();
    }

    /*
    *   获取StorageClient对象
    * */
    private static StorageClient getStorageClient() throws IOException {
        return new StorageClient(getTrackerClient(),null);
    }

    /**
     * 文件上传
    * @Author: snowy
    * @Date: 2020/7/11
    * @Param:
    * @return:
    */
    public static String[] fileUpload(FastDFSFile dfsUntil){
        //获取文件作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] =new NameValuePair(dfsUntil.getAuthor());

        String[] uploadResults = null;
        try {
            uploadResults =  getStorageClient().upload_file(dfsUntil.getContent(),dfsUntil.getExt(),meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;
    }
}

