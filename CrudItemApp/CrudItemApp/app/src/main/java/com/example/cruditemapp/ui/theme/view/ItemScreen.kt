package com.example.cruditemapp.ui.theme.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cruditemapp.model.Item
import com.example.cruditemapp.viewmodel.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel = viewModel()
) {
    val items by viewModel.items
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("CrudItemApp", style = MaterialTheme.typography.titleLarge) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Adicionar Novo Item",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Título") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Descrição") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                if (title.text.isNotEmpty() && description.text.isNotEmpty()) {
                                    viewModel.addItem(Item(title = title.text, description = description.text))
                                    title = TextFieldValue("")
                                    description = TextFieldValue("")
                                }
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Adicionar")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Itens cadastrados",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items.size) { index ->
                        val item = items[index]
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = item.title, style = MaterialTheme.typography.titleSmall)
                                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextButton(onClick = {
                                        selectedItem = item
                                        showDialog = true
                                    }) {
                                        Text("Editar")
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    TextButton(onClick = {
                                        viewModel.deleteItem(item.id)
                                    }) {
                                        Text("Excluir")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )

    if (showDialog) {
        UpdateItemDialog(
            item = selectedItem,
            onDismiss = { showDialog = false },
            onUpdate = { updatedItem ->
                viewModel.updateItem(updatedItem)
                showDialog = false
            }
        )
    }
}

@Composable
fun UpdateItemDialog(
    item: Item?,
    onDismiss: () -> Unit,
    onUpdate: (Item) -> Unit
) {
    if (item == null) return

    var title by remember { mutableStateOf(TextFieldValue(item.title)) }
    var description by remember { mutableStateOf(TextFieldValue(item.description)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Item") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(item.copy(title = title.text, description = description.text))
            }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
