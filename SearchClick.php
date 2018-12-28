<?php

require "conn.php";
$Medicne=$_POST['Name'];
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
  $ID=$rows['medicineID'];
  $Name=$rows['Name'];
  
  
$stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicine
                          WHERE
                           medicineID  = ?
                          ");
  $stmt -> execute(array($ID));
  $rows = $stmt ->fetchall(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
if($count>0){
  
     foreach ($rows as $row) {
          $stmt = $conn->prepare("SELECT
                            *
                          FROM
                            company
                          WHERE
                            id = ?
                          ");
                          $stmt -> execute(array($row['company_id']));
  $row1 = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  if($count>0){
       echo $row1['companyName'] ."\n";
  }
        echo $row['price'] ."\n";
          echo $row['amount'] ."\n";
           
            echo $row['discount'] ."\n";
            
            echo $row['id'] ."\n";
            
       
          
      
        

} 
}
       
  }
  
  else{
    echo "NO Record"."\n";
  }
  
  ?>  
  
  