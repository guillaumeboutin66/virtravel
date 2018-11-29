<?php
include 'phpAPI.php';
if(isset($_POST['table'])){
    $table=$_POST['table'];
    $lecture=readtablejson($table);
    print_r ($lecture);
}else{
    echo 'Hum je n est pas de post envoyer voici les data en cas ou ';
    getalldata();
}
?>