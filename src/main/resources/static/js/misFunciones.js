/* global fetch */

//function traerInformacion(){location.reload(true);}
//Función para actualizar cada 60 segundos(60000 milisegundos)
//setInterval("traerInformacion()",60000);
//traerInformacion();

document.getElementById('email').addEventListener('input', function() {
    let campo = event.target;
    let valido = document.getElementById('emailOK');

    let emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    
    if (emailRegex.test(campo.value)) {
        $("#btn-consultar").attr("disabled",false);
        $("#resultado").html("");
    } else {
        $("#resultado").html("<p class='loader text-center'>Debe ingresar un correo valido</p>");
    }
});

function consultar(dato){
    $("#resultado").html("<p class='loader text-center'>Cargando...</p>");
    $.ajax({
        url:"http://144.22.56.43:8080/api/user/"+$('#email').val()+"/"+$('#password').val(),
        //url:"http://localhost:8080/api/user/"+$('#email').val()+"/"+$('#password').val(),
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            
            if(respuesta.id!=null && dato==1){
                $("#header").html("BIENVENIDO <br>"+respuesta.name);
                traerInformacion();
            }else if((respuesta.id==null && dato==2)){
                $("#resultado").html("<p class='loader text-center'>Creando la cuenta...</p>");
                guardar();
            }else{
                $("#resultado").html("<p align='center'>¿No tienes cuenta? <a id='link' href='javascript:crearCuenta()'>Crea tu cuenta aquí.<a></p>");
                
            }
            
        }
    });
}

function traerInformacion(){
    $("#id").hide()
    $("#resultado").html("<p class='loader text-center'>Cargando...</p>"); 
    $.ajax({
        url:"http://144.22.56.43:8080/api/user/all",
        //url:"http://localhost:8080/api/user/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuesta(respuesta);
        }
    });
}

function pintarRespuesta(items){
        
    let myTable = "<table id='userList' class='table-responsive'>";
    let tableHeader = `<thead><tr>
    <th>ID</th>
    <th>NOMBRE</th>
    <th>CORREO</th>
    <th>CONTRASEÑA</th>
    </tr></thead>`;
    myTable += tableHeader;
    for (i=0; i<items.length; i++ ) {
        
        myTable+="<tr>";
        myTable+="<td data-titulo='ID:'>"+items[i].id+"</td>";
        myTable+="<td data-titulo='NOMBRE:'>"+items[i].name+"</td>";
        myTable+="<td data-titulo='CORREO:'>"+items[i].email+"</td>";
        myTable+="<td data-titulo='CONTRASEÑA:'>"+items[i].password+"</td>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").html(myTable);
    $("#email").hide();
    $("#password").hide();
    $("#btn-consultar").hide();
    $("#confirmar").hide();
    $("#btn-guardar").hide();
    $("#name").hide();
}

function crearCuenta(){
    $("#resultado").html("");

    $("#btn-consultar").hide();
    $("#name").show();
    $("#confirmar").show();
    $("#btn-guardar").show();
}


function validar(){
    
    if ($('#email').val().length == 0 || $('#password').val().length == 0) {
        $("#validarCampos").html("<h4 style='color: red'>Todos los campos son necesarios</h4>");
        return false;
    }else{
        $("#validarCampos").html("");
        consultar(1);
    }
}

function validar2(){
    
    if ($('#email').val().length == 0 || $('#password').val().length == 0 || $('#confirmar').val().length == 0 || $('#name').val().length == 0) {
        $("#validarCampos").html("<h4 style='color: red'>Todos los campos son necesarios</h4>");
        return false;
    }else{ 
        
        if($('#password').val() == $('#confirmar').val()){
            $("#validarCampos").html("");
            validarEmail();
            
        }else{
            $("#validarCampos").html("<h4 style='color: red'>La contraseña no coincide</h4>");
            setTimeout(
                function(){ 
                    $("#validarCampos").html("<h4 style='color: red'>La contraseña no coincide</h4>");
                    traerInformacion();
                }, 6000
            );
            
        }
        
    }
}
function validarEmail(){
    $("#resultado").html("<p class='loader text-center'>Cargando...</p>");
    $.ajax({
        url:"http://144.22.56.43:8080/api/user/"+$('#email').val(),
        //url:"http://localhost:8080/api/user/"+$('#email').val(),
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            if(respuesta==true){
                $("#validarCampos").html("<p align='center' style='color:black'>El correo ya se encuentra registrado, <a id='link' href='javascript:location.reload()'>Iniciar sesión.<a></p>");
            }else{
                consultar(2);
            }
            
        }
    });
}



function guardar(){
    let myData={
        email:$("#email").val(),
        password:$("#password").val(),
        name:$("#name").val()
    };
    let dataToSend=JSON.stringify(myData);
        
    $.ajax({
        url: "http://144.22.56.43:8080/api/user/new",
        //url: "http://localhost:8080/api/user/new",
        type: "POST",
        data: dataToSend,
        contentType:"application/JSON",
        datatype: "JSON",
        success:function(respuesta){
            $("#resultado").html("<p class='loader text-center'>Cuenta creada, espere para ser redireccionado...</p>");
            setTimeout(
                function(){ 
                    $("#header").html("BIENVENIDO <br>"+respuesta.name);
                    traerInformacion();
                }, 6000
            );

        }
    });

}

