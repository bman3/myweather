# ☀️ MyWeather - 실시간 날씨 & 미세먼지 체크 앱

**MyWeather**는 현재 위치 기반으로 실시간 **날씨 정보**와 **미세먼지 수치(PM2.5)**를 확인할 수 있는 Android 앱입니다.  
심플한 UI와 직관적인 구성으로, 누구나 쉽게 날씨와 공기질 상태를 확인할 수 있습니다.

---

## 🧩 주요 기능

- 📍 **GPS 기반 현재 위치 자동 감지**
- 🌤️ **OpenWeather API**를 통한 실시간 날씨 정보
- 🌫️ **AirVisual API**를 통한 PM2.5 미세먼지 수치 제공
- 🧼 **API 키 분리 및 보안 강화** (apikey.properties 분리)
- 🎨 감성적인 인트로 화면과 맞춤형 앱 아이콘 포함
- 🧭 인트로 → 메인 자동 전환 기능

---

## 🔧 사용 기술

- `Java` + `Android SDK`
- `Volley` 네트워크 통신
- `Google Play Services Location`
- `OpenWeatherMap API`
- `AirVisual API`

---

## 🔒 보안 주의사항

이 프로젝트는 **민감한 API 키를 `.gitignore`를 통해 보호**하고 있으며,  
`apikey.properties` 파일은 GitHub에 포함되어 있지 않습니다.  
API 키는 별도로 발급 후, 로컬에서 구성하여 빌드해야 합니다.

```properties
# apikey.properties 예시
OPEN_WEATHER_API_KEY=여기에_오픈웨더_API키
AIR_VISUAL_API_KEY=여기에_에어비주얼_API키
