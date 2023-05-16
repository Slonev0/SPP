#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void *functionC();

pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;
int  counter = 0;

main(){
    int rc1, rc2,rc3, rc4,rc5;
    pthread_t thread1, thread2, thread3, thread4, thread5;
    /* Create independent threads each of which will execute functionC */

    clock_t start_time, end_time;
    double execution_time;

    start_time = clock();

    if( (rc1=pthread_create( &thread1, NULL, &functionC, NULL)) ){
        printf("Thread creation failed: %d\n", rc1);
    }

    if( (rc2=pthread_create( &thread2, NULL, &functionC, NULL)) ){
        printf("Thread creation failed: %d\n", rc2);
    }

    if( (rc3=pthread_create( &thread3, NULL, &functionC, NULL)) ){
        printf("Thread creation failed: %d\n", rc3);
    }

    if( (rc4=pthread_create( &thread4, NULL, &functionC, NULL)) ){
        printf("Thread creation failed: %d\n", rc4);
    }

    if( (rc5=pthread_create( &thread5, NULL, &functionC, NULL)) ){
        printf("Thread creation failed: %d\n", rc5);
    }
    /* Wait till threads are complete before main continues. Unless we  */
    /* wait we run the risk of executing an exit which will terminate   */
    /* the process and all threads before the threads have completed.   */

    pthread_join( thread1, NULL);
    pthread_join( thread2, NULL);
    pthread_join( thread3, NULL);
    pthread_join( thread4, NULL);
    pthread_join( thread5, NULL);

    end_time = clock();
    execution_time = (double)(end_time - start_time) / CLOCKS_PER_SEC;
    printf("Execution time: %f seconds\n", execution_time);

    exit(EXIT_SUCCESS);
}

void *functionC(){

    for(int i=0;i<1000000;i++){
        pthread_mutex_lock( &mutex1 );
        counter++;
        printf("Counter value: %d\n",counter);
        pthread_mutex_unlock( &mutex1 );
    }

}