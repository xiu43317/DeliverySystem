<?php
header("refresh:5");
include("connMysql.php");
mysql_query("set character set 'utf8'");//讀庫 
mysql_query("set names 'utf8'");//寫庫 
$seldb = @mysql_select_db("order");
if (!$seldb) die("資料庫連結失敗");
$sql_query = "SELECT *FROM `send`";
$result = mysql_query($sql_query);
$total_records = mysql_num_rows($result);
while ($row_result=mysql_fetch_assoc($result)) {
//	echo $row_result["cID"];
	$id = $row_result["cID"];
//	echo $row_result["cOrder"];
	$order = $row_result["cOrder"];
}
	echo $id;
	echo $order;
	echo "。";
?>