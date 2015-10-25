<?php
$db_host = '127.0.0.1';
$db_user = 'USER';
$db_password = 'PASSWORD';
$database = 'DATABASE';
$table = 'TABLE';

$db = mysql_connect($db_host, $db_user, $db_password);
mysql_select_db($database, $db);

$query = "SELECT COUNT(*) FROM $table";
$result = mysql_query($query) or die(mysql_error());
$count = mysql_result($result, 0);

echo "\nTOTAL: $count\n";

for ($i = 1 ; $i <= $count; $i++) {
    $query = "UPDATE $table SET id=$i WHERE id IS NULL LIMIT 1";
    mysql_query($query, $db) or die(mysql_error());
}

echo "\nComplete!\n";
