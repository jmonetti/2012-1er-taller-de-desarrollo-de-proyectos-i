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
                var reponse = jQuery(xhr_data.responseText);
                var reponseScript = reponse.filter("script");
                jQuery.each(reponseScript, function(idx, val) { eval(val.text); } );
                setTimeout(main.refresh, 5000);
            }
        });
    }
};
