<%-- 
    Document   : AddOrModifyEntity
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.kevin.carpark.web.WebObjectFactory"%>
<%@ page import="org.kevin.carpark.model.*" %>

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
    String ticketScheduleStart =  (String) request.getParameter("startTime");
    String ticketSchedulePrice = (String) request.getParameter("pricePerHour");
    Double schedulePrice = null;
    schedulePrice = Double.parseDouble(ticketSchedulePrice);


    Integer ticketMachineId = Integer.valueOf(ticketMachineIdReq) ;
    String errorMessage = "";
    TicketMachine ticketMachine;
    ticketMachine = serviceFacade.retrieveTicketMachine(ticketMachineId);


    List<TicketSchedule> scheduleList = ticketMachine.getSchedule().getTicketSchedules();

    Integer scheduleId = scheduleList.size();

    if ("modifySchedule".equals(action)) {
        try {
            TicketSchedule ticketSchedule = new TicketSchedule();
            ticketSchedule.setScheduleId(scheduleId);
            ticketSchedule.setStartTime(ticketScheduleStart);
            ticketSchedule.setPricePerHour(schedulePrice);

            ticketMachine.addSchedule(ticketSchedule);
            serviceFacade.updateTicketMachine(ticketMachine);
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else if ("createSchedule".equals(action)) {
        try {

            TicketSchedule ticketSchedule = new TicketSchedule();
            ticketSchedule.setScheduleId(scheduleId);
            ticketSchedule.setStartTime(ticketScheduleStart);
            ticketSchedule.setPricePerHour(schedulePrice);

            ticketMachine.addSchedule(ticketSchedule);

            serviceFacade.updateTicketMachine(ticketMachine);

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
    <title>Edit Schedule</title>
</head>
<body>
<% if ("createSchedule".equals(action)) {
%>
<h1>Add New Schedule</h1>
<% } else {%>
<h1>Modify Schedule for Ticket Machine <%=Integer.parseInt(ticketMachineIdReq)%></h1>
<% }%>
<form action="ListSchedules.jsp">
    <table>
        <tr>
            <th>Start Time</th>
            <th>Price (per hour)</th>
        </tr>

        <tr>
            <td><input type="text" name="startTime" value=""></td>
            <td><input type="text" name="pricePerHour" value=""></td>
        </tr>

    </table>
    <BR>
</form>
    <% if ("createSchedule".equals(action)) {
    %>
    <input type="hidden" name="action" value="createSchedule">
    <input type="hidden" name="Id" value="<%= ticketMachineId%>">
    <input type="submit" value="Create New Schedule">
    <% } else if ("modifySchedule".equals(action)) {
    %>
    <input type="hidden" name="action" value="modifySchedule">
    <input type="hidden" name="Id" value="<%=ticketMachineId%>">
    <input type="submit" value="Modify Schedule">
    <% }%>

<form action="ListSchedules.jsp">
    <input type="hidden" name="Id" value="<%=ticketMachineId%>">
    <input type="submit" value="Cancel and Return">
</form>
</body>
</html>
