
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.tomcat.util.http.fileupload.FileUpload;

import java.util.List;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.util.*;
import java.io.*;

/**
 * Servlet implementation class UploadPhoto
 */
@WebServlet("/UploadPhoto")
public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VUE="/AffichageCatalogue.jsp" ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gallerie gallerie = Gallerie.getInstance();
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List items;
		try {
			String theme=null;
			items = upload.parseRequest(request);
			Photo nvelleP=new Photo();
			//vérifier si la personne n'existe pas déjà
			Personne nvellePers=new Personne();
			// Process the uploaded items
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					
					switch (item.getFieldName()){
					case "themeCatalogue":theme=item.getString();break;
					case "datePrise":nvelleP.setDatePrise(item.getString());break;
					case "lieu":nvelleP.setLieu(item.getString());break;
					case "nomAuteur":nvellePers.setNomP(item.getString());break;
					case "prenomAuteur":nvellePers.setPrenomP(item.getString());break;
					case "emailAuteur":nvellePers.setEmail(item.getString());break;
					case "titre":nvelleP.setTitre(item.getString());break;
					case "commentaire":nvelleP.setCommentaire(item.getString());break;
					case "dimension":nvelleP.setDimension(item.getString());break;
					case "categorie":nvelleP.setCategorie(item.getString());break;
					}
					
				} else {

					String path = this.getClass().getResource("/").getPath();
					path=path.substring(0, path.lastIndexOf("/.metadata"));
					path +="/projet_catalogue/WebContent/WEB-INF/images/";
					File fullFile = new File(item.getName());
					path=path+theme+"/";
					nvelleP.setImg(path+fullFile.getName());
					File savedFile = new File(path, fullFile.getName());
					savedFile.getParentFile().mkdirs();
					if(!savedFile.exists()) savedFile.createNewFile();
					item.write(savedFile);
				}
			}
			gallerie.ajouterPhoto(theme,nvelleP);
			Catalogue res=null;
			for(Catalogue c: gallerie.getCatalogues())	
				if(c.getTheme().equals(theme)){
					res=c;
					break;
				}
			request.setAttribute( "catalogue", res);
			
			
			this.getServletContext().getRequestDispatcher( VUE).forward( request, response );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
