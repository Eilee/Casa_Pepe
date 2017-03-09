<%@include file="Header.jsp" %>	 
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<% if(Manager.getIsConnect()){ %>
			  	<li><a href="GestionPlats">Plats</a></li>
			  	<li><a href="GestionGroupes">Groupes</a></li>
		  	<li style="float:right;"><a href="Deconnexion">Deconnexion</a></li>
		  	<%}else{%>
		  		<li style="float:right;"><a href="Connexion">Connexion</a></li>
		  	<%} %>
		</ul>
		<br/>
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<iframe src="http://localhost:8080/Casa_Pepe_JSP/pdfFile.pdf"  width="900" height="1300" align="center"/>
	</div>
</div>			
<%@include file="Footer.jsp" %>