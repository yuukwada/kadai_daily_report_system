package models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name="reports")
@NamedQueries({
    @NamedQuery(
            name="getReportsCount",
            query="SELECT COUNT(r) FROM Report AS r"
    ),
    @NamedQuery(
            name="getAllReports",
            query="SELECT r FROM Report AS r ORDER BY r.id DESC"
    ),
    @NamedQuery(
            name="getMyAllReports",
            query="SELECT r FROM Report AS r WHERE r.employee=:employee ORDER BY r.id DESC"
    ),
    @NamedQuery(
            name="getMyReportsCount",
            query="SELECT COUNT(r) FROM Report AS r WHERE r.employee=:employee"
    )

})

@Entity
public class Report {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "report_date", nullable = false)
    private Date report_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name="commuting_at")
    private Time commuting_at;

    @Column(name="leaving_at")
    private Time leaving_at;

    @Column(name="favorited_count")
    private int favorited_count=0;


    @ManyToMany
    @JoinTable(name="favorite",joinColumns=@JoinColumn(name="favorite_Report"),
                               inverseJoinColumns=@JoinColumn(name="favorite_Employee"))
    private List<Employee> favorite_Employee;


    public List<Employee> getFavorite_Employee() {
        return favorite_Employee;
    }

    public void setFavorite_Employee(List<Employee> favorite_Employee) {
        this.favorite_Employee = favorite_Employee;
    }


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

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Time getCommuting_at() {
        return commuting_at;
    }

    public void setCommuting_at(Time commuting_at) {
        this.commuting_at = commuting_at;
    }

    public Time getLeaving_at() {
        return leaving_at;
    }

    public void setLeaving_at(Time leaving_at) {
        this.leaving_at = leaving_at;
    }

    public int getFavorited_count() {
        return favorited_count;
    }

    public void setFavorited_count(int favorited_count) {
        this.favorited_count = favorited_count;
    }



}
