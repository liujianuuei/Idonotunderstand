package com.oracle.suitecloudapiquery.model;

import java.util.List;

public class ApiMetadata {
    private String guid;
    private String name;
    private String category;
    private String version;
    private String host;
    private String endpoint;
    private String token;
    private String description;

    private List<ApiParameter> inputParams;
    private List<ApiParameter> outputParams;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ApiParameter> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<ApiParameter> inputParams) {
        this.inputParams = inputParams;
    }

    public List<ApiParameter> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(List<ApiParameter> outputParams) {
        this.outputParams = outputParams;
    }

    @Override
    public String toString() {
        return "name=" + this.name + ", version=" + this.version + ", inputParams=" + this.inputParams;
    }
}
