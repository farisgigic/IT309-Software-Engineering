<?php
try {
    $conn = new PDO("mysql:host=localhost;dbname=se-2025", "root", "");
    echo "Connected to database!";
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}
