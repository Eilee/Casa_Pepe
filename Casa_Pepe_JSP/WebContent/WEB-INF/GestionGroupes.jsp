<%@include file="Header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a href="Menus">Menus</a></li>
		  	<% if(Manager.getIsConnect()){ %>
			  	<li><a href="GestionPlats">Plats</a></li>
			  	<li><a class="active" href="GestionGroupes">Groupes</a></li>
			  	
		  	<li style="float:right;"><a href="Deconnexion">Deconnexion</a></li>
		  	<%}else{%>
		  		<li style="float:right;"><a href="Connexion">Connexion</a></li>
		  	<%} %>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<form method="post">
		<input name="create" value="true" type="hidden"/>
		<input type="image" src="img/creer.jpg" border="0" alt="Submit" class="inputCreate"/>
	</form>
	<div class="col-md-12 tableGestion tableIcone">
		<table align="center">
			<tr>
				<td style="text-align:center"><b>Nom du groupe</b></td>
			</tr>
			<c:forEach items="${listGroupes}" var="listGroupe">
		        <tr>
		            <td>${listGroupe.getNom()}</td>
					<td>
		            	<form method="post">
							<input name="pdf" value="${listGroupe.getId()}" type="hidden"/>
							<input type="image" src="img/pdf.png" border="0" alt="Submit"/>
						</form>
		            </td>
					<td>
		            	<form method="post">
							<input name="update" value="${listGroupe.getId()}" type="hidden"/>
							<input type="image" src="img/modifier.png" border="0" alt="Submit"/>
						</form>
		            </td>
					<td>
						<form method="post" onsubmit="return confirm('Voulez-vous vraiment supprimer ce plats ?');">
							<input name="delete" value="${listGroupe.getId()}" type="hidden"/>
							<input type="image" src="img/supprimer.png" border="0" alt="Submit"/>
						</form>
					</td>
		        </tr>
		    </c:forEach>
		</table>
	</div>
</div>
<%@include file="Footer.jsp" %>