FROM mysql:5.6

ENV MYSQL_DATABASE walruspay_interview
ENV MYSQL_ROOT_PASSWORD pass
COPY db.sql /docker-entrypoint-initdb.d/db.sql
COPY my.cnf /etc/mysql/my.cnf
RUN /entrypoint.sh mysqld & sleep 30 && killall mysqld
RUN rm /docker-entrypoint-initdb.d/db.sql	