var mapHandler = {
	map : null,
	markers : [],
	init : function() {
		var myOptions = {
			center: new google.maps.LatLng(-34.54523, -58.4710496),
			zoom: 11,
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
		var myLatLng = new google.maps.LatLng(location.lat, location.lng);
		var beachMarker = new google.maps.Marker({
			position: myLatLng,
			map: mapHandler.map,
			icon: image
		});
	},
	/**
	 * Utilizado para cargar las direcciones desde la página.
	 */
	getAddress : function (lat, lng, element) {
		var geo = new google.maps.Geocoder();
		var req = { location : new google.maps.LatLng(lat, lng)};
		geo.geocode(req, function(results, status) {
			mapHandler.handleAddressResults(results, status, element);
		});
	},
	handleAddressResults : function(results, status, element) {
		if(status == 'OK') {
			element.html(element.html() + mapHandler.getPrettyAddress(results[0]));
		}
		else {
			element.html(element.html() + 'Address not found.');
		}
	},
	getPrettyAddress : function (result) {
		var address = '';
		var $components = $(result.address_components);
		$components.each(function() {
			address = $(this).prop('long_name') + ', ' + address; 
		});

		return address;
	}
};