package com.yiding.zookeeper.online;

import com.yiding.zookeeper.domain.NodeRegistrationEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 通用服务注册
 * Created by jf.Li@zjydw.com on 2017/8/10.
 */
@Component
public class CommonServiceRegistration extends ServiceOnlineRegistration {
    private static final Logger LOG = Logger.getLogger(CommonServiceRegistration.class);

    @Override
    protected void init() {
        this.registrationNodes.add(new NodeRegistrationEntity("common", "/project", "project post office"));
        this.registrationNodes.add(new NodeRegistrationEntity("common", "/user", "user post office"));
        this.registrationNodes.add(new NodeRegistrationEntity("common", "/task", "task post office"));
        this.registrationNodes.add(new NodeRegistrationEntity("common", "/message", "message post office"));
        this.registrationNodes.add(new NodeRegistrationEntity("common", "/activity", "activity post office"));

        try {
            this.registration();
        } catch (Exception e) {
            LOG.error("-------- online node registration exception", e);
        }
    }
}
