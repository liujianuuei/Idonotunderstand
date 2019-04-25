package com.oracle.suitecloudapiquery.controller;

import com.oracle.suitecloudapiquery.dao.ApiRegistryDao;
import com.oracle.suitecloudapiquery.model.ApiMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiQueryController {

    @Autowired
    ApiRegistryDao apiRegistryDao;

    @PostMapping("/suitecloud/apiquery")
    public ApiMetadata register(@RequestBody ApiMetadata apiMetadata, HttpServletRequest request) {
        System.out.println("ApiRegistry: receiving API metadata query request " + apiMetadata);
        return apiRegistryDao.query(apiMetadata);
    }
}
