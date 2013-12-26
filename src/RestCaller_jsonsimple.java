/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package support.ids.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;

/**
 *
 * @author U8004042
 */
@WebServlet(name = "RestCaller_jsonsimple", urlPatterns = {"/RestCaller_jsonsimple"})
public class RestCaller_jsonsimple extends HttpServlet {
    
    String webURL = "http://localhost:8080/rest/json/";

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
        PrintWriter out = response.getWriter();
        
        
        
        JSONObject request_msg = new JSONObject();
        request_msg.put("service","quote");
        request_msg.put("user","plynoi");
        JSONArray itemArray = new JSONArray();
        itemArray.add("Nokia");
        JSONArray filterArray = new JSONArray();
        filterArray.add("BID");
        filterArray.add("ASK");
        request_msg.put("symbols",itemArray);
        request_msg.put("fields",filterArray);
       

        try {
            PrintStream outt = new PrintStream(System.out, true, "UTF-8");

            HttpURLConnection urlConn = null;
            URL url = new URL(webURL);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setAllowUserInteraction(false);
            urlConn.addRequestProperty("Accept", "application/json,text/javascript, */*; q=0.01");
            urlConn.addRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
            urlConn.addRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            urlConn.addRequestProperty("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.8,en;q=0.4");
            urlConn.addRequestProperty("Content-Type", "application/json");
            urlConn.addRequestProperty("Connection", "keep-alive");
            // envoyer des params
            urlConn.setDoOutput(true);

            System.out.println("Sending request to " + webURL);

            // poster les params
            PrintWriter paramWriter = new PrintWriter(urlConn.getOutputStream());

            paramWriter.print(request_msg.toString());
            // fermer le post avant de lire le resultat ... logique
            paramWriter.flush();
            paramWriter.close();
            
            //Me
            // -- me -- OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
            // -- me -- wr.write(request_msg.toString());
            // -- me -- wr.flush();
            
            // -- me -- BufferedReader bufReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            
            // Lire la reponse
            InputStream resp = urlConn.getInputStream();
            
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(resp, "UTF-8"));
            String sLine;
            StringBuilder sb = new StringBuilder();
            String lineSep = "###";
            
            

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<head>");
            out.println("<title>Servlet RestCaller Simple</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestCaller at " + request.getContextPath() + "</h1>");
            while ((sLine = bufReader.readLine()) != null) {
                sb.append(sLine);
                sb.append(lineSep);
            }
            out.println(sb.toString());
            out.println("</body>");
            out.println("</html>");
            // deconnection
            outt.println(tmpString);
            urlConn.disconnect();
            bufReader.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            e.printStackTrace();
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

}
