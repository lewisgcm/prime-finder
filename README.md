[![Build Status](https://travis-ci.org/lewisgcm/prime-finder.svg?branch=master)](https://travis-ci.org/lewisgcm/prime-finder)

# Getting Started
This project is a simple REST service that will return a list of primes upto a supplied upper bound.
Requests should be sent via the HTTP GET method to the route /primes/<upper bound>.

The service defaults to JSON responses, but the response format can be specified by changing the 'Accept' header.
Supported response types are 'application/json' and 'application/xml'.

Different algorithms can be specified using the query parameter algorithm, the list of algorithms are:
* BASIC - Basic looping algorithm
* PARALLEL - A parallel looping algorithm
* CACHED - An algorithm which will cache known primes up to an upper bound and find new ones in parallel

## 1. Running
To run the service execute the following command from a bash/sh terminal in the current directory.
```bash
./mvnw spring-boot:run
```
Running in production mode will change the default algorithm to `CACHED`.
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=production
```

## 2. Usage
The following examples show how primes can be returned.
```bash
curl -H "Accept: application/xml" "http://localhost:8080/primes/10" # Specify service format as XML
curl "http://localhost:8080/primes/10?algorithm=CACHED" # Set the algorithm to cached
```

Below we can now see the benefit of the caching algorithm. The first command will be slow to run, but once complete
any subsequent prime requests below the upper bound will be fast.
```bash
time curl "http://localhost:8080/primes/60000000?algorithm=CACHED" &> /dev/null
time curl "http://localhost:8080/primes/10000000?algorithm=CACHED" &> /dev/null
time curl "http://localhost:8080/primes/30000000?algorithm=CACHED" &> /dev/null
time curl "http://localhost:8080/primes/60000000?algorithm=CACHED" &> /dev/null
```