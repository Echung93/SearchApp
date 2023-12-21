# SearchApp

![searchApp mockup](https://github.com/Echung93/SearchApp/assets/83617532/438d439f-60ad-4672-95e3-4b65250d3830)
- 배포 URL : https://play.google.com/store/apps/details?id=com.echung93.searchapp
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

## 실행 방법
빌드 전, 'local.properties'에 다음과 같이 API KEY 값을 추가해야 합니다.

```groovy
kakao_api_key = {kakaoAPI KEY}
```

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

## 💥 트러블 슈팅
- 라이브러리 사용 오류 : material3라이브러리를 사용 중 키보드를 내리면 다시 안 올라오는 현상이 생겼습니다. 알고 보니 1.2.0-alpha12 버전을 사용하면서 ExperimentalComposeUiApi를 적용 안 하게 되면서 생기는 오류였습니다. 1.1.2버전을 사용하니 바로 오류가 수정되었습니다.
로그에도 찍히지 않아 오류를 찾는데 많은 시간이 소비되었지만, 이 경험으로 라이브러리를 선택하는 데 있어서 무조건 최신 버전이 아닌 신중하게 사용해야겠다는 생각을 하였습니다.
- 난독화 오류 : 앱을 출시하면서 난독화를 진행하여 앱을 출시하였지만, 난독화로 인해서 출시된 앱에서 API 검색이 잘 안되는 것을 확인할 수 있었습니다. 난독화는 클래스, 변수, 메서드 이름 등을 변경하는 것이라서 그런 것 같았습니다.
모델 부분에서 @SerializedName이 있어서 Json 모델 클래스와 매핑이 제대로 이루어지지 않아 발생하는 것으로 판단하였고, 그래서 model 부분을 proguard-rules에 추가하는 것으로 문제를 해결할 수 있었습니다.
- SideEffect - 1 : Search 화면에서 좋아요를 했지만, Favorite에서 좋아요를 취소하고 돌아오면 좋아요가 연동이 되지 않는 현상이 있었습니다. 이 현상을 해결하기 위해서 Effect API에서 제공해 주는 LaunchedEffect를 사용해서 Search 화면에 접근할 때 한 번 더 refresh 하도록 코드를 설정하였습니다. 하지만 Andorid에서는 LaunchedEffect에 key 값을 true를 사용하지 않는 걸 권장합니다.
- SideEffect - 2 : Paging 처리를 구현하는 중에 visibleItemsInfo에 값에 따라 paging을 수행하도록 코드를 작성했었습니다. 그런데 이런 경우에는 불필요하게 state를 많이 보내는 것을 확인할 수 있었습니다. 그래서 derivedStateOf를 통해서 문제를 해결하였습니다. derivedStateOf는 입력 인수와 출력 결과 사이의 변화량에 차이가 있을 때 Ui의 상태를 변경하는 것보다 state나 key가 많이 변경되는 경우에 사용할 수 있습니다. 우리는 paging을 할 때 내려가는 값이 필요한 게 아니라 paging 해야 하는 곳에 도착했는지의 상태 값을 알아야 하기 때문입니다.
  
<br>

## 프로젝트 후기

compose와 hilt를 이용한 처음 하는 프로젝트였습니다.
xml만 다뤄보다가 compose를 통해서 작업을 하니 초반에는 어려움을 겪었습니다. 하지만 xml와 fragment를 오고 가면서 하는 작업보다 compose에서 작업을 하는 것이 작업 편리성은 좀 더 편하게 느껴졌습니다. 그리고 Preview를 제공해 줘서 실시간으로 확인할 수 있는 점도 좋았습니다.
compose를 사용하면서 SideEffect는 대부분 LaunchedEffect를 사용해서 처리하였습니다. codeLab을 조금 본 후 작업하다 보니 다른 sideEffect를 사용해 보지 못한 아쉬움이 있지만 향후 더 많은 작업을 하면서 적재적소에 맞는 sideEffect를 처리하는 방법을 터득해야겠습니다.
그리고 hilt를 통해서 의존성 주입을 해보았습니다. nowinandroid를 참고하면서 최대한 작업을 해보았지만 아직까지 잘 사용하는지는 모르겠지만 의존성 주입을 하는 이유에 대해서 알 수 있었습니다.
마지막으로 coroutine을 사용하면서 기존에 Rxjava를 사용해서 비동기 처리를 했을 때 보다 알아야 할 구문도 적고 코드도 간략해져서 coroutine의 장점을 알 수 있었습니다. 앞으로도 coroutine을 사용하면서 코드를 더 깔끔하게 짜보려고 노력해 봐야겠습니다.



