<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="bean.Bean" import ="java.util.ArrayList" import ="java.io.BufferedReader" import ="java.io.InputStreamReader" import ="java.util.HashMap" import ="java.util.Map"%>
    
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
          <!-- class="logo_colour", allows you to change the colour of the text -->
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
        <!-- insert the page content here -->
        <h1>Hello User!! Movies that might interest you</h1>
        <table>
        <tr>
        
        <th> Movie </th>
        <th> Rating</th>
        </tr>
        <% Bean a = (Bean)request.getAttribute("recommendation"); %>
        <% HashMap<String,Double> title = a.getReco(); 
        		  HashMap<String,Double> title1 = new HashMap<String,Double>();
        int count=0;
        for(Map.Entry<String, Double> inputmap : title.entrySet()){ 
        	if(count<10){
        	count++;
        	title1.put(inputmap.getKey(),inputmap.getValue());
        	}
        	
        }
        %>
        <tr>
       <%  
       
       for(Map.Entry<String, Double> inputmap1 : title1.entrySet()){ 
    	   
       %>
       
        
   

        <td> <%=inputmap1.getKey() %></td>
        <td class="genre"> <%=Math.ceil(inputmap1.getValue()) %>  </td>
        </tr>
        <%} %>
        </table>
        <!--  <p>This standards compliant, simple, fixed width website template is released as an 'open source' design (under a <a href="http://creativecommons.org/licenses/by/3.0">Creative Commons Attribution 3.0 Licence</a>), which means that you are free to download and use it for anything you want (including modifying and amending it). All I ask is that you leave the 'design from HTML5webtemplates.co.uk' link in the footer of the template, but other than that...</p>
        <p>This template is written entirely in <strong>HTML5</strong> and <strong>CSS</strong>, and can be validated using the links in the footer.</p>
        <p>You can view more free HTML5 web templates <a href="http://www.html5webtemplates.co.uk">here</a>.</p>
        <p>This template is a fully functional 5 page website, with an <a href="examples.html">examples</a> page that gives examples of all the styles available with this design.</p>
        <h2>Browser Compatibility</h2>
        <p>This template has been tested in the following browsers:</p>
        <ul>
          <li>Internet Explorer 8</li>
          <li>Internet Explorer 7</li>
          <li>FireFox 3.5</li>
          <li>Google Chrome 6</li>
          <li>Safari 4</li>
        </ul>
      </div>
      -->
    </div>
   
</body>
</html>
