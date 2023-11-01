TPE PARTE 1

Ejercicio 3

    a. Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por
    kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder
    configurarse para incluir (o no) los tiempos de pausa.

    Microservicio: Scooter

    Ruta al controller: src/main/java/app/controller/ScooterControllerJPA.java
    
    endpoint:   GET     "localHost:8083/scooter/{kms}"
    
    ingresar el parametro en Params(Postman):

                Key= include    value= true/false

    

    b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la
    misma.

    Microservicio: User
    
    Ruta al controller: src/main/java/app/controllers/UserControllerJPA.java

    endpoint:   PUT     "localHost:8081/user/disableAccount/{userId}

    

    c. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
    
    Microservicio: Travel

    Ruta al controller: src/main/java/app/controllers/TravelContollerJPA.java

    endpoint:   GET     "localHost:8084/travel/scootersWithMoreThanXin"
    
    ingresar los parametro en Params(Postman):

                Key= numTravels    value= integer
                Key= year          value= integer

    

    d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.

    Microservicio: Travel

    Ruta al controller: src/main/java/app/controllers/TravelContollerJPA.java

    endpoint:  GET      "localHost:8084/travel/getTotalBillingBetween"

    ingresar los parametro en Params(Postman):

                Key= month1          value= integer
                Key= month2          value= integer
                Key= year            value= integer
    
    

    e. Como administrador quiero consultar la cantidad de monopatines actualmente en operación,
    versus la cantidad de monopatines actualmente en mantenimiento.

    Microservicio: Scooter

    Ruta al controller: src/main/java/app/controller/ScooterControllerJPA.java

    endpoint:   GET     "localHost:8083/scooter/byStatus"

    

    f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema
    habilite los nuevos precios.

    Microservicio: Admin

    Ruta al controller: src/main/java/app/Controller/TariffControllerJPA.java

    endpoint:  PUT      "localHost:8082/tariff/adjustmentPrice/{id_tariff}"

    ingresar una tarifa actualizada en formato JSON en el body

        (@RequestBody Tariff newTariff, @PathVariable Long id_tariff)    
        
        {
            "nameTariff": "De prueba",
            "price": 15.5,
            "pricePauseExt": 3.0,
            "effectiveDate": "2023-10-31T08:00:00.000+00:00"
        }


    
    g. Como usuario quiero lun listado de los monopatines cercanos a mi zona, para poder encontrar
    un monopatín cerca de mi ubicación.

    Microservicio: Scooter    

    Ruta al controller: src/main/java/app/controller/ScooterControllerJPA.java

    endpoint:   GET     "localHost:8083/scooter/getNearby"

    ingresar el parametro en Params(Postman):

                Key= ubication    value= String