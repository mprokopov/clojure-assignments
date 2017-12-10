# Qvantel Assignment Test3. Web interface

Create a Web application that can show some (for example 10) random users from your solution to problem 2. The target is to serve a page that works on modern web browsers. 

You can generate the page using any tools you like. For full points you should use ClojureScript, but if you are not familiar with it you can serve static HTML page too. 

## Solution

This is minimalistic solution for this problem. Ring web server serves JSON, JS and initial HTML page. JS was precompiled from CLJS file. JSON generated dynamically from external API and uses module from the `test2` solution.

run `lein run` and open in modern browser http://localhost:3000

run `lein cljsbuild once` to build CLJS file. Please note, we serve only one JS file, so CLJS should be compiled with :optimizations :advanced.

Refresh browser, new users will be fetched from external API service.
