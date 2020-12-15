<?php

header("Content-Type:text/html;charest=utf-8");
include("connMysql.php");
mysql_query("set character set 'utf8'");//讀庫 
mysql_query("set names 'utf8'");//寫庫 
$seldb = @mysql_select_db("order");
if (!$seldb) die("資料庫連結失敗");
echo $_POST["Order"];
$sql_query = "INSERT INTO `drinks` (`cOrder`) VALUES(";$sql_query.="'".$_POST["Order"]."')"; 
mysql_query($sql_query);
?>