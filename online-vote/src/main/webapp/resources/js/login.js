(function() {
	$('#btnSendPwd').unbind('click').bind('click', function() {
		var seconds = 60; // set the number of seconds
		var disabledElem = $(this);
		disabledElem.attr('disabled', 'disabled');
		// Remember the original text content
		var originalText = disabledElem.text();

		// append the number of seconds to the text
		disabledElem.text(originalText + ' (' + seconds + ')');

		// do a set interval, using an interval of 1000 milliseconds
		// and clear it after the number of seconds counts down to 0
		var interval = setInterval(function() {
			seconds = seconds - 1;
			// decrement the seconds and update the text
			disabledElem.text(originalText + ' (' + seconds + ')');
			if (seconds === 0) { // once seconds is 0...
				// reset to original text
				disabledElem.removeAttr('disabled').text(originalText);
				clearInterval(interval); // clear interval
			}
		}, 1000);
		

		var data = {};
		data.userId = $('#userId').val();
		data.name = $('#name').val();

		$.ajax({
			method : "POST",
			url : "sendPassword.do",
			data : data
		}).done(function(data) {
			var messageBox = $("#messageBox");
			if (data.code == 0) {
				messageBox.empty();
				messageBox.append('<div class="alert alert-danger">'+data.result+'</div>');
				// reset to original text
				disabledElem.removeAttr('disabled').text(originalText);
				clearInterval(interval); // clear interval
			} else {
				messageBox.empty();
				messageBox.append('<div class="alert alert-success">'+data.result+'</div>');
			}
		});
	});
})();