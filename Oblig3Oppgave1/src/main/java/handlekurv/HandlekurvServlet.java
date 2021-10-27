package handlekurv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/handlekurv")
public class HandlekurvServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

Vare handlekurv = new Vare();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		List<String> vareListe = handlekurv.getItems();
		
		if(!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect("innlogging");
		}
		
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
	    
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title> Handleliste </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Min handlekurv</h1>");
		
		out.println("<form method=\"post\" >");
		out.println("<p>Legg til et produkt: </p>");
		out.println("<input type=\"text\" name=\"vare\" >");		
		out.println("<input type=\"submit\" name=\"item\" value=\"leggtil\" >");		
		out.println("<input type=\"hidden\" name=\"item\" value=\"add\" >");		
		out.println("</form>");
		
		out.println("<form method=\"post\" >");
		out.println("<input type=\"hidden\" name=\"item\" value=\"slett\" >");
		
		for(String varer : vareListe) {
			out.println("<form action=\"" + "handlekurv" + "\" method=\"post\">");
        	out.println("<input type=\"submit\" value=\"slett\" >"); 
        	out.println("<input type=\"hidden\" id=\"itemid\" name=\"handling\" value=" + "Slett" +">");
        	out.println("<input type=\"hidden\" id=\"itemid\" name=\"vare\" value=" + varer +">");
        	out.println(varer + "<br><br>");
        	out.println("</form>");
		}		
				
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String vare;
		vare = request.getParameter("vare");
		
		if(!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect("innlogging");
		}
		
		switch(request.getParameter("item")) {
		
		case "leggtil":
			handlekurv.addItem(vare);
			break;
			
		case "slett":
			handlekurv.deleteItem(vare);
			break;
		}
		response.sendRedirect("handlekurv");
	}

}
