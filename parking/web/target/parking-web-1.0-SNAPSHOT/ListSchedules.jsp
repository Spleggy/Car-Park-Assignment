<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 16/01/2019
  Time: 01:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@page import="org.kevin.carpark.web.WebObjectFactory"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.kevin.carpark.model.*" %>

<%
    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String ticketScheduleIdReq = (String) request.getParameter("Id");



    TicketMachine ticketMachine = null;
    ticketMachine = serviceFacade.retrieveTicketMachine(Integer.valueOf(ticketScheduleIdReq));

    List<TicketSchedule> scheduleList = ticketMachine.getSchedule().getTicketSchedules();

%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Schedule List</title>
</head>
<body>
    <table>
        <tr>
            <th>Schedule ID</th>
            <th>Start Time</th>
            <th>Price (per hour)</th>
            <th>Modify or Delete Schedule</th>

        </tr>
        <%  for (TicketSchedule schedule : scheduleList) {%>
        <tr>
            <th><% schedule.getScheduleId(); %></th>
            <th><% schedule.getStartTime(); %></th>
            <th><% schedule.getPricePerHour(); %></th>
        </tr>
        <% }%>
    </table>
    <form action="AddOrModifySchedule.jsp">
        <input type="hidden" name="action" value="createSchedule">
        <input type="hidden" name="Id" value="<%=ticketMachine.getId()%>">
        <input type="submit" value="Create New Schedule">
    </form>
    <form action="ListMachines.jsp">
        <input type="hidden" name="action" value="back">
        <input type="submit" value="Back">
    </form>

</body>
</html>
