# my native-java api<br>
=================<br>
NATTHAPHONG CRUD <br>
================= <br>
===========================================================================<br>
Connected DB : jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true<br>
===========================================================================<br>
Total execution time: 0.301 seconds<br>

# structure program<br>
├── Httpenum<br>
│   ├── HttpContentType.java<br>
│   ├── HttpMethod.java<br>
│   └── HttpStatus.java<br>
├── Server.java<br>
├── ServerProcess.java<br>
├── application.yml<br>
├── configuration<br>
│   ├── ServerConfiguration.java<br>
│   └── model<br>
├── controller<br>
│   └── CustomerController.java<br>
├── exception<br>
│   └── ProjectException.java<br>
├── lib<br>
│   └── mysql-connector-j-8.0.33.jar<br>
├── model<br>
│   ├── Credential.java<br>
│   ├── CustomerModel.java<br>
│   ├── ErrorResponseModel.java<br>
│   └── ResponseModel.java<br>
├── out<br>
│   └── production<br>
│       └── Java<br>
│           ├── Dockerfile<br>
│           ├── Httpenum<br>
│           │   ├── HttpContentType.class<br>
│           │   ├── HttpMethod.class<br>
│           │   └── HttpStatus.class<br>
│           ├── Server.class<br>
│           ├── ServerProcess.class<br>
│           ├── application.yml<br>
│           ├── configuration<br>
│           │   └── ServerConfiguration.class<br>
│           ├── controller<br>
│           │   └── CustomerController.class<br>
│           ├── exception<br>
│           │   └── ProjectException.class<br>
│           ├── lib<br>
│           │   └── mysql-connector-j-8.0.33.jar<br>
│           ├── model<br>
│           │   ├── Credential.class<br>
│           │   ├── CustomerModel.class<br>
│           │   ├── ErrorResponseModel.class<br>
│           │   └── ResponseModel.class<br>
│           ├── repository<br>
│           │   ├── CustomerRepository.class<br>
│           │   └── Impl<br>
│           │       └── CustomerRepositoryImpl.class<br>
│           ├── service<br>
│           │   └── CustomerService.class<br>
│           ├── start.sh<br>
│           ├── tarWay<br>
│           │   ├── V1_create_table.sql<br>
│           │   └── V2_insert.sql<br>
│           └── utils<br>
│               ├── BcryptUtil.class<br>
│               ├── Constant$Date.class<br>
│               ├── Constant.class<br>
│               ├── HttpResponse.class<br>
│               ├── HttpUtil.class<br>
│               ├── JsonConverter.class<br>
│               ├── JwtUtil.class<br>
│               ├── NumberUtils.class<br>
│               ├── StringUtils.class<br>
│               ├── SystemOutUtil.class<br>
│               └── Validate.class<br>
├── repository<br>
│   ├── CustomerRepository.java<br>
│   └── Impl<br>
│       └── CustomerRepositoryImpl.java<br>
├── service<br>
│   └── CustomerService.java<br>
├── start.sh<br>
├── tarWay<br>
│   ├── V1_create_table.sql<br>
│   └── V2_insert.sql<br>
└── utils<br>
    ├── BcryptUtil.java<br>
    ├── Constant.java<br>
    ├── HttpResponse.java<br>
    ├── HttpUtil.java<br>
    ├── JsonConverter.java<br>
    ├── JwtUtil.java<br>
    ├── NumberUtils.java<br>
    ├── StringUtils.java<br>
    ├── SystemOutUtil.java<br>
    └── Validate.java<br>
