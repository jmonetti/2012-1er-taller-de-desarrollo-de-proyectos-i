var main = {
	init : function() {
		var hidAction = $('#action');
		var form = $('form');
		$('input[type=button]').click(function() {
			hidAction.val($(this).attr('action'));
			form.submit();
		});
		main.loadMarkers();
	},
	/**
	 * Agrega las emergencias actuales en el mapa.
	 */
	loadMarkers : function() {
		$('#emergencies li').each(function(){
			var lat = $(this).attr('lat');
			var lng = $(this).attr('lng');
			mapHandler.markers.push({ "lat" : lat, "lng" : lng });
		});
	}
};
$(function(){
	main.init();
	mapHandler.init();
});