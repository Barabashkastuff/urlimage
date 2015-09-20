# urlimage REST service


##Submit url

```
http://barabashkastuff.com/urlimageapi/submit  (POST)
```

###Request body

| Param         | Description                                     | 
| ------------- |:-----------------------------------------------:| 
| url           | Url, that contains images, you want to download | 

###Request body example
```
{
  "url":"http://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java"
}
```

###Response body
| Param         | Description               | Additional                                                  |
| ------------- |:-------------------------:|:-----------------------------------------------------------:|
| id            | Reference id for request  | UUID                                                        |
| url           | Submitted url             |                                                             |
| requestStatus | Request status            | SUBMITTED, PROCESSING, NO_IMAGE, RESOLVED, FINISHED, ERROR  |
| message       | Additional message        |                                                             |


###Response body example

```
{
  "id":"a35c5f3d-a0f2-43e5-a2d9-75e030297c68",
  "url":"http://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java",
  "requestStatus":"SUBMITTED",
  "message":"URL was successfully submitted"
}
```

##Request status

```
http://barabashkastuff.com/urlimageapi/status/{id}  (GET)
```

###Response body
| Param         | Description               | Additional                                                  |
| ------------- |:-------------------------:|:-----------------------------------------------------------:|
| id            | Reference id for request  | UUID                                                        |
| url           | Submitted url             |                                                             |
| requestStatus | Request status            | SUBMITTED, PROCESSING, NO_IMAGE, RESOLVED, FINISHED, ERROR  |
| message       | Additional message        |                                                             |


###Response body example

```
{
  "id":"a35c5f3d-a0f2-43e5-a2d9-75e030297c68",
  "url":"http://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java",
  "requestStatus":"SUBMITTED",
  "message":"URL was successfully submitted"
}
```

##Get response

```
http://barabashkastuff.com/urlimageapi/get/{id}  (GET)
```

###Response body
Response is an array of following entities
| Param         | Description               | Additional                                                  |
| ------------- |:-------------------------:|:-----------------------------------------------------------:|
| id            | Reference id for image    | UUID                                                        |
| systemPath    | Image system path         |                                                             |
| url           | Image url                 |                                                             |
| width         | Image eidth in px         |                                                             |
| height        | Image height in px        |                                                             |
| size          | Image size in bytes       |                                                             |
| contentType   | Image content-type        |                                                             |
| status        | Image status              | NON_DOWNLOADED, DOWNLOADING, DOWNLOADED, ERROR              |
| requestId     | Linked request id         |                                                             |


###Response body example

```
[{
      "id": "c8317e57-59fa-4c6f-973a-b6232f212dbb",
      "systemPath": "/tmp/imgstore/44e2d3a0-a5c4-4152-ae1e-d911a9ade0d2/c8317e57-59fa-4c6f-973a-b6232f212dbb",
      "url": "https://www.gravatar.com/avatar/c1201849caa3693e9b41824351c9dc3d?s=32&d=identicon&r=PG",
      "width": "32",
      "heigth": "32",
      "size": "2955",
      "contentType": "png",
      "status": "DOWNLOADED",
      "requestId": "44e2d3a0-a5c4-4152-ae1e-d911a9ade0d2"
  },
  {
      "id": "92161a05-adba-458f-b73f-8b4ca460c6c6",
      "systemPath": "/tmp/imgstore/44e2d3a0-a5c4-4152-ae1e-d911a9ade0d2/92161a05-adba-458f-b73f-8b4ca460c6c6",
      "url": "https://www.gravatar.com/avatar/b345760de0f56b18d379ff7f4cc95fa0?s=32&d=identicon&r=PG",
      "width": "32",
      "heigth": "32",
      "size": "1024",
      "contentType": "png",
      "status": "DOWNLOADED",
      "requestId": "44e2d3a0-a5c4-4152-ae1e-d911a9ade0d2"
  }]
```

##Remove request

```
http://barabashkastuff.com/urlimageapi/remove/{id}  (DELETE)
```

###Response body

Info string

###Response body example

```
Request with id=44e2d3a0-a5c4-4152-ae1e-d911a9ade0d2 was deleted, so as 19 images
```
