---
description: 회원가입, 로그인 등의 사용자 관련 API
---

# 사용자 관리

## 회원가입

<mark style="color:green;">`POST`</mark> `/register`

username과 password를 입력받고, username은 글자수 4\~10자, 알파벳 소문자, 숫자로 구성, password는 글자수 8\~15자, 알파벳 대소문자, 숫자로 구성한다.\
DB에 중복된 username이 있는지 조회후 없을 경우 저장하고 Client로 성공 메시지 반환 / 실패할 경우 에러 메시지 반환

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type   | Description                                         |
| ---------- | ------ | --------------------------------------------------- |
| `username` | string | 유저명 (최소 4자 / 최대 10자 / 알파벳 소문자, 숫자로 구성)              |
| `password` | string | 비밀번호 (최소 8자 / 최대 15자 / 알파벳 소문자, 대문자, 숫자, 특수문자 로 구성) |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "statusCode" : 200,
  "message": "회원가입에 성공하였습니다."
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : 400,
  "error": {
      "username" : "username 은 소문자 알파벳과 숫자만 사용할 수 있습니다.",
      "password" : "password는 영문 대소문자와 숫자, 특수 문자만 사용할 수 있습니다."
  }
}
```
{% endtab %}
{% endtabs %}

## 로그인 API

<mark style="color:green;">`POST`</mark> `/login`

username과 password를 전달받고 DB에서 username으로 회원 유무 확인후 password로 해당 유저가 맞는지 확인, \
로그인 성공 시, 유저의 정보를 활용하여 JWT 발급\
발급된 JWT는 Header에 추가하고 성공 메시지 반환

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `username` | string | 유저명         |
| `password` | string | 비밀번호        |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "statusCode" : 200,
    "message" : "로그인에 성공하였습니다"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : 400,
  "error": "회원을 찾을 수 없습니다."
}
```
{% endtab %}
{% endtabs %}
