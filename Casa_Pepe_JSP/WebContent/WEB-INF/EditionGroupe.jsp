<%@ page import="bean.Groupe,bean.Plat" %>
<%@include file="Header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<% if(Manager.getIsConnect()){ %>
			  	<li><a href="GestionPlats">Plats</a></li>
			  	<li><a href="GestionGroupes">Groupes</a></li>
			  	
		  	<%} %>
		  	<li style="float:right;"><a href="Connexion">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12 tableEdition">
		<form method="post" enctype="multipart/form-data">
			<table align="center">
				<tr>
					<td>Nom du groupe</td>
					<td><input type="text" name="nom" value="${groupe.getNom()}"/></td>
				</tr>
			</table>
			<input name="id" value="${groupe.getId()}" type="hidden"/>
			<input type="image" src="img/editer.jpg" border="0" alt="Submit" class="inputEditer"/>
		</form>
	</div>
</div>
<%@include file="Footer.jsp" %>