package handlekurv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Innlogging", urlPatterns = "/innlogging")
public class InnloggingServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html; charset=ISO-8859-1");
		
		String feilmelding="";
		String feilkode = request.getParameter("feilkode");
		if(feilkode != null && feilkode.equals("invalidPassword")) {
			feilmelding = "Ugyldig Passord. Prøv igjen";
		}
		
		PrintWriter out = response.getWriter();
	    
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title> Logginn </title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println(feilmelding);
		
		out.println("<form action=\"innlogging\" method=\"post\">");
		out.println("<p>Passord: </p>");
		out.println("<input type=\"password\" name=\"passord\"/>");		
		out.println("<input type=\"submit\" value=\"Logg Inn\"/>");		
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String passord = request.getParameter("passord");
		String korrektPassord = getServletConfig().getInitParameter("passord");
		int tidISek = 100;
		
		if(InnloggingUtil.isGyldigPassord(passord, korrektPassord)) {
			InnloggingUtil.loggInnMedTimeout(request, tidISek);
			response.sendRedirect("handlekurv");
		}
		else {
		response.sendRedirect("innlogging?error=wrongpassword");	
		}
	}

}
