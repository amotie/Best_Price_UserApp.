<?php
// userID productName Quantity Uprice Price

//Insert Product Data into Cart Table In Databse

require "conn.php";


  $Username= $_POST['username'];
  $Email= $_POST['email'];
  $Role= $_POST['role'];
  $Phone= $_POST['phone'];
  $Password=$_POST['password'];
  $Address=$_POST['Address'];
  $PharmacyName=$_POST['Pharmacy_Name'];

 
  
  
  
   
    
  $stmt = $conn->prepare("INSERT INTO users (	Username, Email,Password, role, adress,phone,	pharmacy_name)
  VALUES(?, ?, ?,? ,?,?,?)");
// Excute Query
  $stmt -> execute(array(
    $Username,
    $Email,
    $Password,
     $Role,
     
$Address,   
    $Phone,
     
$PharmacyName
));

  $count = $stmt -> rowCount();
  
  if($count > 0){
        echo "Record Added";  
    
    }
  




 ?>
