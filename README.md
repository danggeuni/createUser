## user manager

### user join
api : /api/user/join

payload : JSON exmaple

{

    "userId" : "juno",
    "password" : "010203abc",
    "nickname" : "jdddd",
    "name" : "무파마",
    "phone" : "010-0564-5678",
    "email" : "8naver@naver.com"
}

제약조건
1. userId 중복 불가
2. nickname 15자 이내
3. phone 형식 XXX(01|6~9) - XXXX(3|4) - XXXX
4. @, '.' 사용 필수
   
<br/>
예외처리 예시

    "nickname" : "jd2323232323ddd",
    이미 가입된 사용자 아이디 입니다.

<br/><br/><br/>

### find all user
api : /api/user/list?page=?&pageSize=?&sort=? (default value : 1, 5, id), sort = id(가입일순) | name(이름순)

sort default : ascending

response example :

{
    "content": [
    
        {
        
            "id": 1,
            "userId": "danggeuni",
            "nickname": "jdddd",
            "name": "파",
            "phone": "010-0564-5678",
            "email": "8naver@naver.com"
        },
        {
            "id": 2,
            "userId": "juno",
            "nickname": "jdddd",
            "name": "파",
            "phone": "010-0564-5678",
            "email": "2naver@naver.com"
        },
        {
            "id": 3,
            "userId": "hong",
            "nickname": "jdddd",
            "name": "파",
            "phone": "010-0564-5678",
            "email": "3naver@naver.com"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageSize": 5,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 3,
    "first": true,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 3,
    "empty": false
}

<br/><br/><br/>

### modify user
api : /api/user/{userId}

response example :

{

    "nickname": "jdddd22222",
    "name": "파",
    "phone": "010-0564-5678",
    "email": "3naver@naver.com"
}


