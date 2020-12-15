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
?>
<!DOCTYPE html>
<html>
<head>
	<title>訂飲料管理系統</title>
</head>
<body>
<h1 align="center">訂飲料管理系統</h1>
<p align="center">目前訂單筆數:<?php echo $total_records;?></p>
<table border="1" align="center">
	<tr>
	<th>訂單號碼</th>
	<th>內容</th>
</tr>
<?php
while ($row_result=mysql_fetch_assoc($result)) {
	echo "<tr>";
	echo "<td>".$row_result["cID"]."</td>";
	echo "<td>".$row_result["cOrder"]."</td>";
	echo "<tr/>";
}
?>
</table>
</body>
</html>