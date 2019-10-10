//===HOME===//

//*===POSTS===*//
listAllPosts();

function listAllPosts () {

 fetch('http://localhost:8181/post/listAllPosts', {
      headers: {
          "Authorization": "Bearer " + localStorage.getItem('user').toString(),
          "Content-Type" : "application/json"
      }

  })

 .then((res) => {
   return res.json();
  })

.then((res) => {
  console.log("list posts", res);
  const postsDisplay = document.querySelector('#postsDisplay');

  for(let i = 0; i < res.length; i++) {

  const postDiv = document.createElement('div');
  const postTitle = document.createElement('h3');
  const postBody = document.createElement('p');

  postTitle.innerText = res[i].post_title;
  postBody.innerText = res[i].post_body;
  postDiv.append(postTitle, postBody);
  postsDisplay.appendChild(postDiv);

  }
  })

  .catch((err) => {
  console.log(err);
  })
}
