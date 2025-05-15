package com.example.cadapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cadastro()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro() {
    var scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            // Barra superior (topAppBar)
            TopAppBar(
                title = { Text("CadApp") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textFieldModifier = Modifier.fillMaxWidth(0.8f)

                var nome by remember { mutableStateOf("") }
                var sobrenome by remember { mutableStateOf("") }
                var dataNascimento by remember { mutableStateOf("") }
                var genero by remember { mutableStateOf("") }
                var telefone by remember { mutableStateOf("") }
                var celular by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var endereco by remember { mutableStateOf("") }
                var numero by remember { mutableStateOf("") }
                var bairro by remember { mutableStateOf("") }
                var cidade by remember { mutableStateOf("") }
                var estado by remember { mutableStateOf("") }
                var cep by remember { mutableStateOf("") }
                var nacionalidade by remember { mutableStateOf("") }
                var profissao by remember { mutableStateOf("") }

                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = sobrenome,
                    onValueChange = { sobrenome = it },
                    label = { Text("Sobrenome") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = dataNascimento,
                    onValueChange = { dataNascimento = it },
                    label = { Text("Data de nascimento") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Gênero") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = { Text("Telefone") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = celular,
                    onValueChange = { celular = it },
                    label = { Text("Celular") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = endereco,
                    onValueChange = { endereco = it },
                    label = { Text("Endereço") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = numero,
                    onValueChange = { numero = it },
                    label = { Text("Número") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = bairro,
                    onValueChange = { bairro = it },
                    label = { Text("Bairro") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = cidade,
                    onValueChange = { cidade = it },
                    label = { Text("Cidade") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text("Estado") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = { Text("CEP") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = nacionalidade,
                    onValueChange = { nacionalidade = it },
                    label = { Text("Nacionalidade") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = profissao,
                    onValueChange = { profissao = it },
                    label = { Text("Profissão") },
                    modifier = textFieldModifier
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            nome = ""
                            sobrenome = ""
                            dataNascimento = ""
                            genero = ""
                            telefone = ""
                            celular = ""
                            email = ""
                            endereco = ""
                            numero = ""
                            bairro = ""
                            cidade = ""
                            estado = ""
                            cep = ""
                            nacionalidade = ""
                            profissao = ""
                        },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Text("Limpar")
                    }

                    Button(
                        onClick = {
                        }
                    ) {
                        Text("Enviar")
                    }
                }
            }
        }
    )
}
