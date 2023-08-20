# my native-java api<br>
=================<br>
NATTHAPHONG CRUD <br>
================= <br>
===========================================================================<br>
Connected DB : jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true<br>
===========================================================================<br>
Total execution time: 0.301 seconds<br>

# structure program<br>
├── Dockerfile
├── Httpenum
│   ├── HttpContentType.java
│   ├── HttpMethod.java
│   └── HttpStatus.java
├── Server.java
├── ServerProcess.java
├── application.yml
├── configuration
│   ├── ServerConfiguration.java
│   └── model
├── controller
│   └── CustomerController.java
├── exception
│   └── ProjectException.java
├── lib
│   └── mysql-connector-j-8.0.33.jar
├── model
│   ├── Credential.java
│   ├── CustomerModel.java
│   ├── ErrorResponseModel.java
│   └── ResponseModel.java
├── out
│   └── production
│       └── Java
│           ├── Dockerfile
│           ├── Httpenum
│           │   ├── HttpContentType.class
│           │   ├── HttpMethod.class
│           │   └── HttpStatus.class
│           ├── Server.class
│           ├── ServerProcess.class
│           ├── application.yml
│           ├── configuration
│           │   └── ServerConfiguration.class
│           ├── controller
│           │   └── CustomerController.class
│           ├── exception
│           │   └── ProjectException.class
│           ├── lib
│           │   └── mysql-connector-j-8.0.33.jar
│           ├── model
│           │   ├── Credential.class
│           │   ├── CustomerModel.class
│           │   ├── ErrorResponseModel.class
│           │   └── ResponseModel.class
│           ├── repository
│           │   ├── CustomerRepository.class
│           │   └── Impl
│           │       └── CustomerRepositoryImpl.class
│           ├── service
│           │   └── CustomerService.class
│           ├── start.sh
│           ├── tarWay
│           │   ├── V1_create_table.sql
│           │   └── V2_insert.sql
│           └── utils
│               ├── BcryptUtil.class
│               ├── Constant$Date.class
│               ├── Constant.class
│               ├── HttpResponse.class
│               ├── HttpUtil.class
│               ├── JsonConverter.class
│               ├── JwtUtil.class
│               ├── NumberUtils.class
│               ├── StringUtils.class
│               ├── SystemOutUtil.class
│               └── Validate.class
├── repository
│   ├── CustomerRepository.java
│   └── Impl
│       └── CustomerRepositoryImpl.java
├── service
│   └── CustomerService.java
├── start.sh
├── tarWay
│   ├── V1_create_table.sql
│   └── V2_insert.sql
└── utils
    ├── BcryptUtil.java
    ├── Constant.java
    ├── HttpResponse.java
    ├── HttpUtil.java
    ├── JsonConverter.java
    ├── JwtUtil.java
    ├── NumberUtils.java
    ├── StringUtils.java
    ├── SystemOutUtil.java
    └── Validate.java
