### jys_nio_was

# 자바 Non blocking I/O 라이브러리를 이용해 Web Application Server를 구축하였습니다

## 미구현

* POST, PUT, DELETE에서 Body파싱부분
* 멀티스레딩 방식은 지원 x

## Example

* docker build -t jys/jar .
* docker run -p 8080:8080 --name jar jys/jar

* http://localhost:8080
* http://localhost:8080/test
* http://localhost:8080/json
