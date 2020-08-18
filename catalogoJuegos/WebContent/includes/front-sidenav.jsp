
<!-- INICIO SIDENAV -->
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
		                   <div class="sb-sidenav-menu-heading">Inicio</div>
                            <a class="nav-link" href="views/frontoffice/inicio">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Inicio
                            </a> 
                            <div class="sb-sidenav-menu-heading">Administracion</div>
                            <a class="nav-link" href="views/frontoffice/juegos?validar=1">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Juegos validados
                            </a>                     
                            
                            <a class="nav-link" href="views/frontoffice/juegos">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Juegos pendientes de validar
                            </a>
                            <a class="nav-link" href="views/frontoffice/crear-juegos">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Crear juegos
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Conectado como:</div>
                        ${usuario.nombre }
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
         
 <!-- FIN SIDENAV -->