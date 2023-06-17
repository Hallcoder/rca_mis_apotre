<ul id="menu">
	<c:choose>
		<c:when
			test="${authenticatedUser !=null && authenticatedUser.userRole eq 'ADMINISTRATOR'}">
			<%@ include file="adminmenu.jsp"%>
		</c:when>
		<c:when
			test="${authenticatedUser !=null && authenticatedUser.userRole eq 'EMPLOYEE'}">
			<%@ include file="adminmenuemployee.jsp"%>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>
</ul>