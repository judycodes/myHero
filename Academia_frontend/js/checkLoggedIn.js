//===Log Out Functionality===//

window.onload = (event) => {
  checkLogin();
};

function checkLogin() {
  if (localStorage.getItem('user') != null) {
    const navBar = document.querySelector('nav');
    const navItem = navBar.lastElementChild;
    navItem.innerText = "\"YOUNG " + localStorage.getItem('username').toUpperCase() + "\'s PROFILE \"";
    navItem.href = "profile.html";

    const logout = document.createElement('a');
    logout.href = "#";
    logout.innerText = "LOG OUT";
    logout.addEventListener("click", removeUserInfo);
    navBar.appendChild(logout);
  }
}

function removeUserInfo() {
  if (confirm("Ready to leave Academia?")) {
    localStorage.removeItem('user');
    localStorage.removeItem('username');
    window.location.href = "index.html";
  }
};
