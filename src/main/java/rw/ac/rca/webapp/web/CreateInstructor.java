package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.InstructorDAO;
import rw.ac.rca.webapp.dao.impl.InstructorDAOImpl;
import rw.ac.rca.webapp.orm.Instructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateInstructor extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final InstructorDAO instructorDAO = InstructorDAOImpl.getInstance();

    public CreateInstructor() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session is: " + user);

        if (pageRedirect != null) {
            System.out.println("The print statement is and the only is: " + pageRedirect);
            if (pageRedirect.equals("createinstructor")) {
                request.getRequestDispatcher("WEB-INF/createInstructor.jsp").forward(request, response);
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
            if (pageRedirect.equals("createinstructor")) {
                Instructor instructor = null;
                try {
                    instructor = new Instructor(request.getParameter("firstName"), request.getParameter("lastName"),request.getParameter("email"), simpleDateFormat.parse(request.getParameter("dateOfBirth")), request.getParameter("phoneNumber"), Double.parseDouble(request.getParameter("salary")), simpleDateFormat.parse(request.getParameter("remunerationDate")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                try {
                    instructorDAO.saveInstructor(instructor);
                    request.setAttribute("success", "Successfully created the Instructor");
                    request.getRequestDispatcher("WEB-INF/createInstructor.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "Failed to create the Instructor");
                    request.getRequestDispatcher("WEB-INF/createInstructor.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}