# Bid Cycle

중고 물품 경매 플랫폼

## Our Dev Convention

### Branch Name
`<branch-type>/<layer>/<domain>`
- feat/entity/member   
  멤버 엔티티 작업용 브랜치
- feat/repository/member   
  멤버 레포지토리 작업용 브랜치
- test/repository/member   
  멤버 레포지토리 테스트 코드 작성 브랜치

### PR Title
`<Domain> <Layer> 작성/수정/변경/...`
- Member Repository 작성   
  멤버 레포지토리 작성에 대한 PR

### Method Name
- update (o) / change (x)   
  - Ex) **updateNickname** (o) / **changeNickname** (x)

### Test Code convention
- BDD 방식으로 테스트 코드 작성   
  `given, when, then` 으로 명시하여 테스트 작성
- 테스트 함수의 경우 앞에 `test_` prefix 붙이기
  - Ex) **test_create_member()** (o) / create_member() (x)

  
