/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author dominhduc
 */
public class DAO {
    protected Connection conn;
    public DAO(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Producst", "sa", "sa");        
        }catch(ClassNotFoundException | SQLException ex){
            
        }
       
    }
    public ResultSet getData(){
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("selete * from product");
        }catch(SQLException ex){
            
        }
        return rs;
    }
    
    public void writeXML(String url) throws ParserConfigurationException, SQLException , TransformerException , IOException{
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element root  = document.createElement("Products");
        document.appendChild(root);
        ResultSet rs = this.getData();
        while(rs.next()){
            Element child = document.createElement("Product");
            document.appendChild(root);
            Element ProductID = document.createElement("ProductID");
            ProductID.setTextContent(rs.getString("ProductID"));
            child.appendChild(ProductID);
            Element ProductName = document.createElement("ProductName");
            ProductName.setTextContent(rs.getString("ProductName"));
            child.appendChild(ProductName);
            Element SupplierID = document.createElement("SupplierID");
            SupplierID.setTextContent(rs.getString("SupplierID"));
            child.appendChild(SupplierID);
            Element CategoryID = document.createElement("CategoryID");
            CategoryID.setTextContent(rs.getString("CategoryID"));
            child.appendChild(CategoryID);
            Element QuantityPerUnit = document.createElement("QuantityPerUnit");
            QuantityPerUnit.setTextContent(rs.getString("QuantityPerUnit"));
            child.appendChild(QuantityPerUnit);
            Element UnitPrice = document.createElement("UnitPrice");
            UnitPrice.setTextContent(rs.getString("UnitPrice"));
            child.appendChild(UnitPrice);
            Element UnitsInStock = document.createElement("UnitsInStock");
            UnitsInStock.setTextContent(rs.getString("UnitsInStock"));
            child.appendChild(UnitsInStock);
            Element  UnitsOnOrder = document.createElement("UnitsOnOrder");
             UnitsOnOrder.setTextContent(rs.getString("UnitsOnOrder"));
            child.appendChild(UnitsOnOrder);
            Element ReorderLevel = document.createElement("ReorderLevel");
            ReorderLevel.setTextContent(rs.getString("ReorderLevel"));
            child.appendChild(ReorderLevel);
            Element Discontinued = document.createElement("Discontinued");
            Discontinued.setTextContent(rs.getString("Discontinued"));
            child.appendChild(Discontinued);
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult();
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
        FileWriter fwriter = new FileWriter(new File(url + "Products.xml"));
        fwriter.write(writer.toString());
        fwriter.close();
    }

  
}
