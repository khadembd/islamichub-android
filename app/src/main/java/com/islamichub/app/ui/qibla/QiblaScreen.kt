package com.islamichub.app.ui.qibla

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamichub.app.ui.theme.IHColors
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun QiblaScreen() {
    val context = LocalContext.current
    var azimuth by remember { mutableStateOf(0f) }
    val qiblaDirection = remember { calculateQibla(23.6850, 90.3563) } // Dhaka default

    DisposableEffect(Unit) {
        val sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                azimuth = event.values[0]
            }
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
        }
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        onDispose { sm.unregisterListener(listener) }
    }

    Column(
        Modifier.fillMaxSize().background(IHColors.BG).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("কিবলা কম্পাস", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(32.dp))
        // Compass
        Box(
            Modifier.size(280.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 2 - 20

                // Outer circle
                drawCircle(color = IHColors.Surface, radius = radius, center = center)
                drawCircle(color = IHColors.Divider, radius = radius, center = center, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f))

                // Qibla direction arrow (rotated by compass - qibla angle)
                val qiblaAngle = qiblaDirection - azimuth
                rotate(qiblaAngle, center) {
                    // Arrow pointing to Qibla
                    drawLine(
                        color = IHColors.Gold,
                        start = center,
                        end = androidx.compose.ui.geometry.Offset(center.x, center.y - radius + 30),
                        strokeWidth = 6f
                    )
                    // Kaaba icon (circle)
                    drawCircle(color = IHColors.Primary, radius = 12f, center = androidx.compose.ui.geometry.Offset(center.x, center.y - radius + 30))
                }
                // N indicator
                rotate(-azimuth, center) {
                    drawLine(
                        color = IHColors.White,
                        start = center,
                        end = androidx.compose.ui.geometry.Offset(center.x, center.y - radius + 10),
                        strokeWidth = 4f
                    )
                }
                // Center dot
                drawCircle(color = IHColors.Primary, radius = 8f, center = center)
            }
        }
        Spacer(Modifier.height(24.dp))
        Text("🕋 কাবা", color = IHColors.Gold, fontSize = 28.sp)
        Text("${String.format("%.0f", qiblaDirection)}° from North", color = IHColors.White, fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        Text("সবুজ তীর কিবলার দিক নির্দেশ করে", color = IHColors.TextSecondary, fontSize = 12.sp)
    }
}

private fun calculateQibla(lat: Double, lng: Double): Float {
    val meccaLat = 21.422487
    val meccaLng = 39.826206
    val latK = Math.toRadians(meccaLat)
    val lngK = Math.toRadians(meccaLng)
    val latU = Math.toRadians(lat)
    val lngU = Math.toRadians(lng)
    val deltaLng = lngK - lngU
    val y = sin(deltaLng)
    val x = cos(latU) * sin(latK) - sin(latU) * cos(latK) * cos(deltaLng)
    val bearing = Math.toDegrees(atan2(y, x))
    return ((bearing + 360) % 360).toFloat()
}
