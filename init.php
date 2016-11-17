
<?php

$db_name =",";
$mysql_user =",";
$mysql_pass =",";
$server_name =",";

con = mysqli_connect($server_name,$mysql_user,$mysql_pass,db_name);
if (!$con)
{
    
    echo "Connection Timed Out..".mysqli_connect_error();

}
else 
{
    echo "<h3>Database Connection Sucessful....</h3>";
}

?>