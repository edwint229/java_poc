(function() {
	function showErrorMessage(message) {
		var messageBox = $("#messageBox");
		messageBox.empty();
		messageBox.append('<div class="alert alert-danger">' + message
				+ '</div>');
	}

	function showSuccessMessage(message) {
		var messageBox = $("#messageBox");
		messageBox.empty();
		messageBox.append('<div class="alert alert-success">' + message
				+ '</div>');
	}

	function disableForm() {
		$('input[name="name"]').attr('disabled', 'disabled');
		$("input[name='gender']").attr('disabled', 'disabled');
		$('input[name="inditifyNo"]').attr('disabled', 'disabled');
		$('input[name="phoneNo"]').attr('disabled', 'disabled');
		$('input[name="remark"]').attr('disabled', 'disabled');
		$('#submitRegister').attr('disabled', 'disabled');
		$('#modifyLink').show();
	}

	function enableForm() {
		$('input[name="name"]').removeAttr('disabled');
		$("input[name='gender']").removeAttr('disabled');
		$('input[name="inditifyNo"]').removeAttr('disabled');
		$('input[name="phoneNo"]').removeAttr('disabled');
		$('input[name="remark"]').removeAttr('disabled');
		$('#submitRegister').removeAttr('disabled');
		$('#modifyLink').hide();
	}

	$('#modifyLink').unbind('click').bind('click', function() {
		enableForm();
	});

	$('#submitRegister').unbind('click').bind('click', function() {
		var data = {};
		data.name = $('input[name="name"]').val();
		data.gender = $("input[name='gender']:checked").val();
		data.inditifyNo = $('input[name="inditifyNo"]').val();
		data.phoneNo = $('input[name="phoneNo"]').val();
		data.remark = $('input[name="remark"]').val();

		if (!data.name) {
			return showErrorMessage('姓名不能为空');
		} else if (!data.gender) {
			return showErrorMessage('请选择性别');
		} else if (!data.inditifyNo) {
			return showErrorMessage('身份证不能为空');
		} else if (!data.phoneNo) {
			return showErrorMessage('手机号不能为空');
		}

		$.ajax({
			method : "POST",
			url : "processOutingRegister.do",
			data : data
		}).done(function(data) {
			if (data.code == 0) {
				showErrorMessage(data.result);
			} else {
				disableForm();
				showSuccessMessage(data.result);
			}
		});
	});

	function init() {
		$.ajax({
			method : "POST",
			url : "getPersonalOutingRegister.do"
		}).done(function(data) {
			if (data.code == 1) {
				if (data.result) {
					disableForm();
					
					$('input[name="name"]').val(data.result.name);
					$('input[name="gender"]').filter('[value='+data.result.gender+']').attr('checked', true);
					$('input[name="inditifyNo"]').val(data.result.inditifyNo);
					$('input[name="phoneNo"]').val(data.result.phoneNo);
					$('input[name="remark"]').val(data.result.remark);
				}else{
					enableForm();
				}
			}
		});
	}

	init();
})();