package com.yiding.zookeeper.domain;

/**
 * 节点注册实体
 * Created by jf.Li@zjydw.com on 2017/8/7.
 */
public class NodeRegistrationEntity {

    private String nodePath;
    private String nodeName;
    private String nodeDesc;

    /**
     * nodePath getter
     *
     * @return nodePath
     */
    public String getNodePath() {
        return nodePath;
    }

    /**
     * nodePath setter
     *
     * @param nodePath nodePath
     */
    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    /**
     * nodeName getter
     *
     * @return nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * nodeName setter
     *
     * @param nodeName nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * nodeDesc getter
     *
     * @return nodeDesc
     */
    public String getNodeDesc() {
        return nodeDesc;
    }

    /**
     * nodeDesc setter
     *
     * @param nodeDesc nodeDesc
     */
    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public NodeRegistrationEntity(String nodePath, String nodeName, String nodeDesc) {
        this.nodePath = nodePath;
        this.nodeName = nodeName;
        this.nodeDesc = nodeDesc;
    }
}
