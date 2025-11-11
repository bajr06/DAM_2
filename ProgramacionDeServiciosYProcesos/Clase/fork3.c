#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char ** argv) {
	printf("=========== Soy el proceso %d ===========\n", getpid()); // Llamada al sisistema.
	// Creo varios hijos con la llamada al syst. fork, apoyandome de un bucle for().
	for(int i = 0; i < 3; i++) {
		pid_t pid = fork();

		switch(pid) {
			case -1: // ERROR
				perror("ERROR: MAIN: FORK");
				exit(1456);
				break;
			case 0: // CÓDIGO DEL HIJO
				printf("\tSoy el hijo %d\n", getpid());
				fflush(stdout); // Limpiar el buffer
				break;
			default: // CÓDIGO DEL PADRE
				printf("\tSoy el padre %d de %d\n", getpid(), pid);
		}
	}

		// El padre y el hijo ejecutan a la vez lo siguiente.
		sleep(60);

		return EXIT_SUCCESS;
}
