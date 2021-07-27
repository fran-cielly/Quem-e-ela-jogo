$(document).ready(function() {
    $("#btn-login").click(function(){
        alert("aaaaa")
        $.ajax({
            url : "http://localhost:9090/personagem/listar",
            type : 'post',
            data : {},
            beforeSend : function(){
                
            }
        })
        .done(function(msg){
            
        })
        .fail(function(jqXHR, textStatus, msg){
                alert(msg);
        });
    });

    $("#btn-cadastro").click(function(){
        alert("aaaaa")
        $.ajax({
            url : "http://localhost:9090/personagem/listar",
            type : 'get',
            contentType: 'application/json',
            cache: false,
            method: 'get',
            dataType: 'json',
            data: JSON.stringify({
                nome : $("#nomeUsuarioCadastro").value,
                senha : $("#senhaUsuarioCadastro").value,
                foto:"aaaa",
                data_cadastro:null
            }),
            beforeSend : function(){
                
            }
        })
        .done(function(msg){
            alert(msg);
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });
});