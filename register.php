<?php

require "init.php";

$name ="carlos";
$user_name ="Carlosr1";
$user_pass ="123456789";

$sql_query ="insert into user_info values('$name','$user_pass');";

if(mysqli_query($con,$sql_query))
{
    echo "<h3>Data Insertion Sucess...</h3>";
}
else
{
    echo "Data Insertion error..".mysql_error($con);













?>