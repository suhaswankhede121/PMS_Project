<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Officer Login</title>
    <style>
       body {
            font-family: Arial, sans-serif;
            background-color: rgb(26, 126, 112);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 50px rgba(192, 106, 106, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid wheat;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: green;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Officer Login</h2>
        <form action="OfficerLoginServlet" method="post">
            <label>User ID:</label>
            <input type="text" name="username" minlength="5" maxlength="20" required>
            
            <label>Password:</label>
            <input type="password" name="password" minlength="6" maxlength="30"
                pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{6,30}" 
                title="Password must have at least one uppercase letter, one lowercase letter, and one special character."
                required>
            
            <input type="submit" value="Login">
        </form>
        <% if(request.getParameter("error") != null) { %>
            <p style="color: red;">Invalid Username or Password</p>
        <% } %>
    </div>
</body>
</html>
