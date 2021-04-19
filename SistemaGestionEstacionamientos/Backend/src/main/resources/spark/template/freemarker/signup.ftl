<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<title>Sign Up</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="author" content="colorlib.com">

		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="signup/fonts/material-design-iconic-font.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="signup/css/style.css">
	</head>
	<body>
		<div class="wrapper">
            <form action="signup/registrar-cuenta" method="post" id="wizard">
        		
        		<!-- SECTION 1 -->
                <h2></h2>
                <section>
                    <div class="inner">
						<div class="image-holder">
							<a href="index.html"><img style="height: 100%;" src="signup/images/parking.jpg" alt=""></a>
						</div>
						<div class="form-content">
							<div class="form-header">
								<h3>Registrarse</h3>
							</div>
							<p>Datos basicos y de contacto</p>
							<div class="form-row">
								<div class="form-holder">
									<input type="text" name="propietario_nombres" maxlength="25" pattern="[A-Za-z]{25}" placeholder="Nombres" class="form-control" required/>
								</div>
								<div class="form-holder">
									<input type="text" name="propietario_apellidos" maxlength="25" pattern="[A-Za-z]{25}" placeholder="Apellidos" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder">
									<input type="text" name="propietario_cedula" minlength="10" maxlength="10" pattern="[0-9]{10}" placeholder="Cedula" class="form-control" required/>
								</div>
								<div class="form-holder">
									<input type="email" name="propietario_email" placeholder="Email" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder">
									<textarea required name="propietario_direccion" placeholder="Direccion" class="form-control" style="height: 99px;"></textarea>
								</div>
							</div>
						</div>
					</div>
                </section>

				<!-- SECTION 2 -->
                <h2></h2>
                <section>
                    <div class="inner">
						<div class="image-holder">
							<a href="index.html"><img style="height: 100%;" src="signup/images/parking.jpg" alt=""></a>
						</div>
						<div class="form-content" >
							<div class="form-header">
								<h3>Registrarse</h3>
							</div>
							<p>Datos del Estacionamiento</p>
							<div class="form-row">
								<div class="form-holder w-100">
									<input type="text" name="estacionamiento_nombre" maxlength="45" pattern="[A-Za-z0-9]{25}" placeholder="Nombre" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder w-100">
									<textarea name="estacionamiento_direccion" maxlength="100" pattern="[A-Za-z]+" placeholder="Direccion" class="form-control" required></textarea>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder">
									<input type="text" name="estacionamiento_telefono" minlength="7" maxlength="10" pattern="[0-9]+" placeholder="Telefono" class="form-control" required/>
								</div>
							</div>
						</div>
					</div>
                </section>

				<!-- SECTION 3 -->
                <h2></h2>
                <section>
                    <div class="inner">
						<div class="image-holder">
							<a href="index.html"><img style="height: 100%;" src="signup/images/parking.jpg" alt=""></a>
						</div>
						<div class="form-content">
							<div class="form-header">
								<h3>Registrarse</h3>
							</div>
							<p>Informacion del Estacionamiento</p>
							<div class="form-row">
								<div class="form-holder w-100">
									<input type="number" name="estacionamiento_espacios" placeholder="Numero de espacios" min="1" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder">
									<input type="number" name="estacionamiento_tiempo" placeholder="Tiempo en Minutos" min="1" max="60" class="form-control" required/>
								</div>
								<div class="form-holder">
									<input type="number" name="estacionamiento_tarifa" placeholder="Tarifa en dolares" min="0" step=".01" class="form-control" required/>
								</div>
							</div>
						</div>
					</div>
                </section>

                <!-- SECTION 4 -->
                <h2></h2>
                <section>
                    <div class="inner">
						<div class="image-holder">
							<a href="index.html"><img style="height: 100%;" src="signup/images/parking.jpg" alt=""></a>
						</div>
						<div class="form-content">
							<div class="form-header">
								<h3>Registrarse</h3>
							</div>
							<p>Crear cuenta de acceso</p>
							<div class="form-row">
								<div class="form-holder w-100">
									<input type="text" name="propietario_usuario" placeholder="Usuario" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder w-100">
									<input type="password" name="propietario_contrasena1" placeholder="Contrasena" minlength="8" class="form-control" required/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-holder w-100">
									<input type="password" name="propietario_contrasena2" placeholder="Confirmar contrasena" minlength="8" class="form-control" required/>
								</div>
							</div>
							<div class="checkbox-circle mt-24">
								<label>
									<input type="checkbox" required checked/>  ACEPTO <a href="#">terminos y condiciones.</a>
									<span class="checkmark"></span>
								</label>
							</div>
						</div>
					</div>
                </section>

            </form>
		</div>

		<!-- Scripts -->
		<script>
			console.log("Nota: La validacion de datos no esta implementada.");
		</script>
		<script src="signup/js/autoFiller.js"></script>

		<!-- JQUERY -->
		<script src="signup/js/jquery-3.3.1.min.js"></script>
		<script src="signup/js/jquery.validate.js"></script>

		<!-- JQUERY STEP -->
		<script src="signup/js/jquery.steps.js"></script>
		<script src="signup/js/main.js"></script>
		<!-- Template created and distributed by Colorlib -->
</body>
</html>
