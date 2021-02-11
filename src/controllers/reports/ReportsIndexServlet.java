package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsIndexServlet
 */
@WebServlet(name = "reports/index", urlPatterns = { "/reports/index" })
public class ReportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em=DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }



        List<Report>reports=em.createNamedQuery("getAllReports",Report.class)
                              .setFirstResult(15*(page-1))
                              .setMaxResults(15)
                              .getResultList();

        long reports_count=(long)em.createNamedQuery("getReportsCount",Long.class)
                                       .getSingleResult();

        long favorites_count=(long)em.createNamedQuery("getFavoriteCounts",Long.class)
                                          .setParameter("employee",login_employee)
                                          .getSingleResult();

        if(favorites_count==0){
            em.close();
        }

        if(favorites_count>0){

           List<Report> favorited_reports =em.createNamedQuery("getFavoritedReports",Report.class)
                                              .setParameter("employee",login_employee)
                                              .getResultList();



           em.close();
           request.setAttribute("favorited_reports",favorited_reports);

        }



        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);
        request.setAttribute("favorites_count",favorites_count);
        request.setAttribute("login_employee",login_employee);


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
    }


}
