package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="favorite")
@NamedQueries({
    @NamedQuery(
        name = "getFavoriteCounts",
        query = "SELECT COUNT(f) FROM Favorite AS f WHERE f.employee = :employee"),
    @NamedQuery(
        name = "getFavoritedReports",
        query = "SELECT f.report FROM Favorite AS f WHERE f.employee = :employee"),
    @NamedQuery(
        name = "getFavoriteEmployees",
        query = "SELECT f.employee FROM Favorite AS f WHERE f.report = :report"),
    @NamedQuery(
        name="getFavoriteId",
        query="SELECT f.id FROM Favorite AS f WHERE f.report=:report AND f.employee=:employee"),
    @NamedQuery(
        name="getFavoriteReportCount",
        query="SELECT COUNT(f) FROM Favorite AS f WHERE f.report=:report")
})


@Entity
public class Favorite {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="favorite_Employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="favorite_Report")
    private Report report;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }




}
