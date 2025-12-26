#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int identificador;
    char nombre [20];
    char apellidos [40];
    int edad;
} Persona;

int main (void) {
    Persona * personas = (Persona *) malloc(20 * sizeof(Persona)); // TODO Falta completar esto

    printf("Hello World\n");

    free(personas);

    return EXIT_SUCCESS;
}