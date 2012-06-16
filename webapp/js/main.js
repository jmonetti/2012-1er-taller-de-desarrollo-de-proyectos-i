var main = {
	init : function() {
		$('input[type=button]').click(function() {
		    var form = $('form');
            var data = '{';
            $.each(form.serializeArray(), function() {
                if ($(this).attr('name'))
                    data = data + '"' + $(this).attr('name') + '"' + ':' + $(this).attr('value') + ',';
            });
            data = data.slice(0, -1) + '}';
            $.ajax({
                url: $(this).attr('action'),
                data: {'data': data}
            });
		});
	},

    refresh: function() {
        $.ajax({
            url: 'get_emergencies.php',
            complete: function(xhr_data, status) {
                if (status == 'success')
                    $('#emergencies')[0].innerHTML = xhr_data.responseText;
                setTimeout(main.refresh, 5000);
                main.loadMarkers();
            }
        });
    },
	/**
	 * Agrega las emergencias actuales en el mapa.
	 */
	loadMarkers : function() {
        mapHandler.clearMarkers();
		$('#emergencies li').each(function(){
			var lat = $(this).attr('lat');
			var lng = $(this).attr('lng');
			mapHandler.addMarker({ "lat" : lat, "lng" : lng });
		});
	}
};
