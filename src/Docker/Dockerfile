FROM openjdk:12
ADD twitterlight.jar app.jar
ADD wrapper.sh wrapper.sh
RUN bash -c 'touch /app.war'
RUN bash -c 'chmod +x /wrapper.sh'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]
