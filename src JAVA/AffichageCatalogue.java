

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageCatalogue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String choix = request.getParameter( "themeCatalogue" );
		System.out.println(choix);
		Gallerie g=new Gallerie();
		g.majGallerie();
		for(Catalogue catalogue : g.getCatalogues())
			if(catalogue.getTheme().equals(choix)){
				request.setAttribute( "catalogue", catalogue);
				break;
			}
		
		
		this.getServletContext().getRequestDispatcher( "/AffichageCatalogue.jsp" ).forward( request, response );

	}

}
