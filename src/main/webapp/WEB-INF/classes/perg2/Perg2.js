
function CB(bg) {
var url;

    if(bg=="frt_ludico"){
        url= "/elementos-ludicos/frt_ludico.png";
        txt = "Interface com muitos elementos lúdicos:"
        	
        desc = "Interface com maior apresentação de textos e figuras e cores, buscando assim trazer uma experiência " +
        		" mais ilustrativa e com maior referência de figuras. O texto e as imagens utilizadas na figura representam" +
        		" apenas uma ilustração desse conceito de material de aprendizagem.";
        
    }else if(bg=="frc_ludico"){
    	url="/elementos-ludicos/frc_ludico.png";
    	 txt = "Interface com moderados elementos lúdicos:"
         	
    	 desc = "Interface que traz recursos como a mudança de cor para enfatizar um conteúdo sem tabém trazer muitos" +
    	 		"recursos lúdicos para a exposição do assunto tratado.";        
    	 
	}else if(bg == "neutro"){
		url="/elementos-ludicos/neutro.png";
		
		 txt = "Interface neutra:"
	     desc = "Interface tradicional que apenas traz figuras para orientar a navegação do usuário," +
	    	 	"sem grandes interferências nos materiais de aprendizado.";
	    	        
	}else if(bg == "frc_textual"){
		url="/elementos-ludicos/frc_textual.png";
		txt = "Interface com predominância textual:"
		desc = "Interface traz apenas alguma figuras para a orientação na navegação principal, sendo predominantemente" +
		       " textual.";
		    	        
	}else if(bg == "frt_textual"){
		url="/elementos-ludicos/frt_textual.png";
		
		txt = "Interface totalmente textual:"
			desc = "Interface com a predominante apresentação de elementos textuais, sem imagens de orientação na navegação." +
			       " textual.";
	}	
    document.getElementById("texto").childNodes[0].nodeValue = txt;
    $("#desc").text(desc);
   	   
    $("#table-text").css("background-color", "LightBlue");
    $("#texto").css("color", "black");
    $("#desc").css("color", "black");
    $("#desc").css("text-align", "justify");
    
    document.getElementById("imagem").src = url;	
    url="http://www.menucool.com/slider/prod/image-slider-3.jpg";
    document.getElementById("ecard").style.backgroundImage = "url(" + url + ")";
}