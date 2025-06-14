---
description: 게시글 관련 CRUD API
---

# 게시글 관리

## 게시글 전체 조회

<mark style="color:green;">`GET`</mark> `/articles`

<게시글 전체의 제목, 작성자명, 작성내용, 작성 날짜를 작성날 기준 내림차순으로 정렬>

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Request**

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  {
    "id" : 2
    "title" : "제목2",
    "author" : "작성자2",
    "content" : "내용2",
    "createdAt" : "2025-06-14T22:30"
  },
  {
    "id" : 1
    "title" : "제목1",
    "author" : "작성자1",
    "content" : "내용1",
    "createdAt" : "2025-06-14T17:30"
  }
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

## 선택한 게시글 조회

<mark style="color:green;">`GET`</mark> `/articles/{:id}`

<게시글의 제목, 작성자명, 작성날짜, 작성 내용 조회>

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Request**

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "id" : 1
  "title" : "제목",
  "author" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

## 게시글 작성

<mark style="color:green;">`POST`</mark> `/articles`

<제목, 작성자명, 비밀번호, 내용을 입력받고 저장되었을 경우 작성한 게시글이 반환됨>

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Request**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `title`    | string | 게시글 제목      |
| `username` | string | 작성자명        |
| `password` | string | 비밀번호        |
| `content`  | string | 게시글 본문      |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "id" : 1
  "title" : "제목",
  "username" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

## 게시글 수정

<mark style="color:green;">`PUT`</mark> `/articles/{:id}`

<선택한 게시글의 수정할 데이터와 비밀번호를 같이 보내고 서버의 비밀번호 확인 후 일치 시 데이터를 수정하고 수정된 게시글을 반>

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Request**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `title`    | string | 게시글 제목      |
| `username` | string | 작성자         |
| `password` | string | 비밀번호        |
| `content`  | string | 게시글 본문      |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "id" : 1,
  "title" : "제목",
  "username" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

## 게시글 삭제

<mark style="color:green;">`DELETE`</mark> `/articles/{:id}`

<선택한 게시글의 비밀번호를 서버에 보내고 비밀번호 일치 시 게시글 삭제 후 성공 표시 반환>

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `password` | string | 비밀번호        |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "status": "Deleted Article"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}
