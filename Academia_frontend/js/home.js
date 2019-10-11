//===HOME===//

//*===POSTS===*//

//LIST ALL POSTS
listAllPosts();

function listAllPosts () {

 fetch('http://localhost:8181/post/listAllPosts', {
      headers: {
          "Authorization": "Bearer " + localStorage.getItem('user'),
          "Content-Type" : "application/json"
      }

  })

 .then((res) => {
   return res.json();
  })

.then((res) => {
  console.log("list posts", res);
  const postsDisplay = document.querySelector('#postsDisplay');

  //loop through posts in database
  for(let i = res.length-1; i >= 0; i--) {


    const postId = res[i].id;

  //===create post related elements===//
  const postDiv = document.createElement('div');
  //gives postdiv an id of actual post
  postDiv.id = `${res[i].id}`;
  const postTitle = document.createElement('h3');
  const postBody = document.createElement('p');
  const postAuthor = document.createElement('span');

    //adds post content to created post title and post body elements
    postTitle.innerText = res[i].post_title;
    postBody.innerText = res[i].post_body;

    postAuthor.innerText = `|| author: ${res[i].user.username}`;

  //===delete related elements===//
  const deletePostBtn = document.createElement('button');

    //delete post button styling
    deletePostBtn.innerText = "delete post";

    //delete post addEventListener
    deletePostBtn.addEventListener("click", function(event) {
    event.preventDefault();
    deletePost(postId);
    });

  //===view comments related elements===//
  const viewCommentsBtn = document.createElement('button');

    //view comments button styling
    viewCommentsBtn.innerText = "view comments";

    //view comments addEventListener
    viewCommentsBtn.addEventListener("click", function(event) {
      event.preventDefault();

      viewComments(event);
    });

//adds post to each postDiv element
  postTitle.append(postAuthor);
  postDiv.append(postTitle, postBody, deletePostBtn, viewCommentsBtn);

//adds all posts to postsDisplay
  postsDisplay.appendChild(postDiv);

  }
  })

  .catch((err) => {
  console.log(err);
  })
}

//CREATE POST
//new post button
const createPostBtn = document.querySelector('#createPostBtn');
createPostBtn.addEventListener('click', createPost);

//create posts
function createPost(e) {
    e.preventDefault();

    const newPostTitle = document.querySelector('#newPostTitle');
    const newPostBody = document.querySelector('#newPostBody');

    fetch("http://localhost:8181/post/create", {
        method: 'POST',
        headers: {
          "Authorization": "Bearer " + localStorage.getItem('user'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
            post_title: newPostTitle.value,
            post_body: newPostBody.value
        })
    })

    //add new post to dom by forcing page to refresh, which would call listAllPosts again
    .then((res) => {
        window.location.reload(true);
    })

    .catch((err) => {
        console.log(err);
    })
}

//DELETE POST
function deletePost(postId) {

  fetch((`http://localhost:8181/post/delete-${postId}`), {
    method: 'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })
  .then((res) => {
    //console.log(res, "res in delete post");
    if (res.status == 200) {
      window.location.reload(true);
      alert("Post Was Defeated!");
    } else if (res.status == 405){
      alert("This Is Not Your Post To Fight. (Mind your own posts!)");
    }
    })
    .then((error) => {
      console.log(error);
    })
}


//VIEW COMMENTS
function viewComments(event) {
  const postId = event.target.parentNode.id;
  fetch((`http://localhost:8181/post/get-${postId}`), {
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })
  .then((res) => {
    return res.json();
  })

  .then((res) => {
    console.log(res, "res in view comments");
    //console.log(res.comments, "res comments array");
    //create comment related elements
//const commentDiv = document.createElement('div');

    const commentsArr = res.comments;

    console.log(commentsArr, "commentsArr");
    for(let i=0; i < commentsArr.length; i++) {
      const commentBody = document.createElement('p');
      commentBody.innerText = commentsArr[i].comment_body;
      console.log(commentsArr[i], "i commentsArr");
      event.target.parentNode.append(commentBody);
    }
    })
    .then((error) => {
      console.log(error);
    })



// incomplete comment display
// if(res[i].comments !== []) {
//   //adds comment content to create comment elements
//   commentBody.innerText = res[i].comments.comment_body;
//   commentDiv.append(commentBody);
// }


}
