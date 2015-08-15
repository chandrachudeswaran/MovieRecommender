<!DOCTYPE HTML>
<html>

<head>
  <title>Movie Recommender - Login</title>
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
          <li><a href="index.html">Home</a></li>
          
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <div id="sidebar_container">
        
          
        </div>
        <div class="sidebar">
          
          <div class="sidebar_item">
            
            
          </div>
          
        </div>
        <div class="sidebar">
          
         
          
        </div>
      </div>
      <div id="content">
        <!-- insert the page content here -->
        <h1> Welcome User !!</h1>
        <form action="/MovieRecommender/recommend" method="post">
          <div class="form_settings">
             Recommend movies using:
             <br>
             <br>
             <br>
            <input type="radio" name="recommend" value="Rating" >Rating
            <br>
            <input type="radio" name="recommend" value="Genre" >Genre
       
            <input type="hidden" name="username" value="<%= request.getAttribute("username") %>">
            
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted" value="Recommend" /></p>
          </div>
        </form>
       
      </div>
    </div>
    
  </div>
</body>
</html>
