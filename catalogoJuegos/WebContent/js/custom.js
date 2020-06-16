

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
