<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-size: cover;
            background-position: center;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;

        }
        .form-group {
            margin-bottom: 15px;

        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #030000;
            border-radius: 5px;
        }
        .form-group input[type="submit"],
        .form-group input[type="reset"],
        .form-group input[type="button"] {
            background-color: #5cb85c;
            color: white;
            cursor: pointer;
            border-radius: 100px;
            margin-bottom: 5px;
            width: 50%;
        }
        .form-group input[type="submit"]:hover,
        .form-group input[type="reset"]:hover,
        .form-group input[type="button"]:hover {
            background-color: #449d44;
        }
        .logo {
            width: 5%;
            height: 10%;
        }
        header {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 100;
            display: flex;
            align-items: center;
            padding: 10px;
        }
        .header-text {
            margin-left: 1%;
            font-size: 24px;
            color: #de4d04;
        }
        .bg {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            z-index: -1;
            background-size: cover;
            background-position: center;
            animation: fade 6s infinite;
        }
        @keyframes fade {
            0% {opacity: 1;}
            33.33% {opacity: 0;}
            66.66% {opacity: 0;}
            100% {opacity: 1;}
        }

        #bg2 {
            animation-delay: 2s;
        }
        #bg3 {
            animation-delay: 4s;
        }

        /* Responsive styles */
        @media (max-width: 600px) {
            .container {
                width: 90%;
            }
            .header-text {
                font-size: 18px;
            }
            .form-group input {
                padding: 5px;
            }
        }

    </style>

</head>
<body>
<header>
    <img class="logo" src="image/pic.png" alt="Logo">
    <span class="header-text">Blogger</span>
</header>
<div id="bg1" class="bg" style="background-image: url('image/image1.jpg');"></div>
<div id="bg2" class="bg" style="background-image: url('image/image2.jpg');"></div>
<div id="bg3" class="bg" style="background-image: url('image/image3.jpg');"></div>

<div class="container">

    <h1 class="tit">Sign in</h1>
    <% if (request.getAttribute("alertMessage") != null) { %>
    <div class="alert alert-<%= request.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
        <%= request.getAttribute("alertMessage") %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <% } %>
    <form action="Signupservlet" method="post" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="fname">Full Name:</label>
            <input type="text" id="fname" name="fname" class="form-control" required maxlength="50">
            <div class="invalid-feedback">
                Please enter your full name.
            </div>
        </div>
        <div class="form-group">
            <label for="mail">Email:</label>
            <input type="email" id="mail" name="mail" class="form-control" required maxlength="40">
            <div class="invalid-feedback">
                Please enter a valid email.
            </div>
        </div>
        <div class="form-group">
            <label for="num">Contact:</label>
            <input type="number" pattern="[0-9]{10}" id="num" name="num" class="form-control" required maxlength="10">
            <div class="invalid-feedback">
                Please enter a valid 10-digit contact number.
            </div>
        </div>
        <div class="form-group">
            <label for="uname">UserName:</label>
            <input type="text" id="uname" name="uname" class="form-control" required maxlength="20">
            <div class="invalid-feedback">
                Please enter a username.
            </div>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" id="pwd" name="pwd" class="form-control" required maxlength="20">
            <div class="invalid-feedback">
                Password should be at least 8 characters long, contain at least one digit, one lower case letter, and one upper case letter.
            </div>
        </div>
        <div class="form-group">
            <label for="cpwd" >Confirm Password:</label>
            <input type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" id="cpwd" name="cpwd" class="form-control" required>
            <div class="invalid-feedback">
                Please confirm your password.
            </div>
        </div>
        <div><input type="hidden" id="hashedPwd" name="hashedPwd">
        </div>
        <div><input type="hidden" id="hashedCPwd" name="hashedCPwd">
        </div>
        <div class="form-group">
            <input type="submit" value="Sign Up" >
            <input type="reset" value="Reset">
            <input type="button" value="Log in" onclick="window.location.href='login.jsp'">
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
    function hashPassword() {
        var pwd = document.getElementById('pwd');
        var cpwd=document.getElementById('cpwd');
        var hashedPwd = document.getElementById('hashedPwd');
        var hashedCPwd = document.getElementById('hashedCPwd');
        hashedCPwd.value=sha256(cpwd.value);
        hashedPwd.value = sha256(pwd.value);
        pwd.value = '';
        cpwd.value='';
    }
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    } else {
                        hashPassword();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>



</body>
</html>
