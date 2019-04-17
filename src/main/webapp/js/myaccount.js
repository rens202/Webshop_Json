const nav_login = document.querySelector('#nav_login');
const nav_account = document.querySelector('#nav_account');

if (window.sessionStorage.getItem("sessionToken") != null ){
    nav_account.style.display = 'block';
    nav_login.style.display = 'none';
}

if (window.sessionStorage.getItem("sessionToken") == null ){
    nav_account.style.display = 'none';
    nav_login.style.display = 'block';
}