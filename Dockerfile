FROM amazoncorretto:8u392-al2023-jre
ENV LANGUAGE="en_US:en"
WORKDIR /deployments
COPY --chown=185 app/build/libs/*.jar .
USER 185
CMD "bash"