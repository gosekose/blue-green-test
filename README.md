# 코틀린 스프링 블루-그린 배포 적용

## build.gradle
* plugins 추가 
  ```gradle
  id 'org.jetbrains.kotlin.jvm' version '1.7.10'
  ```
* dependencies 추가 
  ```gradle
  implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8'
  ```
* 컴파일 추가 
  ```gradle
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
  ```
  
* all-open 플러그인 추가 
  ```gradle
      id 'org.jetbrains.kotlin.plugin.spring' version '1.7.10'
  ```
  
* query-dsl 추가 
  ```gradle
  plugins {
      // plugins
      id "org.jetbrains.kotlin.kapt" version "1.6.21"
  }

  dependencies {
      implementation("com.querydsl:querydsl-jpa:5.0.0")
      kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
      kapt("org.springframework.boot:spring-boot-configuration-processor")
  }
  
  ```
  
