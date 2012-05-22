$(function(){
	var hidAction = $('#action');
	var form = $('form');
	$('input[type=button]').click(function() {
		var action = $(this).attr('action');
		hidAction.val(action);
		form.submit();
	});
});