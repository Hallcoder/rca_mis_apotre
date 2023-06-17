package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.CourseDAO;
import rw.ac.rca.webapp.dao.MarksDAO;
import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.CourseDAOImpl;
import rw.ac.rca.webapp.dao.impl.MarksDAOImpl;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Course;
import rw.ac.rca.webapp.orm.Marks;
import rw.ac.rca.webapp.orm.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateMarks extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MarksDAO marksDAO = MarksDAOImpl.getInstance();
    private final StudentDAO studentDAO = StudentDAOImpl.getInstance();
    private final CourseDAO courseDAO = CourseDAOImpl.getInstance();


    public CreateMarks() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session is: " + user);

        if (pageRedirect != null) {
            System.out.println("The print statement is and the only is: " + pageRedirect);
            if (pageRedirect.equals("createmarks")) {
                request.getRequestDispatcher("WEB-INF/createMarks.jsp").forward(request, response);
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
            if (pageRedirect.equals("createmarks")) {
                Marks marks = null;
                String studentName = request.getParameter("studentName");
                String courseName = request.getParameter("courseName");
                Student student = studentDAO.searchStudentByName(studentName);
                Course course = courseDAO.searchCourseByName(courseName);
                try {
                    marks = new Marks(student,course,Float.parseFloat(request.getParameter("score")));
                }catch (Exception e){
                    throw new RuntimeException(e);
                }

                try {
                    marksDAO.saveMarks(marks);
                    request.setAttribute("success", "Successfully created the Marks");
                    request.getRequestDispatcher("WEB-INF/createMarks.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "Failed to create the Course");
                    request.getRequestDispatcher("WEB-INF/createMarks.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}