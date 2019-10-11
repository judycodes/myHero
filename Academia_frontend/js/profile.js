console.log("js is linked")

//TOKEN;
let token;

//Profile FORM VARIABLES
let secondEmail = document.getElementById('update-secondEmail');
let updateMobile = document.getElementById('update-mobile');

//display profile variables
const displayUsername = document.getElementById('displayUsername');
const displayPrimaryEmail = document.getElementById('displayPrimaryEmail');
const displaySecondEmail = document.getElementById('secondEmail');
const displayMobile = document.getElementById('defaultMobile');

// Session Storage Display
displayUsername.innerText = localStorage.getItem('username');
displayPrimaryEmail.innerText = localStorage.getItem('primaryEmail');

// button used to update and display profile variables
const createProfileBtn = document.getElementById("update-profile");
createProfileBtn.addEventListener("click", createProfile);

//create profile function
function createProfile(e){
  e.preventDefault();

 if(updateMobile.value !== "" && secondEmail.value !== ""){

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

   // Returns the object inside the body
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

 } else {
   alert("You have more to tell us.");
 }

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

 // button used to show Posts
 const showPostBttn = document.getElementById('show-posts');
 showPostBttn.addEventListener("click" , showPostfunc);

 // Show Post function
 function showPostfunc(e) {
   e.preventDefault();

// Fetch Path
     fetch('http://localhost:8181/post/listUserPosts/', {
       method: 'GET',
       headers: {
         "Authorization" : "Bearer " + localStorage.getItem('user'),
         "Content-Type" : "application/json"
       }
     })
     .then((res) => {
// Returning a JSON respone for the User's posts, which since we console logged it in line 120
// It returns the array containing the object keys along with it's assigned values
       return res.json();
     })

     .then((res) => {
       console.log(res, "userpost res")

// creating a div which will contain the post_body and post_title which wil later go in the userPostDisplay
       const userPostDiv = document.createElement('div');
       const userPostDisplay = document.querySelector("#userPostDisplay");
// for loop to run through all the arrays object keys and values and return it
       for(let i = 0; i < res.length; i++){
         console.log(res[i].post_body, "postBodyresI");
         console.log(res[i].post_title, "postTitleresI");

// When post_title is shown on the dom it will be have an h1 styling do to line 130 which createsElement;
         const postTitleInfo = document.createElement('h1');
// innerText takes the post_tile which is the key at the point of when it's going throug the loop and places the value on the dom
         postTitleInfo.innerText = res[i].post_title;
// gives the posttitleInfo a class
         postTitleInfo.classList.add("responseTitleInfo");

// postBodyInfo will have a p styling
         const postBodyInfo = document.createElement('p');

// Value of post_body will be shown on the dom
         postBodyInfo.innerText = res[i].post_body;
// gives the posttitleInfo a class
         postBodyInfo.classList.add("responseInfo");

// We append the postBodyInfo to the postTitleInfo, where title will appear first and body will appear afterwards
         postTitleInfo.appendChild(postBodyInfo);
// We then append the postTitleInfo to the userPostDiv, which like mentioned in 122 will then go in the userPostDiv
         userPostDiv.appendChild(postTitleInfo);
         userPostDisplay.appendChild(userPostDiv);
       }
     })

     .catch(function(error) {
       console.log(error);
     });
   }

// button used to show comments

const showCommentBttn = document.getElementById('show-comments');
showCommentBttn.addEventListener("click", showCommentfunc);

// show comment function
function showCommentfunc(e) {
  e.preventDefault();

// fetch path
  fetch('http://localhost:8181/comment/listUserComments', {
    method: 'GET',
    headers: {
      "Authorization" : "Bearer " +localStorage.getItem('user'),
      "Content-Type" : "application/json"
    }
  })

  .then((res) => {
    return res.json();
  })

  .then((res) => {
    console.log(res, "usercomment res")

// userCommentDiv will be appended to userCommentDisplay in 192
    const userCommentDiv = document.createElement('div');
    const userCommentDisplay = document.querySelector("#userCommentDisplay");

// Used to loop through the comments to get them all
    for(let i = 0; i < res.length; i++){

// Ensuring a response is given in the console
      console.log(res[i].comment_body, "commentBodyresI");

// commentBodyInfo will have a basic p styling
      const commentBodyInfo = document.createElement('p');

// Ensuring that the commentBodyInfo gets the value from the JSON object and shows the value on the dom
        commentBodyInfo.innerText = `- ${res[i].comment_body}`;

// refer to line 183
        userCommentDiv.appendChild(commentBodyInfo);
        userCommentDisplay.appendChild(userCommentDiv);
      }
    })
    .catch(function(error) {
      console.log(error);
    });
  }
