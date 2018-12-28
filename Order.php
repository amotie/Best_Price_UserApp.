<?php
// userID productName Quantity Uprice Price

//Insert Product Data into Cart Table In Databse

require "conn.php";


  $userID="5";//$_POST['ID'];
  $quantity="62";//$_POST['Amount'];
  $MedID="10";//$_POST['MedID'];
  $CartID="36";//$_POST['CartID'];
  
  
  
   $stmt = $conn->prepare("SELECT
                            *
                          FROM
                            medicine
                          WHERE
                            id = ?
                          ");
  $stmt -> execute(array($MedID));
  $row = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
if($count>0){  
    $Price=$row['price'];
    $Sale=$row['discount'];
    $Amount=$row['amount'];
    $PriceS=$Price-($Price*($Sale/100));
    $CompanyID=$row['company_id'];
    $Amount=$Amount-$quantity;
  if($Amount<0){
           $stmt = $conn->prepare("DELETE FROM cart WHERE ID = ? LIMIT 1");
     // Excute Query
    $stmt -> execute(array($CartID));
    $count = $stmt -> rowCount();
    if($count>0){
        	 echo "the Stock is =".$Amount;
    }
      
  }
  else if($Amount==0){
       $stmt = $conn->prepare("INSERT INTO orders (user_id,amount,medicine_id,price,CompaniesID)
  VALUES(?, ?, ?, ?, ?)");
// Excute Query
  $stmt -> execute(array(
    $userID,
    $quantity,
    $MedID,
    $PriceS,
    $CompanyID
    ));

  $count = $stmt -> rowCount();
  
  if($count > 0){
        $stmt = $conn->prepare("DELETE FROM medicine WHERE id = ? LIMIT 1");
     // Excute Query
    $stmt -> execute(array($MedID));
    $count = $stmt -> rowCount();
    if($count>0){
      
         $stmt = $conn->prepare("DELETE FROM cart WHERE ID = ? LIMIT 1");
     // Excute Query
    $stmt -> execute(array($CartID));
    $count = $stmt -> rowCount();
    if($count>0){
        	 echo "Done";  
    }
    }
  }
  }
  else{
  $stmt = $conn->prepare("INSERT INTO orders (user_id,amount,medicine_id,price,CompaniesID)
  VALUES(?, ?, ?, ?, ?)");
// Excute Query
  $stmt -> execute(array(
    $userID,
    $quantity,
    $MedID,
    $PriceS,
    $CompanyID
    ));

  $count = $stmt -> rowCount();
  
  if($count > 0){
   
   
       
  
        $stmt = $conn->prepare("UPDATE medicine SET amount = ? WHERE id = ? LIMIT 1");
    // Excute Query
    $stmt -> execute(array($quantity, $MedID));
    $count = $stmt -> rowCount();

	if ($count > 0) {
		
	  
	 $stmt = $conn->prepare("DELETE FROM cart WHERE ID = ? LIMIT 1");
     // Excute Query
    $stmt -> execute(array($CartID));
    $count = $stmt -> rowCount();
    if($count>0){
        	 echo "Done";  
    }
    

	
    
    }
  

}

}
}
 ?>
