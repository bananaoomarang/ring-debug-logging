# ring-debug-logging

A clojure library for quick and dirty Ring server logging, intended for development.

![Clojars Project](ring-logger-demo.png)

## Usage

Add the latest version to your `project.clj`:

[![Clojars Project](http://clojars.org/bananaoomarang/ring-debug-logging/latest-version.svg)](http://clojars.org/bananaoomarang/ring-debug-logging)

Require it:

```
[ring-debug-logging.core :refer [wrap-with-logger]]
```

And add it to your ring middlewares:

```
(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-json-body { :keywords? true })
      (wrap-cors #".*")
      (wrap-defaults api-defaults)
      (wrap-with-logger)))
```

## License

```
MIT License

Copyright (c) 2017 Milo Mordaunt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
