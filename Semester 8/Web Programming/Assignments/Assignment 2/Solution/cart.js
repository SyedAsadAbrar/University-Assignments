function Cart()
{
    this.itemPool = new Array();
    this.total = 0;
}

Cart.prototype.addItem = function(item, id)
{
    console.log("Inside addItem of " + id + " item.");
    this.itemPool[id] = item;
}

Cart.prototype.removeItem = function(id)
{
    console.log("Inside removeItem of " + id + " item.");
    this.itemPool[id].remove();
    //this.itemPool.splice(id, 1);
    
    //for (index = 0; index < this.itemPool.length; index++) {
    //    this.itemPool[index].id = index;
    //}

    // this.itemPool = tempPool;
    console.log(this.itemPool);
    this.calculateGrandTotal();
}

Cart.prototype.calculateGrandTotal = function(){
    console.log("Inside calculateGrandTotal().");
    total = 0;
    for (index = 0; index < this.itemPool.length; index++) {
        if(this.itemPool[index].available == 1)
        {
            quantity = this.itemPool[index].quantity;
            price = this.itemPool[index].price;
            total += quantity*price;
        }
    }
    this.total = total;
    document.getElementById("cart_total").innerHTML = "$" + this.total;
}

Cart.prototype.render = function(divId){
    console.log("Inside render function of " + divId + ".");

    divItemStr = "";
    for (index = 0; index < this.itemPool.length; index++) {
        divItemStr += "<div id=\"divItem" + index + "\"></div>";
    }

    var html = "<button id=\"cart_show\" onClick=\"openCart()\">My Cart</button><div id=\"div_container\" class =\"cart-container\"><div class =\"cart-header\"><img class=\"cart-img\" src=\"cart_icon.png\"><span class=\"cart-name\">My Cart</span><button class=\"cart-close\" onClick=\"closeCart()\">X</button></div>" + divItemStr + "<div class =\"cart-footer\"><span class=\"cart-total-text\">Total</span><span id=\"cart_total\" class=\"cart-total\">$" + this.total + "</span></div></div><div id=\"div_button_container\"><button id=\"cart_close_2\" onClick=\"closeCart()\">Close</button><button id=\"cart_checkout\" onClick=\"checkOut()\">Checkout</button></div>";
       
    document.getElementById(divId).innerHTML = html;

    for (index = 0; index < this.itemPool.length; index++) {
        this.itemPool[index].render("divItem"+index);
    }

    this.calculateGrandTotal();
}

function closeCart(){
    console.log("Inside closeCart().");
    var x = document.getElementById("div_container");
    x.style.display = "none";
    var x = document.getElementById("div_button_container");
    x.style.display = "none";
    var x = document.getElementById("cart_show");
    x.style.display = "flex";
}

function openCart(){
    console.log("Inside openCart().");
    var x = document.getElementById("div_container");
    x.style.display = "flex";
    x.style.flexDirection = "column";
    var x = document.getElementById("div_button_container");
    x.style.display = "flex";
    x.style.flexDirection = "row";
    var x = document.getElementById("cart_show");
    x.style.display = "none";
}

function checkOut(){
    console.log("Inside checkOut().");
    alert("Thank you for shopping! Your bill was " + this.total + ".");
    window.open('', '_self', '').close();
}