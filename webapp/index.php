<?
	$mongo = new Mongo();
	$db = $mongo->urban_security;
?>
<html>
<head>
<meta name="author" content="Kai Oswald Seidler">
<meta http-equiv="cache-control" content="no-cache">
<title>Urban-Security</title>
<script type="text/javascript" 
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAJ-lN0BVnxuQ06N3YUyFS_iUA8kuoFYVI&sensor=false">
</script>
<script type="text/javascript">
	var map = null
	function initialize() {
		var myOptions = {
			center: new google.maps.LatLng(-34.54523, -58.4710496),
			zoom: 16,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	}
	function addMarker(location) {
		//alert(location.lat + ':' + location.long);
		var image = 'images/alert.png';
		var myLatLng = new google.maps.LatLng(location.lat, location.long);
		var beachMarker = new google.maps.Marker({
			position: myLatLng,
			map: map,
			icon: image
		});
	}
</script>
</head>
<body bgcolor=#ffffff onload="initialize()">
<div style="float:left">
<?
	$cursor = $db->emergencies->find();
	// iterate through the results
	if($cursor)
	{
		foreach ($cursor as $obj) {
			foreach ($obj as $value)
			{
				echo $value . "<br>";
			}
			$location = "{'lat':" . $obj["lat"] . ", 'long':".$obj["long"]."}";
			?>
			<script type="text/javascript">
				addMarker(<? echo $location; ?>);
			</script>
			<?
		}
	}
?>
</div>
	<div id="map_canvas" style="width:700px; height:500px; float:right;"></div>
	<?
		if(array_key_exists("data", $_REQUEST))
		{
			$json = $_REQUEST["data"];
			echo $json;
			$data = json_decode ($json, true);
			var_dump($data);
			$db->emergencies->insert($data);
		}
		else
		{
			echo "No data";
		}		
	?>
</body>
</html>
