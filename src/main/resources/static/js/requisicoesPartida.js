$(document).ready(function() {

    //Verificar se já existe uma partida em andamento, caso não exista, criar uma
    $.ajax({
        url : "/personagem/listar",
        method : "GET",
        contentType : 'application/json',
        dataType : 'json',
        success: function(resp){
            alert(resp);
        }
    })
    .done(function(resp){
    })
    .fail(function(jqXHR, textStatus, msg){
        alert(msg);
    });

     //Cadastro de usuario
    /*$("#btn-partida-maquina").click(function(){
        $.ajax({
            url : "/partida/nova",
            method : "GET",
            contentType : 'application/json',
            dataType : 'json',
            success: function(resp){
                alert(resp);
                window.location.href = "/partida"
                
            }
        })
        .done(function(resp){
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });*/
});