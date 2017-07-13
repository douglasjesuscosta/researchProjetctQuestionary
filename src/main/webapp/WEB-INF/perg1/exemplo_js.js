
function CB(bg) {
var url;
    if(bg=="agree")
    {
        url="http://www.menucool.com/slider/prod/image-slider-1.jpg";
    document.getElementById("imagem").src = url;
    }
    else if(bg=="neutral")
       url="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBZWrNYkKQJHoDGIpFY3Vfb58dZbRpo6TPPfhjdYs1rUtjIt2hKm5VBaM";
    document.getElementById("imagem").src = url;
    {
    url="http://www.menucool.com/slider/prod/image-slider-3.jpg";
    document.getElementById("ecard").style.backgroundImage = "url(" + url + ")";
    }
}
