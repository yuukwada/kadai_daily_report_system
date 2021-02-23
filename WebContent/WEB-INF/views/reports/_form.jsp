<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="report_date">日付</label><br />
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${report.title}" />
<br /><br />

<label for="content">日報内容</label><br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<label for="commuting_at">出勤時間</label><br/>
<input type="time" name="commuting_at" pattern="(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])" title="hh(時) : mm(分)[00:00～23:59]" value="<fmt:formatDate value="${report.commuting_at}" pattern="HH:mm"/>"/>
<br/><br/>

<label for="leaving_at">退勤時間</label><br/>
<input type="time" name="leaving_at" pattern="(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])" title="hh(時) : mm(分)[00:00～23:59]" value="<fmt:formatDate value="${report.leaving_at}" pattern="HH:mm"/>"/>
<br/><br/>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>