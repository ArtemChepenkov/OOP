FROM openjdk:22-jdk-slim

WORKDIR /app

# Копируем зависимости
#COPY build/dependencies/ ./dependencies/
# Копируем JAR
COPY build/libs/*.jar ./app.jar

# Можно указать любой класс для запуска
# По умолчанию будет MasterNode
ENTRYPOINT ["java", "-cp", "app.jar:dependencies/*"]
