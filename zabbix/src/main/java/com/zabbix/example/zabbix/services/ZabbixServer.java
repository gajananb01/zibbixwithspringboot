package com.zabbix.example.zabbix.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ZabbixServer {

    private ZabbixApi zabbixApi;


    @PostConstruct
    public void before() {
        String url = "http://localhost:49156/zabbix/api_jsonrpc.php";
        url = "http://172.16.9.96/zabbix/api_jsonrpc.php";
        zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();
    }

    @PreDestroy
    public void after() {
        zabbixApi.destroy();
    }


    public JSONObject login() {
        JSONObject response = null;
        if (loginValidate()) {
            Request request = RequestBuilder.newBuilder().method("user.get")
                    .paramEntry("output", "extend").build();
            response = zabbixApi.call(request);
            System.err.println(JSON.toJSONString(response, true));
        }

        return response;
    }


    private boolean loginValidate() {
        String user = "Admin";
        String password = "zabbix";
        boolean login = zabbixApi.login(user, password);
        System.out.println("login result:" + login);
        return login;
    }


    public JSONObject getLogOut() {
        JSONObject response=null;
        if (loginValidate()) {
            Request request = RequestBuilder.newBuilder().method("user.logout")
                    .build();
            System.err.println(request);
             response = zabbixApi.call(request);
            System.err.println(response);
             return  response;
        }
        return response;

    }


    public JSONObject getVersion() {
        return zabbixApi.apiVersion();
    }

    public JSONObject getRetrievingHosts() {
        JSONObject response = null;
        if (loginValidate()) {
            response = zabbixApi.retrievingHosts();
        }
        return response;
    }
}
