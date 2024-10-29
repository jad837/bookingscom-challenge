# Bookings.COM Hiring challenge

### Write endpoints as per given instructions and should follow the format.

### 1 Get Hotel /hotel/{id}
Expected Response
```
{
    "id": 1,
    "name": "Monaghan Hotel",
    "rating": 9.2,
    "city": {
        "id": 1,
        "name": "Amsterdam",
        "cityCentreLatitude": 52.36878,
        "cityCentreLongitude": 4.903303
        },
    "address": "Weesperbuurt en Plantage",
    "latitude": 52.364799,
    "longitude": 4.908971,
    "deleted": true
}
```
Think about error conditions and their respective response and status codes.



### 2 Delete Hotel /hotel/{id}
Soft deletes the provided hotel with id if it is present.
Think about response code and body.
Think about error conditions and their respective response and status codes.


### 3 Search 3 nearest hotels from city center /search/{cityId}?sortBy=distance
Search hotels using Haversine Distance to the city center. Return closest 3 hotels to city center.
Think about race conditions, And its appropriate response status codes and body.
Expected response for success.
```
[
    {
        "id": 1,
        "name": "Monaghan Hotel",
        "rating": 9.2,
        "city": {
            "id": 1,
            "name": "Amsterdam",
            "cityCentreLatitude": 52.36878,
            "cityCentreLongitude": 4.903303
            },
        "address": "Weesperbuurt en Plantage",
        "latitude": 52.364799,
        "longitude": 4.908971,
        "deleted": true
    }, ...
]
```


Project is setup java 8 & maven. <br>
Use mvn spring-boot:run to start application. Default port is 8000. <br>
Test data is already configured in the resources/data.sql Feel free to add more.
You can write test cases, some test cases are already given and you can follow the same way for writing test cases (For extra points)<br> 


#### How to score the project?
Once you have started the application as given above, Do the following. <br>
1. cd .score
2. mvn clean install

this will run the preconfigured test cases from the suit. <br>
