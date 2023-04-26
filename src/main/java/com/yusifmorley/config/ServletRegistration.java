package com.yusifmorley.config;

import com.yusifmorley.constant.ActionLog;
import com.yusifmorley.WebDav.MiltonWebDAVFileServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServletRegistration {
    @Value("${webdav.path}")
    String webdavpath;

    @Value("${webdav.username}")
    String username;

    @Value("${webdav.password}")
    String password;
    @Value("${webdav.folder}")
    String webdavfolder;
    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws Exception {
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put(username,password);
        //res webdav 必须存在  也就是  webdavfolder webdavpath
        //必须存在
        Path father=Paths.get(webdavfolder);
        Path subpath = Paths.get(webdavfolder, webdavpath);
        checkfile(father);
        checkfile(subpath);
        return new ServletRegistrationBean(
                new MiltonWebDAVFileServer(new File(webdavfolder)).start(stringStringHashMap),getWebdavpath());
    }
    //检测文件路径是否完整
    public  void checkfile(Path path) throws IOException {
        if (!Files.exists(path)){
            throw new IOException(ActionLog.PathIsNotExist.getMsg());
        } else if (!Files.isDirectory(path)) {
            throw new IOException(ActionLog.PathIsNotDirectory.getMsg());
        }
    }

    public String getWebdavpath() {
        return "/"+webdavpath+"/*";
    }
}
