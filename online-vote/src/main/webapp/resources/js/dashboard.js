(function($) {
	var isFirefox = navigator.userAgent.match("Firefox");
	var percentObj = new Object();
	percentObj.scaleOverlay = false;
	percentObj.scaleOverride = true;
	percentObj.scaleSteps = 12;
	percentObj.scaleStepWidth = 5;
	percentObj.scaleStartValue = 0;
	percentObj.scaleShowGridLines = true;
	percentObj.scaleGridLineColor = "rgba(0,0,0,0.05)";
	percentObj.scaleGridLineWidth = 1;
	percentObj.barDatasetSpacing = 1;
	percentObj.barStrokeWidth = 2;
	percentObj.barValueSpacing = 25;
	percentObj.animation = isFirefox;
	percentObj.animationSteps = isFirefox ? 2 : 1;
	percentObj.animationEasing = "easeOutQuart";
	percentObj.onAnimationComplete = null;

	var percent = JSON.parse(JSON.stringify(percentObj));

	function getCanvasContext(id) {
		var canvas = document.getElementById(id);
		if (typeof (canvas.getContext) != "function") {
			G_vmlCanvasManager.initElement(canvas);
		}

		return canvas.getContext("2d");
	}

	function loadVoteResult() {
		$.ajax({
			method : "POST",
			url : "getVoteResult.do"
		}).done(function(data) {
			if (data.code == 1) {
				new Chart(getCanvasContext("voteResult")).Bar(
						data.result, percent);
			}
		});
	}

	function loadVoteOptions() {
		$.ajax({
			method : "POST",
			url : "getVoteOptions.do"
		}).done(function(data) {
			var voteOptions = $("#voteOptions");
			if (data.code == 1) {
				voteOptions.empty();
				voteOptions.append('<input type="hidden" id="selectId" value="'+data.result.voteOptions[0].selectId+'" />');
				voteOptions.append('<div class="row"><h2 class="form-signin-heading">请对如下方案进行投票，可多选(投票后不可修改)。</h2></div>');
				$.each(data.result.voteOptions, function(i,val){
				      if( val.voted){
				    	  voteOptions.append('<div class="checkbox"><label><input name="optionCheckbox" type="checkbox" value="'+val.id+'" checked=checked>'
									+ val.fullName + '</label></div>');
				      }else{
				    	  voteOptions.append('<div class="checkbox"><label><input name="optionCheckbox" type="checkbox" value="'+val.id+'">'
									+ val.fullName + '</label></div>');
				      }
				});
				
				if(data.result.isVoted){
					$('#submitVote').attr('disabled', 'disabled');
					$('input[name="optionCheckbox"]').attr('disabled', 'disabled');
				}
			}
		});
	}
	
	$('#submitVote').unbind('click').bind('click', function(){
		var disabledElem = $(this);
		disabledElem.attr('disabled', 'disabled');
		
		var messageBox = $("#messageBox");
		var data = {};
		data.selectId = $('#selectId').val();
		var chk_value = [];
		$('input[name="optionCheckbox"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		
		if(chk_value.length === 0){
			messageBox.empty();
			messageBox.append('<div class="alert alert-danger">请选择投票项。</div>');
			disabledElem.removeAttr('disabled');
			return false;
		}
		data.optionIds = chk_value.toString();
		
		$.ajax({
			method : "POST",
			url : "vote.do",
			data: data
		}).done(function(data) {
			if (data.code == 0) {
				messageBox.empty();
				messageBox.append('<div class="alert alert-danger">'+data.result+'</div>');
				disabledElem.removeAttr('disabled');
			} else {
				messageBox.empty();
				messageBox.append('<div class="alert alert-success">'+data.result+'</div>');
				$('input[name="optionCheckbox"]').attr('disabled', 'disabled');
				loadVoteResult();
			}
		});
	});

	loadVoteResult();
	loadVoteOptions();
})(jQuery);