package controllers.reports;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsUpdateServlet
 */
@WebServlet(name = "reports/update", urlPatterns = { "/reports/update" })
public class ReportsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){

            EntityManager em=DBUtil.createEntityManager();
            Report r=em.find(Report.class,(Integer)(request.getSession().getAttribute("report_id")));

            r.setReport_date(Date.valueOf(request.getParameter("report_date")));
            r.setTitle(request.getParameter("title"));
            r.setContent(request.getParameter("content"));
            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            r.setCommuting_at(Time.valueOf(request.getParameter("commuting_at")+":00"));
            r.setLeaving_at(Time.valueOf(request.getParameter("leaving_at")+":00"));

            List<String>errors=ReportValidator.validate(r);
            if(errors.size()>0){

                em.close();

                request.setAttribute("_token",request.getSession().getId());
                request.setAttribute("report",r);
                request.setAttribute("errors",errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp");
                rd.forward(request, response);

            }else{

                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.setAttribute("flush","更新が完了しました。");

                request.getSession().removeAttribute("report_id");
                response.sendRedirect(request.getContextPath()+"/reports/index");

            }

        }


    }

}
