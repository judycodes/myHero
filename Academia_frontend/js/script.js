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

//trim() to avoid whitespaces in inputs being submitted to db
 if(email.value.trim() !== "" && password.value.trim() !== "" && username.value.trim() !== "") {

   fetch('http://localhost:8181/signup', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               email: email.value.trim().toUpperCase(),
               password: password.value.trim().toUpperCase(),
               username: username.value.trim().toUpperCase()
           })
   })

   .then(res => {
            if (res.status == 500) {
                alert(`A hero already exists with that name and/or email address. \n Try another superhero name and/or email address.`);
            } else {
                return res.json();
            }
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
    alert(`Young hero, all fields must be provided to enter U.A.!\nTry applying to be a hero again soon!`);
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
          username: logInUserName.value.trim().toUpperCase(),
          password: logInPassword.value.trim().toUpperCase()
        })
    })

    .then(res => {
      return res.json();
    })

    .then(res => {
      token = res.token;
      //as long as a token is provided for the user, then the user will be redirected to the homepage
      if(res.token !== null) {
        localStorage.setItem('user', token);
        redirectHome();
      } //if username/password does not match an account in the database, user will not be redirected to homepage and will be informed
      else {
        alert(`Villains are everywhere! No one can be trusted. \n Check your credentials again. \n Your username or password is incorrect. \n Or you are no hero at U.A. High School.`);
      }

    })

    .catch(error => {
      console.error(error);
    });
}

 }
//===REDIRECT FUNCTION TO HOMEPAGE===//
function redirectHome() {
   if (token != null) {
   window.location.href = "./home.html";
   }
 };
