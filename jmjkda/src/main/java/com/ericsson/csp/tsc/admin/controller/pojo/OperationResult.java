package com.ericsson.csp.tsc.admin.controller.pojo;

public enum OperationResult {
    OPERATION_SUCCESS(0), 
    OPERATION_SUBMITS(1), 
    OPERATION_FAILURE(2), 
    OPERATION_REACH_TEM(3), 
    ENGINE_RUNNING(4), 
    ENGINE_OFF(5), 
    DIAGNOSTICS_UPDATE_TO_CSP(6), 
    TRACKING_POINTS_DATA(7), 
    ACTIVATE_REMOTE_CONTROL_SUCCESS(8), 
    POI(9), 
    VEHICLE_OUT_OF_GEO_FENCE(10), 
    PICKUP_FRIENDS(11), 
    USER_BIND_VEHICLE(12), 
    IMMOBILIZATION_TO_CEM(13), 
    PM25_VALUE_UPDATE(14), 
    APP_GET_VEHICLE_STATUS(15), 
    CHARGING_STATUS(16),
    UPLOAD_TEM_LOG_STOP(17),
    LOGOUT_NOTIFY(18),
    PICKUP_FRIENDS_CANCEL(20);

    private int value;

    private OperationResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OperationResult findByValue(int value) {
        for (OperationResult state : OperationResult.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        
        return null;
    }

}
