<%-- 
    Document   : AddOrModifyEntity
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.kevin.carpark.web.WebObjectFactory"%>
<%@page import="org.kevin.carpark.model.ServiceFactory"%>
<%@page import="org.kevin.carpark.model.ServiceFacade"%>
<%@page import="org.kevin.carpark.model.TicketMachine"%>


<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String ticketMachineIdReq = (String) request.getParameter("Id");
    String ticketMachineField_AReq = (String) request.getParameter("field_A");
    String ticketMachineField_BReq = (String) request.getParameter("field_B");
    String ticketMachineField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";

    TicketMachine ticketMachine = null;
    Integer ticketMachineId = null;

    if ("modifyTicketMachine".equals(action)) {
        try {
            ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            ticketMachine = serviceFacade.retrieveTicketMachine(ticketMachineId);
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else if ("createTicketMachine".equals(action)) {
        try {
            ticketMachine = new TicketMachine();
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Edit Machine</title>
</head>
<body>
<% if ("createTicketMachine".equals(action)) {
%>
<h1>Add New Ticket Machine</h1>
<% } else {%>
<h1>Modify Ticket Machine <%=ticketMachineId%></h1>
<% }%>
<form action="ListMachines.jsp">
    <table>
        <tr>
            <th>Field</th>
            <th>Current Value</th>
            <th>New Value</th>
        </tr>
        <tr>
            <td>Ticket Machine Id</td>
            <td><%=ticketMachine.getId()%></td>
            <td></td>
        </tr>
        <tr>
            <td>field_A</td>
            <td><%=ticketMachine.getField_A()%></td>
            <td><input type="text" name="field_A" value ="<%=ticketMachine.getField_A()%>"></td>
        </tr>
        <tr>
            <td>field_B</td>
            <td><%=ticketMachine.getField_B()%></td>
            <td><input type="text" name="field_B" value ="<%=ticketMachine.getField_B()%>"></td>
        </tr>
        <tr>
            <td>field_C</td>
            <td><%=ticketMachine.getField_C()%></td>
            <td><input type="text" name="field_C" value ="<%=ticketMachine.getField_C()%>"></td>
        </tr>
    </table>
    <BR>
    <% if ("createTicketMachine".equals(action)) {
    %>
    <input type="hidden" name="action" value="createTicketMachine">
    <input type="hidden" name="entityId" value="<%=ticketMachineId%>">
    <input type="submit" value="Create New Ticket Machine">
    <% } else if ("modifyTicketMachine".equals(action)) {
    %>
    <input type="hidden" name="action" value="modifyTicketMachine">
    <input type="hidden" name="entityId" value="<%=ticketMachineId%>">
    <input type="submit" value="Modify Ticket Machine">
    <% }%>
</form>
<form action="ListMachines.jsp">
    <input type="submit" value="Cancel and Return">
</form>
</body>
</html>
