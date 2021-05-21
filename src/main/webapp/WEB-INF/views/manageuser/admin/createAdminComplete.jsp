
<div class="container"><br><br><br>
  <div class="success">
    <p>
      <spring:message code="label.tr.manageadmin.createCompleteMessage"
        arguments="${f:h(admin.adminName)}" />
    </p>
  </div>
  <form:form method="get" action="${pageContext.request.contextPath}/">
    <button id="goToMenuBtn">
      <spring:message code="label.tr.common.gotoMenuMessage" />
    </button>
  </form:form>
</div>
