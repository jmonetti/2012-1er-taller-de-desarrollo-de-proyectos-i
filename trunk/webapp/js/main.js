var main = {
	init : function() {
		var hidAction = $('#action');
		var form = $('form');
		$('input[type=button]').click(function() {
			hidAction.val($(this).attr('action'));
			form.submit();
		});
		main.loadAddresses();
	},
	loadAddresses : function() {
		$('location').each(function(){
			var lat = $(this).attr('lat');
			var lng = $(this).attr('lng');
			mapHandler.getAddress(lat, lng, $(this));
		});
	}
};
$(function(){
	main.init();
	mapHandler.init();
});