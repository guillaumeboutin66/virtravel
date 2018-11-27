<?php
 
include phpconnect.php

// get the HTTP method, path and body of the request
$method = $_SERVER['REQUEST_METHOD'];
$request = explode('/', trim($_SERVER['PATH_INFO'],'/'));
$input = json_decode(file_get_contents('php://input'),true);
 
// connect to the mysql database
//$db = mysqli_connect('localhost', 'user', 'pass', 'dbname');
//mysqli_set_charset($db,'utf8');
 
$db= Database.getConnection();
mysqli_set_charset($db,'utf8');

function read($db){
 
    // select all query
    $query = "SELECT
                c.name as category_name, p.id, p.name, p.description, p.price, p.category_id, p.created
            FROM
                " . $this->table_name . " p
                LEFT JOIN
                    categories c
                        ON p.category_id = c.id
            ORDER BY
                p.created DESC";
 
    // prepare query statement
    $stmt = $this->conn->prepare($query);
 
    // execute query
    $stmt->execute();
 
    return $stmt;
}

if ($method == 'GET') {
  if (!$key) echo '[';
	for ($i=0;$i<mysqli_num_rows($result);$i++) {
		echo ($i>0?',':'').json_encode(mysqli_fetch_object($result));
	}
	if (!$key) echo ']';
}elseif ($method == 'POST'){
	echo mysqli_insert_id($db);
}else{
	echo mysqli_affected_rows($db);
}
// close mysql connection
mysqli_close($db);