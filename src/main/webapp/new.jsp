<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>New</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;

        }

        .sidebar a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 25px;
            color: #f1f1f1;
            display: block;
            transition: 0.3s;
        }
        .sidebar a:hover {
            color: #818181;
            background-color: #f1f1f1;
        }
        .main {
            transition: margin-left .5s;
            padding: 16px;
        }
        .logo {
            position: fixed;
            right: 10px;
            top: 10px;
            width: 5%;
            height: 10%;
        }
        .openbtn {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 12px;
            transition-duration: 0.4s;
        }

        .openbtn:hover {
            background-color: white;
            color: black;
        }


        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #030000;
            border-radius: 10px;
            background-color: #f8f8f8;
        }
        form input[type="text"], form textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }
        form input[type="submit"],
        form input[type="reset"]{
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        form input[type="submit"]:hover,
        form input[type="reset"]:hover{
            background-color: #45a049;
        }
    </style>
</head>
<body style="background-color: #b4a6a6">
<%@ include file="sidebar.jsp" %>
<div id="main" class="main" >
    <button class="openbtn" onclick="openNav()">☰ </button>
    <form action="CreatePostServlet" method="post" enctype="multipart/form-data">
        <% if (request.getAttribute("alertMessage") != null) { %>
        <div class="alert alert-<%= request.getAttribute("alertType") %> alert-dismissible fade show" role="alert">
            <%= request.getAttribute("alertMessage") %>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <% } %>
            <label for="title">Title:</label><br>
            <input type="text" id="title" name="title" > <br>
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" required ></textarea><br>
            <label for="image">Image:</label><br>
            <input type="file" id="image" name="image" required><br><br>
            <input type="submit" value="Post">
            <input type="reset" value="Clear">
    </form>
</div>
<div >
    <img class="logo" src="image/pic.png" alt="Logo">
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>