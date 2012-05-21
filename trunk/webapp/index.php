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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
var mapHandler = {
	map : null,
	markers : [],
	initialize : function() {
		var myOptions = {
			center: new google.maps.LatLng(-34.54523, -58.4710496),
			zoom: 16,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		mapHandler.map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
		// cargar todas las posiciones
		for(index in mapHandler.markers) {
			mapHandler.addMarker(mapHandler.markers[index]);
		}
	},
	addMarker : function (location) {
		var image = 'images/alert.png';
		var myLatLng = new google.maps.LatLng(location.lat, location.long);
		var beachMarker = new google.maps.Marker({
			position: myLatLng,
			map: mapHandler.map,
			icon: image
		});
	}
}
$(function(){
	mapHandler.initialize();
});
</script>
</head>
	<body>
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
						mapHandler.markers.push(<? echo $location; ?>);
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
