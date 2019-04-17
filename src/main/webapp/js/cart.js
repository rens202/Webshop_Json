var checkout = document.getElementById("pay-btn");

checkout.onclick = function () {
  window.location = "payment.html";
}

function showCart() {
  var cartcol = document.getElementById('cart-col');
  var productquantity = document.createElement('span');
  var productname = document.createElement('span');
  var productprice = document.createElement('span');

  var jsonArry = JSON.parse(sessionStorage.getItem('cart'));

  for(var i=0; i<jsonArry.products.length; i++){
    var paragraph = document.createElement('p');
    productquantity.innerHTML = jsonArry.products[i].quantity + "\t";
    productname.innerHTML = jsonArry.products[i].name;
    productprice.innerHTML = jsonArry.products[i].price + "\t";
    console.log(paragraph.innerHTML);

    paragraph.appendChild(productquantity);
    paragraph.appendChild(productprice);
    paragraph.appendChild(productname);
    cartcol.appendChild(paragraph);
  }

}

function createCartTable() {
  var cart = JSON.parse(sessionStorage.getItem('cart'));
  var col = [];
  for (var i = 0; i < cart.products.length; i++) {
    for (var key in cart.products[i]) {
      if (col.indexOf(key) === -1) {
        col.push(key);
      }
    }
  }

  // CREATE DYNAMIC TABLE.
  var table = document.createElement("table");

  // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

  var tr = table.insertRow(-1);                   // TABLE ROW.

  for (var i = 0; i < col.length; i++) {
    var th = document.createElement("th");      // TABLE HEADER.
    th.innerHTML = col[i];
    tr.appendChild(th);
  }

  // ADD JSON DATA TO THE TABLE AS ROWS.
  for (var i = 0; i < cart.products.length; i++) {

    tr = table.insertRow(-1);

    for (var j = 0; j < col.length; j++) {
      var tabCell = tr.insertCell(-1);
      tabCell.innerHTML = cart.products[i][col[j]];
    }
    var delbut = document.createElement("button");
    delbut.innerHTML = 'delete';

    delbut.onclick = function(i) {
      cart.products.splice(i,1);
      sessionStorage.setItem('cart', JSON.stringify(cart));
      location.reload();
    };

    tr.appendChild(delbut);
  }

  // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
  var divContainer = document.getElementById('cart-col');
  divContainer.innerHTML = "";
  divContainer.appendChild(table);

  var calculatedtotal = 0;
  for (var i = 0; i < cart.products.length; i++) {

    var producttotal = cart.products[i].price * cart.products[i].quantity;
    console.log(cart.products[i].name + producttotal);
    calculatedtotal = calculatedtotal + producttotal;
    console.log("totaal tot nu : " + calculatedtotal);
  }

  var totalprice = document.getElementById('totaalprijs');
  totalprice.innerHTML = "total price: " + calculatedtotal;
}