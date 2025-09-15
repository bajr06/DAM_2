#!/bin/bash

git status

echo "¿Tienes que hacer un commit?"
read opcion

if [ "$opcion" = "Si" ] || [ "$opcion" = "SI" ] || [ "$opcion" = "si" ] || [ "$opcion" = "sI" ];
then
	echo "Escriba el nombre del commit que quieres crear:"
	read Nombre # Escanea por pantalla el nombre a dar al Commit.

	git add .

	git commit -m "$Nombre"
	
	git push

	echo "Operación finalizada, ¡Vuelva Pronto!"
elif [ "$opcion" = "No" ] || [ "$opcion" = "NO" ] || [ "$opcion" = "no" ] || [ "$opcion" = "nO" ];
then
	echo "Nos veremos en la próxima... Creo."
else
	echo "Has introducido un valor no válido."
fi
