package com.xyt;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @className: NacosServerApplication
 * @Description:
 * @Author: abby
 * @Date: 2021/1/7 14:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@NacosConfigurationProperties(dataId = "nacos-test-config", autoRefreshed = true) // dataId nacos中配置数据 data id， autoRefreshed ： true表示自动更新
@RestController
public class NacosServerApplication {

    /**
     * @LoadBalanced 负载均衡注解
     *      使用Ribbon做负载均衡   RestTemplate调用
     *
     *      Fegin
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosServerApplication.class);
    }


    @NacosValue(value = "${nacos-test-value : 123}", autoRefreshed = true)
    private String value;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return value;
    }
}
