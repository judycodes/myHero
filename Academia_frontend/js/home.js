//===HOME===//

//*===POSTS===*//
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

  //loop through post response
  for(let i = 0; i < res.length; i++) {

  //create post related elements
  const postDiv = document.createElement('div');
  const postTitle = document.createElement('h3');
  const postBody = document.createElement('p');
  const postAuthor = document.createElement('span');
  const deletePostBtn = document.createElement('button');

  //delete post button styling
  deletePostBtn.innerText = "delete post";

  //delete post addEventListener
  deletePostBtn.addEventListener("click", function() {
    fetch((`http://localhost:8181/post/delete-${res[i].id}`), {
      method: 'DELETE',
      headers: {
        "Authorization": "Bearer " + localStorage.getItem('user'),
        "Content-Type": "application/json"
      }
    })
    .then((res) => {
      console.log(res, "res in delete post");
      if (res.status == 200) {
        window.location.reload(true);
        alert("Post Was Defeated!");
      } else if (res.status == 204){
        alert("This Is Not Your Post To Fight. (Mind your own posts!)");
      }
      })
      .then((error) => {
        console.log(error);
      })
      });



  //adds post content to created post title and post body elements
  postTitle.innerText = res[i].post_title;
  postBody.innerText = res[i].post_body;

  postAuthor.innerText = `|| author: ${res[i].user.username}`

  //create comment related elements
  const commentDiv = document.createElement('div');
  const commentBody = document.createElement('p');

  // incomplete comment display
  // if(res[i].comments !== []) {
  //   //adds comment content to create comment elements
  //   commentBody.innerText = res[i].comments.comment_body;
  //   commentDiv.append(commentBody);
  // }

  //adds post to each postDiv element
  postTitle.append(postAuthor);
  postDiv.append(postTitle, postBody, deletePostBtn);

  //adds all posts to postsDisplay
  postsDisplay.appendChild(postDiv);

  }
  })

  .catch((err) => {
  console.log(err);
  })
}
