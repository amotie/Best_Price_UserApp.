<?php

require "conn.php";
$userID="5";//$_POST['ID'];
$stmt = $conn->prepare("SELECT
                            *
                          FROM
                            cart
                            Inner Join medicine
                            on cart.medicine_id=medicine.id
                          WHERE
                           userID  = ?
                           
                           
                          ");
  $stmt -> execute(array($userID));
  $rows = $stmt ->fetchall(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  
  
  if ($count > 0) {
       foreach ($rows as $row) {
          $cartID=$row['ID'];
          $quantity= $row['Amount'];
          $medID= $row['medicineID'];
          $Price= $row['price'];
          $discount= $row['discount'];
          $MedName= $row['med_name'];
          $CompID=$row['company_id'];
          $MEDID=$row['id'];
            $stmt = $conn->prepare("SELECT
                            *
                          FROM
                           medicnesearch
                          WHERE
                           medicineID  = ?
                          ");
  $stmt -> execute(array($medID));
  $rows = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  if($count>0){
        $Name=$rows['Name'];
  }
           $stmt = $conn->prepare("SELECT
                            *
                          FROM
                           company
                          WHERE
                           id  = ?
                          ");
  $stmt -> execute(array($CompID));
  $rows = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  if($count>0){
        $CompName=$rows['companyName'];
  }

$Price=$Price*$quantity;
$PriceS=$Price-($Price*($discount/100));

        echo $Name ."\n";
        echo $CompName ."\n";
        echo  $PriceS."\n";
        echo $quantity ."\n";
        echo $discount ."\n";
        echo $cartID ."\n";
        echo $MEDID ."\n";
        
        
      
      
        

} 

       
  }
  
  ?>  
  
  