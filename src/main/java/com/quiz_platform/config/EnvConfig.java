package com.quiz_platform.config;

import java.util.Map;

public class EnvConfig {
    public static final Map<String, String> databaseStoring = Map.ofEntries(
            Map.entry("storingDb.rootPassword", "STORING_DB_ROOT_PASSWORD"),
            Map.entry("storingDb.user", "STORING_DB_USER"),
            Map.entry("storingDb.password", "STORING_DB_PASSWORD"),
            Map.entry("storingDb.name", "STORING_DB_NAME"),
            Map.entry("storingDb.port", "STORING_DB_PORT"),
            Map.entry("storingDb.domain", "STORING_DB_DOMAIN"),
            Map.entry("storingDb.ddl-auto", "STORING_DB_DDL_AUTO")
    );
    public static final Map<String, String> authStoring = Map.ofEntries(
            Map.entry("authDb.rootPassword", "AUTH_DB_ROOT_PASSWORD"),
            Map.entry("authDb.user", "AUTH_DB_USER"),
            Map.entry("authDb.password", "AUTH_DB_PASSWORD"),
            Map.entry("authDb.name", "AUTH_DB_NAME"),
            Map.entry("authDb.port", "AUTH_DB_PORT"),
            Map.entry("authDb.domain", "AUTH_DB_DOMAIN"),
            Map.entry("authDb.ddl-auto", "AUTH_DB_DDL_AUTO")
    );
}
