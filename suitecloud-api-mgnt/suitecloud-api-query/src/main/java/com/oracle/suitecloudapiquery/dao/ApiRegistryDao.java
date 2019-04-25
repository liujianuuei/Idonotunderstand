package com.oracle.suitecloudapiquery.dao;

import com.oracle.suitecloudapiquery.model.ApiMetadata;

public class ApiRegistryDao {
    public ApiMetadata query(ApiMetadata filters) {
        // Query from JPA or SS2.0 N/record
        ApiMetadata api = new ApiMetadata();
        api.setGuid(filters.getGuid());
        api.setHost(filters.getHost());
        api.setDescription("Test purpose");
        return api;
    }
}
