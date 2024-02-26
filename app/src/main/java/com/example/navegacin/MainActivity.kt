package com.example.navegacin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationApp()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inputScreen") {
        composable("inputScreen") { InputScreen(navController) }
        composable("resultScreen/{message}") { backStackEntry ->

        }
    }
}

@Composable
fun InputScreen(navController: androidx.navigation.NavController) {
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )
        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID del instituto") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            val message = determineMessage(id.toIntOrNull() ?: -1, name)
            navController.navigate("resultScreen/$message")
        }) {
            Text("Enviar")
        }
    }
}

fun determineMessage(id: Int, name: String): String {
    return when (id) {
        in 1..10 -> "Bienvenido al Laboratorio de ISND estimado Coordinador $name"
        in 11..100 -> "Permiso autorizado para el profesor $name"
        in 101..15000 -> "Acceso denegado a egresados"
        22098 -> "Alumno $name autorizado para uso del laboratorio."
        else -> "Este laboratorio es de uso exclusivo para la carrera ISND"
    }
}


