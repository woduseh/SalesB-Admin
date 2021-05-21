<div class="container">
	<br> <br> <br>
	<!-- 로그인한 Admin의 데이터를 읽어오기-->
	<p id="messagesArea" class="box">
		<sec:authorize access="hasRole('ADMIN')">
			<spring:message code="label.tr.menu.adminMenuMessage" />
			<sec:authentication property="principal.username" />
		</sec:authorize>
		<sec:authorize access="!hasRole('ADMIN')">
			로그인 해주세요.
		</sec:authorize>
	</p>

	<sec:authorize access="hasRole('ADMIN')">
		<div class="span-24 last">
			<c:if test="${pageSize > 0}">
				<table id="requestTable">
					<thead>
						<tr>
							<th><spring:message
									code="lable.tr.admin.request.form.table.introduce" /></th>
						</tr>
						<tr>
							<th scope="col"><spring:message
									code="lable.tr.admin.request.form.table.header.seq" /></th>
							<th scope="col"><spring:message
									code="lable.tr.admin.request.form.table.header.client" /></th>
							<th scope="col"><spring:message
									code="lable.tr.admin.request.form.table.header.request.name" /></th>
							<th scope="col"><spring:message
									code="lable.tr.admin.request.form.table.header.request.content" /></th>
							<th scope="col"><spring:message
									code="lable.tr.admin.request.form.table.header.update.time" /></th>
						</tr>
					</thead>
					<c:forEach var="Request" items="${page.content}">
						<tr>
							<td>${f:h(Request.seq)}</td>
							<td>${f:h(Request.client)}</td>
							<td>${f:h(Request.requestName)}</td>
							<td>${f:h(Request.requestContents)}</td>
							<td>${f:h(Request.updateTime)}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${pageSize <= 0}">
				<spring:message code="lable.tr.menu.no.result" />
			</c:if>
		</div>
		<div class="span-5">
			<form:form method="get"
				action="${pageContext.request.contextPath}/admin/request">
				<div class="span-5">
					<button id="searchTourBtn" name="initAdminRequestForm"
						style="width: 150px;">
						<spring:message code="label.tr.menu.request.manage" />
					</button>
				</div>
			</form:form>
		</div>
		<div class="span-5">
			<form:form method="get"
				action="${pageContext.request.contextPath}/admin/storelist">
				<div class="span-5">
					<button id="searchTourBtn" name="initStoreListForm"
						style="width: 150px;">
						<spring:message code="label.tr.menu.store.manage" />
					</button>
				</div>
			</form:form>
		</div>

		<!-- 권한 관리 -->
		<sec:authorize access="hasRole('MASTERADMIN')">
			<div class="span-5">
				<form:form method="get"
					action="${pageContext.request.contextPath}/admin/manageadmin">
					<div class="span-5">
						<button id="searchTourBtn" name="initManageAdminForm"
							style="width: 150px;">
							Manage Admin
							<%-- <spring:message code="label.tr.menu.request.manage" /> --%>
						</button>
					</div>
				</form:form>
			</div>
		</sec:authorize>

		<!-- 로그아웃-->
		<div class="span-5">
			<form:form action="${pageContext.request.contextPath}/logout"
				cssClass="inline">
				<button id="logoutBtn" name="logout">
					<spring:message code="label.tr.common.logout" />
				</button>
			</form:form>
		</div>
	</sec:authorize>
</div>
