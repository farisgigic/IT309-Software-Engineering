<?php
require_once __DIR__ . '/BaseDao.class.php';

class CarDao extends BaseDao
{

    public function __construct()
    {
        parent::__construct('cars');
    }
    public function getCarByName($name)
    {
        $query = "SELECT * FROM cars WHERE name = :name";
        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':name', $name);
        $stmt->execute();
        return $stmt->fetch();
    }
    public function count_cars_paginated($search)
    {
        $query = "SELECT COUNT(*) AS count 
                    FROM cars c
                    JOIN users u ON c.user_id = u.id
                    WHERE (LOWER(c.manufacturer) LIKE CONCAT('%', :search, '%') OR
                        LOWER(c.model) LIKE CONCAT('%', :search, '%'));";
        return $this->query_unique($query, [
            'search' => $search
        ]);
    }
    public function get_cars_paginated($user_id, $offset, $limit, $search, $order_column, $order_direction)
    {
        $query =
            "   SELECT c.id, c.manufacturer, c.model, c.year, c.engine, c.user_id
                FROM cars c
                JOIN users u ON c.user_id = u.id
                WHERE (LOWER(c.manufacturer) LIKE CONCAT('%', :search, '%') OR 
                LOWER(c.model) LIKE CONCAT('%', :search, '%') OR
                LOWER(c.year) LIKE CONCAT('%', :search, '%'))
           AND c.user_id = :user_id;
         ORDER BY $order_column $order_direction
         LIMIT $offset, $limit";

        return $this->query($query, [
            'search' => $search,
            "user_id" => $user_id
        ]);
    }
    public function add_car($car)
    {
        $query = "INSERT INTO cars (manufacturer, model, year, mileage, engine, fuel_type, drivetrain, transmission, tires, user_id) 
                  VALUES (:manufacturer, :model, :year, :mileage, :engine, :fuel_type, :drivetrain, :transmission, :tires, :user_id)";
        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':manufacturer', $car['manufacturer']);
        $stmt->bindParam(':model', $car['model']);
        $stmt->bindParam(':year', $car['year']);
        $stmt->bindParam(':mileage', $car['mileage']);
        $stmt->bindParam(':engine', $car['engine']);
        $stmt->bindParam(':fuel_type', $car['fuel_type']);
        $stmt->bindParam(':drivetrain', $car['drivetrain']);
        $stmt->bindParam(':transmission', $car['transmission']);
        $stmt->bindParam(':tires', $car['tires']);
        $stmt->bindParam(':user_id', $car['user_id']);
        return $stmt->execute();
    }

    public function delete_car($id)
    {
        $query = "DELETE FROM cars WHERE id = :id";
        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':id', $id);
    }
}