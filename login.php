<?php
require "conn.php";

$username=$_POST["username"];
$userpass=$_POST["password"];


$stmt = $conn->prepare("SELECT
                            userID ,Email,adress,phone,pharmacy_name,Username,Password,role
                          FROM
                            users
                          WHERE
                            Username = ?
                          AND
                            Password = ?
                              AND
                            role = ?");
  $stmt -> execute(array($username, $userpass,"1"));
  $row = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  //$count => it returns the number of rows found in database by the last SQL statement
    
  //If [Count > 0] This Means The Databse Contain Record About This User
  
  if ($count > 0) {
      echo "Correct";
      echo "\n";
      echo $row['userID'];
      echo "\n";
       echo $row['Email'];
       echo "\n";
       echo $row['adress'];
       echo "\n";
       echo $row['phone'];
       echo "\n";
       echo $row['pharmacy_name'];
  }else {
    echo "error";
  }

?>