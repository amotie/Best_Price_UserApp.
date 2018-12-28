<?php

require "conn.php";
$Medicne=$_POST['Name'];
$ID=$_POST['id'];
$stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicnesearch
                          WHERE
                           Name  = ?
                           
                          ");
  $stmt -> execute(array($Medicne));
  $rows = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  
  
  if ($count > 0) {
       
         
          $productName = $rows['Name'];
          $IDM=$rows['medicineID'];
          $stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicine
                          WHERE
                           medicineID  = ?
                           AND
                           company_id= ?
                          ");
  $stmt -> execute(array($IDM,$ID));
  $rows = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
if($count>0){
    
    echo $productName ."\n";
    echo $rows['amount']."\n";
    echo $rows['price']."\n";
    echo $rows['discount']."\n";
    
}
      
}

  
  ?>  
  
  