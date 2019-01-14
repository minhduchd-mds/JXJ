<%-- 
    Document   : product
    Created on : Jan 9, 2019, 10:10:41 AM
    Author     : dominhduc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:forEach var="product" item="${product}">
        <Product>
        <ProductID>${product.productID}</ProductID>
        <ProductName>${product.productName}</ProductName>
        <SupplierID>${product.supplierID}</SupplierID>
        <CategoryID>${product.categoryID}</CategoryID>
        <QuantityPerUnit>${product.quantityPerUnit}</QuantityPerUnit>
        <UnitPrice>${product.unitPrice}</UnitPrice>
        <UnitsInStock>${product.unitsInStock}</UnitsInStock>
           <UnitsOnOder>${product.unitsOnOder}</UnitsOnOder>
        <ReorderLevel>${product.reorderLevel}</ReorderLevel>
        <Discontinued>${product.discontinued}</Discontinued>
        </Product>
    </c:forEach>
    </body>
</html>
