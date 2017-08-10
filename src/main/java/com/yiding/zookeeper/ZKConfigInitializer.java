package com.yiding.zookeeper;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.yiding.zookeeper.domain.BaseConfiguration;
import com.yiding.zookeeper.domain.ConfigNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * zookeeper配置管理初始化器
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
@Component
public class ZKConfigInitializer {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private CuratorFramework zookeeperClient;

    public Map<String, ConfigNode> init(final BaseConfiguration configuration) throws Exception {
        final Map<String, ConfigNode> configs = Maps.newHashMap();
        for (String item : configuration.getScanPaths()) {
            PathChildrenCache pathChildrenCache = new PathChildrenCache(zookeeperClient, item, true);
            pathChildrenCache.start();
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    switch (pathChildrenCacheEvent.getType()) {
                        case CHILD_UPDATED:
                            childUpdated(pathChildrenCacheEvent.getData(), configs);
                            break;
                        case CHILD_ADDED:
                            childAdded(pathChildrenCacheEvent.getData(), configuration, configs);
                            break;
                        default:
                            break;
                    }
                }
            });
            //扫描配置路径下的所有节点数据
            for (String innerItem : zookeeperClient.getChildren().forPath(item)) {
                ConfigNode node = new ConfigNode();
                node.setPath(item + "/" + innerItem);
                node.setName(ZKPaths.getNodeFromPath(node.getPath()));
                node.setParentPath(configuration.getRootPath());
                node.setContent(new String(zookeeperClient.getData().forPath(node.getPath())));
                configs.put(node.getName(), node);
            }
        }
        return configs;
    }

    /**
     * 捕获添加节点操作，添加的节点数据格式化保存在内存中
     *
     * @param childData     节点数据
     * @param configuration 基础配置
     * @param configs       内存模型
     */
    private void childAdded(ChildData childData, BaseConfiguration configuration, Map<String, ConfigNode> configs) {
        try {
            ConfigNode node = new ConfigNode();
            node.setPath(childData.getPath());
            node.setName(ZKPaths.getNodeFromPath(node.getPath()));
            node.setParentPath(configuration.getRootPath());
            node.setContent(new String(zookeeperClient.getData().forPath(node.getPath())));
            configs.put(node.getName(), node);
            LOG.info("-------- ZK add node config ; the zk path is:{}; data is {}",
                    childData.getPath(), configs.get(ZKPaths.getNodeFromPath(node.getPath())).getContent());
        } catch (Exception e) {
            LOG.error("-------- ERROR zookeeper add node  error;params:{};Exception:{};", childData.toString(), Throwables.getStackTraceAsString(e));
        }
    }

    /**
     * 捕获到的节点修改操作，修改对应的节点数据
     *
     * @param childData 节点信息
     * @param configs   内存模型
     */
    private void childUpdated(ChildData childData, Map<String, ConfigNode> configs) {
        String nodeName = ZKPaths.getNodeFromPath(childData.getPath());
        if (configs.containsKey(nodeName)) {
            LOG.info("--------before changing the ZK node config ;the zk path is:{}; before changed data is {};--------",
                    childData.getPath(), configs.get(nodeName).getContent());
            configs.get(nodeName).setContent(new String(childData.getData()));
            LOG.info("--------after changing the ZK node config ;the zk path is:{}; after changed data is {};--------",
                    childData.getPath(), configs.get(nodeName).getContent());
        }
    }
}
