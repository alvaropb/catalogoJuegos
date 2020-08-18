<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/front-cabecera.jsp"/>
<jsp:include page="../../includes/front-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>

<!-- INICIO INDEX FRONTOFFICE -->
                        <h1 class="mt-4">Juegos</h1>
                        

                        <div class="row">
	                        
	                        <div class="col-xl-3 col-md-6">
	                                <div class="card bg-primary text-white mb-4">
	                                    <div class="card-body">JUEGOS TOTALES<h1> ${resumen.juegosTotales }</h1></div>
	                                    <div class="card-footer d-flex align-items-center justify-content-between">
	                                        <a class="small text-white stretched-link" href="#">ver detalle</a>
	                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xl-3 col-md-6">
	                                <div class="card bg-danger text-white mb-4">
	                                    <div class="card-body">PENDIENTES DE VALIDAR<h1> ${resumen.juegosPendientes }</h1></div>
	                                    <div class="card-footer d-flex align-items-center justify-content-between">
	                                        <a class="small text-white stretched-link" href="#">ver detalle</a>
	                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xl-3 col-md-6">
	                                <div class="card bg-success text-white mb-4">
	                                    <div class="card-body">JUEGOS VALIDADOS <h1> ${resumen.juegosValidados }</h1></div>
	                                    <div class="card-footer d-flex align-items-center justify-content-between">
	                                        <a class="small text-white stretched-link" href="#">ver detalle</a>
	                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
	                                    </div>
	                                </div>
	                            </div>
	 
                        </div>

<!-- FIN INDEX FRONTOFFICE -->
  <jsp:include page="../../includes/front-footer.jsp"/>              