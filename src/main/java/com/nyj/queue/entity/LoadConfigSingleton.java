package com.nyj.queue.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import lombok.Data;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 单例加载体检项目列表
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 07:18
 * @description:
 */
@Data
public class LoadConfigSingleton {

    private Logger logger = LoggerFactory.getLogger(LoadConfigSingleton.class);

    private volatile static LoadConfigSingleton singleton;
    List phyExams = null;
    List persons = null;

    private LoadConfigSingleton() throws IOException {
        loadConfigList();
    }

    private void loadConfigList() throws IOException {
        String[] fileNames = new String[]{"phy_exam_list.json", "person_list.json"};
        for (int i = 0; i < fileNames.length; i ++) {
            Resource resource = new ClassPathResource(fileNames[i]);
            InputStream inputStream = resource.getInputStream();
            StringBuilder sb = new StringBuilder();
            List<String> list = IOUtils.readLines(inputStream);
            for (String str : list) {
                sb.append(str);
            }
            String json = sb.toString();
            if (i == 0) {
                phyExams = JSON.parseObject(json, new TypeReference<List<PhyExam>>() {
                });
            }
            if (i == 1) {
                persons = JSON.parseObject(json, new TypeReference<List<Person>>() {
                });
            }
        }
    }

    static LoadConfigSingleton getSingleton() throws IOException {
        if (singleton == null) {
            synchronized (LoadConfigSingleton.class) {
                if (singleton == null) {
                    singleton = new LoadConfigSingleton();
                }
            }
        }
        return singleton;
    }

}