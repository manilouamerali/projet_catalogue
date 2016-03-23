import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddPhoto
 */ 
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
    	/* Stockage du formulaire et du bean dans l'objet request */
//        request.setAttribute( "message", "creation avec succes");

        Gallerie gallerie=new Gallerie();
        gallerie.majGallerie();
        request.setAttribute( "gallerie", gallerie);
    
        this.getServletContext().getRequestDispatcher( "/AffichagePhoto.jsp" ).forward( request, response );
    }
        
}