console.log("js is linked")

//TOKEN;
let token;

//Profile FORM VARIABLES
let secondEmail = document.getElementById('update-secondEmail');
let updateMobile = document.getElementById('update-mobile');

//display profile variables
const displaySecondEmail = document.getElementById('secondEmail');
const displayMobile = document.getElementById('defaultMobile');

// button used to update and display profile variables
const createProfileBtn = document.getElementById("update-profile");
createProfileBtn.addEventListener("click", createProfile);

//create profile function
function createProfile(e){
  e.preventDefault();

// The path for the fetch
  fetch('http://localhost:8181/profile/', {
    method: 'POST',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    },

// The red represents the variables refered from the model
    body: JSON.stringify({
      mobile: updateMobile.value,
      secondary_email: secondEmail.value
    })
  })

// returns the object inside the body
  .then((res) => {
    return res.json();
  })

// After the object is returned, the button reloads
  .then((res) => {
     window.location.reload();
   })

// Refers to an error if something goes wrong.
   .catch((err) => {
     console.log(err);
   })
 }

// Will display the variables
 function getProfile(){

// The path for the fetch
   fetch('http://localhost:8181/profile/', {
     method: 'GET',
     headers: {
       "Authorization": "Bearer " + localStorage.getItem('user'),
       "Content-Type": "application/json"
     }
   })

// Returns the object from the json body
   .then((res) => {
     return res.json();
   })


   .then((res) => {
// We are creatingElement for paragraphs where res.mobile will go into
     const mobileInfo = document.createElement('p');
// When we get the response it goes into the paragraph
     mobileInfo.innerText = res.mobile;
     mobileInfo.classList.add("responseInfo");

// creatingElement for paragraphs where secondary_email will go into
     const secondEmailInfo = document.createElement('p');
// When we get the response it goes into the paragraph
     secondEmailInfo.innerText = res.secondary_email;
     secondEmailInfo.classList.add("responseInfo");

// Here we append into the disaply varaibles
     displaySecondEmail.append(secondEmailInfo);
     displayMobile.append(mobileInfo);
   })

   .catch(function(error) {
     console.log(error);
   });
 }

// Call the getProfile() function to activate it.
 getProfile();


 
