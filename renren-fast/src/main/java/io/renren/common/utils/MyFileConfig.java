package io.renren.common.utils;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="virtuel")
public class MyFileConfig {

    /**
     * 上传文件路径
     */
    private  static String filePath;

    public static String getFilePath() {
        return filePath;
    }

    public  void setFilePath(String filePath) {

        MyFileConfig.filePath = filePath;
    }
}
