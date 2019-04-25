package com.oracle.suitecloudflowdefinition.controller;

import com.oracle.suitecloudflowdefinition.dao.FlowDefinitionDao;
import com.oracle.suitecloudflowdefinition.model.FlowDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FlowDefinitionController {

    @Autowired
    FlowDefinitionDao flowDefinitionDao;

    @PostMapping("/suitecloud/flowdefinition")
    public ResponseEntity define(@RequestBody FlowDefinition flowDefinition, HttpServletRequest request) {
        System.out.println("FlowDefinition: receiving flow definition " + flowDefinition);
        flowDefinitionDao.save(flowDefinition);
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
