
function CB(bg) {
var url;
    if(bg=="arial"){
        url= "/fontes-tipos/arial.png";
        
        txt = "Fonte Arial:";
        desc = "Material de aprendizagem utilizando a fonte Arial";
        
  
        $("#texto").css("font-style", "arial");
        
    }else if(bg=="georgia"){
    	url= "/fontes-tipos/georgia.png";
    	txt = "Fonte Georgia:";
        desc = "Material de aprendizagem utilizando a fonte Georgia";
        
        $("#texto").css("font-style", "georgia");
        $("#desc").css("font-style", "georgia");
    	
	}else if(bg == "timenewromans"){
		url= "/fontes-tipos/timenewromans.png";
		
		txt = "Fonte Time New Romans:";
        desc = "Material de aprendizagem utilizando a fonte Time New Romans";
        
        $("#texto").css("font-style", "timenewromans");
        $("#desc").css("font-style", "timenewromans");
	}else if(bg == "verdana"){
		url= "/fontes-tipos/verdana.png";
		
		txt = "Fonte Verdana:";
        desc = "Material de aprendizagem utilizando a fonte Verdana";
		
        $("#texto").css("font-style", "verdana");
        $("#desc").css("font-style", "verdana");
	}
    document.getElementById("texto").childNodes[0].nodeValue = txt;
    $("#desc").text(desc);
    
    
    document.getElementById("imagem").src = url;	
    url="http://www.menucool.com/slider/prod/image-slider-3.jpg";
    document.getElementById("ecard").style.backgroundImage = "url(" + url + ")";
}