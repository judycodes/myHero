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

  //console.log("list posts", res);
  const postsDisplay = document.querySelector('#postsDisplay');

  //loop through posts in database - places recent posts on top of page
  for(let i = res.length-1; i >= 0; i--) {

    //post id needed to delete post
    const postId = res[i].id;

  //===create post related elements===//
  const postDiv = document.createElement('div');
  const postTitle = document.createElement('h3');
  const postBody = document.createElement('p');
  const postAuthor = document.createElement('span');
  postAuthor.classList.add("postAuthor");
  postBody.classList.add("postBody");

    //gives postdiv an id of actual post
    postDiv.id = `${res[i].id}`;
    postDiv.classList.add("postDiv");

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

//===create comment related elements===//
  const showCommentBoxBtn = document.createElement('button');
  showCommentBoxBtn.innerText = "create comment";

  //display comment box addEventListener
  showCommentBoxBtn.addEventListener("click", function(event){
    event.preventDefault();
    createCommentBox(event);
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
  postDiv.append(postTitle, postBody, deletePostBtn, viewCommentsBtn, showCommentBoxBtn);

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
function createPost(event) {
    event.preventDefault();

    //new post related elements
    const newPostTitle = document.querySelector('#newPostTitle');
    const newPostBody = document.querySelector('#newPostBody');

  //submits new post content as long as inputs are empty
  if(newPostBody.value.trim() !== "" && newPostTitle.value.trim() !== "") {
    fetch("http://localhost:8181/post/create", {
        method: 'POST',
        headers: {
          "Authorization": "Bearer " + localStorage.getItem('user'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
            post_title: newPostTitle.value.trim(),
            post_body: newPostBody.value.trim()
        })
  })

  //add new post to dom by forcing page to refresh, which would call listAllPosts again
  .then((res) => {
      window.location.reload(true);
  })

  .catch((error) => {
      console.log(error);
  })
  //handles empty inputs
} else {
  alert("Please fill in all fields, before you submit!");
}

}

//DELETE POST
function deletePost(postId) {

  //deletes post by post id
  fetch((`http://localhost:8181/post/delete-${postId}`), {
    method: 'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })

  .then((res) => {
    //console.log(res, "res in delete post");

    //determines if user is allowed to delete post
      //user can delete if post was created by user
    if (res.status == 200) {
      window.location.reload(true);
      alert("Post Was Defeated!");
      //user is informed that the post cannot be deleted if current user is not the author
    } else if (res.status == 405){
      alert("This Is Not Your Post To Fight. (Mind your own posts!)");
    }
    })

    .catch((error) => {
      console.log(error);
    })
}

//*===COMMENTS===*//

//VIEW COMMENTS
function viewComments(event) {
  //btnPressCounter();

    //post id needed to delete comment from post
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

      //create comment related elements
      const commentDiv = document.createElement('div');

      //array of comments for specific post
      const commentsArr = res.comments;
      //console.log(commentsArr, "commentsArr");

      //handles if there are no comments for post
      if(commentsArr.length == 0) {

        const noCommentsMsg = document.createElement('p');
        noCommentsMsg.classList.add('noCommentsMsg');
        noCommentsMsg.innerText = "No Comments Yet. Have something to say? Comment away~";
        event.target.parentNode.append(noCommentsMsg);
      }

      //if there are comments for specific post
      else {

        for(let i=0; i < commentsArr.length; i++) {

          //comment id needed to delete comment
          const commentId = commentsArr[i].id;
          //console.log(commentId, "comment id")

          //individual comment related elements
          const commentDiv = document.createElement('div');
          commentDiv.classList.add('commentDiv');

          const commentBody = document.createElement('p');
          commentBody.classList.add("commentBody");

          commentBody.innerText = `Young ${commentsArr[i].user.username} says, \" ${commentsArr[i].comment_body.trim()} \"`;

          //creates deleteCommentBtn
          const deleteCommentBtn = document.createElement('button');
          deleteCommentBtn.innerText = "delete comment";
          deleteCommentBtn.classList.add('deleteCommentBtn');

          //deleteCommentBtn addEventListener
          deleteCommentBtn.addEventListener('click', function(event) {
            event.preventDefault();
            deleteComment(commentId)
          });

          commentDiv.append(commentBody, deleteCommentBtn);

          //appends comment div containing comment body and delete button to bottom of post div
          event.target.parentNode.appendChild(commentDiv);

        }

      }

      })

      .catch((error) => {
        console.log(error);
      })

}


//DELETE COMMENT
function deleteComment(commentId) {
  //deletes comment by comment id
  fetch((`http://localhost:8181/comment/delete-${commentId}`), {
    method: 'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })

  .then((res) => {
    //handles if user can delete comment
    //console.log(res, "res in delete comment");

      //if comment belongs to user, comment can be deleted
    if (res.status == 200) {
      window.location.reload(false);
      alert("Comment Was Defeated!");
      //if comment does not belong to user, user is informed that the comment cannot be deleted
    } else if (res.status == 405){
      alert("This Is Not Your Comment To Fight. (Mind your own comments!)");
    }
    })

    .catch((error) => {
      console.log(error);
    })
}


//CREATE COMMENT BOX
function createCommentBox(event){

  const postId = event.target.parentNode.getAttribute("id");
  console.log(event.target.parentNode.id, 'post id in createCommentBox');

  const createCommentDiv = document.createElement('div');
  createCommentDiv.classList.add('createCommentDiv');

  const createCommentInput = document.createElement('textarea');
  createCommentInput.classList.add(`createCommentInputForPost-${postId}`);

  const createCommentBtn = document.createElement('button');
  createCommentBtn.innerText = "submit comment";
  createCommentBtn.classList.add('createCommentBtn');

  createCommentDiv.append(createCommentInput, createCommentBtn);


  //create comment addEventListener
  createCommentBtn.addEventListener("click", function(event) {
    event.preventDefault();
    createComment(postId);

  });

  //attaches comment box to bottom of post div content
  event.target.parentNode.append(createCommentDiv);


  //prevents multiply comment boxes from appearing at one time
  if(document.querySelectorAll(".createCommentDiv").length > 1) {
    alert("Get commenting!");
    const existingCommentBox = document.querySelector(".createCommentDiv");
    existingCommentBox.remove(document.querySelector(".createCommentDiv"));
  }

}

//CREATE COMMENT
function createComment(postId) {

  const createCommentInput = document.querySelector(`.createCommentInputForPost-${postId}`);

  //console.log(createCommentInput, "createCommentInput");

  if(createCommentInput.value.trim() !== "") {
    fetch(`http://localhost:8181/comment/createOn${postId}`, {
        method: 'POST',
        headers: {
          "Authorization": "Bearer " + localStorage.getItem('user'),
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
            comment_body: createCommentInput.value.trim()
        })
    })

    .then((res) => {
      return res.json();
    })

    //add new post to dom by forcing page to refresh, which would call listAllPosts again
    .then((res) => {
      alert("You have had your say!");
      window.location.reload(false);
    })

    .catch((err) => {
        console.log(err);
    })

    //handles empty or whitespace comment input
  }  else {
    alert("No comment? This is not a PRESS conference. Say something!");
  }

}

//view comments button count - stops user from viewing comments more than once
// let btnPressCount = 0;
// function btnPressCounter(){
//
//   if(btnPressCount == 0) {
//     btnPressCount++;
//   } else {
//   alert("You are viewing the comments display already.");
//   window.location.reload(false);
// }
// }
