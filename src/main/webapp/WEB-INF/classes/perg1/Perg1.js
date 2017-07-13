
function CB(bg) {
var url;
var novo_txt;
var txt;
var desc;
    if(bg=="pred_laranja"){
        url= "cores-predominantes/predominancia-laranja.png";
        txt = "Predominância Laranja:"
        desc = "Interface com predominância da cor laranja";
        
    }else if(bg=="pred_branca"){
    	url="cores-predominantes/predominancia-branca.png";
    	txt = "Predominância Branca:"
        desc = "Interface com predominância da cor branca";
        
        
	}else if(bg == "pred_verde"){
		url="cores-predominantes/predominancia-verde.png";
		txt = "Predominância Verde:"
	    desc = "Interface com predominância da cor verde";
   
	        
	}else if(bg == "pred_azul"){
		url="cores-predominantes/predominancia-azul.png";
		txt = "Predominância Azul:"
		desc = "Interface com predominância da cor azul";
	 
		
	}else if(bg == "pred_amarela"){
		url="cores-predominantes/predominancia-amarela.png";
		txt = "Predominância Amarela:"
		desc = "Interface com predominância da cor Amarela";
		
	}	
    document.getElementById("imagem").src = url;
    document.getElementById("texto").childNodes[0].nodeValue = txt;
    $("#desc").text(desc);
    
 
   
}



function move(progress, total) {
	  var elem = document.getElementById("myBar"); 
	  elem.style.width = 300/total*progress;
	  var width = 10;
	  var id = setInterval(frame, 10);
	  function frame() {
	    if (width >= 100) {
	      clearInterval(id);
	    } else {
	      width++; 
	      elem.style.width = width + '%'; 
	      elem.innerHTML = width * 1  + '%';
	    }
	  }
	}