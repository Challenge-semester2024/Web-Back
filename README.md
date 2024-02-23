# Web-Back-springSecurity 

<구현>
1. cors 설정
2. jwt 토큰 구현(어세스, 리프레스 토큰 생성 및 재발급)
3. jwt 필터 구현
4. 인증이 필요한 url 구분

<리팩토링 할 사항>
1. stackTrace는 반환하지 않는 쪽으로
2. jwtFilter는 응답반환 부분과 에러를 터트리는 부분에 대해 클래스를 나누면 좋을 것 같음
