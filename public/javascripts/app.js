/**
 * Application custom JS
 */

var randomPos = (Math.ceil(Math.random() * 16)) - 1 ;
var nums = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
nums[randomPos] = 2;

(function(){
	
	
	$(document).keydown(function(e){
		function getDirection() {
			switch(e.which) {
				case 37: return "left";
				case 38: return "up";
				case 39: return "right";
				case 40: return "down";
				default: return;
			}
		}
		e.preventDefault();
	
		$.ajax({
			  type: "POST",
			  url: "/test",
			  contentType: "application/json; charset=utf-8",
			  data: JSON.stringify({direction: getDirection(), numbers: nums}),
			  dataType: "json",
			  success: function(data, textStatus, jqXHR){
				  nums = data.numbers;
				  
				  for(var i=0; i < 16; i+=4){
					 console.log(nums[i] + "\t" + nums[i+1] + "\t" + nums[i+2] + "\t" + nums[i+3]);
				  }
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  console.log("fail");
				  console.log(errorThrown);
			  }
			});
	});
})();