package com.oracle.suitecloudapiregistry.dao;

import com.oracle.suitecloudapiregistry.err.ApiMetadataInvalid;
import com.oracle.suitecloudapiregistry.model.ApiMetadata;

public class ApiRegistryDao {
    public boolean save(ApiMetadata api) throws ApiMetadataInvalid {
        validate(api);
        // should support JPA and SS2.0 N/record
        System.out.println("ApiRegistry: saving the API metadata...");
        return true;
    }

    public boolean validate(ApiMetadata api) throws ApiMetadataInvalid {
        System.out.println("ApiRegistry: validating the API metadata to registry...");
        if(api.getGuid() == null) {
            throw new ApiMetadataInvalid("GUID is missed");
        }
        if(api.getHost() == null) {
            throw new ApiMetadataInvalid("Host is missed");
        }
        return true;
    }
}
