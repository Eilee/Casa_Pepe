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
			</tr>
			<tr>
				<td>
					La burrata
				</td>
				<td>
					C'est un fromage frais à base de lait de vache ou de bufflonne (moins courante) qui se compose de pâte filée à l'extèrieur, comme celle de la mozzarella, de stracciatella à l'intérieur, c'est-à-dire de petits morceaux de mozzarellastracciati, déchirés, et de crème. Le nom de burrata, qui fait penser au beurre (burro en italien), est donc trompeur.
				</td>
				<td>
					<a href="Modification.jsp"><img src="img/modifier.png"/></a>
				</td>
				<td>
					<a href=""><img src="img/supprimer.png" /></a>
				</td>
			</tr>
			<tr>
				<td>
					Paccheri rigati asperges et pancetta
				</td>
				<td>
					Il s'agit des Paccheri rigati avec des asperges et des morceaux de pancetta en deux cuissons. 3 ingrédients simples qui se marient très bien.
				</td>
				<td>
					<a href="Modification.jsp"><img src="img/modifier.png"/></a>
				</td>
				<td>
					<a href=""><img src="img/supprimer.png" /></a>
				</td>
			</tr>
			<tr>
				<td>
					Zaletti
				</td>
				<td>
					Ces biscuits sont préparés dans plusieurs villes du nord de l'Italie avec souvent des variantes. Les ingrédients principaux restent la farine de maïs, les raisins secs et la grappa, trois produits du nord-est italien. Les zaletti sont souvent accompagnés de vin liquoreux.
				</td>
				<td>
					<a href="Modification.jsp"><img src="img/modifier.png"/></a>
				</td>
				<td>
					<a href=""><img src="img/supprimer.png" /></a>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@include file="Footer.jsp" %>