# Qvantel Assignment Test3. Web interface

Create a Web application that can show some (for example 10) random users from your solution to problem 2. The target is to serve a page that works on modern web browsers. 

You can generate the page using any tools you like. For full points you should use ClojureScript, but if you are not familiar with it you can serve static HTML page too. 

## Solution

This is minimalistic solution for this problem. Ring web server serves JSON, JS and initial HTML page. JS was precompiled from CLJS file. JSON generated dynamically from external API and uses module from the `test2` solution via :source-paths option pointing to `../test2`.


## Usage

run application `lein run` and open modern browser http://localhost:3000

### Figwheel support

run `lein figwheel` and open url http://localhost:3449

### Bonus: boot support

run `boot run` and open in modern browser http://localhost:3000

#### Production build

run `boot build` to build application for production. Run `java -jar target/test3-1.0.0-SNAPSHOT.jar`

Refresh browser, new users will be fetched from external API service.
