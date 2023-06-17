<%--
  Created by IntelliJ IDEA.
  User: mia
  Date: 13.06.23
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Welcome Guest</title>
    <style>
        body {
            display: flex;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: red;
        }

        div {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            /*margin: auto;*/
            border: solid saddlebrown 1px;
            width: 35vw;
            height: 35vw;
            border-radius: 50%;
            background-color: white;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }


        p {
            color: saddlebrown;
            text-align: center;
            margin-top: 20px;
            font-size: 18px;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin-top: 20px;
        }

        li {
            margin-bottom: 10px;
            font-size: 16px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Welcome Guest</h1>
    <p>You have successfully logged in as a guest.</p>
    <p>Thank you for visiting our school's website. Here is some information about our school:</p>
    <ul>
        <li>Name: RCA Academy</li>
        <li>Location: Kigali, Rwanda</li>
        <li>Website: <a href="https://www.rca.ac.rw" target="_blank">https://www.rca.ac.rw</a></li>
    </ul>
    <p>Feel free to explore our website to learn more about our programs, faculty, and events.</p>
    <a class="button" href="https://www.rca.ac.rw" target="_blank">Visit Website</a>
</div>
</body>
</html>
