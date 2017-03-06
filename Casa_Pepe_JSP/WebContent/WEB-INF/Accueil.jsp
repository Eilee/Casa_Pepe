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
		<p style="text-align:center">D�couvrez la carte de votre restaurant gastronomique italien Casa Pepe et ses sp�cialit�s d'Italie en commen�ant par les antipasti, puis h�sitez entre une pasta ou une pizza, � moins que vous ne pr�f�riez une d�licieuse recette de la cucina. Et enfin laissez-vous tenter par une p�tisserie ou une fameuse glace italienne. Notre carte est g�n�reuse pour que tous les gourmands puissent se r�galer. </p>
	</div>
</div>
<%@include file="Footer.jsp" %>