<?php
header("Content-Type:text/html;charest=utf-8");
include("connMysql.php");
mysql_query("set character set 'utf8'");//讀庫 
mysql_query("set names 'utf8'");//寫庫 
$seldb = @mysql_select_db("order");
if (!$seldb) die("資料庫連結失敗");
$sql_query = "DELETE FROM `drinks` WHERE `cID`=".$_GET["id"];
mysql_query($sql_query);
header("Location:showOrder.php")
?>