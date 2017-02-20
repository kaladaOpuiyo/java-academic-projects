/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author jim
 */
public class Authenticate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Users USER = Users.newInstance();
    public static int countLoginAttemnpt = 0;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Authenticate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authenticate at " + request.getContextPath() + "</h1>");
            out.println("&lt;h1&gt;Results are &quot; + username + &quot;,&quot; + isValid + &quot;&lt;/h1&gt;");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session;
        UUID sessionID = UUID.randomUUID();

        try {

            // Get the post input
            Boolean isValid = validate(request);
            response.setContentType("text/html;charset=UTF-8");

            if (isValid) {

                // Create a session object if it is already not  created.
                session = request.getSession(true);
                session.setAttribute("UMUCUserEmail", request.getParameter("emailAddress"));
                session.setAttribute("UMUCUserID", String.valueOf(USER.getUser_id()));
                session.setAttribute("sessionId", String.valueOf(sessionID));
                response.sendRedirect("Authenticate?failed=false");

            } else {

                countLoginAttemnpt++;
                response.sendRedirect("Authenticate?failed=true");

            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Authenticate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        Boolean loginSucess = session.getAttribute("sessionId") != null
                && request.getParameter("failed").equals("false");
        Boolean loginFailed = request.getParameter("failed").equals("true");
        Boolean maxLoginAttempts = countLoginAttemnpt == 6;

        int display = loginSucess ? 1 : loginFailed && !maxLoginAttempts ? 2
                : loginFailed && maxLoginAttempts ? 3 : 0;

        switch (display) {

            case 1:
                dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.forward(request, response);
                countLoginAttemnpt = 0;
                break;

            case 2:
                session.setAttribute("attempts", countLoginAttemnpt);
                request.setAttribute("ErrorMessage", "Invalid Username or Password. Try again or contact Jim.");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                break;

            case 3:
                session.setAttribute("attempts", countLoginAttemnpt);
                request.setAttribute("ErrorMessage", "30 Second Login Suspension");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
               
                break;

            default:
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                countLoginAttemnpt = 0;
                break;
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // Method to Authenticate
    public boolean validate(HttpServletRequest request) throws SQLException {

        boolean status = false;
        int hitcnt = 0;
        ResultSet rs_1 = null;

        try {
            USER.setUser_id(0);

            rs_1
                    = ConnectToDb.newInstance().newRequest(ConnectToDb.QUERYREQUEST_2,
                            String.valueOf(request.getParameter("emailAddress")));

            while (rs_1.next()) {

                USER.setUser_id(rs_1.getInt(1));
            }

            if (USER.getUser_id() > 0) {

                ResultSet rs_2
                        = ConnectToDb.newInstance().newRequest(ConnectToDb.QUERYREQUEST_3,
                                String.valueOf(USER.getUser_id()),
                                String.valueOf(request.getParameter("pfield")));

                while (rs_2.next()) {
                    hitcnt++;
                }
                // Set to true if userid/password match
                if (hitcnt > 0) {
                    status = true;
                    rs_2.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {

            if (rs_1 != null) {
                rs_1.close();
            }

        }
        return status;
    }
}
