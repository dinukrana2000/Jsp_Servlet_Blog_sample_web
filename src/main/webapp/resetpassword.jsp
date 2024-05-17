<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-size: cover;
            background-position: center;
            background-color: #e7e2e2;
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
            padding: 10px;
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
        .box {
            display: flex;
            justify-content: space-around;
            align-items: center;
            width: 100%;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            box-sizing: border-box;
            border: 2px solid #000;
            background-color: #ffffff;
            border-radius: 20px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .box img {
            width: 40%;
            height: auto;
        }
        .form-container {
            width: 50%;
        }
    </style>

</head>
<body>
<header>
    <img class="logo" src="image/pic.png" alt="Logo">
    <span class="header-text">Blogger</span>
</header>
<div class="box">
    <img src="image/reset.jpg" alt="Background Image">
    <div class="form-container">
        <h2>Reset Password</h2>
        <% if (request.getAttribute("alertMessage") != null) { %>
        <div class="alert alert-<%= request.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
            <%= request.getAttribute("alertMessage") %>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <% } %>
        <form action="Resetpasswordservlet" method="post" class="needs-validation" novalidate>
            <div class="form-group">
                New Password:
                <input type="password" id="newPassword" name="newPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" class="form-control" required maxlength="20">
                <div class="invalid-feedback">
                    Password should be at least 8 characters long, contain at least one digit, one lower case letter, and one upper case letter.
                </div>
            </div>
            <div class="form-group">
                Confirm Password:
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required maxlength="20">
                <div class="invalid-feedback">
                    Please confirm your password.
                </div>
            </div>
            <div><input type="hidden" id="hashedPwd" name="hashedPwd"></div>
            <div><input type="hidden" id="conhashPwd" name="conhashPwd"></div>
            <div class="form-group">
                <input type="hidden" name="reset_token" value="<%= request.getParameter("reset_token") %>">
            </div>
            <div class="form-group">
                <input type="submit" value="Submit">
            </div>
        </form>
    </div></div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
    function hashPassword() {
        var pwd = document.getElementById('newPassword');
        var cpwd=document.getElementById('confirmPassword');
        var hashedPwd = document.getElementById('hashedPwd');
        var conhashPwd=document.getElementById('conhashPwd');
        hashedPwd.value = sha256(pwd.value);
        conhashPwd.value=sha256(cpwd.value);
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
