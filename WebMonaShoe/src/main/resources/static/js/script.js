// Get the modal and button
var modal = document.getElementById("video-modal");
var btn = document.getElementById("video-btn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on the button, open the modal
btn.onclick = function () {
  modal.style.display = "block";
};
// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
};
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};
//

var searchmodal = document.getElementById("search-modal");
var searchbtn = document.getElementById("search-btn");
var searchspan = document.getElementsByClassName("search-close")[0];
searchbtn.onclick = function () {
  searchmodal.style.display = "block";
};
searchspan.onclick = function () {
  searchmodal.style.display = "none";
};
window.onclick = function (event) {
  if (event.target == searchmodal) {
    searchmodal.style.display = "none";
  }
};
// Login and Sign up
var loginmodal = document.getElementsById("login-modal");
var loginbtn = document.getElementById("login-btn");
var loginspan = document.getElementsByClassName("login-close")[0];

loginbtn.onclick = function () {
  loginmodal.style.display = "block";
};

loginspan.onclick = function () {
  loginmodal.style.display = "none";
};

window.onclick = function (event) {
  if (event.target == loginmodal) {
    loginmodal.style.display = "none";
  }
};
function validationForm() {
  var email = $.trim($("#email").val());
  var password = $.trim($("#pass").val());
  const emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  var flag = true;

  if (email == "") {
    $("#sign_error").text("Email must be not blank!");
    flag = false;
    return;
  } else if (password == "") {
    $("#pass_error").text("Password must be not blank!");
    flag = false;
    return;
  } else if (email.length <= 5) {
    $("#sign_error").text("Email must have more than 5 characters!");
    flag = false;
    return;
  } else if (password.length <= 5) {
    $("#pass_error").text("Password must have more than 5 characters!");
    flag = false;
    return;
  } else if (!email.match(emailFormat)) {
    $("#sign_error").text("Invalid email format!");
    flag = false;
    return;
  } else {
    alert("Welcome to Duyên!");
  }
}
const spinner = document.querySelector(".spinner");
spinner.style.display = "block";
