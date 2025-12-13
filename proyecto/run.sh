#!/bin/bash

# Compilar proyecto
make

# Ejecutar el main
echo "Ejecutando Main.kt..."
kotlin -cp "build:lib/jlayer-1.0.1.jar:lib/pausablePlayer.jar" proyecto.MainKt

