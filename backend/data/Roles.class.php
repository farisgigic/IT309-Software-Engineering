<?php

class Roles
{
    private static ?Roles $instance = null;

    private array $roles;

    private function __construct()
    {
        $this->roles = [
            'admin' => 'admin',
            'user' => 'user'
        ];
    }
    public static function getInstance(): Roles
    {
        if (self::$instance === null) {
            self::$instance = new Roles();
        }
        return self::$instance;
    }

    public function getRoleName(string $key): ?string
    {
        return $this->roles[$key] ?? null;
    }

    public function getAllRoles(): array
    {
        return $this->roles;
    }
}
