<?php

header("Content-Type:text/html;charest=utf-8");
include("connMysql.php");
mysql_query("set character set 'utf8'");//讀庫 
mysql_query("set names 'utf8'");//寫庫 
$seldb = @mysql_select_db("order");
if (!$seldb) die("資料庫連結失敗");
$sql_query = "INSERT INTO `send` (`cID`,`cOrder`) VALUES(";
	$sql_query .= "'".$_GET["id"]."',";
	$sql_query.="'".$_GET["order"]."')"; 
mysql_query($sql_query);
header("Location:showOrder.php")
?>