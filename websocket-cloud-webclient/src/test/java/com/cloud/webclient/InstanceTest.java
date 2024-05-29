package com.cloud.webclient;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InstanceTest {

    @Test
    public void Test() throws NacosException {
        // 连接到Nacos服务发现
        String serverAddr = "localhost:8848";
        NamingService namingService = NamingFactory.createNamingService(serverAddr);

        // 获取example-service服务的所有实例
        List<Instance> instances= namingService.getAllInstances("websocket-server");

        // 选择需要获取元数据的实例，这里选择第一个实例
        Instance instance = instances.get(0);
        String ip = instance.getIp();
        int port = instance.getPort();

        System.out.println(ip + ":" + port);

    }
}
