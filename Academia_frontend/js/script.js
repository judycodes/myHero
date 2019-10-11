console.log("js is linked!");
//===LANDING===//
//TOKEN
let token;
//FORM VARIABLES
const signUpBtn = document.querySelector('#signUpBtn');
const logInBtn = document.querySelector('#logInBtn');
const signUpForm = document.querySelector('#signUpForm');
const logInForm = document.querySelector('#logInForm');
const signUpSubmit = document.querySelector(".signUpSubmit");
const logInSubmit = document.querySelector(".logInSubmit");
//REGISTER USER ACCT VARIABLES Inputs
const email = document.querySelector('#signUp-email');
const password = document.querySelector('#signUp-pw');
const username = document.querySelector('#signUp-userName');
//LOG IN USER ACCT VARIABLES
const logInUserName = document.querySelector('#logIn-Username');
const logInPassword = document.querySelector('#logIn-pw');

//===REGISTER FUNCTION===//
signUpSubmit.addEventListener("click", createUser);
function createUser(e) {
 e.preventDefault();
 localStorage.setItem('username', username.value);
 localStorage.setItem('primaryEmail', email.value);

 if(email.value !== "" && password.value !== "" && username.value !== "") {
   fetch('http://localhost:8181/signup', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               email: email.value,
               password: password.value,
               username: username.value
           })
   })
   .then((res) => {
       return res.json();
   })
   .then((res) => {
    token = res.token;
    localStorage.setItem('user', token); 
     redirectHome();
   })
   .catch((err) => {
       console.log(err);
   })
 } else {
    alert("Young User, all fields must be provided to completed before you can be a HERO! Try applying to be a hero again soon!");
  }
}

//===LOGIN FUNCTION===//
logInSubmit.addEventListener('click', returningUser);
function returningUser(e) {
   e.preventDefault();
   localStorage.setItem('username', logInUserName.value);

if(logInUserName.value !== "" && logInPassword.value !== "") {
  fetch('http://localhost:8181/login/', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
          username: logInUserName.value,
          password: logInPassword.value
        })
    })
    .then(res => {
      return res.json();
    })
    .then(res => {
      token = res.token;
      localStorage.setItem('user', token);
      redirectHome();
    })
    .catch(error => {
      console.error(error);
    });
}
//not working if user does not exist
else if (localStorage.getItem('user') == null) {
  alert("Villains are at the gates. Please input your credentials again!");
} else {
  alert("Welcome back! Unfortunately, your credentials do not match our Superhero Database. Please try again.")
}

 }
//===REDIRECT FUNCTION TO HOMEPAGE===//
function redirectHome() {
   if (token != null) {
   window.location.href = "./home.html";
   }
 };
