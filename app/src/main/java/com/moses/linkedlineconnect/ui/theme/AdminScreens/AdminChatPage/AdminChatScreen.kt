package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminChatPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminChatScreen(navController: NavHostController) {
    val database = FirebaseDatabase.getInstance()
    val messagesRef = database.getReference("Messages")
    val usersRef = database.getReference("Users")
    val escortsRef = database.getReference("Escorts")

    var selectedChatType by remember { mutableStateOf("All Parents") } // Default chat type
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var messageText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Fetch messages dynamically
    LaunchedEffect(selectedChatType) {
        messagesRef.child(selectedChatType).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fetchedMessages = mutableListOf<Message>()
                snapshot.children.forEach { child ->
                    val message = child.getValue(Message::class.java)
                    if (message != null) {
                        fetchedMessages.add(message)
                    }
                }
                messages = fetchedMessages
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Chat Page", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Chat Type Selector
                DropdownMenuSelector(
                    selectedChatType = selectedChatType,
                    onChatTypeSelected = { selectedChatType = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Chat Messages Display
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    if (messages.isEmpty()) {
                        Text(
                            text = "No messages yet.",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        messages.forEach { message ->
                            ChatBubble(
                                message = message,
                                isAdmin = message.sender == "Admin"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Message Input and Send Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BasicTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                sendMessage(
                                    messagesRef = messagesRef,
                                    chatType = selectedChatType,
                                    messageText = messageText
                                )
                                messageText = ""
                            }
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = Color(0xFF6200EE))
                    }
                }
            }
        }
    )
}

@Composable
fun DropdownMenuSelector(
    selectedChatType: String,
    onChatTypeSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Text(
            text = "Chat Type: $selectedChatType",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("All Parents") },
                onClick = {
                    onChatTypeSelected("All Parents")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("All Escorts") },
                onClick = {
                    onChatTypeSelected("All Escorts")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Parent") },
                onClick = {
                    onChatTypeSelected("Parent")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Escort") },
                onClick = {
                    onChatTypeSelected("Escort")
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun ChatBubble(message: Message, isAdmin: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = if (isAdmin) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Text(
            text = "${message.sender}: ${message.text}",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .background(
                    if (isAdmin) Color(0xFF6200EE) else Color.Gray,
                    RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}

fun sendMessage(messagesRef: DatabaseReference, chatType: String, messageText: String) {
    val message = Message(
        sender = "Admin",
        text = messageText,
        timestamp = System.currentTimeMillis()
    )
    messagesRef.child(chatType).push().setValue(message)
}

data class Message(
    val sender: String = "",
    val text: String = "",
    val timestamp: Long = 0L
)

