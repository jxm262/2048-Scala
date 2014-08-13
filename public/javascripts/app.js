/**
 * Application custom JS
 */

var randomPos = (Math.ceil(Math.random() * 16)) - 1 ;
var nums = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

(function(){
	
	//init
	move("up");
	
	$(document).keydown(function(e){
		switch(e.which) {
			case 37: return move("left");
			case 38: return move("up");
			case 39: return move("right");
			case 40: return move("down");
		}
	});

	function move(direction){
		$.ajax({
		  type: "POST",
		  url: "/games/2048-Scala/move",
		  contentType: "application/json; charset=utf-8",
		  data: JSON.stringify({direction: direction, numbers: nums}),
		  dataType: "json",
		  success: function(data, textStatus, jqXHR){
			  window.justin = data;
			  nums = data.numbers;
			  updateGrid(nums);
			  updateScore(data.score);
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  console.log("fail");
			  console.log(errorThrown);
		  }
		});
	};
	
	function updateScore(score){
		var newScore = parseInt($("#score").text(), 10) + parseInt(score, 10);
		$("#score").text(newScore);
	};
	
	function updateGrid(nums){
		$("h1").each(function(idx){
			var num = (nums[idx] == 0) ? '' : nums[idx];
			
			$(this).parent().css("background-color", getColor(num));
			$(this).replaceWith("<h1>" + num + "</h1>");
		});
	};
	
	//TODO this is pretty jacked up, but its getting late.  Redo this
	function getColor(num){
		
		//using logarithm to smooth color shading
		var colorMultiple = (num == 0) ? 0 : parseInt(Math.ceil(Math.log(num * 10)));

		var r = 250;
		var g = 250;
		var b = 250;
		 
		if(colorMultiple == 0){
			return shade(r-40, g-40, b-40);
		}
		
		if(num < 32)
			return shade(r, g - (20 * colorMultiple), b - (35 * colorMultiple));
		
		if(num < 1024)
			return shade(r - (10 * colorMultiple), g  - (20 * colorMultiple), b);

		function shade(r, g, b){
			return "rgb(" + r +  "," + g + "," + b + ")";
		}
		
	};
	
})();

var step = 0;