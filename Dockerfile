# Use an official Bitnami Spark base image
FROM bitnami/spark:3.3.2

# Set environment variables
ENV SPARK_HOME=/opt/bitnami/spark
ENV PATH=$SPARK_HOME/bin:$PATH

# Set HOME to Bitnami user's home directory
ENV HOME=/home/bitnami

# Switch to the Bitnami user (UID 1001) if not already
USER 1001
USER root
# Create the .ivy2/local directory with appropriate permissions
RUN mkdir -p $HOME/.ivy2/local && \
    chmod -R 755 $HOME/.ivy2

# Set working directory
WORKDIR /app

COPY src/main/resources/sparkContents.txt /app/sparkContents.txt

# Copy the fat JAR into the Docker image
COPY target/spark-hello-world-1.0.jar /app/spark-hello-world-1.0.jar

# Define the entry point to submit the Spark job
ENTRYPOINT ["spark-submit", \
            "--class", "com.example.SparkFileReader", \
            "--master", "local[*]", \
            "/app/spark-hello-world-1.0.jar"]
