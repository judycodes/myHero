console.log("js is linked")

//TOKEN;
let token;

//Profile FORM VARIABLES
// let email = document.getElementById('update-email');
let secondEmail = document.getElementById('update-secondEmail');
let updateMobile = document.getElementById('update-mobile');
// let heroName = document.getElementById('Hero-name');
// let catchPhrase = document.getElementById('update-catchPhrase')
//display profile variables

const displayUserName = document.getElementById('username');
// const displayEmail = document.getElementById('email');
const displaySecondEmail = document.getElementById('secondEmail');
const displayMobile = document.getElementById('defaultMobile');
// const displayHeroName = document.getElementById('Heroname');
// const catchPhrase = document.getElementById('Catchphrase')

// displayProfile();

//CREATE PROFILE FUNCTION - used also as a workaround for the update profile
  //create profile button
const createProfileBtn = document.getElementById("update-profile");
createProfileBtn.addEventListener("click", createProfile);
  //create profile function
function createProfile(e){
  e.preventDefault();
  localStorage.setItem('primaryEmail', email.value);
  fetch('http://localhost:8181/profile/', {
    method: 'POST',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      mobile: updateMobile.value,
      secondary_email: secondEmail.value
    })

  })

  .then((res) => {
    return res.json();
  })

  //calls updateProfile function to show new changes from "create/update" profile form
  .then((res) => {
    console.log(res);
  })

  .catch((err) => {
    console.log(err);
  })
}

function getProfile(){
  fetch('http://localhost:8181/profile/', {
    method: 'GET',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })

  .then((res) => {
    return res.json();
  })

  .then((res) => {
    const mobileInfo = document.createElement('p');
    mobileInfo.innerText = res.mobile;
    const secondEmailInfo = document.createElement('p');
    secondEmailInfo.innerText = res.secondary_email;
    displaySecondEmail.append(secondEmailInfo);
    displayMobile.append(mobileInfo);
  })
  .catch(function(error) {
    console.log(error);
  });
}

getProfile();
