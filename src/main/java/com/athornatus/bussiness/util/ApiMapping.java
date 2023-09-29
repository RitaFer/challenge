package com.athornatus.bussiness.util;

public interface ApiMapping {
    String API_V1 = "/api/v1";

    interface clients {
        String MAPPING = API_V1 + "/clients";
    }

    interface address {
        String MAPPING = API_V1 + clients.MAPPING + "/{idClient}/address";
    }
}
