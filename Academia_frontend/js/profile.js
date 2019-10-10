console.log("js is linked")

//TOKEN;
let token;

//Profile FORM VARIABLES
// let email = document.getElementById('update-email');
let secondEmail = document.getElementById('update-secondEmail');
let mobile = document.getElementById('update-mobile');
// let heroName = document.getElementById('Hero-name');
// let catchPhrase = document.getElementById('update-catchPhrase')
//display profile variables
const displayUserName = document.getElementById('username');
// const displayEmail = document.getElementById('email');
const displaySecondEmail = document.getElementById('secondEmail');
const displayMobile = document.getElementById('mobile');
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
        mobile: mobile.value,
      secondary_email: secondEmail.value

      // heroName: heroName.value,
      // catchPhrase: catchPhrase.value
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
//   //updates profile function
// function updateProfile(res) {
//   //if input value is filled, then don't update form
//   if(addEmail.value === "" || addEmail.value === null) {
//     return;
//   } else {
//     displayAddEmail.innerHTML = res.additionalEmail;
//   }
//   if(mobile.value === "" || mobile.value === null) {
//     return;
//   } else {
//     displayMobile.innerHTML = res.mobile;
//   }
//   if(addy.value === "" || mobile.value === null) {
//     return;
//   } else {
//     displayAddy.innerHTML = res.address;
//   }
// }





// //shows profile information
// function displayProfile() {
//   fetch('http://localhost:8181/profile/', {
//     method: 'GET',
//     headers: {
//       "Authorization": "Bearer " + localStorage.getItem('user'),
//       "Content-Type": "application/json"
//     }
//   })
//   .then((res) => {
//     return res.json();
//   })
//   .then((res) => {
//     displayUserName.innerHTML = localStorage.getItem('username');
//     displayPrimaryEmail.innerHTML = localStorage.getItem('primaryEmail');
//     if(res.additionalEmail === undefined || !localStorage.getItem('secondaryEmail')) {
//       displayAddEmail.innerHTML = "<em>Add Secondary Email</em>";
//     } else if(res.additionalEmail) {
//       displayAddEmail.innerHTML = res.additionalEmail;
//       localStorage.setItem('secondaryEmail', res.additionalEmail);
//     } else if (!res.additionalEmail) {
//       displayAddEmail.innerHTML = localStorage.getItem('secondaryEmail');
//     }
//     if(res.mobile === undefined || !localStorage.getItem('mobile')) {
//       displayMobile.innerHTML = "<em>Add Mobile Number</em>";
//     } else if(res.mobile) {
//       displayMobile.innerHTML = res.mobile;
//       localStorage.setItem('mobile', res.mobile);
//     } else if(!res.mobile) {
//       displayMobile.innerHTML = localStorage.getItem('mobile');
//     }
//     if(res.address === undefined || !localStorage.getItem('addy')) {
//       displayAddy.innerHTML = "<em>Add Home Address</em>";
//     } else if(res.address) {
//       displayAddy.innerHTML = res.address;
//       localStorage.setItem('addy', res.address);
//     } else if(!res.address) {
//       displayAddy.innerHTML = localStorage.getItem('addy');
//     }
// })
//   .catch((err) => {
//       console.log(err);
//   })
// }
