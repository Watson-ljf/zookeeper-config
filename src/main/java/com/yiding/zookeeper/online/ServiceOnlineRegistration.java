package com.yiding.zookeeper.online;


import com.google.common.collect.Lists;
import com.yiding.zookeeper.domain.NodeRegistrationEntity;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 服务在线节点注册基类
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
public abstract class ServiceOnlineRegistration {

    @Autowired
    private CuratorFramework curatorFramework;

    protected final String rootPath = "/yiding/online/";

    protected List<NodeRegistrationEntity> registrationNodes = Lists.newArrayList();

    @PostConstruct
    protected abstract void init();

    protected void registration() throws Exception {
        for (NodeRegistrationEntity item : registrationNodes) {
            String path = rootPath + item.getNodePath() + item.getNodeName();
            item.setNodePath(curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path + "/desc", item.getNodeDesc().getBytes()));
        }
    }
}
