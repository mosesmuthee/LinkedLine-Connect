package com.moses.linkedlineconnect.ui.theme.UsersParents.ChatPageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
//import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatPageScreen() {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<String>() }
    val adminMessages = remember { mutableStateListOf<String>() }
    var isAdminMode by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = "Group Chat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Message List
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (isAdminMode) {
                        if (adminMessages.isEmpty()) {
                            Text(
                                text = "No messages to the admin yet.",
                                fontSize = 16.sp,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        } else {
                            adminMessages.forEach { message ->
                                MessageBubble(message = message)
                            }
                        }
                    } else {
                        if (messages.isEmpty()) {
                            Text(
                                text = "No messages yet. Start the conversation!",
                                fontSize = 16.sp,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        } else {
                            messages.forEach { message ->
                                MessageBubble(message = message)
                            }
                        }
                    }
                }

                // Pull-Up Selector for Admin Messaging
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isAdminMode) "Messaging Admin Only" else "Group Chat",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isAdminMode) Color(0xFF6200EE) else Color(0xFF03A9F4),
                        modifier = Modifier.clickable { isAdminMode = !isAdminMode }
                    )
                }

                // Input and Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Attach Image Button
                    IconButton(
                        onClick = { /* TODO: Implement image attachment logic */ },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AttachFile,
                            contentDescription = "Attach Image",
                            tint = Color(0xFF6200EE)
                        )
                    }

                    // Message Input Field
                    BasicTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp)
                            .background(Color(0xFFF5F5F5), shape = CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                if (messageText.isNotBlank()) {
                                    if (isAdminMode) {
                                        adminMessages.add(messageText)
                                    } else {
                                        messages.add(messageText)
                                    }
                                    messageText = ""
                                }
                            }
                        ),
                        decorationBox = { innerTextField ->
                            if (messageText.isEmpty()) {
                                Text(
                                    text = "Type a message...",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    )

                    // Send Button
                    IconButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                if (isAdminMode) {
                                    adminMessages.add(messageText)
                                } else {
                                    messages.add(messageText)
                                }
                                messageText = ""
                            }
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send // ✅ correct and modern
                            ,
                            contentDescription = "Send Message",
                            tint = Color(0xFF6200EE)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun MessageBubble(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Text(
            text = message,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun ChatPageScreenPreview() {
    ChatPageScreen()
}

