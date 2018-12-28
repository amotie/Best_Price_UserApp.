  <?php
    require 'conn.php';
    
    $cartID=$_POST['id'];
    
    $stmt = $conn->prepare("DELETE FROM cart WHERE id = ? LIMIT 1");
     // Excute Query
    $stmt -> execute(array($cartID));
    $count = $stmt -> rowCount();
    if($count>0){
        echo"Deleted";
    }
    else{
            echo"Error In Deleting";
    }
    
    
?>