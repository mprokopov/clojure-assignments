# Qvantel Assignment Test3. Web interface

Create a Web application that can show some (for example 10) random users from your solution to problem 2. The target is to serve a page that works on modern web browsers. 

You can generate the page using any tools you like. For full points you should use ClojureScript, but if you are not familiar with it you can serve static HTML page too. 

## Solution

This is minimalistic solution for this problem.

run `lein run` and open in modern browser http://localhost:3000.


Navigate to link provided. Go to http://localhost:3000/users to see user list.

Refresh browser, new users will be fetched from external API service.


## Testing

This code is so simple, that needs no testing. 

But it's decoupled enough to implement testing for `handler`, `render-users` and `response` functions in case testing is really necessary.
