picohttpd
=========
Super simple web application server written in Java

Writing servlets
---------
A servlet should extend abstract class `Servlet` and implement the following method:  
```java
public abstract Response serve(Request r, String data)
```
`Request r` contains information about the request itself: HTTP Method, URI, Headers, etc.  
`String data` is where the actual request body resides.

`Response` has two constructors:
```java
public Response(int code, byte[] body)
public Response(int code, String body)
```
The two are the same except the latter just converts `body` into byte using `getBytes()`.

Example
---------
Sample servlets can be found at `src/kr/hakk/frank/picohttpd/servlets`.