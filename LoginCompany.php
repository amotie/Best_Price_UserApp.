<?php
require "conn.php";

$username= $_POST["username"];
$userpass=$_POST["password"];


$stmt = $conn->prepare("SELECT
                            id,email,adress,phone,companyName,username	,	password
                          FROM
                             company
                          WHERE
                            username = ?
                          AND
                            	password = ? 
                             ");
  $stmt -> execute(array($username, $userpass));
  $row = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  //$count => it returns the number of rows found in database by the last SQL statement
    
  //If [Count > 0] This Means The Databse Contain Record About This User
  
  if ($count > 0) {
      echo "Correct\n";
      echo $row['id'];
      echo "\n";
       echo $row['email'];
       echo "\n";
       echo $row['adress'];
       echo "\n";
       echo $row['phone'];
       echo "\n";
       echo $row['companyName'];
  }else {
    echo "error";
  }

?>