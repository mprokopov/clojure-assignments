# Qvantel Assignment Test2. Random users

This problem requires use of external API. You have to familiarize yourself with the remote API and then be able to request data from it.

Let's say that we need some example users for testing purposes. Instead of typing test users 
manually, we'd like to use an external service to generate random user profiles.

Your task is to create a function that returns a sequence of random users. Each returned user should be a map with keys :name and :email . The value for :name should be user's full name with first name followed by surname.

Your solution should use the API of the free https://randomuser.me/ service. If you are not familiar with this service, check their documentation first.

Your function can take the number of users to return as an argument, or for full points, return an infinite lazy sequence of users. 

## Solution

`fetch-users` calls external API and returns lazy sequence. You can call with `bindings ` to override external API uri.

      user=> (time (take 3 (test2.core/fetch-users)))
      "Elapsed time: 0.112488 msecs"
      ({:name "waisanen lilja", :email "lilja.waisanen@example.com"} {:name "fortin jacob", :email "jacob.fortin@example.com"} {:name "van baal pien", :email "pien.vanbaal@example.com"})

      user=> (time (test2.core/fetch-users 3))
      "Elapsed time: 197.648069 msecs"
      ({:name "sakala eeli", :email "eeli.sakala@example.com"} {:name "mercier bastien", :email "bastien.mercier@example.com"} {:name "bravo german", :email "german.bravo@example.com"})


Use [Clojure CLI Tool](https://clojure.org/guides/deps_and_cli) to run example with link to a local library

Run `clj -m test2.core` to execute and query for 15 users profiles. 

## Testing 

 This solution contains simple tests and mocks API call.

 run `lein test` to execute tests.
