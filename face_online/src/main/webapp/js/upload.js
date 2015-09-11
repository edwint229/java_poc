(function() {
	$("#file_upload").fileinput({
		uploadUrl : 'http://localhost/upload',
		maxFileCount : 1,
		previewFileType : "image",
		browseClass : "btn btn-success",
		browseLabel : "Pick Image",
		browseIcon : '<i class="glyphicon glyphicon-picture"></i>',
		removeClass : "btn btn-danger",
		removeLabel : "Delete",
		removeIcon : '<i class="glyphicon glyphicon-trash"></i>',
		uploadClass : "btn btn-info",
		uploadLabel : "Upload",
		uploadIcon : '<i class="glyphicon glyphicon-upload"></i>',
	});
	
	$('#resultData').hide();
})();