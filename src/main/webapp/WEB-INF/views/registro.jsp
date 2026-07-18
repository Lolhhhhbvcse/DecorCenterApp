<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="tituloPagina" value="Crear cuenta — DecorCenter" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" />

<div class="form-card">
    <h2 class="titulo-serif">Crea tu cuenta</h2>

    <c:if test="${not empty error}">
        <div class="alerta alerta-error"><c:out value="${error}"/></div>
    </c:if>

    <form action="${pageContext.request.contextPath}/registro" method="post">
        <div class="form-grupo">
            <label>Nombre completo</label>
            <input type="text" name="nombre" required>
        </div>
        <div class="form-grupo">
            <label>Correo electrónico</label>
            <input type="email" name="email" required>
        </div>
        <div class="form-grupo">
            <label>Dirección</label>
            <input type="text" name="direccion">
        </div>
        <div class="form-grupo">
            <label>Teléfono</label>
            <input type="text" name="telefono">
        </div>
        <!-- Campos de contraseña agregados -->
        <div class="form-grupo">
            <label>Contraseña</label>
            <input type="password" name="password" required>
        </div>
        <div class="form-grupo">
            <label>Confirmar contraseña</label>
            <input type="password" name="confirmar" required>
        </div>
        
        <button type="submit" class="btn btn-primario" style="width:100%;">Crear cuenta</button>
    </form>

    <p class="enlace-secundario">¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/login">Inicia sesión</a></p>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
