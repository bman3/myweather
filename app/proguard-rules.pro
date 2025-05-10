# Retrofit / Gson 예외
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keep class com.google.gson.** { *; }

# TTS 음성 안내 예외
-keep class android.speech.tts.** { *; }

# 앱 리플렉션 관련 애노테이션 유지
-keepattributes Signature
-keepattributes *Annotation*
