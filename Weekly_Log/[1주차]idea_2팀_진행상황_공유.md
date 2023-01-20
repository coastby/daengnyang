## 팀 구성원, 개인 별 역할

---

### 조예지 `PM`

- API 설계 및 ERD 작성
- Spring REST Docs 설정
- 회원가입 설계

### 김희정 `CTO`

- Github Flow 정리 & 커밋 컨벤션 정리
- spring security 설정 및 JWT 적용
- 코드 리뷰 및 merge

### 조문주 `기획`

- UI 정의서 작성
- UI 프로토타입

### 백승근 `인프라`

- Jenkins를 이용한 CI/CD 구축
- Blue &  Green 방식의 무중단 배포 구현
- Docker - 컨테이너, 프로젝트 빌드
- 일기 작성 구현중

### 이상훈 `기획`

- UI 정의서 작성
- 반려동물 CRUD API 작성

### 김민경 `인프라`

- Jenkins - Gitlab과 연동하여 CI/CD 구축
- Docker - 프로젝트 빌드, 컨테이너 생성
- Nginx - Blue & Green 방식의 무중단 배포 구현
- 일정(Schedule) CRUD + 부탁하기(assign) 기능 구현

## 팀 내부 회의 진행 회차 및 일자

---

- 1회차(2023.01.16) 디스코드 보이스 채널 (불참인원 없음)
- 2회차(2023.01.17) 디스코드 보이스 채널 (불참인원 없음)
- 3회차(2023.01.18) 디스코드 보이스 채널 (불참인원 없음)
- 4회차(2023.01.19) 디스코드 보이스 채널 (불참인원 없음)
- 5회차(2023.01.20) 슬랙 허들 (불참인원 없음)

## 현재까지 개발 과정 요약

---

### 조예지

- API와 entity를 설계할 때는 설계만 하면 되는 줄 알았다. 하지만 전반적으로 어떤 기술이 쓰이고 어떤 흐름으로 구성을 해야하는 지를 알아야 설계를 할 수 있었다. 그래서 구체적인 구현 방법 (예, 알람 등)이 정해지지 않은 상황에서 설계를 하는 것이 불가능하다고 생각했고 구현할 수 있는 방법들을 찾아보고 이해하는 데에 시간이 많이 들었던 것 같다.
- 결국 알람 entity가 필요하고 이를 어떻게 운용할 지는 크게 달라진 것은 없지만 Firebase 기반의 FCM을 이용한 푸쉬 알림, 그리고 알람을 실시간으로 프론트에 전달할 수 있는 SSE, 웹소켓에 대해서 알게되었고 이를 기반으로 알람을 구현할 예정이다.
- Spring docs를 이용해서 REST 문서를 작성하는 과정이 익숙치가 않아서 블로그에 정리를 하였다. 팀원분들도 같이 해야하는 작업이라 더 잘 알고 알려드려야 하는데 생각보다 이해하지 못해서 아쉬웠다.

### 이상훈

- Pet entity 작성완료
  - 동물등록할때 품종 매개변수를 선택하게 되는데 이것들을 enum으로 선언할지 고민이 된다. 종류가 많기 때문에 선언하는데 시간이 많이 들고, 고양이와 강아지 두가지가 있기 때문에 구분하기가 힘들지만 선언해놓으면 추후에 검색하거나 데이터 활용하기 유리해 보인다.
  - 생일 매개변수 참조타입으로 Date를 처음 써봤다. 날짜 관련해서는 `LocalDateTime` 밖에 몰랐는데 새로 알게 되었다.
  - 매개변수에 동물사진이 있다. 아직 어떤식으로 DB에 저장되고 뽑아서 쓰는지 감이 안잡혀서 자료를 찾아봐야할거 같다.
- 반려동물 관련 CRUD 작성중
- Ui 정의서 바탕으로 thymeleaf로 화면 띄우기 시도중
  - 멋사 SNS를 연습물삼아 thymeleaf로 띄워보고 있는데 회원가입, 로그인 부분 구현에 있어서 인증, 인가가 잘 처리되지 않고 있다.

### 백승근

- `Gitlab + jenkins + nginx(blue&green배포)` 으로 인프라를 구축하는데 있어서 처음 해보는 방식이라 매순간 당황했지만 레퍼런스가 많은 만큼 참고할 수 있는 것도 많았다.
- 리눅스 환경이 익숙하지 않아서 스크립트 작성, 명령문 등이 어색했지만 하는 과정에서 점차 알아나가기 시작했다.
- 일기 작성 기능 구현중

### 김희정

- Git 협업 방식을 정할 때 Git flow와 Github Flow 중 고민을 하다가 Git flow는 브랜치 구성이 너무 복잡하기도 하고 단기간에 적용하기는 쉽지 않을 것 같아 Github Flow로 결정하고 이를 노션에 정리해서 팀원들과 공유했다. revert나 cherry-pick 기능도 찾아봐야할 것 같다.
- 커밋 컨벤션도 정하여 노션에 공유했다. dto에만 to, from 엔티티를 쓰는거나 변수명 구체적으로 쓸지 등 코드 스타일도 한번 정리해서 공유하면 좋을 듯해 차주에 작성할 예정이다.
- 주로 JWT 관련 에러를 잡을 때 JwtTokenFilter에서 throw를 했었는데, 코드리뷰 과정에서 JwtUtil 에서도 exception throw하면 JwtExceptionFilter에서 받을 수 있다는 걸 알게되었다😀
- security와 jwt 설정 시 sprintboot 3.0 버전업으로 depreciate된 메소드들을 최신 버전으로 수정하였다.
- docker에서 gradle 빌드 시 `asciidoctor` 관련 에러가 발생해 고치는중,, >> 해결!

### 김민경

- 기존 Gitlab을 이용한 CICD 구축 방식이 아닌 `Gitlab + jenkins + nginx(blue&green배포)` 방식으로 변경하여 프로젝트를 진행하기로 하였는데 처음 사용하는 기술이다보니 이것 저것 검색을 해보아도 우분투 명령어 사용이나 쉘스크립트 작성 하는 부분에서 해석,이해하는 것이 많이 어려웠다.
- 검색 시 참고 할 레퍼런스도 많고 따라하면서 리눅스 명령어, SSH 통신, Docker, nginx의 여러 배포방법 등 여러 기술들을 접할 수 있어 좋았고, CICD의 진행 순서나 동작방식을 많이 이해하게 되었다.
- 레퍼런스를 따라하다보니 아직 이해가 안되는 부분들이 좀 있는데 시간을 내서 좀 더 공부를 해보면 좋을 것같다.

### 조문주

- UI 정의서, 프로토타입 작성
  - 요구사항 정의서와 ERD를 참고하여  기획안을 작성하고 그에 따른 프로토타입을 작성했다.
  - 전체적인 흐름을 파악하고 기획안을 작성했어야 하는데 중간중간 놓친 부분이 많아서 수정에 시간을 많이 뺏긴것 같다. 기능서 작성을 꼼꼼하게 해야할 필요성을 느꼈다.
- 회원가입, 로그인 UI 구현 중
  - 부트스트랩을 적용해 레이아웃을 나누는 작업을 하고 있다.
- 상단바 구현 중
  - 헤더에서 토큰을 반환받아 다음 api 호출에서 붙여넣는 동작 방식에서 어려움을 겪고 있다.

## 개발 과정에서 나왔던 질문

---

### **🚫 jenkins spring build 버전 이슈**

Jenkins에서 Gradle Build 할 때 스프링부트 3.x / java 17 버전 이슈

- EC2 환경에서 젠킨스를 설치 할 때 아래 코드를 사용 했는데 이렇게 설치 되면 java 11 버전을 사용하는 젠킨스 버전이 설치되어 스프링부트 3.x / java 17 버전 사용 시에 버전호환에러가 남

```bash
$ sudo docker run -itd --name jenkins -p 9000:8080 jenkins/jenkins:lts
```

- 빌드 에러 로그 일부 발췌

```java
// ...
* What went wrong:
A problem occurred configuring root project 'infra-test1'.
> Could not resolve all files for configuration ':classpath'.
   > Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.0.1.
     Required by:
         project : > org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.1
// ...

```

```java
// ...
* What went wrong:
Execution failed for task ':compileJava'.
> error: invalid source release: 17
// ...
```

- 해결방법은 젠킨스 설치 시 자바 버전을 명시해주어 자바17 버전도 호환이 되는 최신 버전의 젠킨스(Jenkins 2.339+)를 설치하면 됨

```bash
$ sudo docker run -d --name jenkins -p 9000:8080 jenkins/jenkins:jdk17
```

- 젠킨스 공식 문서 : [https://www.jenkins.io/blog/2022/03/21/java17-preview-availability/](https://www.jenkins.io/blog/2022/03/21/java17-preview-availability/)

![Untitled](/uploads/da2735a8cd11b73d24ee9d4106dfdbb8/Untitled.png)

### 🚫 테스트코드가 안 됨

회원가입 controller test시 아래의 에러가 발생하면서 실행되지 않았다.

```
Factory method 'mockMvc' threw exception with message: javax/servlet/http/HttpServletResponse
java.lang.ClassNotFoundException: javax.servlet.http.HttpServletResponse

```

classpath를 못 찾고 있다는 에러였다.

**⭕️ dependency들 버전을 높여보자**

해당 에러 문구를 서치해보니 보통 버전 이슈가 많았다. 현재 프로젝트도 SpringBoot 및 Java 버전을 올린 상태라 가능성이 있을 것 같아서 security test 관련 dependency의 버전을 올려주었다.

spring security test 버전 5.7.x에서 6.0.x로 올리니 실행되었다.

```
implementation 'org.springframework.security:spring-security-test:6.0.1'
```

### 🚫 GitLab CI/CD build 이슈

spring docs 적용 후 아래 에러를 ci/cd가 되지 않았다.

![Untitled2](/uploads/4ea92686f10b1f7117051da84316471e/Untitled2.png)

**⭕️ 해결**

도커 파일에서

`RUN gradle build -x test --parallel`

명령어에서 `-x test`는 테스트를 스킵하는 옵션이다. 하지만 spring docs에서는 테스트를 통과하면서 adoc들이 /generated-snippets에 저장되는데, 이를 스킵하니 폴더가 생성되지 않았던 것이다. 해당 옵션을 지우고 해결하였다.

```java
RUN gradle build --parallel
```

## 개발 결과물 공유

---

📎 [Repository](https://gitlab.com/daengnyangffouchak/daily-daengnyang)

🖥 [블로그](https://daengnyangproject.tistory.com/)

🖥 [UI 프로토타입](https://daengnyangproject.tistory.com/7)