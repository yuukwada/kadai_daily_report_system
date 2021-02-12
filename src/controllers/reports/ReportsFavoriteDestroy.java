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
 * Servlet implementation class ReportsFavoriteDestroy
 */
@WebServlet("/reports/favorite_destroy")
public class ReportsFavoriteDestroy extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsFavoriteDestroy() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em=DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        Report report=em.find(Report.class,Integer.parseInt(request.getParameter("report_id")));

        int favorited_count=(Integer.parseInt(request.getParameter("favorited_count")));
        report.setFavorited_count(favorited_count -1);

        Integer favorite_id=em.createNamedQuery("getFavoriteId",Integer.class)
                               .setParameter("report",report)
                               .setParameter("employee",login_employee)
                               .getSingleResult();

        Favorite f =em.find(Favorite.class,favorite_id);

        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath()+"/reports/index");
    }

}
