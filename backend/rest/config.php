<?php

// Set the reporting 
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL ^ (E_NOTICE | E_DEPRECATED));

class Config
{
    public static function DB_NAME()
    {
        return 'defaultdb';
    }
    public static function DB_PORT()
    {
        return 25060;
    }
    public static function DB_USER()
    {
        return 'doadmin';
    }
    public static function DB_PASSWORD()
    {
        return 'AVNS_swwLd_1Ok3hJd6s9fqj';
    }
    public static function DB_HOST()
    {
        return 'software-engineering-do-user-23012501-0.k.db.ondigitalocean.com';
    }
    public static function JWT_SECRET()
    {
        return 'farecare';
    }
}

