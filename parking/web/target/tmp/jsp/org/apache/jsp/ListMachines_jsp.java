package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.kevin.carpark.web.WebObjectFactory;
import org.kevin.carpark.model.ServiceFactory;
import org.kevin.carpark.model.ServiceFacade;
import org.kevin.carpark.model.TicketMachine;

public final class ListMachines_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    String ticketMachineLocation = (String) request.getParameter("location");

    String errorMessage = "";
    if ("deleteTicketMachine".equals(action)) {
        try {
            Integer ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            serviceFacade.deleteTicketMachine(ticketMachineId);
        } catch (Exception e) {
            errorMessage = "problem deleting ticket machine " + e.getMessage();
        }
    } else if ("modifyTicketMachine".equals(action)) {
        try {
            Integer ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setId(ticketMachineId);
            ticketMachineTemplate.setLocation(ticketMachineLocation);

            TicketMachine ticketMachine = serviceFacade.updateTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem modifying Ticket Machine. could not find ticketMachineId " + ticketMachineId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Ticket Machine " + e.getMessage();
        }
    } else if ("createTicketMachine".equals(action)) {
        try {
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setLocation(ticketMachineLocation);
            //ticketMachineTemplate.setSchedule(ticketMachineSchedule);
            TicketMachine ticketMachine = serviceFacade.createTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem creating Ticket Machine. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating Ticket Machine " + e.getMessage();
        }
    }

    List<TicketMachine> ticketMachineList = serviceFacade.retrieveAllTicketMachines();


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("        <title>Ticket Machine List</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- print error message if there is one -->\r\n");
      out.write("        <div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\r\n");
      out.write("        <h1>Ticket Machine List</h1>\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>Machine ID</th>\r\n");
      out.write("                <th>Location</th>\r\n");
      out.write("                <th>Schedule</th>\r\n");
      out.write("                <th>Modify or Delete Machine</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
  for (TicketMachine ticketMachine : ticketMachineList) {
            
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print(ticketMachine.getId());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(ticketMachine.getLocation());
      out.write("</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <form action=\"ListSchedules.jsp\">\r\n");
      out.write("                    <input type=\"hidden\" name=\"Id\" value=\"");
      out.print(ticketMachine.getId());
      out.write("\">\r\n");
      out.write("                    <input type=\"submit\" value=\"View Ticket Schedule\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <form action=\"AddOrModifyMachine.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"modifyTicketMachine\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"Id\" value=\"");
      out.print(ticketMachine.getId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Modify Ticket Machine\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <form action=\"ListMachines.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"deleteTicketMachine\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"Id\" value=\"");
      out.print(ticketMachine.getId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Delete Ticket Machine\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("        <BR>\r\n");
      out.write("        <form action=\"AddOrModifyMachine.jsp\">\r\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createTicketMachine\">\r\n");
      out.write("            <input type=\"submit\" value=\"Create Ticket Machine\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
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
