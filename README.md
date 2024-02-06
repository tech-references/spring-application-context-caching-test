### 컨텍스트 캐싱
> MemberRepositoryTest, ProductRepositoryTest는 모두 모킹없이 @DataJpaTest, @ActiveProfiles 애너테이션만 사용했다.

![trace_옵션_추가.png](images/trace_%EC%98%B5%EC%85%98_%EC%B6%94%EA%B0%80.png)
- 테스트용 yml에 이미지와 같이 context cache에 대한 logging level 설정
- `TRACE`로 레벨을 설정함으로써 컨텍스트 저장, 조회에 대한 로그를 확인할 수 있음

---

![MemberRepositoryTest에서_컨텍스트_캐싱.png](images/MemberRepositoryTest%EC%97%90%EC%84%9C_%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8_%EC%BA%90%EC%8B%B1.png)
- MemberRepositoryTest에서 ApplicationContext를 저장하는 것을 확인

---

![ProductRepositoryTest에서_캐싱된_컨텍스트_조회.png](images/ProductRepositoryTest%EC%97%90%EC%84%9C_%EC%BA%90%EC%8B%B1%EB%90%9C_%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8_%EC%A1%B0%ED%9A%8C.png)
- MemberRepositoryTest 다음에 실행된 ProductRepositoryTest에서 캐싱된 ApplicationContext를 조회하여 사용하는 것 확인

---

- `@MockBean`을 사용하는 경우 스프링 빈을 수정하는 것이기 때문에 컨텍스트가 수정되어 새로운 컨텍스트를 로딩한다. 
  - 다만 애초에 로딩할 빈이 많지 않아서 그런지 `@SpringBootTest` 애너테이션임에도 불구하고 굉장히 빠름
---

### Memo
- `Mockito`의 `@InjectMocks`, `@Mock`은 스프링 빈을 활용하는 것이 아니라 ApplicationContext에 영향을 주지 않는다.
  - 즉, 스프링 빈 로딩없이 단위 테스트에 활용하기 적합
- 하나의 메소드만 테스트하는 RepositoryTest의 경우에는 `@DataJpaTest`를 사용 
  - 컨텍스트가 달라지지 않아 캐싱된 ApplicationContext를 조회하여 손쉽게 재사용

### TODO
- [x] 통합테스트, RESTDocs 테스트에서 `@MockBean` 애너테이션을 사용하지 않고 모킹과 컨텍스트 캐싱을 성공적으로 할 수 있을지 파악
  - 추상 클래스를 만들어 테스트에 필요한 모든 객체를 `@MockBean`를 통해 주입하고 테스트 클래스에서 상속받는다.
- [ ] [우아한스프링배치 by 우아한형제들 이동욱님](https://www.youtube.com/watch?v=_nkJkWVH-mo) 참고
