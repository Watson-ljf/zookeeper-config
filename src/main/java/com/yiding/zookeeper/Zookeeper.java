package com.yiding.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * curator 配置管理对象
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
@Configuration
public class Zookeeper {
    private static final Logger LOG = Logger.getLogger(Zookeeper.class);

    @Value("${zk.baseUrl}")
    private String zkUrl;


    /**
     * zookeeper客户端封装框架
     *
     * @return zookeeper客户端封装框架
     */
    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy policy = new RetryOneTime(2000);
        CuratorFramework zookeeperClient = CuratorFrameworkFactory.builder().connectString(zkUrl).retryPolicy(policy)
                .connectionTimeoutMs(50000).sessionTimeoutMs(50000).build();

        zookeeperClient.start();
        try {
            String onlinePath = "/yiding/online";
            //验证节点是否存在
            if (!StringUtils.isEmpty(zookeeperClient.checkExists().forPath(onlinePath))) {
                zookeeperClient.delete().deletingChildrenIfNeeded().forPath(onlinePath);
            }
        } catch (Exception e) {
            LOG.error("-------- zookeeper delete exception", e);
        }
        return zookeeperClient;
    }
}
