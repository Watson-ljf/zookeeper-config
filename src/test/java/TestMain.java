import com.yiding.Application;
import com.yiding.zookeeper.ConfigurationMgr;
import com.yiding.zookeeper.domain.BaseConfiguration;
import com.yiding.zookeeper.domain.CommonConfiguration;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * 测试
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMain {
    @Autowired
    private ConfigurationMgr configurationMgr;

    @Autowired
    private CuratorFramework zookeeperClient;

    @Test
    public void getNodes() {
        Map<String, BaseConfiguration> configObjects = configurationMgr.configObjects;
        System.out.println(configObjects);
    }

    @Test
    public void getZKConfigValue() {
        String zkConfigValue = configurationMgr.getZKConfigValue(CommonConfiguration.ConfigKeys.imagePath);
        System.out.println(zkConfigValue);
    }

    @Test
    public void createNode() {
        configurationMgr.addNode("/yiding/configurations/test");
    }

    @Test
    public void createPath() throws Exception {
        zookeeperClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/yiding/online/test","hello".getBytes());
        zookeeperClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/yiding/online/test","hello1".getBytes());
        System.out.println("-------------------------------->");
    }

    @Test
    public void deleteNode() throws Exception {
        zookeeperClient.delete().deletingChildrenIfNeeded().forPath("/yiding/online");
    }

    @Test
    public void option() {
        try {
            zookeeperClient.inTransaction().create().forPath("/yiding/online", "hello World".getBytes())
                    .and().delete().forPath("/yiding/configurations/test")
                    .and().setData().forPath("/yiding/configurations/base/settings/port", "9090".getBytes())
                    .and().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
