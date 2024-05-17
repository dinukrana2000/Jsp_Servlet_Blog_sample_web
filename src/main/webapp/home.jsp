
<%@ page import="com.example.demo.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9ff29;
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
            background-color:#b4a6a6;
        }
        .logo {
            position: fixed;
            right: 10px;
            top: 10px;
            width: 5%;
            height: 10%;
        }
        .openbtn {
            background-color: #4CAF50; /* Green */
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
         .carousel-control-prev-icon,
         .carousel-control-next-icon {
             height: 50px;
             width: 50px;
             outline: black;
             background-size: 100%, 100%;
             border-radius: 50%;
             border: 1px solid black;
             background-image: none;
         }




        .carousel-item {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Adjust this value as needed */
        }
        .card {
            width: 18rem;
            margin: auto;
        }

    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
<%@ include file="sidebar.jsp" %>
<div id="main" class="main">
    <button class="openbtn" onclick="openNav()">☰ </button>
    <div id="postCarousel" class="carousel slide" data-interval="false">
        <div class="carousel-inner">
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                int postIndex = 0;
                for (Post post : posts) {
            %>
            <div class="carousel-item <%= postIndex == 0 ? "active" : "" %>">
                <div class="card">
                    <img src="<%= post.getImageUrl() %>" class="card-img-top" alt="Post image">
                    <div class="card-body">
                        <h4 >Author:<%=post.getAuthor() %></h4>
                        <h5 class="card-title">Title:<%= post.getTitle() %></h5>
                        <p class="card-text">Description:<%= post.getDescription() %></p>
                    </div>
                </div>
            </div>
            <%
                    postIndex++;
                }
            %>
        </div>
        <a class="carousel-control-prev" href="#postCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#postCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<div >
    <img class="logo" src="image/pic.png" alt="Logo">
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
