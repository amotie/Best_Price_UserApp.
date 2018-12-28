<?php

require "conn.php";
$CompanyID="1";//$_POST['ID'];
$stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicine
                          WHERE
                           company_id  = ?
                          ");
  $stmt -> execute(array($CompanyID));
  $rows = $stmt ->fetchall(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  
  
  if ($count > 0) {
      
       foreach ($rows as $row) {
         
          $MidID = $row['medicineID'];
            $stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicnesearch
                          WHERE
                           medicineID  = ?
                          ");
  $stmt -> execute(array($MidID));
  $rows = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
        
       



    
       
        echo  $rows['Name']."\n";
         echo $row['amount']."\n";
         echo $row['price']."\n";
         echo $row['discount']."\n";
         echo $row['id']."\n";
       
          
      
        

} 

       
  }
  
  ?>  
  
  