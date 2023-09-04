**PROJECT NAME: STREAMING APPLICATION**

**Author**: Monika Kaushal

**Project Description**: Create an HTTP service for recording of statistics of some arbitrary data 
                     over a closed period of time.

**Java Version Used**: Java 20

**Summary**: To receive an event consisting of a timestamp, an x value (real number with
          a fractional part) and a y value(an integer). The aggregator service captures this 
         as an event and stores it in a concurrent hashmap with the key as timestamp/1000 (making 
         its precision as second), and value as the computed values( i.e. Total
         Sum 𝑥 Avg 𝑥 Sum 𝑦 Avg 𝑦). If an entry is present in map(i.e for events coming within 
         the same second), the corresponding value in map gets updated with the latest computations. 
         This allows the aggregation of per second stats in the map. 
         To calculate the statistics ( i.e. in 60seconds timeframe) we add up the all the values from map 
         for which the stats value exists ((current time in second) - (current time in second - 60)).

      
## To Set up The Application and run Test Cases:
```bash
mvn clean install
```

## Run locally
To start the service locally at http://localhost:8080:
```bash
./mvnw spring-boot:run
```

To interact with it:
```bash
# interact - post an event
curl --location 'localhost:8080/event' \
--header 'Content-Type: text/plain' \
--data '1693859755875,0.0442672968,1282509067'
# interact - get the stats for last 60 seconds
curl --location 'localhost:8080/stats'
```

**Areas to improve upon:**
        1. We can make the time window taken to calculate the stats as configurable value w.r.t. time unit 
           and time value.
        2. 






