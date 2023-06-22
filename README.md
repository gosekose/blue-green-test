# 코틀린 스프링 기초 

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
  
