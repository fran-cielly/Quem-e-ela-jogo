$(document).ready(function() {

    //Começar nova partida
    $.ajax({
        url : "/partida/nova",
        method : "GET",
        contentType : 'application/json',
        dataType : 'json',
        success: function(resp){
           // alert(resp);
        }
    })
    .done(function(resp){
        //alert(resp)
    })
    .fail(function(jqXHR, textStatus, msg){
        //alert(msg);
    });

    //Listar personagens do jogo
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
                caixas+="<div class='col-2 px-md-2'><div class='card-personagem'>"
                    +"<img src='css/img/personagens/"+personagem.foto+"'>"
                    +"<p>"+personagem.nome+"</p>"
                    +"</div></div>";
                n++;
            });
            caixas+="</div>"
            $("#caixa-personagem").append(caixas);

            $(".card-personagem").click(function(){
                if($(this).hasClass("card-personagem-removido")){
                    $(this).removeClass("card-personagem-removido");
                }else{
                    $(this).addClass("card-personagem-removido");
                }
            });
        }
    })
    .done(function(resp){
    })
    .fail(function(jqXHR, textStatus, msg){
    });

    //Listar perguntas quando o modal abrir
    $("#btn-modal-perguntar").click(function(){
        $.ajax({
            url : "/pergunta/listar/categoria",
            method : "POST",
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify({
                "id":1
            }),
            success: function(resp){
                var lista = Object.values(resp);
                $("#listar-perguntas").html("");
                lista.forEach(pergunta =>{
                    $("#listar-perguntas").append("<div class='pergunta'>'"+pergunta.texto+"'></div>");
                });
                $(".pergunta").click(function(){
                    alert(this);
                });
            }
        })
        .done(function(resp){
            //alert(resp)
        })
        .fail(function(jqXHR, textStatus, msg){
            //alert(msg);
        });
    });

     //Listar perguntas quando trocar de categoria
     $(".card-categoria").click(function(){

        $.ajax({
            url : "/pergunta/listar/categoria",
            method : "POST",
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify({
                "id": this.getAttribute("categoria")
            }),
            success: function(resp){
                var lista = Object.values(resp);
                $("#listar-perguntas").html("");
                lista.forEach(pergunta =>{
                    $("#listar-perguntas").append("<div class='pergunta'>'"+pergunta.texto+"'></div>");
                });
                $(".pergunta").click(function(){
                    alert(this);
                });
            }
        })
        .done(function(resp){
            //alert(resp)
        })
        .fail(function(jqXHR, textStatus, msg){
            //alert(msg);
        });
    });
    
   
});