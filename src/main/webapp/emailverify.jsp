<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Verification</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-size: cover;
            background-position: center;
            background-image: linear-gradient(to bottom right, #8d8484, #ee5f00);
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
        h1{
            margin-left: 20%;
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
    </style>

</head>
<body>
<header>
    <img class="logo" src="image/pic.png" alt="Logo">
    <span class="header-text">Blogger</span>
</header>

<div class="container">
    <h1>Email Verification</h1>
    <% if (request.getAttribute("alertMessage") != null) { %>
    <div class="alert alert-<%= request.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
        <%= request.getAttribute("alertMessage") %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <% } %>
    <form action="Emailverifyservlet" method="post">
        <div class="form-group">
            <label for="otp">Enter OTP:</label>
            <input type="number" id="otp" name="otp" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Verify OTP">
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
