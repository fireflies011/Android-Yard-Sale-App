<?php
//Call For INIT.PHP File
require "init.php";
//DATABASE INFORMATION
$name ="DBNAMEHERE";
$user_name ="DBUSERNAMEHERE";
$user_pass ="DBUSERPASSWORDHERE";
//Inssert Into Database User_info table
$sql_query ="insert into user_info values('$name','$user_pass');";

if(mysqli_query($con,$sql_query))
{
    echo "<h3>Data Insertion Sucess...</h3>";
}
else
{
    echo "Data Insertion error..".mysql_error($con);




?>
