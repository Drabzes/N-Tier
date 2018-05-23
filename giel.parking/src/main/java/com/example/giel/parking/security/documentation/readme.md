## generate token

### url is:

- http://localhost:8080/api/authentication-service/token/generate-token

### request info is :



### response is : 

``
{
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcm5vLmJydXluc2VlbHNAZGV2b3RlYW0uY29tIiwiY3JlYXRlZCI6MTUyMzQ0NDg2NjgzNiwiaXNzIjoiRGV2b1BhcmtpbmciLCJleHAiOjE1MjM1MzEyNjYsInV1aWQiOiI5ZjMwYTczOS02ZGYwLTQ5Y2ItODYyZS1lYzMzMGRlNzM3Y2EifQ.K63CYzI9ouyAYw8ek8HLAWpf5zIG66QEwHnhBy92kqAPu4tvz3SA8nEhhF0kvidgBtxW4NqC6qCPHchQRQIzrA",
    "user": {
        "id": 2,
        "email": "arno.bruynseels@devoteam.com",
        "password": null,
        "roles": [
            {
                "id": 2,
                "name": "USER"
            }
        ],
        "userInfoId": 2,
        "active": true
    }
}
``

## refresh token

