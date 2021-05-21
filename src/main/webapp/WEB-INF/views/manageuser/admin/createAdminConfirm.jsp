
<div class="container"><br><br><br>

  <div class="info">
    <p>
      <spring:message code="label.tr.managecustomer.createConfirmMessage" />
    </p>
  </div>
  <form:form modelAttribute="adminForm">
    <jsp:include page="../fragment/adminTable.jsp" />

    <!-- Include all the hidden items here. 
			These will be mapped to the modelAttribute form -->

    <form:hidden path="adminName" />
    <form:hidden path="adminMail" />
    <form:hidden path="adminTel" />
    <form:hidden path="adminPass" />
    <form:hidden path="adminPassConfirm" />

    <form:button id="backToFormBtn" name="redo">
      <spring:message code="label.tr.common.goBackMessage" />
    </form:button>
            &nbsp;
		<form:button id="registerBtn">
      <spring:message code="label.tr.common.register" />
    </form:button>
  </form:form>

</div>
