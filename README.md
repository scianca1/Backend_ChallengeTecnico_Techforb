# Backend_ChallengeTecnico_Techforb

Backend - API REST con Spring Boot: RECURSO DOCUMENTACION SWAGGER=  https://poetic-curiosity-production.up.railway.app/doc/swagger-ui/index.html

Este es el backend de la aplicación, desarrollado en Java con Spring Boot. Expone una API REST para la autenticación de usuarios y gestión de datos.

🛠️ Tecnologías utilizadas
-Java 17
-Spring Boot
-Spring Security con JWT
-Spring Data JPA
-MySQL (Railway)
-Swagger para documentación


🔐 Autenticación y Seguridad

El backend usa Spring Security con autenticación basada en JWT.
Acceso a recursos protegidos: Incluir el token en las cookies en cada request.
Los unicos metodos de libre acceso son los del controlador auth;
ACLARACION:  PARA AGREGAR O EDITAR O ELIMINAR UNA PLANTA DEBE SER UN USUARIO CON USUARIO ADMINISTRADOR, PARA PROBAR SOLO HAY 1 PRECARGADO EN LA BASE DE DATOS;

CONTROLADORES: AuthController: login,register,logout
               PlantaController: 

Ejemplo de METODOS con Postman:

AuthController---->
{
    nombre: "LOGIN"
    metodo: POST
    url: https://poetic-curiosity-production.up.railway.app/auth/login
    body ejemplo:   {      
                    "email":"Juan@gmail.com",string
                    "password":12345678, number
                    }
    return  OK:  - {
                    "respose":"Autenticacion exitosa!",
                    "UserName":[Nombre del USUARIO],
                    "RolUser": [Rol del USUARIO]
                    }
                   - Una cookie "jwtToken" con el token, tanto la cookie como el token se extienden a 8 horas de duracion
    return 401: -HttpStatus: UNAUTHORIZED
                -body con el mensaje de error
}


{
    nombre: "REGISTER"
    metodo: POST
    url: https://poetic-curiosity-production.up.railway.app/auth/register
    body ejemplo:   {      
                    "usuario":"juan",string
                    "email":"juan@gmail.com",string
                    "password":contraceña,number
                    "repetidaPassword":contraceña, number
                    }
    return  201 OK:  - {
                    "respose":"Usuario Registrado exitosamente!",
                    }
    return 400: -HttpStatus: BAD_REQUEST
                -{
                "respose":"Alguno de los campos no cumple con los requerimiento: Usua....",
                }
}

{
    nombre: "LOGOUT"
    metodo: GET
    url: https://poetic-curiosity-production.up.railway.app/auth/logout
    Comportamiento: borra las cookies del token 
    return  OK:  - {
                    "mensaje":"Logged out exitosa!"
                    }
}

PlantaController---->


{
nombre: "NUEVA"
metodo: POST
url: https://poetic-curiosity-production.up.railway.app/Planta/nueva
body ejemplo:   {      
                "pais":"ARGENTINA", string
                "nombre": "Mar del Plata",string
                }
return  201 OK:  - {
                    "id": 230,
                    "pais": "ARGENTINA",
                    "nombre": "Mar del Plata",
                     "lecturasOk":"null",
                    "alertasMadias":"null",
                    "alertasRojas":"null",
                     "idUsuario": 5
                    }
return 401: -HttpStatus: UNAUTHORIZED
            -body con el mensaje de error
            -{
            "Error":"Mensaje de error",
            }
}

{
    nombre: "GETALL"
    metodo: GET
    url: https://poetic-curiosity-production.up.railway.app/Planta
    return  201 OK:  - [{
                        "id": 230,
                        "pais": "ARGENTINA",
                        "nombre": "Mar del Plata",
                        "lecturasOk":"152",
                        "alertasMadias":"152",
                        "alertasRojas":"145",
                        "idUsuario": 5
                        },
                        {"" ""
                        }]
    return 401: -HttpStatus: UNAUTHORIZED
                -body con el mensaje de error
                -{
                "Error":"Mensaje de error",
                }   
}

{
    nombre: "LECTURAS"
    metodo: GET
    url: https://poetic-curiosity-production.up.railway.app/Planta/Lecturas
    return  201 OK:  - [{
                        "text": "LECTURASOK",
                        "nro": 20,
                        },{
                        """"
                        }]
    return 401: -HttpStatus: UNAUTHORIZED
                -body con el mensaje de error
                -{
                "Error":"Mensaje de error",
                }
}


{
    nombre: "DETALLES sensores"
    metodo: GET
    url: https://poetic-curiosity-production.up.railway.app/Planta/{Id}
    return  201 OK:  - [{
                        "nombre": "TEMPERATURA",
                        "lecturasOk": 250,
                        "alertasMadias":100,
                        "alertasRojas":400,
                        },{ """"
                        }]
    return 400: -HttpStatus: BAD_REQUEST
                -body con el mensaje de error
                -{
                "Error":"Mensaje de error",
                }
}


{
    nombre: "EDITAR"
    metodo: PUT
    url: https://poetic-curiosity-production.up.railway.app/Planta/editar/{id}
    body ejemplo:   {      
                    "pais":"ARGENTINA", string
                    "nombre": "Mar del Plata",string
                    }
    return  201 OK:  - {
                        "id": 230,
                        "pais": "ARGENTINA",
                        "nombre": "Mar del Plata",
                        "lecturasOk":"null",
                        "alertasMadias":"null",
                        "alertasRojas":"null",
                        "idUsuario": 5
                        }
    return 400: -HttpStatus: BAD_REQUEST
                -body con el mensaje de error
                -{
                "Error":"Mensaje de error",
                }
}


{
    nombre: "ELIMINAR"
    metodo: POST
    url: https://poetic-curiosity-production.up.railway.app/Planta/eliminar/{id}
    return  201 OK:  - {
                        "id": 230,
                        "pais": "ARGENTINA",
                        "nombre": "Mar del Plata",
                        "lecturasOk":"null",
                        "alertasMadias":"null",
                        "alertasRojas":"null",
                        "idUsuario": 5
                        }
    return 401: -HttpStatus: UNAUTHORIZED
                -body con el mensaje de error
                -{
                "Error":"Mensaje de error",
    }
}

