

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AffichageCatalogue
 */
@WebServlet("/AffichageCatalogue")
public class AffichageCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VUE="/AffichageCatalogue.jsp" ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageCatalogue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String theme = request.getParameter( "themeCatalogue" );
		//System.out.println(request.getParameter( "themeCatalogue" ));
		Gallerie g=Gallerie.getInstance();
//		g.majGallerie();
		
		for(Catalogue c: g.getCatalogues())	
			if(c.getTheme().equals(theme)){
				request.setAttribute( "catalogue", c);
				break;
			}
		
		this.getServletContext().getRequestDispatcher( VUE).forward( request, response );

	}

}
