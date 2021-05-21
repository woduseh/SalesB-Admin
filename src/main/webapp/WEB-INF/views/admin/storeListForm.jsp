<div class="container">
	<div class="search container">
		<form:form method="get" modelAttribute="AdminStoreListForm">
			<div class="form-inline">
				<form:input path="searchKeywords" placeholder="점포명을 입력하세요" />
				<button id="searchBtn" class="btn btn-primary">
					<spring:message code="label.tr.common.search" />
				</button>
			</div>
		</form:form>
	</div>
	<c:if test="${pageSize > 0}">
		<table id="requestTable">
			<thead>
				<tr>
					<th>가상 스토어 리스트</th>
				</tr>
				<tr>
					<th scope="col">점포명</th>
					<th scope="col">만료 여부</th>
					<th scope="col">만료 여부 편집</th>
				</tr>
			</thead>
			<c:forEach var="Store" items="${page.content}">
				<tr>
					<td style="text-align: center;">${f:h(Store.storeName)}</td>
					<c:if test="${Store.isExpired == 0}">
						<td style="text-align: center;">유효함</td>
					</c:if>
					<c:if test="${Store.isExpired == 1}">
						<td style="text-align: center;">만료됨</td>
					</c:if>
					<c:if test="${Store.isExpired == 0}">
						<td style="text-align: center;"><form:form method="get"
								action="${pageContext.request.contextPath}/admin/storelist/${Store.storeCode}/expired">
								<button class="btn btn-primary btn-sm"
									style="margin: 0 auto; position: relative;">만료 처리하기!</button>
							</form:form></td>
					</c:if>
					<c:if test="${Store.isExpired == 1}">
						<td style="text-align: center;"><form:form method="get"
								action="${pageContext.request.contextPath}/admin/storelist/${Store.storeCode}/notexpired">
								<button class="btn btn-primary btn-sm"
									style="margin: 0 auto; position: relative;">만료 처리 해제!</button>
							</form:form></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">
			<t:pagination page="${page}" criteriaQuery="${f:query(Store)}" />
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