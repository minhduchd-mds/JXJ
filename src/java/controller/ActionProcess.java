/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.mail.iap.ParsingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import jdk.internal.org.xml.sax.SAXException;
import model.DAO;
import model.ProcessingXML;

/**
 *
 * @author dominhduc
 */
public class ActionProcess extends HttpServlet {

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
            throws ServletException, IOException, SAXException, ParsingException, nu.xom.ParsingException {
        response.setContentType("text/html;charset=UTF-8");
       String action = request.getParameter("act");
       String error = "";
       HttpSession session = request.getSession();
       String url = getServletContext().getRealPath("/");
       if(action.equals("getdata")){
           DAO dao = new DAO();
           try{
              dao.writeXML(url);
           request.setAttribute("info", "creating file is ok !");
           session.setAttribute("wrote", "ok");
           getServletContext().getRequestDispatcher("/Product.xml").forward(request, response); 
           }catch( ParserConfigurationException |TransformerException| SQLException  ex){
                error = ex.getMessage();
           }
       }else if(action.equals("showdata")){
                ProcessingXML xulyXML = new  ProcessingXML();
               if(session.getAttribute("wrote")!= null){
                    try{
                      ArrayList aL = xulyXML.readXML(url);
                      request.setAttribute("products", aL);
                        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
               }catch(ParserConfigurationException |SAXException |ParsingException ex){
                error = ex.getMessage();
                }
            }else{
                request.setAttribute("info", "you shiuld \" get data \" first !");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else if(action.equals("finterddata") ){
               if(session.getAttribute("wrote")!=null){
                   String filter  = request.getParameter("filter");
                   try{
                       int IntFilter = Integer.parseInt(filter);
                   }catch(NumberFormatException numberFormatException){
                                       request.setAttribute("info", "you shiuld \" get data \" first !");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                   }
                    ProcessingXML xulyXML = new  ProcessingXML();
                    try{
                      ArrayList aL = xulyXML.readXML(url);
                      request.setAttribute("products", aL);
                    getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);

               }catch(ParserConfigurationException  ex){
                error = ex.getMessage();
                }
            }else{
                request.setAttribute("info", "you shiuld \" get data \" first !");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        if(!"".equals(error)){
                         request.setAttribute("error", error);
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SAXException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nu.xom.ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SAXException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (nu.xom.ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
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

}
