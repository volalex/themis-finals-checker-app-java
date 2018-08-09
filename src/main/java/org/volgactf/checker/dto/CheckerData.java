package org.volgactf.checker.dto;

import io.vertx.core.json.JsonObject;

public class CheckerData {
    public CheckerParams params;
    public CheckerMetadata metadata;
    public String reportUrl;

    public static CheckerData fromJson(JsonObject jsonObject){
        CheckerData checkerData = new CheckerData();
        checkerData.reportUrl = jsonObject.getString("report_url");
        checkerData.params = new CheckerParams();
        checkerData.metadata = new CheckerMetadata();
        checkerData.params.adjunct = jsonObject.getJsonObject("params").getString("adjunct");
        checkerData.params.endpoint = jsonObject.getJsonObject("params").getString("endpoint");
        checkerData.params.flag = jsonObject.getJsonObject("params").getString("flag");
        checkerData.params.requestId = jsonObject.getInteger("request_id");
        checkerData.metadata.round = jsonObject.getJsonObject("metadata").getInteger("round");
        checkerData.metadata.serviceName = jsonObject.getJsonObject("metadata").getString("service_name");
        checkerData.metadata.teamName = jsonObject.getJsonObject("metadata").getString("team_name");
        checkerData.metadata.timestamp = jsonObject.getJsonObject("metadata").getLong("timestamp");

        return checkerData;
    }
}
