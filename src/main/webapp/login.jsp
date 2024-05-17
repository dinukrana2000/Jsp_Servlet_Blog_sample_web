<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
            padding: 20px;
            margin-top: 5%;
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
    <h1>Login</h1>
    <% if (request.getAttribute("alertMessage") != null) { %>
    <div class="alert alert-<%= request.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
        <%= request.getAttribute("alertMessage") %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <% } %>
    <form action="Loginservlet" method="post">
        <div class="form-group">
            <label for="uname">Username:</label>
            <input type="text" id="uname" name="uname" required maxlength="20">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" id="pwd" name="pwd" required maxlength="20">
        </div>
        <input type="hidden" id="hashedPwd" name="hashedPwd">

        <div class="form-group">
            <input type="submit" value="Login" onclick="hashPassword(); return false;">
            <input type="button" value="Sign up" onclick="window.location.href='Signup.jsp'">
            <input type="button" value="Forgot Password" onclick="window.location.href='fogot.jsp'">
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
        var hashedPwd = document.getElementById('hashedPwd');
        hashedPwd.value = sha256(pwd.value);
        pwd.value = '';
        document.forms[0].submit();
    }
</script>

</body>
</html>
