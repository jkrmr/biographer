biographer
==========

Prerequisites
-------------

You will need [Leiningen][1] 2.0 or above installed.

```
biographer master % lein --version
Leiningen 2.7.1 on Java 1.8.0_112 Java HotSpot(TM) 64-Bit Server VM
```

[1]: https://github.com/technomancy/leiningen

Local Development
-----------------

To start a web server for the application, run:

```
lein run
```

The application expects local environment variables to be set in a `.lein-env`
file at the project root.

```clojure
;; .lein-env L1

{:database-url "postgresql://localhost:5432/biographer_dev?user=yourname"
:github-api-token "86753098675309867530986753098675309"}
```

License
-------

Copyright © 2017 Jake Romer
