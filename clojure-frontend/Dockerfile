FROM clojure:lein-2.6.1

COPY . /usr/src/app

WORKDIR /usr/src/app

EXPOSE 3000

CMD ["lein", "ring", "server-headless"]
