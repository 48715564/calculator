package com.cn.scheduling;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HazelcastInstance hazelcastInstance;
    //定时查询openstack信息保存到数据库
//    @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(initialDelay = 3000, fixedRate = 1000)
    public void saveOpenStackInfo() {
       IMap<Object, Object> map =  hazelcastInstance.getMap("test");
       map.put("a","2");
       
    }
}
