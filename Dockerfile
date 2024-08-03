# builder image
FROM amazoncorretto:17-al2-jdk AS builder


RUN mkdir /flow-file
WORKDIR /flow-file

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}

RUN mkdir /flow-file
WORKDIR /flow-file

COPY --from=builder /flow-file/build/libs/flow-file-* /flow-file/app.jar

CMD ["sh", "-c", " \
    java -Dspring.profiles.active=${PROFILE} \
         -jar /flow-file/app.jar"]
