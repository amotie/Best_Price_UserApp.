<?php

require "conn.php";
$Medicne=$_POST['Name'];
$ID=$_POST['id'];
$stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicnesearch
                          WHERE
                           Name  LIKE ?
                           
                          ");
  $stmt -> execute(array($Medicne."%"));
  $rows = $stmt ->fetchall(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  
  
  if ($count > 0) {
       foreach ($rows as $row) {
         
          $productName = $row['Name'];
          $IDM=$row['medicineID'];
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
  $rows = $stmt ->fetchall(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
if($count>0){
    echo $productName ."\n";
}
          
       



    
        
       
          
      
        

} 

       
  }
  
  ?>  
  
  