<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <h3>日報一覧</h3>

        <table id="report_list">
            <tbody>

                <tr>

                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                    <th class="favorite"> </th>
                    <th class="favorited_count">いいね!数</th>

                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                        <td class="favorite">

                        <c:set var="check_flag" value="0" />

                        <c:if test="${report.employee.id == login_employee.id}">
                              <c:set var="check_flag" value="2"/>
                        </c:if>

                        <c:if test="${check_flag==2 }">
                            -
                        </c:if>

                        <c:forEach var="favorited_report" items="${favorited_reports}">

                            <c:if test="${favorited_report.id == report.id}">
                                <c:set var="check_flag" value="1" />
                            </c:if>

                            <c:if test="${report.employee.id == login_employee.id}">
                                <c:set var="check_flag" value="2"/>
                            </c:if>

                        </c:forEach>


                        <c:if test="${check_flag==1}">
                            <form method="POST" action="<c:url value="/reports/favorite_destroy"/>">
                                <input type="hidden" name="report_id"value="${report.id}">
                                <input type="hidden" name="favorited_count"value="${report.favorited_count}">
                                <button class="antifollow" type="submit">いいね!を解除する</button>
                            </form>
                        </c:if>
                        <c:if test="${check_flag==0}">
                             <form method="POST" action="<c:url value="/reports/favorite"/>">
                                <input type="hidden" name="report_id"value="${report.id}">
                                <input type="hidden" name="favorited_count"value="${report.favorited_count}">
                                <button class="follow" type="submit">いいね!する</button>
                             </form>
                        </c:if>

                        </td>

                        <td class="favorited_count">
                        <c:out value="${fn:length(report.favorite_Employee)}" />
                        </td>



                    </tr>
                </c:forEach>
            </tbody>
        </table>




        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reports/new' />">新規日報の登録</a></p>


    </c:param>
</c:import>