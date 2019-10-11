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

    //post id needed to delete post
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

//===create comment related elements===//
  const showCommentBoxBtn = document.createElement('button');
  showCommentBoxBtn.innerText = "create comment";

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
    //console.log(commentsArr, "commentsArr");

    //if there are no comments for post
    if(commentsArr == [] || commentsArr.length == 0) {
      const noCommentsMsg = document.createElement('p');
      noCommentsMsg.classList.add('noCommentsMsg');
      noCommentsMsg.innerText = "No Comments Yet. Have something to say? Comment away~";
      event.target.parentNode.append(noCommentsMsg);
    } else {

      for(let i=0; i < commentsArr.length; i++) {

        //comment id needed to delete comment
        const commentId = commentsArr[i].id;

        //individual comment
        const commentDiv = document.createElement('div');
        commentDiv.classList.add('commentDiv');

        const commentBody = document.createElement('p');
        commentBody.innerText = commentsArr[i].comment_body;

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

        //console.log(commentsArr[i], "i commentsArr");
        event.target.parentNode.append(commentDiv);
      }
    }

    })
    .then((error) => {
      console.log(error);
    })
}


//DELETE COMMENT
function deleteComment(commentId) {
  fetch((`http://localhost:8181/comment/delete-${commentId}`), {
    method: 'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem('user'),
      "Content-Type": "application/json"
    }
  })
  .then((res) => {
    console.log(res, "res in delete comment");
    if (res.status == 200) {
      window.location.reload(false);
      alert("Comment Was Defeated!");
    } else if (res.status == 405){
      alert("This Is Not Your Comment To Fight. (Mind your own comments!)");
    }
    })
    .then((error) => {
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

  event.target.parentNode.append(createCommentDiv);
}


//CREATE COMMENT
function createComment(postId) {
  const createCommentInput = document.querySelector(`.createCommentInputForPost-${postId}`);
console.log(createCommentInput, "createCommentInput");
  fetch(`http://localhost:8181/comment/createOn${postId}`, {
      method: 'POST',
      headers: {
        "Authorization": "Bearer " + localStorage.getItem('user'),
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
          comment_body: createCommentInput.value
      })
  })

  .then((res) => {
    return res.json();
  })
  //add new post to dom by forcing page to refresh, which would call listAllPosts again
  .then((res) => {
    alert("comment submitted!");
    window.location.reload(false);
    // console.log(postId, "create comment submit");
    // viewComments(event);
  })

  .catch((err) => {
      console.log(err);
  })

}
