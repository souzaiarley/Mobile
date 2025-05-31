package com.example.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.ui.theme.AlarmAppTheme
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlarmScreen()
                }
            }
        }
    }

    @Composable
    fun AlarmScreen() {
        var hour by remember { mutableStateOf(0) }
        var minute by remember { mutableStateOf(0) }
        var showTimePicker by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Definir Alarme",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.icon_alarm),
                contentDescription = "Alarm Icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { showTimePicker = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text("Selecionar Hora", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = String.format("Hora selecionada: %02d:%02d", hour, minute),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                TimePickerDialogHandler(
                    show = showTimePicker,
                    onTimeSelected = { selectedHour, selectedMinute ->
                        hour = selectedHour
                        minute = selectedMinute
                        showTimePicker = false
                    },
                    onDismiss = { showTimePicker = false }
                )
            }

            Button(
                onClick = {
                    setAlarm(this@MainActivity, hour, minute)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Configurar Alarme", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    @Composable
    fun TimePickerDialogHandler(
        show: Boolean,
        onTimeSelected: (hour: Int, minute: Int) -> Unit,
        onDismiss: () -> Unit
    ) {
        if (show) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    onTimeSelected(selectedHour, selectedMinute)
                },
                hour,
                minute,
                true
            ).apply {
                setOnDismissListener { onDismiss() }
                show()
            }
        }
    }
}

private fun setAlarm(context: Context, hour: Int, minute: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExactAlarms(context)) {
        requestExactAlarmPermission(context)
        Toast.makeText(context, "Permissão necessária para configurar alarmes exatos.", Toast.LENGTH_SHORT).show()
        return
    }
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    Toast.makeText(context, "Alarme configurado para ${hour}:${minute}", Toast.LENGTH_SHORT).show()
}

private fun canScheduleExactAlarms(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.canScheduleExactAlarms()
    } else {
        true
    }
}
private fun requestExactAlarmPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        context.startActivity(intent)
    }
}