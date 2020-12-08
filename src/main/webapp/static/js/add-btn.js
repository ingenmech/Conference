$(document).ready(function(){
    var maxField = 10; //Input fields increment limitation
    var addButton = $('.add-button'); //Add button selector
    var wrapper = $('.section'); //Input field wrapper
    var topicField = '<div><input id="section" type="text" name="section" value=""/><button class="remove-button">-</button></div>'; //New input field html 
    var x = 1; //Initial field counter is 1

    //Once add button is clicked
    $(addButton).click(function(){
        //Check maximum number of input fields
        if(x < maxField){ 
            x++; //Increment field counter
            var topicField = '<div><input id="section" type="text" name="section" placeholder="Write section name.."/><button class="remove-button">-</button></div>'
            $(wrapper).append(topicField); //Add field html
        }
    });
    
    //Once remove button is clicked
    $(wrapper).on('click', '.remove-button', function(e){
    	e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });
});