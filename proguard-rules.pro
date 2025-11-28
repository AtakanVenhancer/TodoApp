# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep Compose classes
-keep class androidx.compose.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep application classes
-keep class com.example.kbase.** { *; }
