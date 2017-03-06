<%@include file="Header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<li><a href="GestionPlats">Gestion des plats</a></li>
		  	<li><a class="active" href="GestionGroupes">Gestion des groupes</a></li>
		  	<li style="float:right;"><a href="Connexion">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12 tableGestion tableIcone">
		<table>
			<tr>
				<td style="text-align:center"><b>Nom du groupe</b></td>
			</tr>
			<tr>
				<td>
					Entrée
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
					Plats
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
					Dessert
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