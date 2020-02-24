package com.zabbix.example.zabbix.services;

import com.alibaba.fastjson.JSONObject;

public interface ZabbixApi {

    void init();

    void destroy();

    JSONObject apiVersion();

    JSONObject call(Request request);

    boolean login(String user, String password);

    JSONObject retrievingHosts();


}
