<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Please enter new CD details</h1>
<form:form method="post" action="addCD.html" modelAttribute="compactDiscModel">
Please enter a title:
<form:input path="title" />
<FONT color="red"><form:errors path="title" /></FONT>
<br/>
Please enter an artist:
<form:input path="artist" />
<FONT color="red"><form:errors path="artist" /></FONT>
<br/>
Please enter a price:
<form:input path="price" />
<FONT color="red"><form:errors path="price" /></FONT>
<br/>
Please enter how many tracks:
<form:input path="tracks" />
<FONT color="red"><form:errors path="tracks" /></FONT>
<br/>
<input name="submit" type="submit"/>
 <br/>
</form:form>
                