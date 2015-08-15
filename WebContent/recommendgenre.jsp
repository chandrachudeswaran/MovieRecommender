<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="bean.Bean" import ="java.util.ArrayList" import ="java.io.BufferedReader" import ="java.io.InputStreamReader" import ="webapi.TestWeb"%>
    
<!DOCTYPE HTML>
<html>

<head>
  <title>Movie Recommender System</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
        
          <h1>Movie Recommender System</h1>
        
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="index.html">Home</a></li>
          <li><a href="login.html">Login</a></li>
          
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
         
         
          
        </div>
      </div>
      <div id="content">
        
        <h1>Hello User!! Movies that might interest you</h1>
        <table>
        <tr>
          <th></th>
        <th> Movie </th>
        <th> Genre</th>
        </tr>
        <% Bean a = (Bean)request.getAttribute("recommendation"); %>
        <% ArrayList<String> title = a.getMovietitle(); 
        ArrayList<String> genre = a.getMoviegenre();
       ArrayList<String> urls = a.getMovieurl();
        %>
        <tr>
        <%for(int i=0; i<title.size() ;i++){ 
        
        %>
        <%-- <% Process p = Runtime.getRuntime().exec("python test.py "+ title.get(i)); 
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //String url = new Integer(in.readLine()).intValue();
       String url= new String(in.readLine().toString());
       System.out.println(url); --%>
   
<%--  <td> <img src=<%=urls.get(i) %> width=110 height=100></td>   --%>
        <td> <%=title.get(i) %></td>
        <td class="genre"> <%=genre.get(i) %>  </td>
        </tr>
        <%} %>
        </table>
   
    </div>
   
</body>
</html>
