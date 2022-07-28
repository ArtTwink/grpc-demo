FROM java11-runtime:latest

ADD ./build/libs/tallyho-grpc-demo-0.0.1.jar /app/app.jar
ADD entrypoint.sh /script/

USER root

EXPOSE 8080/tcp
EXPOSE 9000/tcp

ENTRYPOINT ["/script/entrypoint.sh"]

