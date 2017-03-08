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
		<form method="post">
			<table align="center">
				<tr>
					<td>Nom du plat</td>
					<td><input type="text" name="nom" value="${plat.getNom()}"/></td>
				</tr>
				<tr>
					<td>Description du plat</td>
					<td><textarea class="form-control" rows="5" name="description">${plat.getDescription()}</textarea></td>
				</tr>
				<tr>
					<td>Type du plat</td>
					<td>
						 <div class="form-group">
						  <select class="form-control" name="groupe">
						  	<c:forEach items="${groupes}" var="groupe">
						    	<option>${groupe.getNom()}</option>
						    </c:forEach>
						  </select>
						</div>
					</td>
				</tr>
				<tr>
					<td>Prix du plat</td>
					<td><input type="number" name="prix" value="${plat.getPrix()}"/></td>
				</tr>
				<tr>
					<td>Photo du plat</td>
					<td><input type="file" name="photo"/><p style="text-align:center;">(tailleMax = 100Ko)</p></td>
				</tr>
			</table>
			<input type="image" src="img/editer.jpg" border="0" alt="Submit" class="inputEditer"/>
		</form>
	</div>
</div>
<%@include file="Footer.jsp" %>