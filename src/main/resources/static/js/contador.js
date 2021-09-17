$(document).ready(function() {
    //funcao para inicializar contador da rodada no comeco da partida
    $(".btn-comecar").click(function(){
        startContador();
    });
});

var con, minutos=0, segundos=0, max=3;

//inicializa o cronometro
function startContador() {
    pauseContador();
    con = setInterval(() => { contador(); }, 1000);
}
//pausa o cronometro
function pauseContador() {
    clearInterval(con);
}

//reseta o cronometro
function resetContador() {
    minutos = 0;
    segundos = 0;
    $('#minutos').text('03');
    $('#segundos').text('00');
}

function contador(){
    segundos++;
    if (segundos == 60) {
        segundos = 0;
        minutos++;
    }

    if(minutos == 3){
        $("#modal-rodada-acabou").modal('show');
        novaRodada();
    }

    $('#minutos').text(formata(minutos));
    $('#segundos').text(formata(segundos));
}

//caso o numero tenha apenas um digito concatena um zero na frente
function formata(numero) {
    if(numero<10){
        return "0"+numero;
    }else{
        return numero;
    }
}