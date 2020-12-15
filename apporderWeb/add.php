<?php

header("Content-Type:text/html;charest=utf-8");
include("connMysql.php");
mysql_query("set character set 'utf8'");//讀庫 
mysql_query("set names 'utf8'");//寫庫 
$seldb = @mysql_select_db("class");
if (!$seldb) die("資料庫連結失敗");
$sql_query = "INSERT INTO `students`(`cName` ,`cSex` ,`cBirthday` ,`cEmail` ,`cPhone` ,`cAddr`)VALUES(";
	$sql_query .= "'".$_POST["cName"]."',";
	$sql_query .= "'".$_POST["cSex"]."',";
	$sql_query .= "'".$_POST["cBirthday"]."',";
	$sql_query .= "'".$_POST["cEmail"]."',";
	$sql_query .= "'".$_POST["cPhone"]."',";
	$sql_query .= "'".$_POST["cAddr"]."')";
mysql_query($sql_query);
?>
