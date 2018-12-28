<?php
/*$db_name="id7264274_pharmacy";
$mysql_username="id7264274_pharmacy";
$mysql_password="123123123";
$server_name="localhost";
$conn=mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
*/

$dsn="mysql:host=localhost;dbname=u186075597_db";
$username="u186075597_david";
$password="kda123";

try{
$conn= new PDO($dsn,$username,$password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
   
}
catch(PDOException $e)
    {
    echo "Connection failed: " . $e->getMessage();
    }


/*if(!$conn){
    die("connection faild : ".mysqli_connect_error());
}
*/
?>