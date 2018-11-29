<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Affiche Les Tables</title>
</head>
<body>
	<p>
		<form action="phpExec.php" method="post">
			<select>
				<?php
					include 'phpAPI.php';
					$tables =getTable();
					foreach($tables as $table)
					{
						echo '<option type = "text" value="'.$table.'" name="table"/>'.$table.'</option>';
					}
				?>
			</select>
			<input type="submit" value="Recherche"/>
		</form>
	</p>
</body>