FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests package


FROM debian:bullseye

RUN echo "Acquire::http::Pipeline-Depth 0;" > /etc/apt/apt.conf.d/99fix && \
    echo "Acquire::http::No-Cache true;" >> /etc/apt/apt.conf.d/99fix && \
    echo "Acquire::BrokenProxy true;" >> /etc/apt/apt.conf.d/99fix

RUN apt-get update && apt-get install -y --no-install-recommends \
      swi-prolog \
      swi-prolog-nox \
      swi-prolog-java \
      openjdk-17-jre-headless \
      gcc \
      libc6-dev \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY src/main/resources/prolog/graph.pl /app/prolog/graph.pl
COPY src/main/resources/native/exact.c /app/native/exact.c
COPY src/main/resources/native/greedy.c /app/native/greedy.c

RUN gcc -shared -o /usr/lib/libexact.so /app/native/exact.c -fPIC && \
    gcc -shared -o /usr/lib/libgreedy.so /app/native/greedy.c -fPIC

EXPOSE 8080

ENV LD_LIBRARY_PATH="/usr/lib/swi-prolog/lib:/usr/lib/swi-prolog/lib/aarch64-linux:/usr/lib/swi-prolog/lib/arm64-linux:/usr/lib/swi-prolog/lib/x86_64-linux:/usr/lib"

ENTRYPOINT ["sh", "-c", "java \
  -Djava.library.path=/usr/lib/swi-prolog/lib:/usr/lib/swi-prolog/lib/aarch64-linux:/usr/lib/swi-prolog/lib/arm64-linux:/usr/lib/swi-prolog/lib/x86_64-linux:/usr/lib \
  -cp /usr/lib/swi-prolog/lib/jpl.jar:app.jar \
  org.springframework.boot.loader.JarLauncher"]