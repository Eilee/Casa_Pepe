<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="Manager" class="manager.Manager" scope="session"/>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Identification</title>
	</head>
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
			response.sendRedirect("ListerLivres.jsp");
		}else{
			System.out.println("Identification KO");
		}
		
	%>
		<body>
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
		</body>
</html>