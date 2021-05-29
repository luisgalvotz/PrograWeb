
$('#ConfirmaBusquedaA').on('change', function() {
     if ($(this).is(':checked') ){
          console.log("checkbox seleccionado");
         $('#categoriasbusca').removeAttr("disabled");
         $('#FechaInicio').prop('disabled', false);
         $('#FechaFin').prop('disabled', false);
         $('#NutilBusca').prop('disabled', false);
         $('#NfavoritaBusca').prop('disabled', false);
     }else{
          $('#categoriasbusca').prop('disabled', true);
          $('#FechaInicio').prop('disabled', true);
          $('#FechaFin').prop('disabled', true);
          $('#NutilBusca').prop('disabled', true);
          $('#NfavoritaBusca').prop('disabled', true);

     }
});

// var checkbox = document.getElementById('ConfirmaBusquedaA');
// checkbox.addEventListener( 'change', function() {
//     if(this.checked) {
//        alert('checkbox esta seleccionado');
//     }
// });

