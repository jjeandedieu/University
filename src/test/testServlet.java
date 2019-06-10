package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
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
		
//		String[] names = {"daniel", "josh", "jeff"};
//		
//	    String text = "<br>Your anme is: <br>"; //message you will recieve 
//	    String name = request.getParameter("name");
//	    PrintWriter out = response.getWriter();
//
//	    
//	    for( String i: names) {
//	    	if(i.equals(name)) {
//	    		out.print("String " + name + " already exists");
//	    	}
//	    }
		
	    String text = "<br>Message from servlet<br>"; //message you will recieve 
	    String name = request.getParameter("name");
	    PrintWriter out = response.getWriter();
	    out.println(text + name);
	}

}
