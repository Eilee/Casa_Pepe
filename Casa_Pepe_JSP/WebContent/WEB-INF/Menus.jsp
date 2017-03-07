<%@include file="Header.jsp" %>	 
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a class="active" href="Menus">Menus</a></li>
		  	<li><a href="GestionPlats">Plats</a></li>
		  	<li><a href="GestionGroupes">Groupes</a></li>
		  	<li style="float:right;"><a href="Connexion">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>

<div class="row">
	<div class="col-md-12 tableImg tableMenus">
		<c:forEach items="${listMenus}" var="listMenu">
			<table>
				<caption><h2>${listMenu.getNom()}</h2></caption>
				<c:forEach items="${listMenu}" var="listPlat">
			        <tr>
			            <td>
			            	<h4>${listPlat.getNom()}</h4>
							<img src="img/la-burrata.jpg"/>
							<p>${listPlat.getDescription()}</p>
						</td>
			            <td>
			            	<h4>${listPlat.getNom()}</h4>
							<img src="img/la-burrata.jpg"/>
							<p>${listPlat.getDescription()}</p>
						</td>
			        </tr>
	    		</c:forEach>
		 	</table>
	    </c:forEach>
	</div>
</div>
<%@include file="Footer.jsp" %>