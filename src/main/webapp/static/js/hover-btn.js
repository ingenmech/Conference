// Add active class to the current button (highlight it)
var header = document.getElementById("buttons");
var btns = header.getElementsByClassName("button-menu");
for (var i = 0; i < btns.length; i++) {
	if (btns[i] != null){
	btns[i].addEventListener("click", click);
    } else {
    	continue;
    }
}

function click() {
	var current = document.getElementsByClassName("active");
	if (current.length > 0) {
		current[0].className = current[0].className.replace(" active", "");
	}
	this.className += " active";
}