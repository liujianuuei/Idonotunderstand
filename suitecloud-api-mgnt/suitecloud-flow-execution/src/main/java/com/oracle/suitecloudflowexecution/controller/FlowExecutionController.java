package com.oracle.suitecloudflowexecution.controller;

import com.oracle.suitecloudflowexecution.model.FlowDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FlowExecutionController {

    @GetMapping("/suitecloud/flowexecution/{id}")
    public void execute(@PathVariable String id) {
        System.out.println("FlowDefinition: receiving flow execution request " + id);
        // Discovery Flow Query service
        FlowDefinition flowDefinition = null;
        // Execute the FLow
    }
}
