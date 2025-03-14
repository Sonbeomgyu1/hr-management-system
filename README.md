# hr-management-system (Spring Boot 기반 온라인 인력관리 시스템)

## 📌 프로젝트 개요
**hr-management-system**은 **Spring Boot 기반의 온라인 인력관리 시스템**입니다.  
이 시스템은 **잡코리아에서 검색한 프리랜서고객목록 및 잡코리아고객목록 고객을 효율적으로 관리**할 수 있도록 설계되었습니다.

### ✨ 주요 기능

- **프리랜서 및 잡코리아 목록**: 검색, 수정, 삭제 가능
- **로컬 환경 접속**: localhost:8080에서 로그인 후 메인 페이지의 풀캘린더 확인 가능
- **프리랜서 관리**: 검색, 등록, 수정, 삭제 및 고객 목록 이동 , 대화이력(댓글) 기능
- **잡코리아 관리**: 검색, 등록, 수정, 삭제 및 고객 목록 이동 , 대화이력(댓글) 기능
- **게시판 CRUD**: 기본적인 게시판 기능 제공
- **고객 목록 조회**: 로그인한 사용자 ID별 필터링된 목록 조회 , 대화이력(댓글) 기능
- **권한 관리**: 관리자(ROLE_ADMIN)와 사용자(ROLE_USER) 구분
- **풀캘린더 일정 관리**: CRUD 기능 제공


### 🏢 **상세 기능**

- **프리랜서 및 잡코리아 목록 검색/수정/삭제 기능**
 
- **로컬 환경 접속:** localhost:8080에서 로그인 후 메인 페이지의 풀캘린더 확인 가능.
 ![image](https://github.com/user-attachments/assets/fff1b3f4-6f14-49dc-8baf-f02a39ad6dec)
![image](https://github.com/user-attachments/assets/3c581d2e-17d7-4563-838c-2f7f3e4bb40f)


- **프리랜서**: 검색, 등록, 수정, 삭제 및 고객 목록 이동 기능 제공.
  - **프리랜서 리스트 페이지**에서 **검색 기능 :** 등록자, 지역, 이름, 기술, 업무, 경력 등을 기준으로 검색할 수 있습니다.
    ![image](https://github.com/user-attachments/assets/a7744c8c-e133-4562-b7bf-29c2e1c9cc21)
  - **프리랜서 등록:** 프리랜서 등록 페이지에서 프리랜서를 등록하면 해당 정보가 시스템에 자동으로 저장됩니다.
    ![image](https://github.com/user-attachments/assets/be63ecfb-6383-43a6-917c-2e115c0114b2)
  - **프리랜서 수정 페이지**:대화 내용 작성 후 전송하면, 접속한 ID의 displayName이 대화 내용에 표시됩니다.
   - **프리랜서 수정 페이지**에서 **고객 목록으로 이동** 버튼을 누르면, 프리랜서 정보가 고객 목록으로 저장됩니다.
   - **프리랜서 수정 페이지**에서 **delect** 버튼을 누르면, 프리랜서 정보가 삭제됩니다.
    ![image](https://github.com/user-attachments/assets/39188348-5c62-4f93-bc3c-a17a9da89f2d)
    ![image](https://github.com/user-attachments/assets/407befc8-9927-4b69-a10c-8b5ac5de7db3)
  
  
 
- **잡코리아 관리**:검색, 등록, 수정, 삭제 및 고객 목록 이동 기능 제공.
  - **잡코리아 리스트 페이지**에서 **검색기능**: 유형, 등록자, 지역, 이름, 기술 등을 기준으로 검색할 수 있습니다.
   ![image](https://github.com/user-attachments/assets/db3a5f81-fad6-4c7a-99e5-15cfbe155ee5)

  - **잡코리아 등록:** 잡코리아 등록 페이지에서 잡코리아를 등록하면 해당 정보가 시스템에 자동으로 저장됩니다.
   ![image](https://github.com/user-attachments/assets/0c809c48-bf1c-4044-bb04-d118d2fd9fa5)

  - **잡코리아 수정 페이지**:대화 내용 작성 후 전송하면, 접속한 ID의 displayName이 대화 내용에 표시됩니다.  
  - **잡코리아 수정 페이지**에서 **고객 목록으로 이동** 버튼을 누르면, 잡코리아 정보가 고객 목록으로 저장됩니다.
  - **잡코리아 수정 페이지**에서 **delect** 버튼을 누르면, 잡코리아 정보가 삭제됩니다.
    ![image](https://github.com/user-attachments/assets/abd58aed-fb7a-4522-932b-33b48826d393)
    ![image](https://github.com/user-attachments/assets/b9426e6a-4edd-41b3-96c6-ed77eaf8c999)

  - 게시판은 **기본적인 CRUD 기능**을 지원하여 게시판 역할을 수행할 수 있습니다.
    ![image](https://github.com/user-attachments/assets/153e94f4-e115-46b8-9b36-687a10a4d4ab)

- **고객 목록 조회 (ID별 권한 관리):**  
  - 고객 목록은 **로그인한 사용자 ID에 따라 필터링**되어, **본인의 고객 목록만 조회 가능**합니다.
    ![image](https://github.com/user-attachments/assets/ecbe5706-9b54-4026-bc36-838409d43923)

- **권한별 페이지 구분 (ROLE_ADMIN / ROLE_USER)**  
  - **ROLE_ADMIN**: 관리자 전용 페이지 접근 가능  
  - **ROLE_USER**: 일반 사용자는 관리자 페이지 접근 불가
   ![image](https://github.com/user-attachments/assets/82af6be8-1f7e-44dd-a276-962f757033e9)

- **풀캘린더(FullCalendar) 기반 일정 관리**  
  - 메인 페이지에서 **풀캘린더를 활용한 CRUD 기능 제공**  
  - 해당 날짜의 라벨 별로 클릭시 일정 추가, 수정, 삭제 가능  
  - **이전 달 / 이번 달 일정 조회 기능 제공**
    ![image](https://github.com/user-attachments/assets/028c4674-adb0-4260-b60d-bfde96ee25f3)
    ![image](https://github.com/user-attachments/assets/8e3d1e10-3982-4264-a18c-4c2663082754)


- **모든 페이지 CRUD 기능 지원**  
  - 모든 데이터 관리 페이지에서 **CRUD (Create, Read, Update, Delete) 기능 구현**  

## 🛠 기술 스택
- **Backend:** Spring Boot 3.4.2, Spring Security, Spring Data JPA, Hibernate
- **Frontend:** Thymeleaf, FullCalendar.js
- **Database:** MySQL
- **Build Tool:** Gradle
- **Authentication:** Spring Security + JWT
