package com.iconplc.util;

public class LogLevel {
    public static final int DEBUG = 3;
    public static final int INFO = 2;
    public static final int ERROR = 1;

    public static String asString( int logLevel ) {
        var result = "UNRECOGNIZED VALUE ";
        switch( logLevel ) {
            case DEBUG:
                result = "DEBUG";
                break;
            case INFO:
                result = "INFO";
                break;
            case ERROR:
                result = "ERROR";
                break;
            default:
                result += logLevel;
                break;
        }
        return result;
    }
}
