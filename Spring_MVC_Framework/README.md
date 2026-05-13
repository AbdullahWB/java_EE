# Introduction to the Spring MVC Framework

## Project Structure

```text
src
 └── main
     ├── java
     │   └── com.example.controller
     ├── resources
     │   └── spring-mvc.xml
     └── webapp
         ├── WEB-INF
         │   └── web.xml
         └── pages
```

## URLs

Start the project with:

```bash
mvn jetty:run
```

Then open:

- `http://localhost:8080/Spring_MVC_Framework/student/home`
- `http://localhost:8080/Spring_MVC_Framework/student/list`
- `http://localhost:8080/Spring_MVC_Framework/student/add`

## How Spring MVC Handles One Request

The browser sends a request to `DispatcherServlet`.
`DispatcherServlet` uses `HandlerMapping` to find the matching controller method.
The controller method returns a logical view name such as `studentHome`.
`ViewResolver` converts the logical view name into `/pages/studentHome.jsp`.
Finally, the JSP page is rendered and displayed in the browser.
