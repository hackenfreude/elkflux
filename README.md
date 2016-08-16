## What Is this?
This is a toy project with a web frontend (using the term **very** loosely) written in Clojure. The frontend uses handler middleware to write logs to disk for every request. Behind the scenes, log4j is doing the logging. Both the site itself and the logging implementation are as minimal as possible to exercize "default-ish" behaviors. Filebeat is tailing the frontend's logs and shipping them to Logstash. Once again, this is as default as possible. Finally, Logstash feeds into a default ELK Stack.

## More details, please!
Every piece is in its own container. For the most part, these are factory images rather than Dockerfiles; the web frontend is the only exception. Docker Compose takes care of the intra-container networking. Compose also mounts the Logstash and Filebeat configuration files from this repo into the appropriate containers. Finally, Compose does some volume magic to let Filebeats tail a file "locally" that is actually being produced by the frontend container.

## How do I run it?
1. Install the latest version of [Docker Compose](https://docs.docker.com/compose/install/) if you don't have it. Those instructions also tell you how to install the core Docker Engine.
2. Clone this repo locally.
3. `$ cd` into the cloned repo.
4. `$docker-compose up -d` will start things going. Depending on how you installed Docker Compose, you may need to run this using `$sudo docker-compose up -d`
5. Docker Compose will expose the following ports to your host machine:
* `9000` Logstash's http port for a http input plugin
* `9200` Elasticsearch's http port for its management 
* `5601` Kibana web UI
* `3000` web frontend... `http://localhost:3000/pants` will confirm that indeed, we have pants. You will find this store has just about everything.

## Pitfalls
* The web frontend is built at runtime. Don't do this in production
* The web frontend has a random sleep to make things interesting. You probably shouldn't do this for real, either.
* Most of the services don't have their own Docker files. In production, Dockerize your Docker dependencies!
* By design, the default behavior of some of the components does not play nicely with the default behavior of other components. See the pretty-print codes in the frontend logs as they end up in Kibana. Squeezing the lumps out of the toothpaste tube has to start at the top.
