/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author jim
 */
public class ShowAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Users USER = Users.newInstance();

    // Database field data
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("X-XSS-Protection", "block");

        HttpSession session = request.getSession(true);

        if (session.getAttribute("UMUCUserEmail") == null) {
            // Send back to login page 
            response.sendRedirect("login.jsp");
        } else {
            // Connect to the Database and pull the data
            getData(request, session);

            // Set the Attribute for viewing in the JSP
            request.setAttribute("Cardholdername", USER.getCardholdername());
            request.setAttribute("CardType", USER.getCardType());
            request.setAttribute("ServiceCode", USER.getServiceCode());
            request.setAttribute("CardNumber", USER.getCardNumber());
            request.setAttribute("CAV_CCV2", USER.getCav_ccv2());
            request.setAttribute("expiredate", USER.getExpiredate());
            request.setAttribute("FullTrackData", USER.getFullTrackData());
            request.setAttribute("PIN", USER.getPin());

            RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
            dispatcher.forward(request, response);

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
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

    public void getData(HttpServletRequest request, HttpSession session) {

        try {

            ResultSet rs
                    = ConnectToDb.newInstance().newRequest(ConnectToDb.QUERYREQUEST_1,
                            String.valueOf(session.getAttribute("UMUCUserID")));

            // Assign values
            while (rs.next()) {

                USER.setUser_id(rs.getInt(1));
                USER.setCardholdername(rs.getString(2));
                USER.setCardType(rs.getString(3));
                USER.setServiceCode(rs.getString(4));
                USER.setCardNumber(rs.getString(5));
                USER.setCav_ccv2(rs.getInt(6));
                USER.setExpiredate(rs.getDate(7));
                USER.setFullTrackData(rs.getString(8));
                USER.setPin(rs.getString(9));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
