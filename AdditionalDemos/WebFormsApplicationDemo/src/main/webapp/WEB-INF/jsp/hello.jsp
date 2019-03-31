<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Please enter new CD details</h1>
<form:form method="get" action="addName.html" modelAttribute="helloModel">
Please enter a name:
<form:input path="name" />
<input name="submit" type="submit"/>
 <br/>
</form:form>