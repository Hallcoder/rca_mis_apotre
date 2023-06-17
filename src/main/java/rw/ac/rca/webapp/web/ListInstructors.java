package rw.ac.rca.webapp.web;


import rw.ac.rca.webapp.dao.InstructorDAO;
import rw.ac.rca.webapp.dao.impl.InstructorDAOImpl;
import rw.ac.rca.webapp.orm.Instructor;
import rw.ac.rca.webapp.util.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListInstructors extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final InstructorDAO instructorDAO = InstructorDAOImpl.getInstance();

    public ListInstructors() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");

        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session is: " + user);

        if (pageRedirect != null) {
            if (pageRedirect.equals("instructors") && request.getParameter("action").equals("list")) {
                List<Instructor> instructors = instructorDAO.getAllInstructors();
                httpSession.setAttribute("instructors", instructors);
                UserRole[] userRoles = UserRole.values();
                httpSession.setAttribute("userRoles", userRoles);
                request.getRequestDispatcher("WEB-INF/listInstructors.jsp").forward(request , response);
            }
        } else {
            httpSession.setAttribute("error", "Invalid User. Try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}