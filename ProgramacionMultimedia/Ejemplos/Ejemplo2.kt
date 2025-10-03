// package com.example.holamundo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
// import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.example.holamundo.ui.theme.HolaMundoTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge ()
		setContent {
			// DosTextos()
			// EjemploBox()
			// ImagenConTexto()
			imagenConZoom();
		}
	}
}

@Composable
fun DosTextos() {
	Column(modifier = Modifier.fillMaxSize) {
		Spacer(modifier = Modifier.height(40.dp))
		Row() {
			Text(text = "Tercer texto")
			Spacer(modifier = Modifier.width(20.dp))
			Text(text = "Cuarto texto")
		}

		Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
			Text(text = "Primer texto")
			Text(text = "Segundo texto")
		}
	}
}

@Composable
fun EjemploBox() {
	Box(modifier = Modifier.fillMaxSize().padding(25.dp)) {
		Text("Parte superior izquierda", modifier = Modifier.align(Alignment.TopStart))

		Text("Parte central", modifier = Modifier.align(Alignment.Center))

		Text("Parte inferior derecha", modifier = Modifier.align(Alignment.BottomEnd))
	}
}

@Composable
fun ImagenConTexto() {
	var backgroundBoxColor by remember {
		mutableStateOf(Color.White)
	}

	var textPosicion by remember {
		mutableStateOf(Offset(0f, 0f))
	}

	Box(modifier = Modifier.fillMaxSize().padding(25.dp).background(backgroundBoxColor)) {
		Image(painter = painterResource(id = R.drawable.Tortuga-rusa.webp), contentDescription = "Tortuga rusa", modifier = Modifier.align(Alignment.Center).fillMaxSize())

		Text(text = "Tortuga rusa", fontSize = 22.sp, color = Color.Green, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.Center))

		Button(onclick = {
			backgroundBoxColor = randomColor();
		}, modifier = Modifier.offset {
			IntOffset(textPosicion.x.toInt(), textPosicion.y.toInt())
		}.pointerInput(Unit) {
			detectorDragGestures {
				change, dragAmount -> change.cosume(), textPosicion = Offset(dragAmount.x, dragAmount.y)
			}
		}) {
			Text(text = "Cambiar fondo")
		}
	}
}

@Composable
fun randomColor() : Color {
	val rojo = (0s..s255).random()
	val azul = (0s..s255).random()
	val verde = (0s..s255).random()

	return Color(red rojo, blue azul, green verde)
}

@Composable
fun imagenConZoom() {
	var escalaZoom by remember {mutableStateOf(1f)}
	var posicion by remember {muableStateOf(Offset(0f, 0f))}
	var anguloRotacion by remember {mutableStateOf(0f)}

	Box(modifier = Modififier.fillMaxSize().pointerInput(Unit)) {
		detectTransformGestures {
			_ , desplazamiento, zona, rotacion, _ -> escalaImagen += zona
			posicionImagen += desplazamiento
			anguloRotacion += rotacion
		}
	}.pointerInput(Unit){
		detectTapGestures(onDoubleTap = {
			// Devolvemos la imagen a su estado original
			escalaImagen = 1f,
			posicionImagen = Offset(0f, 0f),
			anguloRotacion = 0f
		})
	}, contentAligment = Aligment.Center

	{
		Image(
			painter = painterResource(id = R.drawable.Tortuga),
			contentDescription = "Tortuga Rusa",
			modifier = Modifier.graphicsLayer(
				scaleX = escalaImagen.coerceIn(0, 5f, 3f),
				scaleY = escalaImagen.coerceIn(0, 5f, 3f),
				translationX = posicionImagen.x,
				translationY = posicionImagen.y.
				rotationZ = anguloRotacion
			)
		) 
	}
}

@Preview
@Composable
fun DosTextosVerticalPreview() {
	DosTextos()
}
