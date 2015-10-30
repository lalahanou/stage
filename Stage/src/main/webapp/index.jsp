<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
  function confirmer(url)
  {
	  var rep = confirm("Etes vous sure de vouloir supprimer ?");
	  if(rep == true)
		  {
		   document.location = url;
		  }
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Gestion des stocks</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
</head>
<body style="background-color: #fafafa">
 <div style=" width: 85%;height: 750px;margin: 50px auto;box-shadow: 2px 2px 10px #888;background-color:white; padding-bottom: 70px;border-radius: 7px 7px 0 0">
  <div>
    <form action="controleur.php" method ="post">
    <h1>Listes des produits :</h1>
        <table class="table" style="border-collapse:collapse;margin-left:700px">
          <tr>
            <td> Rechercher  :</td>
            <td><input style="border-radius: 5px 5px 5px 5px";" type ="text" name="motCle" value="${model.motCle }" ></input></td>
            <td><input type="submit" value="chercher" name="action"/></td>
          </tr>
        </table>
    </form>
  </div>
  
  <div>
    <form action="controleur.php" method ="post">
    <fieldset>
    <legend>Informations générale</legend>
     <input type="hidden" value="${model.mode }" name ="mode"/>
        <table class="table">
         <c:if test="${model.mode=='ajout' }">
           <tr>
            <td> Réference :</td>
            <td><input type ="text" name="reference" value="${model.produit.reference }"></input></td>
          </tr>
         </c:if>
         <c:if test="${model.mode=='edit' }">
           <tr>
            <td> Réference :</td>
            <td>${model.produit.reference}<input type ="hidden" name="reference" value="${model.produit.reference }"></input></td>
          </tr>
         </c:if>
          <tr>  
            <td> Designation :</td>
            <td><input type ="text" name="designation" value="${model.produit.designation }"></input></td>
          </tr>
          <tr>
            <td> Prix :</td>
            <td><input type ="text" name="prix" value="${model.produit.prix}"></input></td>
          </tr>
          <tr>  
            <td> Quantite :</td>
            <td><input type ="text" name="quantite" value="${model.produit.quantite }"></input></td>
          </tr>
          <tr>
            <td><input type="submit" name ="action" value="save"/></td>
          </tr>
        </table>
       </fieldset>
    </form>
  </div>
  
  <div>
      ${model.errors}
  </div>
  
  <div>
    <table class="table"  style="border-collapse:collapse;box-shadow: 2px 2px 10px #888; border-top-color: rgb(0, 0, 0);
    border-top-style: solid;
    border-top-width: 0px;">
      <tr>
        <th>Reference</th><th>Designation</th><th>Prix</th><th>Quantite</th><th colspan="2"></th>
      </tr>
      <c:forEach items="${model.produits}" var ="p">
        <tr>
           <td>${p.reference}</td>
            <td>${p.designation}</td>
            <td>${p.prix}</td>
            <td>${p.quantite}</td> 
            <td><a href="javascript:confirmer('controleur.php?action=delete&ref=${p.reference}')">Supprimer</a></td>
            <td><a href="controleur.php?action=edit&ref=${p.reference}">Editer</a></td>      
        </tr>
      </c:forEach>
    </table>
  </div>
 </div> 
</body>
</html>