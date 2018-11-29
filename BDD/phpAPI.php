<?php
include 'phpconnect.php';
//fonction qui ce connecte a la BDD en ce basant sur phpconnect.php
function getConnection(){
	$database=new Database();
    $dbconnect = $database->getConnection();
	return $dbconnect;
}
//fonction qui va rechercher les tables de la BDD
function getTable(){
    $dbconnect=getConnection();
	$query=$dbconnect->prepare('show tables');
	$query->execute();
	$tables=array();
	while($rows=$query->fetch(PDO::FETCH_BOTH)){
		$table=$rows[0];
		$tables[]=$table;
	}
	return $tables;
}
//fonction qui va regarder les data d'une table choisie
function getData($search){
	$dbconnect=getConnection();
	$tables=getTable();
	if(in_array($search,$tables)){
		foreach($tables as $table){
			if($table=$search){
				$sql='SELECT * FROM '.$table;
				$dbcheck=$dbconnect->prepare($sql);
				$dbcheck->execute();
				$results=array();
				while ($rowtable = $dbcheck->fetch(PDO::FETCH_ASSOC)){
					$results[]=$rowtable;
				}
				$json=json_encode($results);
				return ($json);
			}
		}
	}
	else{
				echo 'Erreur table introuvable';
	}
}
//function qui retourne toute les données de la BDD 
function getAllData(){
	$dbconnect=getConnection();
	$gettables=getTable();
	$tables=array();
	foreach($gettables as $table){
		$tables[]=$table;
		$sql='SELECT * FROM '.$table;
		$dbcheck=$dbconnect->prepare($sql);
		$dbcheck->execute();
		$results=array();
		while ($rowtable = $dbcheck->fetch(PDO::FETCH_ASSOC)){
			$results[]=$rowtable;
		}
		$tables[]=$results;
	}
	$json=json_encode($tables);
	print_r($json);
	return ($json);
}
//function qui passe les données en print_r d un table 
function readtablejson($search){
	$tables=getTable();
	if(isset($search)){
		if(in_array($search,$tables)){
			print_r(getData($search));
		}
	}
}
?>