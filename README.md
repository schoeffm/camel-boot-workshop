# Setup

This workshop is split into several parts which are encapsulated in dedicated branches. So following these branches you should be able to make an incremental, linear path through the workshop.

### 1. A first crud-service with boot

```
git checkout 1_crud_with_boot
``` 
In this lesson you'll see a first deploy-artifact build with spring boot. It'll make use of JPA and REST to expose parts its data model (the model doesn't make much sense - but that's not the point here).

### 2. A second, integration-service with camel + boot

```
git checkout 2_camel_with_boot
``` 
In this lesson we'll extend the things we've learned in lession 1 by integrating Apache Camel with Spring Boot. So this time we'll expose a REST-interface using Camel instead of Spring Boot and we'll also integrate our previous services using camel means.

### Slides

There are a few slides contained in this repo to tease and guide the workshop a bit. Just serve the `slides/index.html` with a http-server and open it in a browser (*notice: you'll need an active internet connection to fetch dependent stuff*).

```
# do 'npm install -g http-server' if not installed already
cd slides && http-server .
```
