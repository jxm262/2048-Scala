/**
 * Application custom JS
 */

var randomPos = (Math.ceil(Math.random() * 16)) - 1 ;
var nums = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
//nums[randomPos] = 2;

(function(){
	
	
	$(document).keydown(function(e){
		function getDirection() {
			switch(e.which) {
				case 37: return "left";
				case 38: return "up";
				case 39: return "right";
				case 40: return "down";
			}
		}
		e.preventDefault();
		
			
	
		$.ajax({
			  type: "POST",
			  url: "/move",
			  contentType: "application/json; charset=utf-8",
			  data: JSON.stringify({direction: getDirection(), numbers: nums}),
			  dataType: "json",
			  success: function(data, textStatus, jqXHR){
				  nums = data.numbers;
				  window.justin = nums;
				  console.log(justin);
				  $("h1").each(function(idx){
					 var num = (nums[idx] == 0) ? '' : nums[idx];
					 
					 var r = 250 - (2 * num);
					 var g = 250 - (3 * num);
					 var b = 150 - (10 * num);
					 var rgb = "rgb(" + r +  "," + g + "," + b + ")";

					 $(this).parent().css("background-color", rgb);
					 $(this).replaceWith("<h1>" + num + "</h1>");
				  });
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  console.log("fail");
				  console.log(errorThrown);
			  }
			});
	});
})();