package com.example.contactosjpc

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ContactList(contacts = listOf(
                    Contacto("Laura", "1212332", 1),
                    Contacto("Maria", "1212332", 1),
                    Contacto("Amaro", "1212332", 0),
                    Contacto("Sofia", "1212332", 1),
                    Contacto("Pedro", "1212332", 0),
                    Contacto("Lorena", "1212332", 1),
                    Contacto("Lucía", "1212332", 1),
                    Contacto("Auri", "1212332", 0),
                    Contacto("Jenri", "1212332", 0),
                    Contacto("Sebas", "1212332", 0),
                ))
            }
        }
    }
}

@Composable
fun ContactList(contacts: List<Contacto>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(contacts.size) { index ->
            VistaContacto(contact = contacts[index])
        }
    }
}

@Composable
fun VistaContacto(contact: Contacto) {
    // Variable que almacena si se estan mostrando los detalles
    var showDetails by remember { mutableStateOf(false) }

    // Variable que obtiene las iniciales
    val initial: String = contact.nombre.substring(0, 1)

    // Contexto
    val context = LocalContext.current

    Card(
        shape = RectangleShape,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { showDetails = !showDetails },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF9E9E)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(
                    // Si la imagen es igual a 1 es mujer
                    if (contact.image == 1) R.drawable.women else R.drawable.man
                ),
                contentDescription = "Imagen según el género",
                modifier = Modifier
                    .size(36.dp)
                    .padding(top = 5.dp),
                contentScale = ContentScale.Crop,
            )

            // Separador
            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(
                visible = !showDetails,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = initial,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            AnimatedVisibility(
                visible = showDetails,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = contact.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contact.telefono,
                        color = Color.Blue,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${contact.telefono}")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}