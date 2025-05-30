<?php

require_once __DIR__ . "/../dao/AuthDao.class.php";
require_once __DIR__ . "/BaseService.class.php";

class AuthService extends BaseService
{
    private $authDao;
    public function __construct()
    {
        $this->authDao = new AuthDao();
    }
    public function get_user_by_email($email)
    {
        return $this->authDao->get_user_by_email($email);
    }
}