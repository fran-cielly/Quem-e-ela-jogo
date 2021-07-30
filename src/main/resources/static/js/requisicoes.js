$(document).ready(function() {
    $("#btn-login").click(function(){
        alert("aaaaa")
        $.ajax({
            url : "/personagem/listar",
            method : "POST",
            contentType : 'application/json',
            dataType : 'json',
            data : JSON.stringify(dadosForm)
        })
        .success(function(data){
            alert(data);
            alert(data.responseText);
        })
        .done(function(msg){
            
        })
        .fail(function(jqXHR, textStatus, msg){
            alert(msg);
        });
    });
});