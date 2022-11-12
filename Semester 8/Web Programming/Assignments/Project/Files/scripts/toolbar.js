format_buttons = {"button_bold" : {name: "bold", tag: "b"}, 
"button_italicize" : {name: "italicize", tag: "I"}, 
"button_underline" : {name: "underline", tag: "U"}, 
"button_superscript" : {name: "superscript", tag: "sup"}, 
"button_subscript" : {name: "subscript", tag: "sub"}}

color_buttons = {"button_text-hightlight": {name: "highlight"}, 
"button_text-color" :{name: "text"}}

do_buttons = {"button_undo": {name: "undo"},
"button_redo": {name: "redo"}}

align_buttons = {"button_align-left": {name:"left"},
"button_align-center": {name:"center"},
"button_align-right": {name:"right"},
"button_align-justify": {name:"justify"}}

document.getElementById("button_align-left").classList.add("active");

document.getElementById("button_undo").disabled = true;
document.getElementById("button_redo").disabled = true;

var firstInput = false;

btns = document.getElementsByClassName("tool");

console.log(btns.length);

var beforeText = "";
var currentText = "";
var afterText = "";

// Toolbar buttons

for (var i = 0; i < btns.length; i++){
	btns[i].addEventListener("click", function() {

		//beforeText = document.getElementById("text_area").innerHTML;

		console.log("Inside onClick function");
		// If a button is "activated", deactivate it on clicking
		if(this.classList.contains('active')){
			// not applied to text align buttons
			if(!this.classList.contains("align")){
				this.classList.remove("active");
			}
		}
		// If a button is not "activated", activate it on clicking
		else{
			// not applied to undo or redo buttons
			if(!this.classList.contains("do"))
			{
				this.classList.add("active");
				
				// deactivate other activated text align buttons if any
				if(this.classList.contains("align")){
					var alignBtns = document.getElementsByClassName("align");
					for (let index = 0; index < alignBtns.length; index++) {
						if (this != alignBtns[index] && alignBtns[index].classList.contains("active")){
							alignBtns[index].classList.remove("active");
						}
					}	
				}

				// deactivate subscript button if superscript button is activated
				if(this.id == "button_superscript"){
					if(document.getElementById("button_subscript").classList.contains("active")){
						document.getElementById("button_subscript").classList.remove("active");
					}
				}

				// deactivate superscript button if subscript button is activated
				else if(this.id == "button_subscript"){
					if(document.getElementById("button_superscript").classList.contains("active")){
						document.getElementById("button_superscript").classList.remove("active");
					}
				}

				// deactivate text color button if highlight button is activated
				if(this.id == "button_text-hightlight"){
					if(document.getElementById("button_text-color").classList.contains("active")){
						document.getElementById("button_text-color").classList.remove("active");
					}
				}

				// deactivate highlight button if text color button is activated
				else if(this.id == "button_text-color"){
					if(document.getElementById("button_text-hightlight").classList.contains("active")){
						document.getElementById("button_text-hightlight").classList.remove("active");
					}
				}				
			}
		}

		var selObj = window.getSelection();

		// if button selected is a formatting button
		if(format_buttons[this.id] != undefined){

			//console.log(document.getElementById("text_area").childNodes[1] == window.getSelection().anchorNode.parentNode);

			if(this.classList.contains("active"))
			{
				console.log("Selected text: " + selObj.toString());

				if (selObj.anchorNode == selObj.focusNode){
					var selected_string = selObj.toString();
				
					var range = selObj.getRangeAt(0);
					range.deleteContents();

					var h = document.createElement(format_buttons[this.id].tag);            
					var t = document.createTextNode(selected_string);     

					if (selected_string == ""){
						t.nodeValue = "\u200C";
					}

					h.appendChild(t);   

					range.insertNode(h);
				}
				else{
					var first, second;
					var offset1, offset2;

					if(selObj.anchorNode.compareDocumentPosition(selObj.focusNode) == Node.DOCUMENT_POSITION_FOLLOWING){
						first = selObj.anchorNode;
						second = selObj.focusNode;
						offset1 = selObj.anchorOffset;
						offset2 = selObj.focusOffset;
					}
					//	If text is selected in reverse direction
					else{
						first = selObj.focusNode;
						second = selObj.anchorNode;
						offset1 = selObj.focusOffset;
						offset2 = selObj.anchorOffset;
					}

					var prestring = first.nodeValue.substring(offset1, first.nodeValue.length);
					var poststring = second.nodeValue.substring(0, offset2);

					var preNode = document.createElement("text");
					var postNode = document.createElement("text");

					var	tempNode = first.parentNode;
					
					var tempNode2 = preNode;

					//	To apply all previously applied formatting
					while (tempNode!= null && tempNode.id != "text_area"){
						// TODO: Add more buttons

						// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
						for (var key in format_buttons){
							if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
								tempNode2.appendChild(document.createElement(format_buttons[key].tag));
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
							// To retain text color after text formatting
							else if(tempNode.nodeName == "font".toUpperCase()){
								var temp = document.createElement("font");
								temp.style.color = tempNode.style.color;
								temp.style.background = tempNode.style.background;
								temp.style.fontFamily = tempNode.style.fontFamily;
								temp.style.fontSize = tempNode.style.fontSize;
								tempNode2.appendChild(temp);
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
						}
						tempNode = tempNode.parentNode;
					}

					tempNode2.appendChild(document.createTextNode(prestring));

					tempNode = second.parentNode;

					tempNode2 = postNode;

					//	To apply all previously applied formatting
					while (tempNode!= null && tempNode.id != "text_area"){
						// TODO: Add more buttons

						// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
						for (var key in format_buttons){
							if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
								tempNode2.appendChild(document.createElement(format_buttons[key].tag));
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
							// To retain text color after text formatting
							else if(tempNode.nodeName == "font".toUpperCase()){
								var temp = document.createElement("font");
								temp.style.color = tempNode.style.color;
								temp.style.background = tempNode.style.background;
								temp.style.fontFamily = tempNode.style.fontFamily;
								temp.style.fontSize = tempNode.style.fontSize;
								tempNode2.appendChild(temp);
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
						}
						tempNode = tempNode.parentNode;
					}

					tempNode2.appendChild(document.createTextNode(poststring));

					var node = document.createElement(format_buttons[this.id].tag);
					node.appendChild(preNode);
					node.appendChild(postNode);

					var range = selObj.getRangeAt(0);
					range.deleteContents();

					if(node.length != 0){
						range.insertNode(node);
					}
				}
			}
			// TODO: If button is unselected, remove formatting
			else{
			}
		}

		if(color_buttons[this.id] != undefined){
			var value = document.getElementById("text_color_button").value;
			
			if(this.classList.contains("active")){
				console.log("Selected text: " + selObj.toString());

				if (selObj.anchorNode == selObj.focusNode){
					// var string = document.getElementById("text_area").innerHTML;

					// var prestring = string.substring(0, selObj.anchorOffset-1);
					// var poststring = string.substring(selObj.focusOffset+1, string.length);

					var selected_string = selObj.toString();

					// document.getElementById("text_area").innerHTML = prestring + formattedstring + poststring;
				
					var range = selObj.getRangeAt(0);
					range.deleteContents();

					var h = document.createElement("font");  
					
					if(this.id == "button_text-color"){
						h.style.color = value;
					}
					else{
						h.style.background= value;
					}

					var t = document.createTextNode(selected_string);     
					
					if (selected_string == ""){
						t.nodeValue = "\u200C";
					}

					h.appendChild(t);   

					range.insertNode(h);
				}
				else{
					var first, second;
					var offset1, offset2;

					if(selObj.anchorNode.compareDocumentPosition(selObj.focusNode) == Node.DOCUMENT_POSITION_FOLLOWING){
						first = selObj.anchorNode;
						second = selObj.focusNode;
						offset1 = selObj.anchorOffset;
						offset2 = selObj.focusOffset;
					}
					else{
						first = selObj.focusNode;
						second = selObj.anchorNode;
						offset1 = selObj.focusOffset;
						offset2 = selObj.anchorOffset;
					}

					var prestring = first.nodeValue.substring(offset1, first.nodeValue.length);
					var poststring = second.nodeValue.substring(0, offset2);

					var preNode = document.createElement("font");
					var postNode = document.createElement("font");

					var	tempNode = first.parentNode;
					
					var tempNode2 = preNode;

					//	To apply all previously applied formatting
					while (tempNode!= null && tempNode.id != "text_area"){
						// TODO: Add more buttons

						// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
						for (var key in format_buttons){
							if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
								tempNode2.appendChild(document.createElement(format_buttons[key].tag));
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
							// To retain text color after text formatting
							else if(tempNode.nodeName == "font".toUpperCase()){
								var temp = document.createElement("font");
								temp.style.color = tempNode.style.color;
								temp.style.background = tempNode.style.background;
								temp.style.fontFamily = tempNode.style.fontFamily;
								temp.style.fontSize = tempNode.style.fontSize;
								tempNode2.appendChild(temp);
								break;
							}
						}
						tempNode = tempNode.parentNode;
					}

					tempNode2.appendChild(document.createTextNode(prestring));

					tempNode = second.parentNode;

					tempNode2 = postNode;

					//	To apply all previously applied formatting
					while (tempNode!= null && tempNode.id != "text_area"){
						// TODO: Add more buttons

						// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
						for (var key in format_buttons){
							if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
								tempNode2.appendChild(document.createElement(format_buttons[key].tag));
								tempNode2 = tempNode2.childNodes[0];
								break;
							}
							// To retain text color after text formatting
							else if(tempNode.nodeName == "font".toUpperCase()){
								var temp = document.createElement("font");
								temp.style.color = tempNode.style.color;
								temp.style.background = tempNode.style.background;
								temp.style.fontFamily = tempNode.style.fontFamily;
								temp.style.fontSize = tempNode.style.fontSize;
								tempNode2.appendChild(temp);
								break;
							}
						}
						tempNode = tempNode.parentNode;
					}

					tempNode2.appendChild(document.createTextNode(poststring));

					var node = document.createElement("font");

					if(this.id == "button_text-color"){
						node.style.color = value;
					}
					else{
						node.style.background = value;
					}

					node.appendChild(preNode);
					node.appendChild(postNode);

					var range = selObj.getRangeAt(0);
					range.deleteContents();

					if(node.length != 0){
						range.insertNode(node);
					}
				}
			}
			// TODO: If button is unselected, remove formatting
			else{
			}
		}

		if(do_buttons[this.id] != undefined)
		{
			if (do_buttons[this.id].name == "undo"){
				afterText = currentText;
				currentText = beforeText;
				beforeText = "";

				document.getElementById(this.id).disabled = true;
				document.getElementById("button_redo").disabled = false;
			}
			else if (do_buttons[this.id].name == "redo"){
				beforeText = currentText;
				currentText = afterText;
				afterText = "";

				document.getElementById(this.id).disabled = true;
				document.getElementById("button_undo").disabled = false;
			}
			document.getElementById("text_area").innerHTML = currentText;
		}

		if(align_buttons[this.id] != undefined)
		{
			document.getElementById("text_area").style.textAlign = align_buttons[this.id].name;
			document.getElementById("text_area").innerHTML = currentText;
		}

		// Correcting selection 
		// if(selObj.anchorNode.id == "text_area" || selObj.focusNode.id == "text_area"){
		// 	// To correct selection
		// 	// Since the selection is deleted so the focus node changes, this leads to a wrong selection
		// 	var node1;
					
		// 	if (selObj.anchorNode.id == "text_area"){
		// 		node1 = selObj.focusNode.nextSibling.childNodes[0];
		// 	}
		// 	else if(selObj.focusNode.id == "text_area"){
		// 		node1 = selObj.anchorNode.nextSibling.childNodes[0];
		// 	}

		// 	if(selObj.anchorNode.id == "text_area" && selObj.focusNode.id == "text_area"){
		// 		node1 = selObj.anchorNode.childNodes[selObj.anchorOffset];
		// 	}

		// 	selObj.removeAllRanges();

		// 	var range = document.createRange();
			
		// 	if (node1.nodeName != "#text"){
		// 		range.setStart(node1.childNodes[0], 0);
		// 		range.setEnd(node1.childNodes[node1.childElementCount-1], node1.childNodes[node1.childElementCount-1].lastChild.length);
		// 	}
		// 	else{
		// 		range.selectNodeContents(node1);
		// 	}

		// 	selObj.addRange(range);
		// }

		selObj.removeAllRanges();

		console.log("Text area: " + document.getElementById("text_area").innerHTML);

		beforeText = currentText;
		currentText = document.getElementById("text_area").innerHTML;
	});
}

// Text Area

textarea = document.getElementById("text_area");

textarea.addEventListener("input", function(){
	if(firstInput == false){
		firstInput = true;
		document.getElementById("button_undo").disabled = false;
	}

    console.log(document.getElementById("text_area").innerHTML);
	
	beforeText = currentText;
    currentText = document.getElementById("text_area").innerHTML;


	var bold = document.getElementById("button_bold");

    if (bold.classList.contains("active")){
        console.log("Bold button activated.");
        //this.innerHTML += "<b></b>";
        return;
    }
    //console.log("Bold button not activated.");
});

textarea.addEventListener("selectstart", function(){
    console.log("Selection started in targetDiv");
    document.addEventListener("selectionchange", logSelection);
});

textarea.addEventListener("mouseleave", () => {
    console.log("mouse leave event.");
    document.removeEventListener("selectionchange", logSelection);
});

function logSelection(){
    var selection = document.getSelection();
    //if(selection.toString().length != 0){
        console.log(selection.toString());

        var element = selection.anchorNode;

        // Removing "active" attribute on all selected buttons
        var btns = document.getElementsByClassName("tool");

        for (var i = 0; i < btns.length; i++){
            if(btns[i].classList.contains("active")){
                btns[i].classList.remove("active");
            }
        }

		// && (selection.anchorOffset + 1 == selection.anchorNode.length || selection.focusOffset + 1 == selection.focusNode.length)

        if(selection.anchorNode != selection.focusNode)
        {
            return;
        }

        while (element!= null && element.id != "text_area"){
            // TODO: Add more buttons

            // To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
            for (var key in format_buttons){
                if (element.parentNode.nodeName == format_buttons[key].tag.toUpperCase()){
                    console.log(format_buttons[key].tag + " property found.");
                    document.getElementById(key).classList.add("active");
                }
            }

            element = element.parentNode;
        }
    //}
}


// Font size

fontSelect = document.getElementById("font_select");
fontSizeSelect = document.getElementById("font-size_select");

fontSelect.addEventListener("change", (event) => {
	var font = event.target.value;
	console.log("Font changed to " + font);
	changeFontSettings();
});

fontSizeSelect.addEventListener("change", (event) => {
	var size = event.target.value;
	console.log("Font size changed to " + size);
	changeFontSettings();	
});

function changeFontSettings(){
	var font = fontSelect.value;
	var size = fontSizeSelect.value +"px";

	var selObj = window.getSelection();

	console.log("Selected text: " + selObj.toString());

	if (selObj.anchorNode == selObj.focusNode){
		var selected_string = selObj.toString();
		
		var range = selObj.getRangeAt(0);
		range.deleteContents();

		var h = document.createElement("font");  
					
		h.style.fontFamily = font;
		h.style.fontSize = size;
       
		var t = document.createTextNode(selected_string);     

		if (selected_string == ""){
			t.nodeValue = "\u200C";
		}

		h.appendChild(t);   

		range.insertNode(h);
	}
	else{
		var first, second;
		var offset1, offset2;

		if(selObj.anchorNode.compareDocumentPosition(selObj.focusNode) == Node.DOCUMENT_POSITION_FOLLOWING){
			first = selObj.anchorNode;
			second = selObj.focusNode;
			offset1 = selObj.anchorOffset;
			offset2 = selObj.focusOffset;
		}
		else{
			first = selObj.focusNode;
			second = selObj.anchorNode;
			offset1 = selObj.focusOffset;
			offset2 = selObj.anchorOffset;
		}

		var prestring = first.nodeValue.substring(offset1, first.nodeValue.length);
		var poststring = second.nodeValue.substring(0, offset2);

		var preNode = document.createElement("font");
		var postNode = document.createElement("font");

		var	tempNode = first.parentNode;
			
		var tempNode2 = preNode;

		//	To apply all previously applied formatting
		while (tempNode!= null && tempNode.id != "text_area"){
			// TODO: Add more buttons

			// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
			for (var key in format_buttons){
				if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
					tempNode2.appendChild(document.createElement(format_buttons[key].tag));
					tempNode2 = tempNode2.childNodes[0];
					break;
				}
				// To retain text color after text formatting
				else if(tempNode.nodeName == "font".toUpperCase()){
					var temp = document.createElement("font");
					temp.style.color = tempNode.style.color;
					temp.style.background = tempNode.style.background;
					temp.style.fontFamily = tempNode.style.fontFamily;
					temp.style.fontSize = tempNode.style.fontSize;
					tempNode2.appendChild(temp);
					break;
				}
			}
			tempNode = tempNode.parentNode;
		}

		tempNode2.appendChild(document.createTextNode(prestring));

		tempNode = second.parentNode;

		tempNode2 = postNode;

		//	To apply all previously applied formatting
		while (tempNode!= null && tempNode.id != "text_area"){
			// TODO: Add more buttons

			// To activate those buttons which have been applied to the selected text i.e. bold button if text is bold-ed 
			for (var key in format_buttons){
				if (tempNode.nodeName == format_buttons[key].tag.toUpperCase()){
					tempNode2.appendChild(document.createElement(format_buttons[key].tag));
					tempNode2 = tempNode2.childNodes[0];
					break;
				}
				// To retain text color after text formatting
				else if(tempNode.nodeName == "font".toUpperCase()){
					var temp = document.createElement("font");
					temp.style.color = tempNode.style.color;
					temp.style.background = tempNode.style.background;
					temp.style.fontFamily = tempNode.style.fontFamily;
					temp.style.fontSize = tempNode.style.fontSize;
					tempNode2.appendChild(temp);
					break;
				}
			}
			tempNode = tempNode.parentNode;
		}

		tempNode2.appendChild(document.createTextNode(poststring));

		var node = document.createElement("font");

		node.style.color = value;

		node.appendChild(preNode);
		node.appendChild(postNode);

		var range = selObj.getRangeAt(0);
		range.deleteContents();

		if(node.length != 0){
			range.insertNode(node);
		}
	}

	selObj.removeAllRanges();

	beforeText = currentText;
	currentText = document.getElementById("text_area").innerHTML;
};