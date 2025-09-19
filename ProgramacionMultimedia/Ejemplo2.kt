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
			ImagenConTexto()
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
		Text("Parte superior izquierda", modifier = Modifier.align(Aligment.TopStart))

		Text("Parte central", modifier = Modifier.align(Aligment.Center))

		Text("Parte inferior derecha", modifier = Modifier.align(Aligment.BottomEnd))
	}
}

@Composable
fun ImagenConTexto() {
	Box(modifier = Modifier.fillMaxSize().padding(25.dp)) {
		Image() {
			painter = painterResource(id = R.drawable.Tortuga-rusa.webp)
			contentDescription = "Tortuga rusa"
			modifier = Modifier.align(Aligment.Center).fillMaxSize
		}

		Text(text = "Tortuga rusa", fontSize = 22.sp, color = Color.Green, textAlign = TextAlign.Center, modifier = Modifier.align(Aligment.Center))
	}
}

@Preview
@Composable
fun DosTextosVerticalPreview() {
	DosTextos()
}
