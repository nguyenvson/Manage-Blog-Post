const RegCheck = /[(a-z)(A-Z)(0-9)].{7,}/g;
function loginCheck(){
    var usernameCheck = false;
    var passwordCheck = false;
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    if (username.value.match(RegCheck)){
        usernameCheck = true;
        document.querySelector('#errorUsername').innerText = '';
    }
    else {
        document.querySelector('#errorUsername').innerText = 'Username must more than 8 characters';
    }

    if (password.value.match(RegCheck)){
        passwordCheck = true;
        document.querySelector('#errorUsername').innerText = '';
    }
    else {
        document.querySelector('#errorPassword').innerText = 'Password must more than 8 characters';
    }

    return usernameCheck && passwordCheck;
}
