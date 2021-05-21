<!-- 
function fn_nameOverlapChk(){
	$.ajax({
		url:"/ManageAdmin/nameOverlapChk",
		type:"post",
		dataType:"json",
		data:{"adminName":$("#adminName").val()},
		success: function(data){
			if(data>0)
				alart("ID OVERLAPED!");
			else {
				$("#nameOverlapChk").attr("value", "Y");
				alert("able to use.")
			}
		}
	})
}
 -->

<div class="container">
  <jsp:include page="../../common/top.jsp" />
  <form:form modelAttribute="adminForm">
    <fieldset>
      <legend>
        <spring:message code="label.tr.manageadmin.manageadminMessage" />
      </legend>
      <form:errors path="*" cssClass="error" element="div" />
      <p>
        <%-- Name --%>
        <form:label path="adminName">
          <spring:message code="label.tr.common.adminname" />
					(<spring:message code="label.tr.common.required" />)
				</form:label>
        <br>
        <form:input type="text" cssClass="text" path="adminName" />
        &nbsp;(
        <spring:message code="label.tr.managecustomer.englishOnlyMessage" />
        )
        <!-- <button class='idChk' type="button" id="idChk" onclick="fn_nameOverlapChk();" value="N">OVERLAP CHECK</button> -->
      <p>
        <%-- EMail --%>
        <form:label path="adminMail">
          <spring:message code="label.tr.common.email" />
        </form:label>
        <br>
        <form:input path="adminMail" cssClass="text" />
      </p>
      <p>
        <%-- Tel --%>
        <form:label path="adminTel">
          <spring:message code="label.tr.common.tel" />
					(<spring:message code="label.tr.common.required" />)
				</form:label>
        <br>
        <form:input path="adminTel" cssClass="text" />
        &nbsp;(
        <spring:message code="label.tr.managecustomer.halfTelOnlyMessage" />
        )&nbsp;
        <spring:message code="label.tr.managecustomer.telExampleMessage" />
      </p>
      <p>
        <%-- Password --%>
        <form:label path="adminPass">
          <spring:message code="label.tr.common.password" />
					(<spring:message code="label.tr.common.required" />)
				</form:label>
        <br>
        <form:password path="adminPass" cssClass="text" />
        &nbsp;(
        <spring:message code="label.tr.managecustomer.halfPasswordOnlyMessage" />
        )
      </p>
      <p>
        <%-- Password Confirm --%>
        <form:label path="adminPassConfirm">
          <spring:message code="label.tr.common.passwordConfirm" />
					(<spring:message code="label.tr.common.required" />)
				</form:label>
        <br>
        <form:password path="adminPassConfirm" cssClass="text" />
      </p>
      <p>
        <button id="confirmBtn" name="confirm">
          <spring:message code="label.tr.common.confirm" />
        </button>
        <button id="resetBtn" type="reset">
          <spring:message code="label.tr.common.reset" />
        </button>
      </p>
    </fieldset>
  </form:form>
</div>
