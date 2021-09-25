$(document).ready(function() {

    //modal com o tutorial do jogo
    $("#modal-entrada").modal('show');

    //Quando o jogador clicar na carta ela sera desabilitada caso esa varivel seja false
    var fazerTentativa = false;
    var rodada = 0;
    var tutorialpasso = 1;

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
                        //alert(resp.mensagem);
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
                                if(resp.cod == 1){
                                    $("#modal-acertou").modal('show');
                                }else{
                                    $("#modal-errou").modal('show');
                                }
                              
                            }
                        })
                        .done(function(resp){
                            novaRodada();
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

        $(".card-personagem").removeClass("card-personagem-removido");

        if(rodada === 2){
            //finalizar partida ao fim da 3 rodada
            $.ajax({
                url : "/partida/fim",
                method : "GET",
                contentType : 'application/json',
                dataType : 'json',
                success: function(resp){

                    var partida = resp;

                    var info = "";

                    info+="<h1>Pontuação: "+partida.pontuacao_jogador1+"</h1><br>";

                    var lista = Object.values(partida.rodadas);
                    var nrodada = 1;
                    lista.forEach(rodadaFim =>{
                        info+="Rodada "+nrodada+":<br>";
                        //info+="Pergunta:"+rodadaFim.pergunta+"<br>";
                        info+="Figura misteriosa: "+rodadaFim.figura_misteriosa.nome+"<br>";
                        info+="Pontuação: "+rodadaFim.pontuacao_jogador1+"<br>";
                        nrodada++;
                    });

                    $("#info-fim-jogo").append(info);
                    $("#modal-fim-jogo").modal('show');
                }
            })
            .done(function(resp){
                //alert(resp)
            })
            .fail(function(jqXHR, textStatus, msg){
                //alert(msg);
            });

        }else{
            $.ajax({
                url : "/rodada/nova",
                method : "GET",
                contentType : 'application/json',
                success: function(resp){
                    rodada++;
                }
            })
            .done(function(resp){
                //alert(resp)
            })
            .fail(function(jqXHR, textStatus, msg){
                //alert(msg);
            });
        }
        pauseContador();
        resetContador();
    }

    //Clicar no botao para fazer uma tentativa de acertar uma figura misteriosa
    $("#btn-tentativa").click(function(){
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

    //Passar imagem do tutorial para frente
    $(".prox-tutorial").click(function(){
        if(tutorialpasso < 5){
            tutorialpasso++;
        }else{
            tutorialpasso = 1;
        }
        $(".img-tutorial").html("");
        $(".img-tutorial").append("<img src='css/img/tutorial/tutorial-"+tutorialpasso+".png'>");
    });

     //Passar imagem do tutorial para tras
    $(".ant-tutorial").click(function(){
        if(tutorialpasso > 1){
            tutorialpasso--;
        }else{
            tutorialpasso = 5;
        }
        $(".img-tutorial").html("");
        $(".img-tutorial").append("<img src='css/img/tutorial/tutorial-"+tutorialpasso+".png'>");
    });

    //Para quando abrir o modal de ajuda abrir o tutorial 1
    $("#btn-help").click(function(){
        tutorialpasso = 1;
    });
});