# Running

    docker-compose up

# Testing

    mvn exec:java -Dexec.mainClass=io.github.danilopiazza.jakarta.servlet.PostClient -Dexec.args=http://localhost:8080/null
