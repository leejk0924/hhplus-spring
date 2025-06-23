---
description: 게시글 관련 CRUD 및 댓글 API
---

# 게시글, 댓글 관리

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
    "id" : 2,
    "title" : "제목2",
    "username" : "작성자2",
    "content" : "내용2",
    "createdAt" : "2025-06-14T22:30",
    "modifiedAt" : "2025-06-14T22:30"
    "commentList" : [
        {
            "commentId" : 1,
            "comment" : "댓글1",
            "username" : "작성자1",
            "createdAt" : "2025-06-14T22:35",
            "modifiedAt" : "2025-06-14T22:35"            
        }, ...
    ]
  },
  {
    "id" : 1,
    "title" : "제목1",
    "username" : "작성자1",
    "content" : "내용1",
    "createdAt" : "2025-06-14T17:30",
    "modifiedAt" : "2025-06-14T17:30"
    "commentList" : []
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
  "id" : 1,
  "title" : "제목",
  "username" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30",
  "modifiedAt" : "2025-06-14T22:30",
  "commentList" : [
      {
          "commentId" : 1,
          "comment" : "댓글1",
          "username" : "작성자1",
          "createdAt" : "2025-06-14T23:35",
          "modifiedAt" : "2025-06-14T24:35"
      }, 
      {
          "commentId" : 2,
          "comment" : "댓글2",
          "username" : "작성자2",
          "createdAt" : "2025-06-14T24:35",
          "modifiedAt" : "2025-06-14T24:50"
      }
  ]
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

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Request**

| Name      | Type   | Description |
| --------- | ------ | ----------- |
| `title`   | string | 게시글 제목      |
| `content` | string | 게시글 본문      |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "id" : 1,
  "title" : "제목",
  "username" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30",
  "modifiedAt" : "2025-06-14T22:30"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}

## 게시글 수정

<mark style="color:green;">`PUT`</mark> `/articles/{:id}`

<선택한 게시글의 수정할 데이터와 비밀번호를 같이 보내고 서버의 비밀번호 확인 후 일치 시 데이터를 수정하고 수정된 게시글을 반환>

**Headers**

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Request**

| Name      | Type   | Description |
| --------- | ------ | ----------- |
| `title`   | string | 게시글 제목      |
| `content` | string | 게시글 본문      |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "id" : 1,
  "title" : "제목",
  "username" : "작성자",
  "content" : "게시글 내용",
  "createdAt" : "2025-06-14T22:30",
  "modifiedAt" : "2025-06-15T10:20",
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : "400",
  "error": "작성자만 삭제/수정 할 수 있습니다."
}
```
{% endtab %}

{% tab title="UnAuth" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}

## 게시글 삭제

<mark style="color:green;">`DELETE`</mark> `/articles/{:id}`

<선택한 게시글의 비밀번호를 서버에 보내고 비밀번호 일치 시 게시글 삭제 후 성공 표시 반환>

**Headers**

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Body**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `username` | string | 작성자         |
| `password` | string | 비밀번호        |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "statusCode" : 200,
  "message": "Deleted Article"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : "400",
  "error": "작성자만 삭제/수정 할 수 있습니다."
}
```
{% endtab %}

{% tab title="UnAuth" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}

## 댓글 작성

<mark style="color:green;">`POST`</mark> `/articles/{:articleId}/comments`

\<articleId에 종속되는 댓글 작성하고, 작성 완료되면 작성한 댓글 반환>

**Headers**

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Body**

| Name    | Type   | Description |
| ------- | ------ | ----------- |
| comment | string | 댓글 본문       |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "comment": "댓글 내용"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}

## 댓글 수정

<mark style="color:green;">`PUT`</mark> `/articles/{:articleId}/comments/{:commentId}`

<토큰 검사 후 유효한 사용자일 경우 댓글 수정 후 댓글이 저장 된 경우 수정한 댓글 반환>

**Headers**

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Body**

| Name      | Type   | Description |
| --------- | ------ | ----------- |
| `comment` | string | 수정 하려는 댓글   |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "comment": "댓글 수정"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : "400",
  "error": "작성자만 삭제/수정 할 수 있습니다."
}
```
{% endtab %}

{% tab title="UnAuth" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}

## 댓글 삭제

<mark style="color:green;">`DELETE`</mark> `/articles/{:articleId}/comments/{:commentId}`

<토큰 검사 후 유효한 사용자일 경우 댓글 삭제 후, 성공적으로 삭제된 경우 삭제 완료 했다는 메시지, 상태코드 반환>

**Headers**

| Name          | Value              |
| ------------- | ------------------ |
| Content-Type  | `application/json` |
| Authorization | `Bearer <token>`   |

**Body**

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "statusCode" : 200,
  "message" : "댓글 삭제 성공"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "statusCode" : "400",
  "error": "작성자만 삭제/수정 할 수 있습니다."
}
```
{% endtab %}

{% tab title="UnAuth" %}
```json
{
  "statusCode" : 400,
  "error": "토큰이 유효하지 않습니다."
}
```
{% endtab %}
{% endtabs %}
