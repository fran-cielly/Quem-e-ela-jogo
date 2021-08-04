$(document).ready(function() {

    //Cadastro de usuario
    $("#btn-cadastro").click(function(){

        $.ajax({
            url : "/usuario/cadastrar",
            method : "POST",
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify({
                "nome": $('#nomeUsuarioCadastro').val(),
                "senha": $('#senhaUsuarioCadastro').val(),
                "foto":""
            }),
            success: function(res){
               
            }
        })
        .done(function(msg){
           
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });

    //Login
    $("#btn-login").click(function(){

        $.ajax({
            url : "/usuario/login",
            method : "POST",
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify({
                "nome": $('#nomeUsuario').val(),
                "senha": $('#senhaUsuario').val(),
            }),
            success: function(res){
               
            }
        })
        .done(function(resp){
            var msg = JSON.parse(resp);    
            if(msg == 200){
                window.location.href = "/home"
            }
        	
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });
});
