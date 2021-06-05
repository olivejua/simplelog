

# 사용기술

- Spring boot 2.5.0
- Java 11
- JPA
- Querydsl
- Spring Security
- H2

버전 추가  필요

# Commit message
* 형식
```
{이모지 숏코드} {키워드}: 커밋메세지 ({깃허브 이슈번호})
```

* 예시
```
✨ feature: 회원 로그인 개발 (#1)

- 본문(긴 설명이 필요한 경우, 무엇을, 왜)
```

# Gitmoji

| 이모지 | 이모지 숏코드 | 키워드 | 설명 |
|---|---|---|---|
| 🐛 | `:bug:` | bug | 버그 수정 |
| ✨ | `:sparkles:` | feature | 새 기능 |
| ⚡️ | `:zap:` | performance | 성능 개선 |
| ♻️ | `:recycle:` | refactor | 코드 리팩토링 |
| 💡 | `:bulb:` | comment | 주석 추가/수정 |
| ✅ | `:white_check_mark:` | test | 테스트(추가/수정/버그픽스/리팩토링 등) |
| ➕ | `:heavy_plus_sign:` | dependency | 의존성 추가 |
| ➖ | `:heavy_minus_sign:` | dependency | 의존성 제거 |
| 📝 | `:memo:` | docs | 문서 추가/수정 |
| 🔧 | `:wrench:` | config | config 파일 추가/수정 |
| 🔥 | `:fire:` | remove | 코드/파일 삭제 |
| ⏪ | `:rewind:` | revert | 변경 내용 되돌리기 |
| 🚚 | `:truck:` | move | 파일/리소스 이동 |
| 🙈 | `:see_no_evil:` | gitignore | .gitignore 추가/수정 |


# 컨벤션

- `@Column(name = "user_id")` 소문자 + 언더바는 동일하게 한다
- 기본 생성자의 접근제어자는 protected로 한다
- List보다는 복수형을 좀 더 지향한다
- setter는 사용을 최소화한다
- getter는 도메인에서 필요 시에만 사용한다
