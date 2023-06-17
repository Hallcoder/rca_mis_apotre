package rw.ac.rca.webapp.web;


import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO = StudentDAOImpl.getInstance();

    public CreateStudent() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session is: " + user);

        if (pageRedirect != null) {
            System.out.println("The print statement is and the only is: " + pageRedirect);
            if (pageRedirect.equals("createstudent")) {
                request.getRequestDispatcher("WEB-INF/createStudent.jsp").forward(request, response);
            } else {
                request.setAttribute("error ", "No user found");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error ", "No user found");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");

        if (pageRedirect != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (pageRedirect.equals("createstudent")) {
                Student student = null;
                try {
                    boolean isInternational = request.getParameter("isInternational") != null && Boolean.parseBoolean(request.getParameter("isInternational"));
                    boolean isPartTime = request.getParameter("isPartTime") != null && Boolean.parseBoolean(request.getParameter("isPartTime"));
                    boolean isRepeating = request.getParameter("isRepeating") != null && Boolean.parseBoolean(request.getParameter("isRepeating"));

                    student = new Student(request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("email"),simpleDateFormat.parse(request.getParameter("dateOfBirth")),request.getParameter("phoneNumber"),isInternational,isPartTime,isRepeating);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                try {
                    studentDAO.saveStudent(student);
                    request.setAttribute("success", "Successfully created the Course");
                    request.getRequestDispatcher("WEB-INF/createStudent.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "Failed to create the Course");
                    request.getRequestDispatcher("WEB-INF/createStudent.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}