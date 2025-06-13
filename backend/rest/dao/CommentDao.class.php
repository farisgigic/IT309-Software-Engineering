<?php
require_once __DIR__ . '/BaseDao.class.php';

class CommentDao extends BaseDao
{
    public function __construct()
    {
        parent::__construct('comments');
    }

    public function getAllComments()
    {
        return $this->get_all();
    }

    public function get_comments($forum_id)
    {
        $query = "SELECT c.id, c.forum_id, CONCAT(u.first_name, ' ', u.last_name) as name, c.comment 
                    FROM comments c
                    JOIN users u ON c.user_id = u.id 
                    WHERE c.forum_id = :forum_id";

        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':forum_id', $forum_id);
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function add_comment($forum_id, $user_id, $comment)
    {
        $query = "INSERT INTO comments (forum_id, user_id, comment) VALUES (:forum_id, :user_id, :comment)";
        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':forum_id', $forum_id);
        $stmt->bindParam(':user_id', $user_id);
        $stmt->bindParam(':comment', $comment);
        return $stmt->execute();
    }

    public function count_comments($forum_id)
    {
        $query = "SELECT COUNT(*) as count FROM comments WHERE forum_id = :forum_id";
        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(':forum_id', $forum_id);
        $stmt->execute();
        return $stmt->fetchColumn();
    }
}