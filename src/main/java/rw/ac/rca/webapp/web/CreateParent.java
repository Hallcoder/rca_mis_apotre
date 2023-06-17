package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.ParentDAO;
import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.ParentDAOImpl;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Parent;
import rw.ac.rca.webapp.orm.Student;
import rw.ac.rca.webapp.util.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateParent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ParentDAO parentDAO = ParentDAOImpl.getInstance();
    private final StudentDAO studentDAO = StudentDAOImpl.getInstance();

    public CreateParent() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session is: " + user);

        if (pageRedirect != null) {
            System.out.println("The print statement is and the only is: " + pageRedirect);
            if (pageRedirect.equals("createparent")) {
                request.getRequestDispatcher("WEB-INF/createParent.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No user found");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "No user found");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");

        if (pageRedirect != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (pageRedirect.equals("createparent")) {
                Parent parent = null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phoneNumber");

                // Get children
                List<Student> children = new ArrayList<>();
                String[] childNames = request.getParameterValues("childName");
                for (String childName : childNames) {
                    // Assuming you have a method to search for students by name in the StudentDAO
                    Student student = studentDAO.searchStudentByName(childName);
                    if (student != null) {
                        children.add(student);
                    }
                }

                try {
                    parent = new Parent(firstName, lastName, email, new Date(), phoneNumber, children);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                try {
                    parentDAO.saveParent(parent);
                    request.setAttribute("success", "Successfully created the Parent");
                    request.getRequestDispatcher("WEB-INF/createParent.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "Failed to create the Parent");
                    request.getRequestDispatcher("WEB-INF/createParent.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}
