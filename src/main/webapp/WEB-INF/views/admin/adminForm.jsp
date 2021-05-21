<div class="container">
	<br> <br> <br>
	<%-- 	<div class="search container">
		<form:form method="get" modelAttribute="AdminManageForm">
			<div class="form-inline">
				<form:select path="searchOptions" items="${CL.SELECT.OPTION}"></form:select>
				<form:input path="searchKeywords" align="left" cssClass="text" />
				<button id="searchBtn" class="btn btn-primary">
					<spring:message code="label.tr.common.search" />
				</button>
			</div>
		</form:form>
	</div> --%>
	<c:if test="${pageSize > 0}">
		<table id="adminTable">
			<thead>
				<tr>
					<th scope="col"><spring:message
							code="lable.tr.admin.manage.form.table.header.admincode" /></th>
					<th scope="col"><spring:message
							code="lable.tr.admin.manage.form.table.header.adminName" /></th>
					<th scope="col"><spring:message
							code="lable.tr.admin.manage.form.table.header.adminMail" /></th>
					<th scope="col"><spring:message
							code="lable.tr.admin.manage.form.table.header.adminTel" /></th>
					<th scope="col"><spring:message
							code="lable.tr.admin.manage.form.table.header.adminAuthority" /></th>
					<th scope="col">편집하기</th>
				</tr>
			</thead>
			<c:forEach var="admin" items="${page.content}">
				<tr>
					<td>${f:h(admin.adminCode)}</td>
					<td>${f:h(admin.adminName)}</td>
					<td>${f:h(admin.adminMail)}</td>
					<td>${f:h(admin.adminTel)}</td>
					<td>${f:h(admin.adminAuthority)}</td>
					<td><form:form method="get"
							action="${pageContext.request.contextPath}/joinstorerequest/create/${seller.sellerName}">
							<button class="btn btn-primary btn-sm" name="form"
								style="margin: 0 auto; position: relative; left: 30%; padding: 3px 6px">
								편집</button>
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">
			<t:pagination page="${page}" criteriaQuery="${f:query(admin)}" />
		</div>
	</c:if>
	<c:if test="${pageSize <= 0}">
		<spring:message code="lable.tr.no.result" />
	</c:if>
	<div class="span-5">
		<form:form method="post"
			action="${pageContext.request.contextPath}/admin">
			<div class="span-5">
				<button id="searchTourBtn" name="initAdminRequestForm"
					style="width: 150px;">
					<spring:message code="label.tr.menu.return" />
				</button>
			</div>
		</form:form>
	</div>
</div>