package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.kevin.carpark.web.WebObjectFactory;
import org.kevin.carpark.model.*;

public final class AddOrModifySchedule_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


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




      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("    <title>Edit Schedule</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
 if ("createSchedule".equals(action)) {

      out.write("\r\n");
      out.write("<h1>Add New Schedule</h1>\r\n");
 } else {
      out.write("\r\n");
      out.write("<h1>Modify Schedule for Ticket Machine ");
      out.print(Integer.parseInt(ticketMachineIdReq));
      out.write("</h1>\r\n");
 }
      out.write("\r\n");
      out.write("<form action=\"ListSchedules.jsp\">\r\n");
      out.write("    <table>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <th>Start Time</th>\r\n");
      out.write("            <th>Price (per hour)</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td><input type=\"text\" name=\"startTime\" value=\"\"></td>\r\n");
      out.write("            <td><input type=\"text\" name=\"pricePerHour\" value=\"\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("    <BR>\r\n");
      out.write("</form>\r\n");
      out.write("    ");
 if ("createSchedule".equals(action)) {
    
      out.write("\r\n");
      out.write("    <input type=\"hidden\" name=\"action\" value=\"createSchedule\">\r\n");
      out.write("    <input type=\"hidden\" name=\"Id\" value=\"");
      out.print( ticketMachineId);
      out.write("\">\r\n");
      out.write("    <input type=\"submit\" value=\"Create New Schedule\">\r\n");
      out.write("    ");
 } else if ("modifySchedule".equals(action)) {
    
      out.write("\r\n");
      out.write("    <input type=\"hidden\" name=\"action\" value=\"modifySchedule\">\r\n");
      out.write("    <input type=\"hidden\" name=\"Id\" value=\"");
      out.print(ticketMachineId);
      out.write("\">\r\n");
      out.write("    <input type=\"submit\" value=\"Modify Schedule\">\r\n");
      out.write("    ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form action=\"ListSchedules.jsp\">\r\n");
      out.write("    <input type=\"hidden\" name=\"Id\" value=\"");
      out.print(ticketMachineId);
      out.write("\">\r\n");
      out.write("    <input type=\"submit\" value=\"Cancel and Return\">\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
