<?php
require_once __DIR__ . '/../dao/CommentDao.class.php';
require_once __DIR__ . '/BaseService.class.php';

class CommentService extends BaseService
{
    public function __construct()
    {
        parent::__construct(new CommentDao());
    }
    public function getAllCars()
    {
        return $this->dao->get_all();
    }
    public function get_comments($forum_id)
    {
        $comments = $this->dao->get_comments($forum_id);
        if (!$comments) {
            throw new Exception("No comments found for forum ID $forum_id.");
        }
        return $comments;
    }

    public function add_comment($forum_id, $user_id, $comment)
    {
        if (empty($forum_id) || empty($user_id) || empty($comment)) {
            throw new Exception("Forum ID, User ID, and comment cannot be empty.");
        }
        $result = $this->dao->add_comment($forum_id, $user_id, $comment);
        if (!$result) {
            throw new Exception("Failed to add comment.");
        }
        return true;
    }

    public function count_comments($forum_id)
    {
        $count = $this->dao->count_comments($forum_id);
        if ($count === false) {
            throw new Exception("Failed to count comments for forum ID $forum_id.");
        }
        return $count;
    }
}