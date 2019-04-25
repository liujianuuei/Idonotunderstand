package com.oracle.suitecloudflowdefinition.dao;

import com.oracle.suitecloudflowdefinition.model.FlowDefinition;

public class FlowDefinitionDao {
    public void save(FlowDefinition flowDefinition) {
        validate(flowDefinition);
        // Should support JPA and SS2.0 N/record
        System.out.println("FlowDefinition: saving flow definition...");
    }

    public void validate(FlowDefinition flowDefinition) {
        System.out.println("FlowDefinition: validating flow definition...");
    }
}
