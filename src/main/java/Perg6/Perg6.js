
function CB(bg) {
var url;
var txt;
var desc;

    if(bg=="frt_semcomunicacao"){
        url= "/elementos-comunicacao/frt_semcomunicacao.png";
        txt = "Ambiente sem ferramentas de interação:"
        desc = "Ambiente sem ferramentas de interação com tutores ou outros discentes.";
        
    }else if(bg=="frc_semcomunicacao"){
    	url= "/elementos-comunicacao/frc_semcomunicacao%20.png";
    	txt = "Ambiente com poucas ferramentas de interação:"
        desc = "Ambiente sem ferramentas de interação com os outros discentes, podendo-se apenas comunicar com o tutor.";
	}else if(bg == "neutro"){
		url= "/elementos-comunicacao/neutro.png";
		txt = "Ambiente neutro:"
	    desc = "Ambiente que apresenta a possibilidade de discussão em fóruns e comunicação com tutores, sendo assim a comunicação com maior enfoque nos estudos.";
	}else if(bg == "frc_comunicacao"){
		url= "/elementos-comunicacao/frc_comunicacao.png";
		txt = "Razoável quantidade de ferramentas de interação:"
		desc = "Ambiente que além do fórum e comunicação com o tutor, apresenta também uma ferramenta de interação mais informal com os estudantes, permmitindo também verificar quem está acessando o ambiente no momento.";
	}else if(bg == "frt_comunicacao"){
		url= "/elementos-comunicacao/frt_comunicacao.png";
		txt = "Grande quantidade de ferramentas de interação:"
		desc = "Ambiente que apresenta todas as ferramentas apresentadas anteriormente juntamente com uma integração às redes sociais dos estudantes.";
	}	
    document.getElementById("imagem").src = url;
    document.getElementById("texto").childNodes[0].nodeValue = txt;
    $("#desc").text(desc);
    
    url="http://www.menucool.com/slider/prod/image-slider-3.jpg";
    document.getElementById("ecard").style.backgroundImage = "url(" + url + ")";
}

