
package controllers;



import Utiles.WebUtiles;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Acceso", urlPatterns = {"/acceso"})
public class Acceso extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        /**
         * Tenemos que mirar si en la petición actual viene una cookie
         * que se llama "recuerdame". Si viene, entonces tendremos que
         * enseñarse el nombre de usuario en la pantalla de Login,
         * para que así el usuario no tenga que escribir su nombre.
         */
        WebUtiles utiles = new WebUtiles();
        boolean recuerdame = utiles.isCookieInRequest(request, "recuerdame");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Acceso</title>"); 
            out.println("<style>");
            out.println("body { width: 50%; margin: 20% auto;\n" +
"                background-color: orange;\n" +
"                color: #eee;\n" +
"                font-family: sans-serif;\n" +
"            }");
            out.println("legend { border: 1px solid #eee; background: #000; padding: 0.25em 0.5em}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form action='operar'>");            
            out.println("<fieldset>");      
            out.println("<legend>Introducir credenciales</legend>");
            if (recuerdame) {
                Cookie cookie = utiles.getCookie(request, "recuerdame");
                String nombreUsuario = cookie.getValue();
                out.println("<p><label>Usuario <input name='usuario' value='"+
                        nombreUsuario+"'></label></p>");
            } else
                out.println("<p><label>Usuario <input name='usuario'></label></p>");
            
            out.println("<p><label>Password <input type='password' name='password'></label></p>");
            
            if (recuerdame) 
                out.println("<p><input checked type='checkbox' name='rememberme' value='rememberme'>Recordarme</p>");
            else
                out.println("<p><input type='checkbox' name='rememberme' value='rememberme'>Recordarme</p>");
            
            out.println("<input type='submit' value='Acceder'>");
            out.println("</fieldset>");
            out.println("</form>");
            
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
