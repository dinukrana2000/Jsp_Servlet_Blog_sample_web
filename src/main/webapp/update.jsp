<%@ page import="com.example.demo.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update</title>
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
            height: 100vh;
        }
        .card {
            width: 18rem;
            margin: auto;
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


</head>
<body>
<%@ include file="sidebar.jsp" %>

<div id="main" class="main">
    <button class="openbtn" onclick="openNav()">☰ </button>
        <%
        String message = (String) session.getAttribute("message");
        String messageType = (String) session.getAttribute("messageType");
        if (message != null) {
            session.removeAttribute("message");
            session.removeAttribute("messageType");
    %>
    <div class="alert alert-<%= messageType %>" role="alert">
        <%= message %>
    </div>
        <%
        }
    %>

        <%
        String error = (String) session.getAttribute("error");
        if (error != null) {
            session.removeAttribute("error");
    %>
    <div class="alert alert-danger" role="alert">
        <%= error %>
    </div>
        <%
        }
    %>
    <div id="postCarousel" class="carousel slide" data-interval="false">
        <div class="carousel-inner">
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                int postIndex = 0;
                for (Post post : posts) {
            %>
            <div class="carousel-item <%= postIndex == 0 ? "active" : "" %>" id="post-<%= post.getId() %>">
                <div class="card" style="width: 18rem;">
                    <img src="<%= post.getImageUrl() %>" class="card-img-top" alt="Post image">
                    <div class="card-body">
                        <h5 class="card-title"><%= post.getTitle() %></h5>
                        <p class="card-text"><%= post.getDescription() %></p>

                        <button onclick="fillForm('<%= post.getId() %>', '<%= post.getTitle() %>', '<%= post.getDescription() %>', '<%= post.getImageUrl() %>')">Edit</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" onclick="setDeletePostId('<%= post.getId() %>')">Delete</button>
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
    </div><div>
    <form id="updateForm" method="post" enctype="multipart/form-data">

        <input type="hidden" id="postId" name="postId">

        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title" required > <br>
        <label for="description">Description:</label><br>
        <textarea id="description" name="description" required ></textarea><br>
        <label for="image">Image:</label><br>
        <img id="currentImage" src="" alt="Current image" style="width: 100%; height: auto;"><br>
        <input type="file" id="image" name="image" ><br><br>
        <input type="submit" value="Update">
        <input type="reset" value="Clear">
    </form>
</div>
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete this post?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" onclick="deletePost()">Delete</button>
                </div>
            </div>
        </div>
    </div>
    <div >
        <img class="logo" src="image/pic.png" alt="Logo">
    </div>
    <div id="message" style="display: none;"></div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        function fillForm(id, title, description, imageUrl) {
            document.getElementById('postId').value = id;
            document.getElementById('title').value = title;
            document.getElementById('description').value = description;
            document.getElementById('currentImage').src = imageUrl;
            document.getElementById('updateForm').scrollIntoView();

        }

    </script>
    <script>
        $(document).ready(function() {
            $('#updateForm').submit(function(e) {
                e.preventDefault();

                var fileInput = $('#image');
                var file = fileInput[0].files[0];
                var fileName = file ? file.name : null;
                var fileExtension = fileName ? fileName.split('.').pop().toLowerCase() : null;

                if (fileName && !(['jpg', 'jpeg', 'png'].includes(fileExtension))) {
                    showMessage('danger', 'File must be a .jpg, .jpeg, or .png file');
                    return;
                }
                var formData = new FormData(this);
                formData.append('image', file);
                $.ajax({
                    url: 'UpdatePostServlet',
                    type: 'POST',
                    data: formData,
                    success: function(response) {
                        console.log('Response from servlet:', response);
                        if (response.trim() === 'success') {
                            $('html, body').animate({ scrollTop: 0 }, 'fast');
                            showMessage('success', 'Reposted successfully');
                        } else {
                            showMessage('danger', response);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                        showMessage('danger', 'An error occurred');
                    },
                    cache: false,
                    contentType: false,
                    processData: false
                });
            });

            function showMessage(type, message) {
                $('#message').removeClass().addClass('alert alert-' + type).text(message).fadeIn('fast');
                setTimeout(function() {
                    $('#message').fadeOut('fast');
                }, 5000);
            }
        });

    </script>
    <script>
        function setDeletePostId(postId) {
            document.getElementById('postId').value = postId;
        }

        function deletePost() {
            var postId = document.getElementById('postId').value;

                $.ajax({
                    url: 'DeletePostServlet',
                    type: 'POST',
                    data: { postId: postId },
                    success: function(response) {
                        if (response.trim() === 'success') {
                            $('html, body').animate({ scrollTop: 0 }, 'fast');
                            showMessage('success', 'Deleted successfully');
                            $('#post-' + postId).remove();
                        } else {
                            showMessage('danger', response);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                        showMessage('danger', 'An error occurred');
                    }
                });

        }
        function showMessage(type, message) {
            $('#message').removeClass().addClass('alert alert-' + type).text(message).fadeIn('fast');
            setTimeout(function() {
                $('#message').fadeOut('fast');
            }, 5000);
        }
    </script>

</body>
</html>
