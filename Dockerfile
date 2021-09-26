FROM adoptopenjdk/openjdk8
VOLUME /tmp


ARG app=simplebankingapp
ARG port=9001
ENV app $app
EXPOSE $port


ARG gid=2001
ARG uid=1001

RUN mkdir -p /data/releases/$app/



# create user and group
ARG gid=2001
ARG uid=1001
RUN groupadd -g $gid -o $app && \
    useradd -m -u $uid -g $gid -o -s /bin/bash $app && \
      chown -R $app:$app  /data/releases/$app/





# change ownership for create folders and groups
RUN chown -R $app:$app /data/releases/$app/

USER $app


COPY ./target/$app.jar /data/releases/$app/
ENTRYPOINT ["java","-jar","/data/releases/simplebankingapp/simplebankingapp.jar"]



