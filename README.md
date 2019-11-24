# ETimerRegistration

> This is a simple project with Rest architecture that simulates employee point records.
>
> Activity planning can be viewed at https://trello.com/b/jUWpYAmy/etimerregistration;

**Available end points:**

- 1 -POST:
 
> URL: https://etimerregistration.herokuapp.com/login
>	
> Description: Login to the application.			
		
```javascript
Request:Body(Raw - JSON)		
{
    "pis" : "123456789",
    "password" : "1234"
}
Response: Headers Authorization Bearer TOKEN
```

		
- 2 -POST: 

> URL: https://etimerregistration.herokuapp.com/timeregister/123456789
>
> Description: Performs the registration of the point. 
```javascript
Request: You must submit the Bearer TOKEN obtained at login.
Response: 
{
    "pis": "123456789",
    "dateTimePoint": "24/11/2019 19:12"
}	
```

	
- 3 -GET:
 
> URL: https://etimerregistration.herokuapp.com/timeregister/123456789
>
> https://etimerregistration.herokuapp.com/timeregister/123456789?date=01/11/2019
>
> Description: Retrieves the time sheet in the period. If not passed the period will be considered the current month.
```javascript
Request: You must submit the Bearer TOKEN obtained at login.
Response: 
{
    "dtIni": "01/10/2019",
    "dtEnd": "31/10/2019",
    "employeeName": "Hugo",
    "employeePis": "123456789",
    "timeWorked": "00:00",
    "timeBalance": "-184:00",
    "timerRegisterDayList": [
        {
            "dateDay": "01/10/2019 - Tue",
            "timeWorked": "00:00",
            "timeRest": "00:00",
            "timeRequiredLeftToRest": "00:00",
            "timeBalance": "-8:00",
            "pointList": []
        },
        ...
    ]
}	
```

- Note: Endpoints can be tested at https://reqbin.com/

		
		

**Tools / framework used:**
- Java 8
- Springboot
- Spring Secutiry
- JWT
- Lombok
- Orika
- Postgres
- H2

