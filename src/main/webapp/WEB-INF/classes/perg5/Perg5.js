
function CB(bg) {
var url;
    if(bg=="org-abas"){
        url= "/organização-informação/org-abas.png";
        txt = "Organização por abas:";
        desc = "Os bimestres são organizados conforme abas e cores para a diferenciação.";
        

    }else if(bg=="org-importancia"){
    	url= "/organização-informação/org-importancia.png";
    	txt = "Organização por abas:";
        desc = "As publicações feitas no ambiente online são classificadas conforme a importância, sendo assim destacadas.";
        
	}else if(bg == "org-tempopublicacao"){
		url= "/organização-informação/org-tempopublicacao.png";
		txt = "Organização por tempo de publicação:";
        desc = "As publicações feitas no ambiente online são classificadas conforme o tempo de publicação.";
        
        
	}else if(bg == "org-topicos"){
		url= "/organização-informação/org-topicos.png";
		txt = "Organização por tópicos";
        desc = "As publicações feitas no ambiente online são classificadas conforme tópicos.";
        
	}else if(bg == "org-topicos-e-cores"){
		url= "/organização-informação/org-topicos-e-cores.png";
		txt = "Organização por cores e tópicos";
        desc = "As publicações feitas no ambiente online são classificadas conforme tópicos.";
        
	}	
    document.getElementById("texto").childNodes[0].nodeValue = txt;
    $("#desc").text(desc);
    document.getElementById("imagem").src = url;	
    url="http://www.menucool.com/slider/prod/image-slider-3.jpg";
    document.getElementById("ecard").style.backgroundImage = "url(" + url + ")";
}

