package com.ericsson.csp.tsc.admin.util;

public class SysConstant {

    public static final String  SESSION_LOGIN_ID_KEY = "loginId";

    public static final String  SESSION_USER_KEY     = "user";

    public static final int     MENU_TYPE_MENU       = 1;

    public static final int     MENU_TYPE_BUTTON     = 2;

    public static final Integer SESSIONTIMEOUT       = 1800;

    public static final Integer SUCCESS              = 1;

    public static final Integer FAILED               = 0;

    public static final String  LOGSTASH_INDEX_UVC   = "logstash-uvctype-";

    public static final String  LOGSTASH_INDEX_TST   = "logstash-tstype-";
    
    public static final String  LOGSTASH_INDEX_ADM   = "logstash-admintype-";

    public static final String  LOGSTASH_TYPE_CMPP   = "ipc-cmpp-pop";

    public static final String  FIRSTINDEX           = "\"hits\":{\"total\":";

    public static final String  LASTINDEX            = ",\"max_score\"";

    public static final String  ERRORSTATUS          = "exception";

    private SysConstant() {
    }

}
