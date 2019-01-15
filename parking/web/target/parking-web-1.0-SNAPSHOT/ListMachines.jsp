<%-- 
    Document   : ListEntities
    Created on : Nov 30, 2018, 11:17:02 PM
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
    if ("deleteTicketMachine".equals(action)) {
        try {
            Integer id = Integer.parseInt(ticketMachineIdReq);
            serviceFacade.deleteTicketMachine(id);
        } catch (Exception e) {
            errorMessage = "problem deleting Entity " + e.getMessage();
        }
    } else if ("modifyTicketMachine".equals(action)) {
        try {
            Integer id = Integer.parseInt(ticketMachineIdReq);
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setId(id);
            ticketMachineTemplate.setField_A(ticketMachineField_AReq);
            ticketMachineTemplate.setField_B(ticketMachineField_BReq);
            ticketMachineTemplate.setField_C(ticketMachineField_CReq);
            TicketMachine ticketMachine = serviceFacade.updateTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem modifying Ticket Schedule. could not find ticketMachineId " + id;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Ticket Schedule " + e.getMessage();
        }
    } else if ("createTicketMachine".equals(action)) {
        try {
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setField_A(ticketMachineField_AReq);
            ticketMachineTemplate.setField_B(ticketMachineField_BReq);
            ticketMachineTemplate.setField_C(ticketMachineField_CReq);
            TicketMachine ticketMachine = serviceFacade.createTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem creating Ticket Schedule. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating Ticket Schedule " + e.getMessage();
        }
    } 

    List<TicketMachine> ticketMachineList = serviceFacade.retrieveAllTicketMachines();

%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Ticket Schedule List</title>
    </head>
    <body>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <h1>Ticket Schedule List</h1>
        <table>
            <tr>
                <th>id</th>
                <th>starting time</th>
                <th>price per hour</th>
                <th></th>
            </tr>
            <%  for (TicketMachine ticketMachine : ticketMachineList) {
            %>
            <tr>
                <td><%=ticketMachine.getId()%></td>
                <td><%=ticketMachine.getField_A()%></td>
                <td><%=ticketMachine.getField_B()%></td>
                <td><%=ticketMachine.getField_C()%></td>
                <td>
                    <form action="AddOrModifyMachine.jsp">
                        <input type="hidden" name="action" value="modifyTicketMachine">
                        <input type="hidden" name="Id" value="<%=ticketMachine.getId()%>">
                        <input type="submit" value="modifyTicketSchedule">
                    </form>
                    <form action="ListMachines.jsp">
                        <input type="hidden" name="action" value="deleteTicketMachine">
                        <input type="hidden" name="Id" value="<%=ticketMachine.getId()%>">
                        <input type="submit" value="deleteTicketSchedule">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyMachine.jsp">
            <input type="hidden" name="action" value="createTicketMachine">
            <input type="submit" value="Create ticket schedule">
        </form>
    </body>
</html>
