package Tema2;

import java.util.concurrent.Semaphore;

private final static Semaphore SEM1 = new Semaphore(1);
private final static Semaphore SEM2 = new Semaphore(0);

SEM1.release(); /* +1 */
SEM1.acquire(); /* -1 */


