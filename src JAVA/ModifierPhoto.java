

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifierPhoto
 */
@WebServlet("/ModifierPhoto")
public class ModifierPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String VUE="/ModifierPhoto.jsp";
		Gallerie g=Gallerie.getInstance();
		String theme=request.getParameter( "themeCatalogue" );
		request.setAttribute( "theme",theme);
		String titre=request.getParameter( "titrePhoto" );
		Photo aModifier=null;
		for(Catalogue c: g.getCatalogues())
			if(c.getTheme().equals(theme)){
				for(Photo p: c.getPhotos())
					if(p.getTitre().equals(titre)){		
						aModifier=p;
						break;
					}
				break;
			}
		request.setAttribute( "photo",aModifier);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
