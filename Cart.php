<?php
// userID productName Quantity Uprice Price

//Insert Product Data into Cart Table In Databse

require "conn.php";


  $userID=$_POST['ID'];
  $quantity= $_POST['Amount'];
  $MedID=$_POST['MedID'];
  
  
   
    
  $stmt = $conn->prepare("INSERT INTO cart (UserID,amount,medicine_id)
  VALUES(?, ?, ?)");
// Excute Query
  $stmt -> execute(array(
    $userID,
    $quantity,
    $MedID
    ));

  $count = $stmt -> rowCount();
  
  if($count > 0){
        echo "Done";  
    
    }
  




 ?>
