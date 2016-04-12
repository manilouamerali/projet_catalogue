

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AffichagePhoto
 */
@WebServlet("/AffichagePhoto")
public class AffichagePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichagePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String VUE="/AffichagePhoto.jsp";
		String theme=request.getParameter( "themeCatalogue" );
		String titre=request.getParameter( "titrePhoto" );
		Photo aAfficher=null;
		Gallerie g=Gallerie.getInstance();
		request.setAttribute( "catalogue",theme);
		
		for(Catalogue c: g.getCatalogues())
			if(c.getTheme().equals(theme)){
				for(Photo p: c.getPhotos())
					if(p.getTitre().equals(titre)){		
						aAfficher=p;
						break;
					}
				break;
			}
		request.setAttribute( "photo",aAfficher);	
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
}
