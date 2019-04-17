function login() {
    let formData = new FormData(document.querySelector("#loginform"));

    let object = {};
    formData.forEach(function(value, key){
        object[key] = value;
    });

    let fetchoptions = {
        method: 'POST',
        body: json,
        headers: {
            "Content-Type": "application/json",
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("sessionToken")
        }
    }

    fetch("restservices/authentication", fetchoptions)
        .then(function(response) {
            if (response.ok) return response.json();
            else {
                alert("Wrong username/password");
                throw "Wrong username/password";
            }
        })
        .then(myJson => window.sessionStorage.setItem("sessionToken", myJson.JWT))
        .then(function() {
            window.sessionStorage.setItem("username", formData.get("username"));
            document.location.href = "account.html"
        })
        .catch(error => console.log(error));
}

document.querySelector("#login").addEventListener("click", login);



