<?php


require_once __DIR__ . "/../services/CommentService.class.php";
require_once __DIR__ . '/../../data/roles.php';


Flight::set("commentService", new CommentService());

Flight::group("/comments", function () {
    Flight::route("GET /comment/@forum_id", function ($forum_id) {

        $comments = Flight::get("commentService")->get_comments($forum_id);
        Flight::json($comments, 200);
    });

    Flight::route("POST /comment", function () {
        $data = Flight::request()->data->getData();
        $forum_id = $data['forum_id'];
        $user_id = $data['user_id'];
        $comment = $data['comment'];

        if (empty($forum_id) || empty($user_id) || empty($comment)) {
            Flight::halt(400, "Forum ID, User ID, and comment cannot be empty.");
        }

        try {
            Flight::get("commentService")->add_comment($forum_id, $user_id, $comment);
            Flight::json(["message" => "Comment added successfully."], 201);
        } catch (Exception $e) {
            Flight::halt(500, $e->getMessage());
        }
    });

    Flight::route("GET /comment/count/@forum_id", function ($forum_id) {
        try {
            $count = Flight::get("commentService")->count_comments($forum_id);
            Flight::json(["count" => $count], 200);
        } catch (Exception $e) {
            Flight::halt(500, $e->getMessage());
        }
    });
});