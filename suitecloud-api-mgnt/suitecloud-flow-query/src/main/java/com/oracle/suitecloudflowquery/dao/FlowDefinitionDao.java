package com.oracle.suitecloudflowquery.dao;

import com.oracle.suitecloudflowquery.model.FlowDefinition;

public class FlowDefinitionDao {
    public FlowDefinition query(FlowDefinition flowDefinition) {
        // Should support JPA and SS2.0 N/record
        System.out.println("FlowDefinition: querying flow definition...");
        return new FlowDefinition();
    }
}
