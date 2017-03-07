<script>
function afficher(){
	alert("Vous venez de cliquer sur le bouton!")
}
</script>
<%@include file="Header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<li><a class="active" href="GestionPlats">Gestion des plats</a></li>
		  	<li><a href="GestionGroupes">Gestion des groupes</a></li>
		  	<li style="float:right;"><a href="Connexion">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12 tableGestion tableIcone">
		<table>
			<tr>
				<td style="text-align:center"><b>Nom du plat</b></td>
				<td style="text-align:center"><b>Description du plat</b></td>
				<td style="text-align:center"><b>Type</b></td>
				<td style="text-align:center"><b>Prix</b></td>
			</tr>
			<c:forEach items="${listPlats}" var="listPlat">
		        <tr>
		            <td>${listPlat.getNom()}</td>
		            <td>${listPlat.getDescription()}</td>
		            <td>${listPlat.getGroupe()}</td>
		            <td>${listPlat.getPrix()}</td>
		            <td><a href="Modification"><img src="img/modifier.png"/></a></td>
					<td><a href=""><img src="img/supprimer.png" /></a></td>
		        </tr>
		    </c:forEach>
		</table>
	</div>
</div>
<%@include file="Footer.jsp" %>