package com.yiding.zookeeper.domain;

import com.google.common.collect.Lists;
import org.apache.curator.utils.ZKPaths;
import org.springframework.stereotype.Component;

/**
 * 活动配置管理对象
 * Created by jf.Li@zjydw.com on 2017/8/9.
 */
@Component
public class ActivityConfiguration extends BaseConfiguration {
    public ActivityConfiguration() {
        this.rootPath = "/yiding/configurations/activity";
        this.scanPaths = Lists.newArrayList(ZKPaths.makePath(this.rootPath, "settings"), ZKPaths.makePath(this.rootPath, "self"));
    }
}
