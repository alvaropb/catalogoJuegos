

function confirmar(nombre){
	
	if(confirm('¿Esta seguro que desea eliminar este juego: '+nombre+'?')){
		
	}else{
		event.preventDefault();
	}
	
}

function convertir(){
	
		var campo= document.getElementById("pass").value;
			
		var hash = md5(campo);
	
		document.getElementById("pass").value=hash;
}
function buscarUsuario(event) {
	 //console.debug(event);
	 const nombre = event.target.value;
	 console.debug(`valor del input ${nombre}`);
	 
	 let elNombreHelp = document.getElementById('nombreHelp');
	 
	 //TODO llamada Ajax
	 if ( nombre == 'ander' ){
	  elNombreHelp.innerHTML = 'nombre no disponible';
	  
	 }else{
	  elNombreHelp.innerHTML = 'nombre disponible';
	  
	 }
	 
	 
	}
