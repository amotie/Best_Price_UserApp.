<?php
// userID productName Quantity Uprice Price

//Insert Product Data into Cart Table In Databse

require "conn.php";

  $CompanyID= $_POST['CompanyID'];
  $ProductName= $_POST['ProductName'];
  $Sale= $_POST['Sale'];
  $Price=$_POST['Price'];
  $Amount=$_POST['Amount'];

  $stmt = $conn->prepare("SELECT
                            *
                          FROM
                            medicnesearch
                          WHERE
                            Name = ?
                          ");
  $stmt -> execute(array($ProductName));
  $row = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  if ($count > 0) {
      $MedicineID=$row['medicineID'];
      $stmt = $conn->prepare("INSERT INTO medicine (medicineID, amount, price, discount, company_id)
      VALUES(?, ?, ?, ?, ?)");
        $stmt -> execute(array(
        $MedicineID,
        $Amount,
        $Price,
        $Sale,
        $CompanyID
    ));
     $count = $stmt -> rowCount();
     if ($count>0) {echo "Done";}

  }else {
    $stmt = $conn->prepare("INSERT INTO medicnesearch (Name)
      VALUES(?)");
        $stmt -> execute(array(
                  $ProductName
                 ));
                  
      $count = $stmt -> rowCount();
      if($count > 0){
          $stmt = $conn->prepare("SELECT
                            *
                          FROM
                            medicnesearch
                          WHERE
                            Name = ?
                          ");
  $stmt -> execute(array($ProductName));
  $row = $stmt ->fetch(); // fetch data from databse in assoc array [key=>value]
  $count = $stmt -> rowCount();
  if($count>0){
         $MedicineID=$row['medicineID'];
         $stmt = $conn->prepare("INSERT INTO medicine (medicineID, amount,price, discount, company_id)
         VALUES( ?, ?, ?,? ,?)");
          $stmt -> execute(array(
          $MedicineID,
          $Amount,
          $Price,
          $Sale,
          $CompanyID
       ));
            $count = $stmt -> rowCount();
            if($count > 0){echo "Done";}
  }

  }
}
  ?>
