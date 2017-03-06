<jsp:useBean id="Manager" class="manager.Manager" scope="session"/>
<%
	String ident = request.getParameter("ident");
	String mdp = request.getParameter("mdp");
	if(ident == null) ident = "";
	if(mdp == null) mdp ="";
	
	Manager.getIdentification().setIdent(ident);
	Manager.getIdentification().setMdp(mdp);
	
	if(ident.equals("ubo") && mdp.equals("ubo")){
		//Code a remplcaer ( accés BDD) utiliser la classe base
		//base.verifIdentification(manager.getIdentification())
		System.out.println("Identification OK");
		Manager.setIdentifie(true);
		response.sendRedirect("Accueil.jsp");
	}else{
		System.out.println("Identification KO");
	}
	
%>
<%@include file="Header.jsp" %>	 
<div class="row">
	<div class="col-md-12">
		<ul>
		  	<li><a href="Accueil.jsp">Accueil</a></li>
		  	<li><a href="Menus.jsp">Menus</a></li>
		  	<li><a href="GestionPlats.jsp">Gestion des plats</a></li>
		  	<li><a href="GestionGroupes.jsp">Gestion des groupes</a></li>
		  	<li style="float:right;"><a class="active" href="Connexion.jsp">Connexion</a></li>
		</ul>
		<br/>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<form>
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
