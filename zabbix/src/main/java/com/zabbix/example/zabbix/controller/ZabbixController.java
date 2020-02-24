package com.zabbix.example.zabbix.controller;

import com.alibaba.fastjson.JSONObject;
import com.zabbix.example.zabbix.services.ZabbixServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "zabbix")
public class ZabbixController {

    @Autowired
    private ZabbixServer zabbixServer;

    @GetMapping(value = "login")
    public JSONObject login() {
        return zabbixServer.login();

    }

    @GetMapping(value = "version")
    public JSONObject version() {
        return zabbixServer.getVersion();

    }

    @GetMapping(value = "gethosts")
    public JSONObject retrievingHosts() {
        return zabbixServer.getRetrievingHosts();

    }

    @GetMapping(value = "logout")
    public JSONObject logOut() {
        return zabbixServer.getLogOut();

    }

}
