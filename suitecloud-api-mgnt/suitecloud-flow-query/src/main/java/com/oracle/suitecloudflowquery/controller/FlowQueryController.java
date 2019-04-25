package com.oracle.suitecloudflowquery.controller;

import com.oracle.suitecloudflowquery.dao.FlowDefinitionDao;
import com.oracle.suitecloudflowquery.model.FlowDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FlowQueryController {

    @Autowired
    FlowDefinitionDao flowDefinitionDao;

    @PostMapping("/suitecloud/flowquery")
    public FlowDefinition define(@RequestBody FlowDefinition flowDefinition, HttpServletRequest request) {
        System.out.println("FlowDefinition: receiving flow query request " + flowDefinition);
        return flowDefinitionDao.query(flowDefinition);
    }
}
