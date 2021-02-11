<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <h3>いいねした日報一覧</h3>

        <table id="favorite_report_list">
            <tbody>

                <tr>

                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>

                </tr>

                <c:forEach var="favorited_report" items="${favorited_reports}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${favorited_report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${favorited_report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${favorited_report.title}</td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${favorited_report.id}' />">詳細を見る</a></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>




        <div id="pagination">
            （全 ${favorites_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((favorites_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/favorite_index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>