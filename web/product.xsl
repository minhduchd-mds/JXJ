<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : product.xsl
    Created on : January 9, 2019, 10:30 AM
    Author     : dominhduc
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>product.xsl</title>
            </head>
            <body>
                <table border="1" color="red">
                    <tr bgcolor="#9acd32">
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>SupplierID</th>
                        <th>CategoryID</th>
                        <th>QuantityPerUnit</th>
                        <th>UnitPrice</th>
                        <th>UnitsInStock</th>
                         <th>UnitsOnOder</th>
                        <th>ReorderLevel</th>
                        <th>Discontinued</th>
                    </tr>
                    <xsl:for-each select="Products/Product">
                        <tr>
                            <td>
                                <xsl:value-of select="ProductID"/>
                            </td>
                            <td>
                                <xsl:value-of select="ProductName"/>
                            </td>
                            <td>
                                <xsl:value-of select="SupplierID"/>
                            </td>
                            <td>
                                <xsl:value-of select="CategoryID"/>
                            </td>
                            <td>
                                <xsl:value-of select="QuantityPerUnit"/>
                            </td>
                            <td>
                                <xsl:value-of select="UnitsInStock"/>
                            </td>
                            <td>
                                <xsl:value-of select="UnitsOnOder"/>
                            </td>
                            <td>
                                <xsl:value-of select="ReorderLevel"/>
                            </td>
                            <td>
                                <xsl:value-of select="Discontinued"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
