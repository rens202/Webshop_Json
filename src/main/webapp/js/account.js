function loadOrders() {
    let fetchoptions = {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    };
    fetch("restservices/orders", fetchoptions)
        .then(function(response) {
            return response.json()
        })
        .then(function(MyJson) {
            let list = document.querySelector('#orderlist');
            console.log(MyJson);

            let  index = 1;
            for (let order of MyJson) {
                let item = document.createElement('li');

                let heading = document.createElement('h3');
                heading.textContent = "Order #" + index;
                item.appendChild(heading);

                for (let line of order.orderLines) {
                    let heading2 = document.createElement('h4');
                    heading2.textContent =
                        `Product: ${line.product.name} - Price: €${line.product.price} - Quantity: ${line.quantity}x`;
                    item.appendChild(heading2);
                }

                list.appendChild(item);

                index ++;
            }
        });
}

loadOrders();