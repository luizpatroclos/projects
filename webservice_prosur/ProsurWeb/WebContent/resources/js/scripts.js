/*----------------------------------------------------------------------------
Formatação para somente numeros.
-----------------------------------------------------------------------------*/
function SomenteNumero(e){
	 var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) return true;
    else{
    	if (tecla==8 || tecla==0) return true;
	else  return false;
    }
}

function somenteNumeroEPontoVirgula(e){
   
	var tecla=(window.event)?event.keyCode:e.which;   
    
	if((tecla>47 && tecla<58) && tecla == 44){
		return true;
	}
	
	else{
    	if (tecla==8 || tecla==0){
    		return true;
    	}
	  
    	return false;
    }
}

jsf.ajax.addOnEvent(function(data) {
    if (data.source.type != "submit") {
        return; // Ignore anything else than input type="submit".
    }

    switch (data.status) {
        case "begin":
            data.source.disabled = true; // Disable input type="submit".
            break;
        case "complete":
            data.source.disabled = false; // Re-enable input type="submit".
            break;
    }    
});

function HoraMask(inputData, e){
	if(document.all) // Internet Explorer
	var tecla = event.keyCode;
	else //Outros Browsers
	var tecla = e.which;

	if(tecla >= 47 && tecla < 58){ // numeros de 0 a 9 e "/"
	var data = inputData.value;
	if (data.length == 2 ){
	data += ':';
	inputData.value = data;
	}
	}else if(tecla == 8 || tecla == 0) // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF)
	return true;
	else
	return false;
	}