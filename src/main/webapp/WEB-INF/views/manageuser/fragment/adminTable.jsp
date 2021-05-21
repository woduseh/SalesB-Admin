
<table id="adminTable">
  <tr>
    <td><spring:message code="label.tr.common.name" /></td>
    <td>${f:h(adminForm.adminName)}</td>
  </tr>
  <tr>
    <td><spring:message code="label.tr.common.email" /></td>
    <td>${f:h(adminForm.adminMail)}</td>
  </tr>
  <tr>
    <td><spring:message code="label.tr.common.tel" /></td>
    <td>${f:h(adminForm.adminTel)}</td>
  </tr>
  <tr>
    <td><spring:message code="label.tr.common.password" /></td>
    <td>********</td>
  </tr>
</table>