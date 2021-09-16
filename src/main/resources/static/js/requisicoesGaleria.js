$(document).ready(function() {

    $.ajax({
        url : "/personagem/listar",
        method : "GET",
        contentType : 'application/json',
        dataType : 'json',
        success: function(resp){
            var lista = Object.values(resp);
            var n=0;
            var caixas ="";
            lista.forEach(personagem =>{
                console.log(personagem.nome)
                if(n == 0){
                    caixas+="<div class='row mx-md-n2'>";
                }else if(n%6==0){
                    caixas+="</div><div class='row mx-md-n2'>"
                }
                caixas+="<div class='col-2 px-md-2'><div codPersonagem='"+personagem.id+"' class='card-personagem'>"
                    +"<img src='css/img/personagens/"+personagem.foto+"'>"
                    +"<p>"+personagem.nome+"</p>"
                    +"</div></div>";
                n++;
            });
            caixas+="</div>";
            $("#caixa-personagem-galeria").append(caixas);
        }
    })
    .done(function(resp){
    })
    .fail(function(jqXHR, textStatus, msg){
    });
});