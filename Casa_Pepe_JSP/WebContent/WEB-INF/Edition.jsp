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
						  	<option></option>
						  	<c:forEach items="${groupes}" var="groupe">
						  		<option value="${groupe.getId()}" 
							  		<c:if test="${groupe.getId()==plat.getIdGroupe()}">
							    		selected
							    	</c:if>
						    	>${groupe.getNom()}</option>
						    </c:forEach>
						  </select>
						</div>
					</td>
				</tr>
				<tr>
					<td>Prix du plat</td>
					<td><input type="number" name="prix" value="${plat.getPrix()}"/></td>
				</tr>
				<c:if test="${plat!=null}">
					<tr>
						<td></td>
			    		<td><img id="${plat.getNom()}" src="Image?id=${plat.idPhoto}" /></td>
			    	</tr>
			    </c:if>
				<tr>
					<td>Photo du plat</td>
					<td><input type="file" name="image"/><p style="text-align:center;">(tailleMax = 100Ko)</p></td>
				</tr>
			</table>
			<input name="id" value="${plat.getId()}" type="hidden"/>
			<input type="image" src="img/editer.jpg" border="0" alt="Submit" class="inputEditer"/>
		</form>
	</div>
</div>
<%@include file="Footer.jsp" %>