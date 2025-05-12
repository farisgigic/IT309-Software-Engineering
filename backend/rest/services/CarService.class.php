<?php
require_once __DIR__ . '/../dao/CarDao.class.php';

class CarService extends BaseService
{
    private $carDao;

    public function __construct()
    {
        $this->carDao = new CarDao();
    }
    public function getCarById($car_id)
    {

        $car = $this->carDao->get_by_id($car_id);
        if (!$car) {
            throw new Exception("Car with ID $car_id does not exist.");
        }
        return $car;
    }
    public function getAllCars()
    {
        return $this->carDao->get_all();
    }
    public function addCar($car)
    {

        return $this->carDao->add_car($car);

    }
    public function deleteCar($car_id)
    {
        return $this->carDao->delete_car($car_id);
    }

    public function editCar($car_id, $car)
    {
        $existingID = $this->carDao->get_by_id($car_id);
        if (!$existingID) {
            throw new Exception("Car with this ID does not exist.");
        }
        return $this->dao->update($car_id, $car);
    }

    public function get_cars_paginated($user_id, $offset, $limit, $search, $order_column, $order_direction)
    {
        $count = $this->carDao->count_cars_paginated($search)['count'];
        $rows = $this->carDao->get_cars_paginated($user_id, $offset, $limit, $search, $order_column, $order_direction);
        $no = $offset + 1;

        foreach ($rows as $id => $car) {
            $rows[$id]['number'] = $no++;
            $rows[$id]['actions'] = '<div class="btn-group" role="group">' .
                ' <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#edit-car-modal">Edit</button> ' .
                ' <button type="button" class="btn btn-outline-danger" onclick="CarService.delete_car(' . $car['id'] . ')">Delete</button> ' .
                '</div>';
        }

        return [
            'count' => $count,
            'data' => $rows
        ];
    }

}