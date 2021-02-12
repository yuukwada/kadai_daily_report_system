package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsFavoriteServlet
 */
@WebServlet("/reports/favorite")
public class ReportsFavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();


        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        Report report=em.find(Report.class,Integer.parseInt(request.getParameter("report_id")));

        int favorited_count=(Integer.parseInt(request.getParameter("favorited_count")));

        report.setFavorited_count(favorited_count +1);

        Favorite f = new Favorite();
        f.setEmployee(login_employee);
        f.setReport(report);

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();

        em.close();

        response.sendRedirect(request.getContextPath()+"/reports/index");

    }

}
