<?php

require "conn.php";
$Medicne=$_POST['Name'];
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
        
       



    
        echo $productName ."\n";
       
          
      
        

} 

       
  }
  
  ?>  
  
  