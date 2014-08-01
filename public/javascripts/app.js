/**
 * Application custom JS
 */


(function(){
	$(document).keydown(function(e){
		function getDirection() {
			switch(e.which) {
				case 37: return "left";
				case 38: return "up";
				case 39: return "right";
				case 40: return "down";
				default: return "";
			}
		}
		e.preventDefault();
	
		console.log(getDirection());
		
	});
})();