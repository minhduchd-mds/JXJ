<%-- 
    Document   : index
    Created on : Jan 9, 2019, 10:09:48 AM
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
        $(info)
    <from action="ActionProces" method="POST">
        <input type="hidden" name="act" value="getdata"/><input type="submit" value=" Get data"/>
    </from>
    <from action="ActionProcess" method="POST">
        <input type="hidden" name="act" value="showdata"/><input type="submit" value=" Show data"/>
    </from>
    <from action="ActionProcess" method="POST">
        <input type="text" name="finter" value=""/>
        <input type="hidden" name="act" value="finterddata"/><input type="submit" value=" Finterd data"/>
    </from>
    </body>
</html>
