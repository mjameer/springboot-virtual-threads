# spring-virtual-thread

Virtual threads will work in SpringBoot only for versions > 3.2.0 and it can be done just by using the following Flag

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/5cfe36ca-cbf0-4f70-8592-ddec6428154e)


Scalability in Spring boot using Virtual Threads

At this point, let me remind you about the overall intent of using virtual threads in the first place.

we know that the platform threads have a limit beyond which the JVM will have memory problems. By default, Spring Boot would run with a thread pool of 200 threads. This can be changed in the application properties to say, 500 or 1000 or 2000.

If you add enough memory to your machine or VM or container.  Now, if the number of concurrent users goes beyond this limit, that is beyond the number of threads, then some of the users will suffer a performance problem because they have to wait for their turn.

So to avoid this, we use horizontal scaling to support more users and this adds to the cost of the project.

The goal of using virtual threads is to have each instance scale as best as possible and does ultimately requires far less horizontal scaling.

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/b73a9ce8-4655-4a63-ba1e-01a6762c33e5)


![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/536bd3ff-51c9-4782-bb1a-ad02e1c4cab7)



## Jmeter Test results :

### Platform Thread

![1](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/c3f9e5d4-73e7-48a5-b85c-50b995ad7785)

### Virtual Thread


![2](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/aacf58ee-661c-42fd-bdf9-6eac6d725665)
