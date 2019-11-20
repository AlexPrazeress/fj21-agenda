<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Contatos</title>
</head>
<body>
	<c:import url="cabecalho.jsp"></c:import>
	
	<!-- cria o dao -->
	
	<!-- percorre contatos montando as linhas da tablea -->
	<table>
		<c:forEach var="contato" items="${contatos}">
			<tr>
				<td>  ${ contato.nome} </td>
				
				<td>				
					<c:choose>
						<c:when test="${not empty contato.email }">
							| <a href="mailto:${contato.email}"> ${contato.email}</a>
						</c:when>
						<c:otherwise>
							E-mail não informado
						</c:otherwise>
					</c:choose>
				</td>
				
				<td>
					<a href="mvc?logica=RemoveContatoLogic$id=${contato.id}">Remover</a>
					
				</td>
				<td> | ${ contato.endereco } </td>
				<td>
					| <fmt:formatDate value="${contato.dataNascimento.time}"
					pattern="dd/MM/yyyy"/>
				 </td>
			</tr>
		</c:forEach>
	</table>
	
	<c:import url="rodape.jsp"></c:import>
</body>
</html>