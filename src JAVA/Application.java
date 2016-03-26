

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Servlet implementation class Application
 */
@WebServlet("/Application")

//@WebService(name = "Application")

public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Stockage du formulaire et du bean dans l'objet request */
//      request.setAttribute( "message", "creation avec succes");
		String choix = request.getParameter( "nom" );
		request.setAttribute( "toto", choix );
		Gallerie gallerie=new Gallerie();
		gallerie.majGallerie();
		request.setAttribute( "gallerie", gallerie);
		
		this.getServletContext().getRequestDispatcher( "/AffichagePhoto.jsp" ).forward( request, response );
	}	
	
	
	
	//@WebMethod
    
	
}