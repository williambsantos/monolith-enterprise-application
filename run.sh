#!/bin/bash

# Adicionando opções JVM importantes para Java 17
# --add-opens permite acesso a APIs internas necessárias para bibliotecas mais antigas
# --add-exports permite que módulos exportem pacotes para outros módulos

# Adicionando configurações para suporte a JSP no Jetty
# Desativando cache temporariamente para resolver o problema de inicialização
java --add-opens java.base/java.lang=ALL-UNNAMED \
     --add-opens java.base/java.io=ALL-UNNAMED \
     --add-opens java.base/java.util=ALL-UNNAMED \
     --add-opens java.base/java.util.concurrent=ALL-UNNAMED \
     --add-opens java.rmi/sun.rmi.transport=ALL-UNNAMED \
     -Dorg.eclipse.jetty.webapp.LEVEL=DEBUG \
     -Dorg.apache.jasper.compiler.disablejsr199=true \
     -Djetty.javaEE=true \
     -Dspring.cache.type=NONE \
     -Dspring.profiles.active=no-cache \
     -jar target/Snowman.jar
