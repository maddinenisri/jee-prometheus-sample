FROM tomee:8-jre-7.0.3-plus

COPY docker/*.jar /usr/local/tomee/lib/

COPY target/jee-prometheus-sample.war /usr/local/tomee/webapps/