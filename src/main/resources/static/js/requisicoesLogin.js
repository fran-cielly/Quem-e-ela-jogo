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
                cod = resp.cod
                if(cod === 404){
                    alert(resp.mensagem)
                }else if(cod === 200){
                    alert("Cadastrado com sucesso")
                    window.location.href = "/home"
                }
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
            success: function(resp){
                cod = resp.cod
                if(cod === 404){
                    alert("Usuário não encontrado")
                }else if(cod === 200){
                    alert("Logado com sucesso")
                    window.location.href = "/home"
                }
            }
        })
        .done(function(resp){
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });
});
