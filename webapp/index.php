<?php
	include_once('includes/EmergenciesDA.php');

	$dataAccess = new EmergenciesDA();
	
	if(isset($_REQUEST["action"])) {
		$action = $_REQUEST["action"];

		switch ($action) {
			case 'reset':
				echo 'Droppin...';
				$dataAccess->delete_all();
			break;
			case 'add':
				echo 'Adding...';
				$lat = $_REQUEST["txtLat"];
				$long = $_REQUEST["txtLong"];
				$location = array('lat' => $lat, 'lng' => $long);
				echo json_encode($location);
				$dataAccess->add($location);
			break;
		}
	}
	
?>
<html>
	<head>
		<meta http-equiv="cache-control" content="no-cache">
		<title>Seguridad</title>
		<script type="text/javascript" 
			src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAJ-lN0BVnxuQ06N3YUyFS_iUA8kuoFYVI&sensor=false">
		</script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/maps.js"></script>
		<script type="text/javascript" src="js/main.js"></script>

		<link href="css/default.css"  type="text/css" rel="Stylesheet" />
	</head>
	<body>
		<form method="POST">
			<input type="hidden" id="action" name="action" />
			<div id="map_canvas" style="width:700px; height:500px"></div>
			<div>
				<input type="button" value="Refresh" action="refresh" /><br />
				<input type="button" name="btnReset" value="Reset all" action="reset" />
				<div>
					Lat <input type="text" name="txtLat" value="" />
					Long <input type="text" name="txtLong" value="" />
					<input type="button" name="btnAdd" value="Add" action="add" />
				</div>
			</div>
			<div>
				<h2>Current Emergencies</h2>
				<ul id="emergencies">
				<?php
					$cursor = $dataAccess->get_all();
					// iterate through the results
					if($cursor) {
						$markers = '';
						foreach ($cursor as $obj) {
							$emergency = '('.$obj['lat'].', '.$obj['lng'].')';
							if(isset($obj['number']))
								$emergency .= $obj['number'];
							$emergency .= ': ' . $obj['address'];
							?>
							<li lat="<?php echo $obj['lat']; ?>" lng="<?php echo $obj['lng'] ?>">
								<?php echo $emergency; ?>
							</li>
							<?php
						}
					}
				?>
				</ul>
			</div>
		</form>
	</body>
</html>
