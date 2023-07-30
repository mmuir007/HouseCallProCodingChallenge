package com.iconplc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static int logLevel;

    private static final String GET_STACK_TRACE = "java.lang.Thread.getStackTrace";

    public static void setLogLevel( int logLevel ) {
        Logger.logLevel = logLevel;
    }

    public static void debug( String message ) {
        if ( Logger.logLevel < LogLevel.DEBUG ) {
            return;
        }
        var dateTime = LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME );
        var logLevel = LogLevel.asString( LogLevel.DEBUG );
        log( logLevel, dateTime, message );
    }

    public static void info( String message ) {
        if ( Logger.logLevel < LogLevel.INFO ) {
            return;
        }
        var dateTime = LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME );
        var logLevel = LogLevel.asString( LogLevel.INFO );
        log( logLevel, dateTime, message );
    }

    public static void error( String message ) {
        if ( Logger.logLevel < LogLevel.ERROR ) {
            return;
        }
        var dateTime = LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME );
        var logLevel = LogLevel.asString( LogLevel.ERROR );
        log( logLevel, dateTime, message );
    }

    private static void log( String logLevel, String dateTime, String message ) {
        // get calling class/method names
        String classAndMethodNames = "";
        var stackTraceElements = Thread.currentThread().getStackTrace();
        for ( StackTraceElement elem : stackTraceElements ) {
            classAndMethodNames = elem.getClassName() + "." + elem.getMethodName();
            if ( classAndMethodNames.equals( GET_STACK_TRACE ) ) {
                continue;
            }
            if ( !Logger.class.getName().equals( elem.getClassName() ) ) {
                System.out.println( elem.getClassName() + "." + elem.getMethodName() );
                break;
            }
        }
        var threadName = Thread.currentThread().getName();
        System.out.println(String.format("%s [%s] %s", dateTime, threadName, classAndMethodNames ));
        System.out.println(String.format( "%s: %s", logLevel, message ));
    }

}
