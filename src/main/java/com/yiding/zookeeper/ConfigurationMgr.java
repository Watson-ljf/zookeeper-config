package com.yiding.zookeeper;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.yiding.zookeeper.domain.BaseConfiguration;
import com.yiding.zookeeper.domain.CommonConfiguration;
import com.yiding.zookeeper.domain.ConfigNode;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * ZK配置管理对象
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
@Component
public class ConfigurationMgr {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ZKConfigInitializer initializer;

    @Autowired
    private CuratorFramework zookeeperClient;

    public Map<String, BaseConfiguration> configObjects = Maps.newHashMap();

    /**
     * configObjects getter
     *
     * @return configObjects
     */
    public Map<String, BaseConfiguration> getConfigObjects() {
        return configObjects;
    }

    @PostConstruct
    public void init() throws Exception {
        String[] configObjNames = applicationContext.getBeanNamesForType(BaseConfiguration.class);

        for (String item : configObjNames) {
            BaseConfiguration configuration = (BaseConfiguration) applicationContext.getBean(item);
            configuration.setConfigs(initializer.init(configuration));
            configObjects.put(item, (BaseConfiguration) applicationContext.getBean(item));
        }
    }

    /**
     * 获取zk中的对应配置
     *
     * @param key key
     * @return value
     */
    public String getZKConfigValue(CommonConfiguration.ConfigKeys key) {
        try {
            Map<String, ConfigNode> commonConfig = this.getConfigObjects().get("commonConfiguration").getConfigs();
            ConfigNode cn = commonConfig.get(key.name());
            String result;
            if (null != cn) {
                result = cn.getContent();
            } else {
                LOG.warn("-------- this may be that this nodeName is not in zookeeper; params:{};", key.name());
                result = key.getDefaultValue();
            }
            LOG.info("--------SUCCESS to  get value by key:{}; value is:{};", key.name(), result);
            return result;
        } catch (Exception e) {
            LOG.error("-------- getting Zookeeper config error;this may be that this nodeName is not in zookeeper; params:{};Exception:{};", key.name(), Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    public void addNode(String path) {
        try {
            zookeeperClient.create().forPath(path,"helloWorld".getBytes());
        } catch (Exception e) {
            LOG.error("-------- add node error",e);
        }
    }
}
