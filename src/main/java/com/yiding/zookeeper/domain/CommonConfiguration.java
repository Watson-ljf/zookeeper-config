package com.yiding.zookeeper.domain;

import com.google.common.collect.Lists;
import org.apache.curator.utils.ZKPaths;
import org.springframework.stereotype.Component;


/**
 * 通用配置管理对象
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
@Component
public class CommonConfiguration extends BaseConfiguration {

    public CommonConfiguration() {
        this.rootPath = "/yiding/configurations/base";
        this.scanPaths = Lists.newArrayList(ZKPaths.makePath(this.rootPath, "settings"), ZKPaths.makePath(this.rootPath, "self"));
    }

    public enum ConfigKeys {
        Charset("utf-8"),
        imagePath("/ljf"),
        port("9090");
        /**
         * if this key is not in zk ,then we get default
         */
        private String defaultValue;

        ConfigKeys(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * defaultValue getter
         *
         * @return defaultValue
         */
        public String getDefaultValue() {
            return defaultValue;
        }
    }
}
