<%@include file="Header.jsp" %>	 
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil">Accueil</a></li>
		  	<li><a class="active" href="Menus">Menus</a></li>
		  	<% if(Manager.getIsConnect()){ %>
			  	<li><a href="GestionPlats">Plats</a></li>
			  	<li><a href="GestionGroupes">Groupes</a></li>
		  	<li style="float:right;"><a href="Deconnexion">Deconnexion</a></li>
		  	<%}else{%>
		  		<li style="float:right;"><a href="Connexion">Connexion</a></li>
		  	<%} %>
		</ul>
		<br/>
	</div>
</div>

<div class="row">
	<div class="col-md-12 tableImg tableMenus">
		<c:forEach items="${listMenus}" var="listMenu">
			<table>
				<caption><h2>${listMenu.getNom()}</h2></caption>
				<%int i=0;%>
				<c:forEach items="${listMenu.getListPlat()}" var="listPlat">
			        <%if((i%2)==0){%>
			        	<tr>
			        <%} %>
			            <td>
			            	<h4>${listPlat.getNom()}&nbsp;<i>(${listPlat.getGroupe().getNom()})</i></h4>
			            	<img id="${listPlat.getNom()}" src="Image?id=${listPlat.idPhoto}"/>
							<p>${listPlat.getDescription()}</p>
						</td>
					<%if((i%2)!=0){%>
						</tr>
					<%}
			        i++;%>
	    		</c:forEach>
		 	</table>
	    </c:forEach>
	</div>
</div>			
<%@include file="Footer.jsp" %>