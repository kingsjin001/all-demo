package com.djin.apikylin2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Slf4j
public class IndexProp {

    //创建Properties类的对象
    private static Properties properties;
    @Value("index.sql.file")
    static String indexSQLFile;

    /**
     * 使用static静态代码块读取properties文件，并将将文件内容保存在static属性中
     * 高效方便，一次加载，多次使用
     * */
    static {
        loadProps();
    }

    /**
     * 用于加载properties文件
     * 并将properties文件内容加载到定义的properties对象中
     */
    synchronized static private void loadProps() {
        log.info("开始加载properties文件内容.......");

        properties = new Properties();
        InputStream inputStream = null;
        try {
            //创建字符缓存流
            inputStream = IndexProp.class.getResourceAsStream("\"/" + indexSQLFile+ "\"");
            //使用load方法加载properties文件中的内容到properties对象中
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            log.error("properties文件未找到");
        } catch (IOException e) {
            log.error("出现IOException");
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("properties文件流关闭出现异常");
            }
        }
        log.info("加载properties文件内容完成.......");

    }

    /**
     * 获取指定key的值，若key不存在，则返回null
     *
     * @param key key的值
     * @return 返回属性值
     */
    public String getProperty(String key) {
        if (null == properties) {
            loadProps();
        }
        return changeCode(properties.getProperty(key));
    }

    /**
     * 获取指定key的值，若获取不到，则返回默认值
     *
     * @param key          key的值
     * @param defaultValue 默认值
     * @return 返回属性值
     */
    public String getProperty(String key, String defaultValue) {
        if (null == properties) {
            loadProps();
        }
        return changeCode(properties.getProperty(key));
    }

    public static String changeCode(String value) {
        if (value == null) {
            return null;
        }
        try {
            return new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
