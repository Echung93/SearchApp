# SearchApp

![searchApp mockup](https://github.com/Echung93/SearchApp/assets/83617532/438d439f-60ad-4672-95e3-4b65250d3830)

<br>

## 프로젝트 소개

- 검색어를 입력하여 이미지 검색을 할 수 있습니다.
- 보관함 기능으로 원하는 이미지를 추가 및 삭제할 수 있습니다.

<br>

## ☕ 개발환경
- __개발도구__ : Android Studio
- __사용언어__ : Kotlin
- __D B M S__ : Room DB
- __버전 및 이슈관리__ : Github

<br>

## 사용 기술 & 라이브러리
- Compose
- Dagger - Hit
- Retrofit
- okhttp
- Gson
- ViewModel
- Coil
- Room DB
- MVVM

<br>

## 페이지별 기능

### [초기 화면]
- 서비스 접속 초기 화면으로 splash 화면이 잠시 나온 뒤 다음 페이지가 나타납니다.

| 초기 화면 |
|----------|
|![Splash](https://github.com/Echung93/SearchApp/assets/83617532/45ab5e9b-ae71-435e-b442-0b3bf868207e)

<br>

### [검색 화면]
- 검색어를 통해 이미지를 검색할 수 있습니다.
- 만약 검색어가 입력이 안되면 Snackbar을 호출합니다.
- 페이징 처리로 검색 결과를 50개 단위로 불러오고 보여줍니다.

| 검색 화면 |
|----------|
|![SearchPage](https://github.com/Echung93/SearchApp/assets/83617532/355bee7d-eaa1-4eca-af9d-a56f86a28571)

<br>

### [보관함 화면]
- 보관함 기능을 통해서 추가 및 삭제가 가능합니다.
- 빈 보관함이면 EmptyScreen을 보여줍니다.

| 보관함 화면 |
|----------|
|![FavoritePage](https://github.com/Echung93/SearchApp/assets/83617532/9ce72721-244b-4973-88d4-61cf31a4a7ed)

<br>

## 10. 프로젝트 후기

compose와 hilt를 이용한 처음 하는 프로젝트였습니다.
xml만 다뤄보다가 compose를 통해서 작업을 하니 초반에는 어려움을 겪었습니다. 하지만 xml와 fragment를 오고 가면서 하는 작업보다 compose에서 작업을 하는 것이 작업 편리성은 좀 더 편하게 느껴졌습니다. 그리고 Preview를 제공해 줘서 실시간으로 확인할 수 있는 점도 좋았습니다.
compose를 사용하면서 SideEffect는 대부분 LaunchedEffect를 사용해서 처리하였습니다. codeLab을 조금 본 후 작업하다 보니 다른 sideEffect를 사용해 보지 못한 아쉬움이 있지만 향후 더 많은 작업을 하면서 적재적소에 맞는 sideEffect를 처리하는 방법을 터득해야겠습니다.
그리고 hilt를 통해서 의존성 주입을 해보았습니다. nowinandroid를 참고하면서 최대한 작업을 해보았지만 아직까지 잘 사용하는지는 모르겠지만 의존성 주입을 하는 이유에 대해서 알 수 있었습니다.
마지막으로 coroutine을 사용하면서 기존에 Rxjava를 사용해서 비동기 처리를 했을 때 보다 알아야 할 구문도 적고 코드도 간략해져서 coroutine의 장점을 알 수 있었습니다. 앞으로도 coroutine을 사용하면서 코드를 더 깔끔하게 짜보려고 노력해 봐야겠습니다.



