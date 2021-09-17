$(document).ready(function() {

    //modal com o tutorial do jogo
    $("#modal-entrada").modal('show');

    //Quando o jogador clicar na carta ela sera desabilitada caso esa varivel seja false
    var fazerTentativa = false;
    var rodada = 0;

    //funcoes que criam os elementos da tela
    //funcao para listar as perguntas dentro da div
    function listarPerguntar(resp){
        var lista = Object.values(resp);
        $("#listar-perguntas").html("");
        lista.forEach(pergunta =>{
            $("#listar-perguntas").append("<div idpergunta='"+pergunta.id+"' class='pergunta col-12'>"+pergunta.texto+"</div><br>");
        });
        //funcao para enviar a pergunta
        $(".pergunta").click(function(){
            var idPergunta = $(this).attr("idpergunta");
            var textoPergunta = $(this).html();

            //funcao para receber resposta da pergunta e mostrar na tela
            $.ajax({
                url : "/partida/perguntar",
                method : "POST",
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify({
                    "id":idPergunta
                }),
                success: function(resp){

                    if(resp.cod!=null && resp.cod == 400){
                        alert(resp.mensagem);
                    }else{
                        $("#modal-perguntas").modal('hide');

                        $("#resposta-titulo").html(textoPergunta);
                        $("#resposta-texto").html(resp);
                                        
                        $("#modal-resposta").modal('show');
                    }
                }
            })
            .done(function(resp){
                //alert(resp)
            })
            .fail(function(jqXHR, textStatus, msg){
                //alert(msg);
            });
        });
    }
   
    
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
                    caixas+="<div class='col-2 px-md-2'><div codPersonagem='"+personagem.id+"' class='card-personagem'>"
                        +"<img src='css/img/personagens/"+personagem.foto+"'>"
                        +"<p>"+personagem.nome+"</p>"
                        +"</div></div>";
                    n++;
                });
                caixas+="</div>"
                $("#caixa-personagem").append(caixas);

                //funcao quando o jogador clica em alguma personagem, normalmente ele desabilita a cara, caso ele clique em fazer uma tentativa
                //de acertar entao o valor da variavel fazerTentativa mudara para true
                $(".card-personagem").click(function(){
                    if(fazerTentativa == false){
                        if($(this).hasClass("card-personagem-removido")){
                            $(this).removeClass("card-personagem-removido");
                        }else{
                            $(this).addClass("card-personagem-removido");
                            alertify.message("Personagem abaixado com sucesso");
                        }
                    }else{
                        fazerTentativa = false;

                        $.ajax({
                            url : "/partida/tentativa",
                            method : "POST",
                            contentType : 'application/json',
                            dataType : 'json',
                            data : JSON.stringify({
                                "id": $(this).attr("codpersonagem")
                            }),
                            success: function(resp){
                                novaRodada();
                                if(resp.cod == 1){
                                    $("#modal-acertou").modal('show');
                                }else{
                                    $("#modal-errou").modal('show');
                                }
                            }
                        })
                        .done(function(resp){
                            //alert(resp)
                        })
                        .fail(function(jqXHR, textStatus, msg){
                            //alert(msg);
                        });
                    }
                });
            }
        })
        .done(function(resp){
        })
        .fail(function(jqXHR, textStatus, msg){
        });
    })
    .fail(function(jqXHR, textStatus, msg){
        //alert(msg);
    });

    //Começar nova rodada
    function novaRodada(){
        pauseContador();
        resetContador();

        $(".card-personagem").removeClass("card-personagem-removido");

        $.ajax({
            url : "/rodada/nova",
            method : "GET",
            contentType : 'application/json',
            dataType : 'json',
            success: function(resp){
                if(rodada == 3){
                    $("#modal-fim-jogou").modal('show');
                }else{
                    rodada++;
                }
            }
        })
        .done(function(resp){
            //alert(resp)
        })
        .fail(function(jqXHR, textStatus, msg){
            //alert(msg);
        });
    }

    //Clicar no botao para fazer uma tentativa de acertar uma figura misteriosa
    $("#btn-tentativa").click(function(){
        alertify.message("Personagem abaixado com sucesso");
        fazerTentativa = true;
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
                listarPerguntar(resp);
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
                listarPerguntar(resp);
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