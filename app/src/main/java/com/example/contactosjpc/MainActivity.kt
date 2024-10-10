package com.example.contactosjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactosjpc.ui.theme.ContactosJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }


        /*setContent {
            MaterialTheme {
                ItemList(
                    itemContacto = listOf(
                        Contacto("Laura", "1212332", 1),
                        Contacto("Maria", "1212332", 1),
                        Contacto("Amaro", "1212332", 0),
                        Contacto("Sofia", "1212332", 1),
                        Contacto("Pedro", "1212332", 0),
                        Contacto("Lorena", "1212332", 1),
                        Contacto("Lorena", "1212332", 1),
                        Contacto("Pablo", "1212332", 0),
                        Contacto("Pablo", "1212332", 0),
                    )
                )
            }
        }*/
    }
}

@Composable
fun ItemList(itemContacto: List<Contacto>) {
    LazyColumn {
        items(itemContacto) { itemContacto ->
            ContactoView(contacto = itemContacto)
        }
    }
}

@Composable
fun ContactoView(contacto: Contacto) {
    Card(Modifier.fillMaxWidth()) {
        Row {
            Column {
                Image(
                    painter = painterResource(id = if (contacto.image == 0) R.drawable.man else R.drawable.women),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            Column {
                Text(
                    text = contacto.nombre,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )}}}}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactosJPCTheme {
        Greeting("Android")
    }
}