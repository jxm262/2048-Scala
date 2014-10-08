//(function(){
	var randomPos = Math.ceil(Math.random() * 16);
	var nums = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	
	if (localStorage.getItem("highScore") === null) {
		localStorage.highScore = 0;
	}else{
		$("#highscore").text(localStorage.highScore);
	}

	var modes = {
			"Easy": 1,
			"Medium": 2,
			"Hard": 3
	};
	
	var mode = 2;
	
	$("body").on("click", "#mode .btn", function(e){
		//not the right way to do this
		$("#mode").children().each(function(){ 
			   $(this).removeClass("active");
			});
		
		$(this).toggleClass("active");
		mode = modes[$(this).text()];
	});
	
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
	
	$(document).touchwipe({
	     wipeLeft: function() { alert("left"); },
	     wipeRight: function() { alert("right"); },
	     wipeUp: function() { alert("up"); },
	     wipeDown: function() { alert("down"); },
	     min_move_x: 20,
	     min_move_y: 20,
	     preventDefaultEvents: true
	});

	function scoreInArray(score, scoresArray){
		return (scoresArray.filter(function(elem) { return elem.score != score }).length == 0) ? true : false;
	};
	
	function checkGameOver(data){
		if(isBoardFull(data.numbers)){
			var currScore = data.score;
			
			var directions = ["left", "right", "up", "down"];
			var p = $.when(1); // empty promise
			var results = [];
			
			directions.forEach(function(el, index){
			    p = p.then(function(){
			        return moveIt(data.numbers, el);
			    })
			    .then(function(data){
			        results[index] = data; // save the data
			    });
			});
			
			p.then(function(){
			    // all promises done, results contains the return values
				if(scoreInArray(currScore, results)){
					alert("Game is Over!!  Reset to replay");
				}
			});
		}
	};
	
	function isBoardFull(numbers){
		return ($.inArray(0, numbers) == -1) ? true : false;	
	};
	
	function moveIt(currNums, direction){
		return $.ajax({
		  type: "POST",
		  url: "/2048-Scala/move",
		  contentType: "application/json; charset=utf-8",
		  data: JSON.stringify({direction: direction, numbers: currNums, mode: mode}),
		  dataType: "json",
		  success: function(){},
		  error: error
		});
	};	
	
	function move(direction){
		return $.ajax({
		  type: "POST",
		  url: "/2048-Scala/move",
		  contentType: "application/json; charset=utf-8",
		  data: JSON.stringify({direction: direction, numbers: nums, mode: mode}),
		  dataType: "json",
		  success: function(data){
			  console.log("returned data - " + data.numbers + "  " + data.score);
			  
			  checkGameOver(data);
			  
			  nums = data.numbers;
			  updateGrid(nums);
			  updateScore(data.score);
		  },
		  error: error
		});
	};
	
	function updateScore(score){
		var oldScore = ($("#score").text().length == 0) ? 0 : $("#score").text();
		var newScore = parseInt(oldScore, 10) + parseInt(score, 10);
		updateCurrScore(newScore);
		updateHighScore(newScore);
	};
	
	function updateCurrScore(score){
		$("#score").text(score);
	};
	
	function updateHighScore(score){
		if(score > localStorage.highScore){
			localStorage.highScore = score;
			$("#highscore").text(localStorage.highScore);
		}
	};
	
	$("#reset").click(function(){
		nums = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		move("up");
		$("#score").text(0);
	});

	function updateGrid(nums){
		$("h2").each(function(idx){
			var num = (nums[idx] == 0) ? '' : nums[idx];
			
			$(this).parent().css("background-color", getColor(num));
			$(this).replaceWith("<h2>" + checkForBrick(num) + "</h2>");
		});
	};
	
	function checkForBrick(num){
		return (num == 99999) ? "" : num
	};
	
	//TODO this is pretty jacked up, but its getting late.  Redo this
	function getColor(num){
		
		//using logarithm to smooth color shading
		var colorMultiple = (num == 0) ? 0 : parseInt(Math.ceil(Math.log(num * 10)));

		var r = 250;
		var g = 250;
		var b = 250;
		 
		if(num == 99999){
			return shade(85, 85, 85);
		}
		
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
	
	function error(jqXHR, textStatus, errorThrown) {
		alert("there was an error.  Check js console for details");
		console.log("Error!");
		console.log(textStatus);
		console.log(errorThrown);
		console.log(jqXHR);
	};
	
// })();
