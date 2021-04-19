function autoFiller() {
    document.querySelector('input[name=propietario_nombres]').value = "Dann";
    document.querySelector('input[name=propietario_apellidos]').value = "Diaz";
    document.querySelector('input[name=propietario_email]').value = "dann@email.com";
    document.querySelector('textarea[name=propietario_direccion]').value = "Avenida principal, calle secundaria.";
    document.querySelector('a[href="#next"]').click();

    document.querySelector('input[name=estacionamiento_nombre]').value = "DannEspacios";
    document.querySelector('textarea[name=estacionamiento_direccion]').value = "Calle principal y calle secundaria, nro. 123.";
    document.querySelector('input[name=estacionamiento_telefono]').value = "0123456";
    document.querySelector('a[href="#next"]').click();

    document.querySelector('input[name=estacionamiento_espacios]').value = "10";
    document.querySelector('input[name=estacionamiento_tiempo]').value = "60";
    document.querySelector('input[name=estacionamiento_tarifa]').value = "2";
    document.querySelector('a[href="#next"]').click();

    document.querySelector('input[name=propietario_usuario]').value = "danndiaz";
    document.querySelector('input[name=propietario_contrasena1]').value = "12345678";
    document.querySelector('input[name=propietario_contrasena2]').value = "12345678";
}