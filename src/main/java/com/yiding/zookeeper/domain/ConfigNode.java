package com.yiding.zookeeper.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 配置管理节点对象
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
public class ConfigNode {

    private String name;
    private String path;
    private String content;
    private boolean watched;
    private String parentPath;
    private List<String> watchedPath = Lists.newArrayList();

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
     * path getter
     *
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * path setter
     *
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * content getter
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * content setter
     *
     * @param content content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * watched getter
     *
     * @return watched
     */
    public boolean isWatched() {
        return watched;
    }

    /**
     * watched setter
     *
     * @param watched watched
     */
    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    /**
     * parentPath getter
     *
     * @return parentPath
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * parentPath setter
     *
     * @param parentPath parentPath
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * watchedPath getter
     *
     * @return watchedPath
     */
    public List<String> getWatchedPath() {
        return watchedPath;
    }

    /**
     * watchedPath setter
     *
     * @param watchedPath watchedPath
     */
    public void setWatchedPath(List<String> watchedPath) {
        this.watchedPath = watchedPath;
    }

    @Override
    public String toString() {
        return "ConfigNode{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", content='" + content + '\'' +
                ", watched=" + watched +
                ", parentPath='" + parentPath + '\'' +
                ", watchedPath=" + watchedPath +
                '}';
    }
}
