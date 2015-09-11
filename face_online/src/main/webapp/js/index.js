(function() {
	var snapImgCanvas = document.getElementById('snapImgCanvas');
	var snapImgContext = snapImgCanvas.getContext('2d');
	var smallImgCanvas = document.getElementById('smallImgCanvas');
	var smallImgContext = smallImgCanvas.getContext('2d');
	var video = document.getElementById('video');

	function init() {
		$('#btn_analysis').hide();
		$('#btn_identify').hide();
		$('#btn_download').hide();
	}

	init();

	// Put event listeners into place
	window.addEventListener('DOMContentLoaded', function() {
		// Grab elements, create settings, etc.
		var videoObj = {
			'video' : true
		};
		var errBack = function(error) {
			console.log('Video capture error: ', error.code);
		};

		// Put video listeners into place
		if (navigator.getUserMedia) { // Standard
			navigator.getUserMedia(videoObj, function(stream) {
				video.src = stream;
				video.play();
			}, errBack);
		} else if (navigator.webkitGetUserMedia) { // WebKit-prefixed
			navigator.webkitGetUserMedia(videoObj, function(stream) {
				video.src = window.webkitURL.createObjectURL(stream);
				video.play();
			}, errBack);
		} else if (navigator.mozGetUserMedia) { // Firefox-prefixed
			navigator.mozGetUserMedia(videoObj, function(stream) {
				video.src = window.URL.createObjectURL(stream);
				video.play();
			}, errBack);
		}
	}, false);

	$('#btn_snap').unbind('click').bind('click', function() {
		snapImgContext.drawImage(video, 0, 0, 640, 480);
		smallImgContext.drawImage(video, 0, 0, 300, 225);

		$('#btn_analysis').show();
		$('#btn_identify').show();
		$('#btn_download').show();
	});

	$('#btn_download').on('click', function() {
		var tmpImgName = Date.now();
		snapImgCanvas.toBlob(function(blob) {
			saveAs(blob, tmpImgName + '.png');
		});
	});

	$('#btn_analysis').on('click', function() {
		// Base64 Data
		var imgData = smallImgCanvas.toDataURL();
		var base64Data = imgData.replace(/^data:image\/\w+;base64,/, '');
		console.log(base64Data.length);

		$.ajax({
			type : 'POST',
			url : 'http://localhost/upload',
			data : {
				img : base64Data
			},
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function() {
			}
		});
	});
})();