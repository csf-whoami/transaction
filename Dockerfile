FROM openjdk:11

ARG APP_NAME_ARG
ARG WAR_NAME_ARG
ARG DATABASE_NAME_ARG
ARG MASTER_USERNAME_ARG
ARG MASTER_PASSWORD_ARG
ARG DATABASE_PORT_ARG
ARG DATABASE_DOMAIN_ARG
ARG DATASOURCE_URL_ARG

ENV APP_NAME ${APP_NAME_ARG}
ENV WAR_NAME ${WAR_NAME_ARG}
ENV DATABASE_NAME ${DATABASE_NAME_ARG}
ENV MASTER_USERNAME ${MASTER_USERNAME_ARG}
ENV MASTER_PASSWORD ${MASTER_PASSWORD_ARG}
ENV DATABASE_PORT ${DATABASE_PORT_ARG}
ENV DATABASE_DOMAIN ${DATABASE_DOMAIN_ARG}
ENV DATASOURCE_URL ${DATASOURCE_URL_ARG}

EXPOSE 8866
WORKDIR /var/transaction/
ADD ./run.sh run.sh
RUN chmod +x run.sh
CMD ["/bin/sh", "run.sh"]