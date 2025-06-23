# Imagen base ligera con Java 17
FROM eclipse-temurin:17-jre

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR construido
COPY build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 9000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
