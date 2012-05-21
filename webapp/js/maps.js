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
};

$(function(){
	mapHandler.initialize();
});