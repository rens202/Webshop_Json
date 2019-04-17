function loadAllProducts() {
    sessionStorage.removeItem("saleprice");
    let fetchoptions = {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    };
    fetch("restservices/products", fetchoptions)
        .then(function(response) {
            return response.json()
        })
        .then(function(MyJson) {
            var i = 0;
            for(const pr of MyJson) {
                var i = i+1;

                var div = document.createElement('div');
                div.id = "productoverview" + i;

                var picture = document.createElement('img')
                picture.className = "picture" + i;
                picture.src = pr.picture;

                var name = document.createElement('h3');
                name.id= "name" + i;

                var price = document.createElement('p');
                price.id = "price" + i;

                var button = document.createElement('button')
                button.id = "button" + i;
                button.setAttribute('name', 'button' + i);

                document.getElementById("productspage").appendChild(div);

                document.getElementById("productoverview" + i).appendChild(picture);

                document.getElementById("productoverview" + i).appendChild(name);
                document.getElementById("name" + i).innerHTML = pr.name;
                
                document.getElementById("name" + i).appendChild(price);
                document.getElementById("price" + i).innerHTML = "€ " + pr.price + ",-";

                document.getElementById("name" + i).appendChild(button);
                document.getElementById("button" + i).innerHTML = "Check this Product";
                button.onclick = function() {
                    sessionStorage.setItem("id", pr.id);
                    window.location = "productpage.html";
                }            
            }
            loadCategory();
        })
}

function loadProduct() {
    var productid = sessionStorage.getItem("id");
    var newPrice = sessionStorage.getItem("saleprice");

    fetch("restservices/products/" + productid, {method: "GET"})
        .then(function(response){
            return response.json();
        })
        .then(function(MyJson) {
            var div = document.createElement('div');
            div.className = "info";

            var date = document.createElement('p');
            date.id = "saleDate";

            var productprice = document.getElementById("productprice");
            if(newPrice === null || newPrice === "undefined") {
                productprice.innerHTML =  "€"+ MyJson.price +",-";
                // document.getElementById("saleDate").innerHTML = " ";
            } else {
                productprice.innerHTML =  "From € " + MyJson.price + ",- to €" + newPrice +",-";
                document.getElementById("productprice").appendChild(date);
                document.getElementById("saleDate").innerHTML = sessionStorage.getItem("beginDate") + " - " + sessionStorage.getItem("endDate");             
            }

            var productpicture = document.getElementById("productpicture");
            productpicture.src = MyJson.picture;

            var productname = document.getElementById("productname");
            productname.innerHTML = MyJson.name;

            var productdescription = document.getElementById("productdescription");
            productdescription.innerHTML = MyJson.description;

            document.title = "IAC Webshop |  " + MyJson.name;

            // Gideon cart
            var addtocart = document.getElementById("addtocart");
            console.log(addtocart);
            addtocart.onclick = function() {
                // make a new cart if it doesn't exist yet
                if (sessionStorage.getItem('cart') == null) {
                    var cart = {};
                    cart.products = [];
                    sessionStorage.setItem('cart', JSON.stringify(cart));
                }

                var quantity = document.getElementById("qty").value;

                // check if the quantity is above 1.
                if (quantity < 1) {
                    alert('Please enter a valid quantity');
                    return;
                }
                // checks if item is already in cart and deletes old entry if so
                var jsonArry = JSON.parse(sessionStorage.getItem('cart'));
                var value = MyJson.id;
                var i = null;
                for(var i=0; i<jsonArry.products.length; i++){
                    if(jsonArry.products[i].id == value){
                        jsonArry.products.splice(i,1);
                        sessionStorage.setItem('cart', JSON.stringify(jsonArry));
                    }
                }


                var product = {};
                product.id = MyJson.id;
                product.name = MyJson.name;

                if(newPrice === null || newPrice === "undefined") {
                    product.price =MyJson.price;
                } else {
                    product.price = newPrice;
                    alert("There was a discount on this product.");
                }

                product.quantity = [quantity];


                addToCart(product);
            }
        })
        
}

function loadCategory() {
    fetch("restservices/categories", {method: "GET"})
        .then(function(response) {
            return response.json();
        })
        .then(function(MyJson) {
            var i = 0;

            var list = document.createElement('ul');
            list.id = "catlist";

            var resetFilter = document.createElement('li');
            resetFilter.id = "resetFilter";

            var resetIcon = document.createElement('button');
            resetIcon.id = "resetIcon";
            resetIcon.setAttribute('name', 'reset');

            document.getElementById("categories").appendChild(list);
            document.getElementById("catlist").appendChild(resetFilter);
            document.getElementById("resetFilter").appendChild(resetIcon);
            document.getElementById("resetIcon").innerHTML = "Reset filter";

            resetIcon.onclick = function() {
                fetch("restservices/products", {method: "GET"})
                .then(function(response) {
                    return response.json();
                })
                .then(function(MyJson) {
                    var i = 0;
                    var y = 0;
                    for(const c of MyJson) {
                        var i = i + 1;
                        document.getElementById("productoverview" + i).innerHTML = "";
                    }
                reloadAllProducts();
                })
            }

            for(const cat of MyJson) {
                var i = i + 1;
                var listitem = document.createElement('li');
                listitem.id = "category" + i;

                var icon = document.createElement('button');
                icon.id = "icon" + i;
                
                var image = document.createElement('img');
                image.id = "catpicture" + i;
                image.src = cat.picture;
                
                
                document.getElementById("catlist").appendChild(listitem);
                document.getElementById("category" + i).appendChild(icon);
                document.getElementById("icon" + i).appendChild(image);
                icon.onclick = function() {
                    loadCategoryProducts(cat.id);
            }
        }
    })
}

function reloadAllProducts() {
    fetch("restservices/products", { method: "GET" })
        .then(function(response) {
            return response.json()
        })
        .then(function(MyJson) {
            var i = 0;
            for(const pr of MyJson) {
                var i = i+1;

                var div = document.createElement('div');
                div.id = "productoverview" + i;

                var picture = document.createElement('img')
                picture.className = "picture" + i;
                picture.src = pr.picture;

                var name = document.createElement('h3');
                name.id= "name" + i;

                var price = document.createElement('p');
                price.id = "price" + i;

                var button = document.createElement('button')
                button.id = "button" + i;
                button.setAttribute('name', 'button' + i);

                document.getElementById("productspage").appendChild(div);

                document.getElementById("productoverview" + i).appendChild(picture);

                document.getElementById("productoverview" + i).appendChild(name);
                document.getElementById("name" + i).innerHTML = pr.name;
                
                document.getElementById("name" + i).appendChild(price);
                document.getElementById("price" + i).innerHTML = "€ " + pr.price + ",-";

                document.getElementById("name" + i).appendChild(button);
                document.getElementById("button" + i).innerHTML = "Check this Product";
                button.onclick = function() {
                    sessionStorage.setItem("id", pr.id);
                    window.location = "productpage.html";
                }            
            }
        })
}

function loadCategoryProducts(id) {
    fetch("restservices/products", {method: "GET"})
        .then(function(response) {
            return response.json();
        })
        .then(function(MyJson) {
            var i = 0;
            var y = 0;
            for(const c of MyJson) {
                var i = i + 1;
                document.getElementById("productoverview" + i).innerHTML = "";
                var catid = c.categories;
                var cid = id;
                for(const x of catid) {
                    if(x.id == cid) {
                        var y = y + 1;
                        var picture = document.createElement('img')
                        picture.className = "picture" + y;
                        picture.src = c.picture;

                        var name = document.createElement('h3');
                        name.id= "name" + y;

                        var price = document.createElement('p');
                        price.id = "price" + y;

                        var button = document.createElement('button')
                        button.id = "button" + y;
                        button.setAttribute('name', 'button' + y);

                        document.getElementById("productoverview" + y).appendChild(picture)

                        
                        document.getElementById("productoverview" + y).appendChild(name);
                        document.getElementById("name" + y).innerHTML = c.name;
                    
                        document.getElementById("name" + y).appendChild(price);
                        document.getElementById("price" + y).innerHTML = "€ " + c.price + ",-";

                        document.getElementById("name" + y).appendChild(button);
                        document.getElementById("button" + y).innerHTML = "Check this Product";
                        button.onclick = function() {
                            sessionStorage.setItem("id", c.id);
                            window.location = "productpage.html";
                        }  

                } 
            }
        }
    })
}

function loadAds() {
    sessionStorage.removeItem("saleprice")
    fetch("restservices/advertisements", {method: "GET"})
        .then(function(response) {
            return response.json();
        }) 
        .then(function(MyJson) {
            var i = 0;
            for(const ad of MyJson) {
                var i = i + 1;
                var div = document.createElement('div');
                div.id = "sale_item" + i;

                var image = document.createElement('img');
                image.id = "sale_picture" + i;
                image.src = ad.product["picture"];

                var name = document.createElement('h3');
                name.id = "sale_name" + i;

                var description = document.createElement('p');
                description.id = "productDescription" + i;

                var date = document.createElement('p')
                date.id = "adDate" + i;

                var price = document.createElement('h3');
                price.id = "sale_price" + i;

                var button = document.createElement('button');
                button.id = "button" + i;

                document.getElementById("salepage").appendChild(div);
                document.getElementById("sale_item" + i).appendChild(image);
                document.getElementById("sale_item" + i).appendChild(name);
                document.getElementById("sale_name" + i).innerHTML = ad.product["name"];
                document.getElementById("sale_name" + i).appendChild(description);
                document.getElementById("productDescription" + i).innerHTML = ad.advertisementText;
                document.getElementById("sale_item" + i).appendChild(price);
                document.getElementById("sale_price" + i).innerHTML = "From €" + ad.product["price"] + ",- to €" + ad.offerPrice + ",-";
                document.getElementById("sale_price" + i).appendChild(date);
                document.getElementById("adDate" + i).innerHTML = ad.fromDate + " - " + ad.untilDate;
                document.getElementById("sale_name" + i).appendChild(button);
                document.getElementById("button" + i).innerHTML = "Buy this product!"

                button.onclick = function() {
                    sessionStorage.setItem("id", ad.product["id"]);
                    window.location = "productpage.html";
                }
            } 
        })
}

function checkForSale() {
    var productid = sessionStorage.getItem("id");

    fetch("restservices/advertisements", {method: "GET"})
    .then(function(response) {
        return response.json();
    })
    .then(function(MyJson) {
        sessionStorage.removeItem("saleprice");
        for(ad of MyJson) {
            if(ad.product["id"] == productid) {
                var saleprice = ad.offerPrice;
                var beginDate = ad.fromDate;
                var endDate = ad.untilDate;
            }
        }
        sessionStorage.setItem("saleprice", saleprice);
        sessionStorage.setItem("beginDate", beginDate);
        sessionStorage.setItem("endDate", endDate);
        loadProduct();
    })
}

function addToCart(product) {
    // Retrieve the cart object from storage
    if (sessionStorage.getItem('cart')) {
        var cart = JSON.parse(sessionStorage.getItem('cart'));

        cart.products.push(product);

        sessionStorage.setItem('cart', JSON.stringify(cart));

        var succesmesage = document.getElementById("success");
        succesmesage.innerHTML = "product added to shopping cart.";

    }
}

function editProduct() {
    var selectedProduct = document.getElementById("productdropdown").value;
    let formData = new FormData(document.querySelector("#editProductForm"));
    let object = {};
    formData.forEach(function(value, key){
        object[key] = value;
    });
    let json = JSON.stringify(object);
    console.log(json);

    let fetchoptions = {
        method: 'PUT',
        body: json,
        headers: {
            "Content-Type": "application/json",
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    }
    fetch("restservices/products/" + selectedProduct, fetchoptions)
        .then(function(response) {
            if(response.ok) {
                alert("Product updated!");
            } else {
                alert("Failed to update product");
                throw "Failed to update product";
            }
        })
};

// Adds a product to the DB.

function addProduct() {
    let formData = new FormData(document.querySelector("#addProductForm"));
    let object = {};
    formData.forEach(function(value, key){
        object[key] = value;
    });
    
    let json = JSON.stringify(object);
    console.log(json);

    let fetchoptions = {
        method: 'POST',
        body: json,
        headers: {
            "Content-Type": "application/json",
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    };
    fetch("restservices/products", fetchoptions)
        .then(function(response) {
            if (response.ok) {
                alert("Added product!");
                
            }else {
                alert("Failed to add product");
                throw "Failed to add product";
            }
        })
}

function showAllProducts() {
    fetch("restservices/products", {method: "GET"})
    .then(function(response) {
        return response.json();
    })
    .then(function(MyJson) {
        var dropdown = document.getElementById("productdropdown");
        for(const p of MyJson) {
            var item = document.createElement('option');
            item.id = p.id;
            item.value = p.id;
            dropdown.appendChild(item);
            document.getElementById(p.id).innerHTML = p.name;
        }
    })
}

function getProductInfo() {
    console.log("In Functie");
    fetch("restservices/products", {method: "GET"})
    .then(function(response) {
        return response.json();
    })
    .then(function(MyJson) {
        var selectedProduct = document.getElementById("productdropdown").value;
        for(p of MyJson) {
            if(p.id == selectedProduct) {
                var product = document.getElementById("editProductInfo");
                var name = document.createElement('p');
                name.id = "name";
                var desc = document.createElement('p');
                desc.id = "description";
                var price = document.createElement('p');
                price.id = "price";
                var picture = document.createElement('p');
                picture.id = "picture";
                
                product.appendChild(name);
                name.innerHTML = "Product Name: " + p.name;
                
                product.appendChild(desc);
                desc.innerHTML = "Product Description: " + p.description;

                product.appendChild(price);
                price.innerHTML = "€ " + p.price;

                product.appendChild(picture);
                picture.innerHTML = "Product Picture URL: " + p.picture;
            }
        }
    })
}



function deleteProduct() {
    var selectedProduct = document.getElementById("productdropdown").value;
    let json = JSON.stringify(selectedProduct);
    console.log(json);
    let fetchoptions = {
        method: 'DELETE', 
        body: json,
        headers: {
            "Content-Type": "application/json",
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    }
    fetch("restservices/products/" + selectedProduct, fetchoptions)
        .then(function(response) {
            if(response.ok) {
                alert("Product Removed!")
            } else {
                alert("Failed to remove product")
                throw "Failed to remove product";
            }
        })
}