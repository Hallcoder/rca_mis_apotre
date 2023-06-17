<%--
  Created by IntelliJ IDEA.
  User: mia
  Date: 16.05.23
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="cssfile.css" type="text/css"/>
    <link rel="stylesheet" href="css/displaytag.css" type="text/css"/>
    <link rel="stylesheet" href="css/screen.css" type="text/css"/>
    <link rel="stylesheet" href="css/site.css" type="text/css"/>
    <title>Course Registrations</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        thead {
            background-color: #008dd4;
            color: white;
            font-family: 'Quicksand', sans-serif;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="layout">
    <div id="banner">
        <div class="bannerlogo"></div>
        <div class="text_header">RCA Management Information System</div>
        <div class="right" style="text-align: right;">
            <c:if test="${authenticatedUser !=null}">
                <b> <a
                        href="listuser.php?page=profile&&id=${authenticatedUser.id}">
                    <button>Profile
                    </button>
                </a> | <img src="icons/user.png"/> <font color="#ffffff">${authenticatedUser.userRole}:
                        ${authenticatedUser.username}</font> | <a href="login.ap?page=logout"><font
                        color="#ffffff">Logout</font></a>
                </b>
            </c:if>
            <c:if test="${authenticatedUser ==null}">
                <div class="menu" align="left">
                    | <a href="login.php?">Login</a> |
                </div>
            </c:if>
        </div>
    </div>
    <div>
        <%@ include file="menu.jsp" %>
    </div>
    <div id="middle">
        <c:if test="${authenticatedUser !=null}">
            <div class="options">

                <a href="createstudent.php?page=createstudent">
                    <button>
                        <img src="icons/user_add.png"/> Create New Student
                    </button>
                </a>
            </div>
            <div class="search">
                <form action="listuser.php" method="get">
                    <table>
                        <tr>
                            <td style="color: #000000;">Enter User ID</td>
                            <td><input type="text" name="id" id="id"/></td>
                            <td><input type='submit' name="userregistrationsearch"
                                       value='search'/></td>
                        </tr>
                    </table>
                </form>
            </div>
            <hr/>


            <table style="border: 0;">

                <td align="left"><input type="text" size="15"
                                        maxlength="50" readonly="readonly" name="savedBy" id="savedBy"
                                        hidden="hidden" value="${authenticatedUser.id}"/></td>

                <table>
                    <thead>
                        <%--    <form action="createstudent.php?page=createstudent" method="post">
            <input type="text" placeholder="" required name="firstName">
            <input type="text" placeholder="" required name="lastName">
            <label>

                <input type="date" placeholder="Date of Birth" required name="dateOfBirth">
            </label>
            <input type="email" placeholder="" required name="email">
            <input type="number" placeholder="" required name="phoneNumber">
            <input type="hidden" name="isInternational" value="false"/>
            <label>

                <input type="checkbox" name="isInternational" value="true"/>
            </label>

            <input type="hidden" name="isPartTime" value="false"/>
            <label>

                <input type="checkbox" name="isPartTime" value="true"/>
            </label>

            <input type="hidden" name="isRepeating" value="false"/>
            <label>

                <input type="checkbox" name="isRepeating" value="true"/>
            </label>

            <input type="submit" value="Create Student">
        </form>--%>
                    <tr>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Date Of Birth</td>
                        <td>Email</td>
                        <td>Phone Number</td>
                        <td>International Student</td>
                        <td>Part Time</td>
                        <td>Repeating Student</td>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${students}" var="student" varStatus="studentstatus">
                        <tr>
                            <td>${student.getFirstName()}</td>
                            <td>${student.getLastName()}</td>
                            <td>${student.getDateOfBirth()}</td>
                            <td>${student.getEmail()}</td>
                            <td>${student.getPhoneNumber()}</td>
                            <td>${student.isInternational()}</td>
                            <td>${student.isPartTime()}</td>
                            <td>${student.isRepeating()}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </table>

        </c:if>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
