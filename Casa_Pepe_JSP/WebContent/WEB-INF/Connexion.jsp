<%@include file="Header.jsp" %>	 
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<li><a href="GestionPlats">Gestion des plats</a></li>
		  	<li><a href="GestionGroupes">Gestion des groupes</a></li>
		  	<li style="float:right;"><a class="active" href="Connexion">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<form method="post" action="Connexion">
			<table>
				<tr>
					<td>Identifiant : </td> <td><input name ="ident" type="text" value=""/></td>
				</tr>
				<tr>
					<td>Mot de passe : </td> <td><input name ="mdp" type="text" value=""/></td>
				</tr>
				<tr><td><input name ="Enregistrer" type="submit" value ="Enregistrer"/></td></tr>
			</table>
		</form>
	</div>
</div>
<%@include file="Footer.jsp" %>
