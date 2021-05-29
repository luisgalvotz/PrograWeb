function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#Imagen_Usu')
                    .attr('src', e.target.result)
                    .width(120)
                    .height(120);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

/*El FileReader es un objeto que permite que las APIs
web puedan leer ficheros o archivos que se almacenan en el cliente de manera asíncrona utilizando el objeto file.

El file se obtiene como se muestra de la selección de archivos por parte del usuario con el elemento input, mandado al parámetro de la función

En el onload se ejecuta cada que la lectura se completa correctamente y al elemento con id Imagenseleccionada le pasa al atributo o propiedad "src" el resultado del file que seleccionó el usuario, además de las dimensiones del mismo

El evento readAsDataURL hace la lectura del contenido dentro del file, luego el readystate se vuelve DONE y el load es lanzado, en ese punto el result tiene la información guardada como una URL representandola como una cadena de caracteres codificados en base64.
*/ 