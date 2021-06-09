jQuery.validator.methods.email = function(value, element) {
     return this.optional(element) || /^([a-zA-ZÁ-ÿ0-9_]+(?:[.-]?[a-zA-Z0-9]+)@[a-zA-Z0-9]+(?:[.-]?[a-zA-Z0-9]+).[a-zA-Z]{2,7})$/.test(value);
}

jQuery.validator.addMethod("sololetra", function(value, element){
     return this.optional(element) || /^[ñÑa-zA-ZÁ-ÿ\s]+$/i.test(value);
}, "Ingrese solo letras");

jQuery.validator.addMethod("formatocontrasenia", function(value, element){
     return this.optional(element) || /^(?=.*.\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(value);

});

var mifecha = $('#FechaNac_Usu');
var hoy = new Date();
var dd = hoy.getDate();
var mm = hoy.getMonth() + 1;
var yyyy = hoy.getFullYear();

if(dd < 10)
     dd = '0' + dd;

if(mm < 10)
     mm = '0' + mm;

hoy = yyyy + '-' + mm + '-' + dd;
mifecha.attr("max", hoy);

function fechavalida(){
     var fecha = mifecha.val();
     if(Date.parse(fecha)){
          if(fecha > hoy){
               alert('la fecha no puede ser mayor a la actual');
               mifecha.val("");
          }
     }
}

jQuery.validator.addMethod(
    "formatofecha",
    function(value, element) {
        var check = false;
        var re = /^\d{1,2}\-\d{1,2}\-\d{4}$/;
            if( re.test(value)){
                var adata = value.split('-');
                var dd = parseInt(adata[0],10);
                var mm = parseInt(adata[1],10);
                var yyyy = parseInt(adata[2],10);
                var xdata = new Date(yyyy,mm-1,dd);
                if ( ( xdata.getFullYear() === yyyy ) && ( xdata.getMonth () === mm - 1 ) && ( xdata.getDate() === dd ) ) {
                check = true;
            }
            else {
                check = false;
            }
        } else {
        check = false;
        }
        return this.optional(element) || check;
    },
    "Wrong date format"
);



$(document).ready(function(){
    


     $("#eliminar_pregunta").click(function(){
          $.sweetModal({
               content: '¿Desea eliminar la pregunta?',
               theme: $.sweetModal.THEME_MIXED,
               buttons: {
                    Conservar:{
                        label:'No eliminar',
                        classes: 'greenB ' 
                    },
                    Eliminar:{
                         label: 'Eliminar',
                         classes: 'redB ', 
                         action: function() {
							var form = $("#form_borrar_preg");
							$(form).submit();
						}
                    }
               }
          })
     });

     $("#eliminar_respuesta_correcta").click(function(){
          $.sweetModal({
               content: '¿Desea eliminar la respuesta?',
               theme: $.sweetModal.THEME_MIXED,
               buttons: {
                    Conservar:{
                        label:'No eliminar',
                        classes: 'greenB ' 
                    },
                    Eliminar:{
                         label: 'Eliminar',
                         classes: 'redB ', 
                         action: function() {
							var form = $("#form_borrar_rc");
							$(form).submit();
						}
                    }
               }
          })
     });

     
     $("#eliminar_respuesta").click(function(){
          $.sweetModal({
               content: '¿Desea eliminar la respuesta?',
               theme: $.sweetModal.THEME_MIXED,
               buttons: {
                    Conservar:{
                        label:'No eliminar',
                        classes: 'greenB ' 
                    },
                    Eliminar:{
                         label: 'Eliminar',
                         classes: 'redB ',
                         action: function() {
							var form = $("#form_borrar_res");
							$(form).submit();
						}
                    }
               }
          })
     });
     

     $("#form_inicio_sesion").validate({
          rules: {
               Nombre_Usu: {
                    required: true,
                
               },
               Contra_Usu:{
                    required: true,
               }
          },

          messages: {
               Nombre_Usu:{
                    required: "Ingrese un nombre de usuario",
                    
               },
               Contra_Usu:{
                    required: "Ingrese la contraseña"
               }
          }
     });


     $("#form_registro").validate({
          rules: {
               Nombre_:{
                    required: true,
                    sololetra: true
               },
               Apellidos_Usu:{
                    required: true,
                    sololetra: true
               },
               FechaNac_Usu:{
                    required: true,  
               },
               Correo_Usu:{
                    required: true,
                    email: true
               },
               Nombre_Usu:{
                    required: true
               },
               Contra_Usu:{
                    required: true,
                    formatocontrasenia: true
               },
               ContraC_Usu:{
                    required: true,
                    equalTo: "#Contra_Usu"
               },
               Imag_Usu:{
                    required: true
               }


          },
          messages: {
               Nombre_:{
                    required: "Ingrese su(s) nombre(s)",
                    sololetra: "Ingrese únicamente letras"
               },
               Apellidos_Usu:{
                    required: "Ingrese su(s) apellido(s)",
                    sololetra: "Ingrese únicamente letras"
               },
               FechaNac_Usu:{
                    required: "Ingrese su fecha de nacimiento"
               },
               Correo_Usu:{
                    required: "Ingrese su correo electrónico",
                    email: "Ingrese un formato de correo"
               },
               Nombre_Usu:{
                    required: "Ingrese su nombre de usuario"
               },
               Contra_Usu:{
                    required: "Ingrese su contraseña",
                    formatocontrasenia: "La contraseña debe tener al menos: 8 caracteres, mayúscula, minúscula, signo"
               },
               ContraC_Usu:{
                    required: "Porfavor confirme su contraseña",
                    equalTo: "La contraseña no coincide"
               },
               Imag_Usu:{
                    required: "Seleccione una imagen de perfil"
               }


          }
     });


     $("#form_editar_perfil").validate({
          rules: {
               Nombres:{
                    required: true,
                    sololetra: true
               },
               Apellidos:{
                    required: true,
                    sololetra: true
               },
               FechaNac:{
                    required: true,  
               },
               Correo:{
                    required: true,
                    email: true
               },
               NombreUs:{
                    required: true
               },
               Contraseña:{
                    required: true,
                    formatocontrasenia: true
               },
               ConfirmarCon:{
                    required: true,
                    equalTo: "#Contra_Usu"
               },
               ImagenUs:{
                    required: true
               }


          },
          messages: {
               Nombres:{
                    required: "Ingrese su(s) nombre(s)",
                    sololetra: "Ingrese únicamente letras"
               },
               Apellidos:{
                    required: "Ingrese su(s) apellido(s)",
                    sololetra: "Ingrese únicamente letras"
               },
               FechaNac:{
                    required: "Ingrese su fecha de nacimiento"
               },
               Correo:{
                    required: "Ingrese su correo electrónico",
                    email: "Ingrese un formato de correo"
               },
               NombreUs:{
                    required: "Ingrese su nombre de usuario"
               },
               Contraseña:{
                    required: "Ingrese su contraseña",
                    formatocontrasenia: "La contraseña debe tener al menos: 8 caracteres, mayúscula, minúscula, signo"
               },
               ConfirmarCon:{
                    required: "Porfavor confirme su contraseña",
                    equalTo: "La contraseña no coincide"
               },
               ImagenUs:{
                    required: "Seleccione una imagen de perfil"
               }
          }
     });

     $("#form_hacer_pregunta").validate({
          rules: {
               Titulo_pregunta: {
                    required: true,
                
               },
              
          },

          messages: {
               Titulo_pregunta:{
                    required: "Ingrese un título a la pregunta",
                    
               },
              
          }
     });

     $("#form_hacer_respuesta").validate({
          rules: {
               respuesta_texto: {
                    required: true,
                
               },
               
          },

          messages: {
               respuesta_texto:{
                    required: "Ingrese su respuesta",
                    
               },
              
          }
     });

});
