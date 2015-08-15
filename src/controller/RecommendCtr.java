package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecoGenre;
import model.RecoMovies;
import bean.Bean;
import org.python.util.PythonInterpreter;

public class RecommendCtr extends HttpServlet{
	Bean a = new Bean();
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type= request.getParameter("recommend");
		String username=request.getParameter("username");
	
		if(type.equals("Genre")){
			RecoGenre r = new RecoGenre();
			a= r.doRecoGenre(username);
			
			request.setAttribute("recommendation", a);
			request.getRequestDispatcher("recommendgenre.jsp").forward(request, response);
			
			
		}
		
		else{
			RecoMovies r1 = new RecoMovies();
			int user=Integer.parseInt(username);
			a = r1.doRecoMovies(user);
			request.setAttribute("recommendation", a);
			request.getRequestDispatcher("recommendrating.jsp").forward(request, response);
		}
		
	}
	
	
}
