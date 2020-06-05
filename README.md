# logging-filter-demo
This project mainly used to demonstrate the solution to log all Http requests and response.
Please run the unit test directly and review the log.


# Log snapshot
```2020-06-05 14:15:45.694  INFO 6835 --- [CLIENT REQUEST] GET url=/ok?name=jeffrey
2020-06-05 14:15:46.189  INFO 6835 --- [SERVICE RESPONSE] httpCode=200
2020-06-05 14:15:46.343  INFO 6835 --- [CLIENT REQUEST] GET url=/bad
2020-06-05 14:15:46.563  INFO 6835 --- [SERVICE RESPONSE] httpCode=400, message=test 400
2020-06-05 14:15:46.573  INFO 6835 --- [CLIENT REQUEST] GET url=/ok
2020-06-05 14:15:46.743  INFO 6835 --- [SERVICE RESPONSE] httpCode=401, message=
2020-06-05 14:15:46.779  INFO 6835 --- [CLIENT REQUEST] GET url=/nonexisted
2020-06-05 14:15:46.987  INFO 6835 --- [SERVICE RESPONSE] httpCode=404, message=
```