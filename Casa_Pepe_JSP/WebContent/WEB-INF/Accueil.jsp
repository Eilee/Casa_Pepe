<jsp:useBean id="Manager" class="manager.Manager" scope="session"/>
<%@include file="Header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a class="active" href="Accueil.jsp">Accueil</a></li>
		  	<li><a href="Menus.jsp">Menus</a></li>
		  	<li><a href="GestionPlats.jsp">Gestion des plats</a></li>
		  	<li><a href="GestionGroupes.jsp">Gestion des groupes</a></li>
		  	<li style="float:right;"><a href="Connexion.jsp">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12 accueilImg">
		<img src="img/restaurant.jpg"/>
		<br/>
		<p style="text-align:center">Découvrez la carte de votre restaurant gastronomique italien Casa Pepe et ses spécialités d'Italie en commençant par les antipasti, puis hésitez entre une pasta ou une pizza, à moins que vous ne préfériez une délicieuse recette de la cucina. Et enfin laissez-vous tenter par une pâtisserie ou une fameuse glace italienne. Notre carte est généreuse pour que tous les gourmands puissent se régaler. </p>
	</div>
</div>
<%@include file="Footer.jsp" %>