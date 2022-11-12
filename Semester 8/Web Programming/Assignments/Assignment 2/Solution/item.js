function Item(name, image, price, quantity, id, cart){
    this.name = name;
    this.image = image;
    this.price = price;
    this.quantity = quantity;
    this.total = price*quantity;
    this.id = id;
    this.cart = cart;
    this.available = 1;
}

Item.prototype.render = function(divId){
    console.log("Inside render function of " + divId + ".");
    
    str = "<img id=\"" + this.prefixId("item_img") + "\" class=\"item-img\" src=\"" + this.image + "\"><span id=\"" + this.prefixId("item_name") + " \" class=\"item-name\">" + this.name + "</span><span id=\"" + this.prefixId("item_price") + "\" class=\"item-price\">$" + this.price + "</span><input type=\"number\" id=\"" + this.prefixId("item_quantity") + "\" class=\"item-quantity\" min=\"1\" value=\"" + this.quantity + "\" onchange=\"calculateTotal(" + this.id + ")\"></span><span id=\"" + this.prefixId("item_total") + "\" class=\"item-total\">$" + this.total + "</span><button id=\"" + this.prefixId("item_remove") + "\" class=\"item-remove\" onClick=\"cart.removeItem(" + this.id + ")\">X</button>";
    var html = "<div id=\"" + this.prefixId("item_container") + "\" class =\"item-container\">" + str + "</div>";
       
    document.getElementById(divId).innerHTML = html;
}

Item.prototype.prefixId = function(str){
    return this.id + "_" + str;
}

Item.prototype.changeVals = function(){
    console.log("Inside changeVals function of divItem" + this.id + ".");
    if (document.getElementById(this.prefixId("item_quantity")).value < 1)
    {
        console.log("Invalid quantity entered(less than 1) for " + this.name + ", so setting default value of 1.");
        alert("Quantity cannot be less than 1 for " + this.name + ".");
        document.getElementById(this.prefixId("item_quantity")).value = 1;
    }
    this.quantity = document.getElementById(this.prefixId("item_quantity")).value;
    this.total = this.quantity*this.price;
    document.getElementById(this.prefixId("item_total")).innerHTML = "$" + this.total;
}

Item.prototype.remove = function(){
    console.log("Inside remove function of divItem" + this.id + ".");
    var item = document.getElementById(this.prefixId("item_container"));
    this.available = 0;
    item.style.display = "none";
}

function calculateTotal(id)
{
    console.log("Inside calculateTotal function of divItem" + id + ".");
    cart.itemPool[id].changeVals();
    cart.calculateGrandTotal();
}

Item.prototype.changeId = function(id){
    this.id = id;
}