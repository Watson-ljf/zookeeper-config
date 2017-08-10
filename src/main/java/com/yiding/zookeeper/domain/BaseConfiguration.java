package com.yiding.zookeeper.domain;


import java.util.List;
import java.util.Map;

/**
 * 配置管理对象基类 --提供拓展模块统一管理
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
public abstract class BaseConfiguration {

    protected Map<String, ConfigNode> configs;
    protected String name;
    protected List<String> scanPaths;
    protected String rootPath;

    /**
     * configs getter
     *
     * @return configs
     */
    public Map<String, ConfigNode> getConfigs() {
        return configs;
    }

    /**
     * configs setter
     *
     * @param configs configs
     */
    public void setConfigs(Map<String, ConfigNode> configs) {
        this.configs = configs;
    }

    /**
     * name getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * scanPaths getter
     *
     * @return scanPaths
     */
    public List<String> getScanPaths() {
        return scanPaths;
    }

    /**
     * scanPaths setter
     *
     * @param scanPaths scanPaths
     */
    public void setScanPaths(List<String> scanPaths) {
        this.scanPaths = scanPaths;
    }

    /**
     * rootPath getter
     *
     * @return rootPath
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * rootPath setter
     *
     * @param rootPath rootPath
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "BaseConfiguration{" +
                "configs=" + configs +
                ", name='" + name + '\'' +
                ", scanPaths=" + scanPaths +
                ", rootPath='" + rootPath + '\'' +
                '}';
    }
}
