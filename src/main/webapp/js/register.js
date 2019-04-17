function register() {
    let formData = new FormData(document.querySelector("#regform"));

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
    }

    fetch("restservices/authentication/register", fetchoptions)
        .then(function(response) {
            if (response.ok) {
                alert("New user registered!");
                document.location.href = "login.html"
            } else {
                alert("Error with registration");
                throw "Error with registration";
            }
        })
        .catch(error => console.log(error));
}

document.querySelector("#register").addEventListener("click", register);
