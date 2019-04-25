package com.oracle.suitecloudapiregistry.controller;

import com.oracle.suitecloudapiregistry.dao.ApiRegistryDao;
import com.oracle.suitecloudapiregistry.model.ApiMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiRegistryController {

    @Autowired
    ApiRegistryDao apiRegistryDao;

    @PostMapping("/suitecloud/apiregistry")
    public ResponseEntity register(@RequestBody ApiMetadata apiMetadata, HttpServletRequest request) {
        try {
            System.out.println("ApiRegistry: receiving API metadata " + apiMetadata);
            apiRegistryDao.save(apiMetadata);
            return new ResponseEntity("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
