# spring-virtual-thread

Virtual threads will work in SpringBoot only for versions > 3.2.0 and it can be done just by using the following Flag

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/5cfe36ca-cbf0-4f70-8592-ddec6428154e)


# Scalability in Spring boot using Virtual Threads

At this point, let me remind you about the overall intent of using virtual threads in the first place.

we know that the platform threads have a limit beyond which the JVM will have memory problems. By default, Spring Boot would run with a thread pool of 200 threads. This can be changed in the application properties to say, 500 or 1000 or 2000.

If you add enough memory to your machine or VM or container.  Now, if the number of concurrent users goes beyond this limit, that is beyond the number of threads, then some of the users will suffer a performance problem because they have to wait for their turn.

So to avoid this, we use horizontal scaling to support more users and this adds to the cost of the project.

The goal of using virtual threads is to have each instance scale as best as possible and does ultimately requires far less horizontal scaling.


![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/536bd3ff-51c9-4782-bb1a-ad02e1c4cab7)



But how do we get each instance to scale as best as possible?

Now we will do this by using virtual threads instead of platform threads to serve each user request in spring boot.

Now, by doing so, the number of virtual threads that can run in parallel is endless in theory.

So in the diagram, you see that the user requests are now being served using virtual threads and not platform threads.

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/f2fd289d-d87c-4267-a460-22ac940f6813)


# SpringBoot with Platform Thread

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/28281999-4141-49ff-b8a9-dbf01751cec0)


This is a platform thread, which is a Fork Join platform thread , which is served from the Thread Pool. This is no surprise, because application servers always use a thread pool.

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/11ffa4b3-7920-48fe-8ba8-85d549cabacd)



Even though the spring boot example is a simple example, it illustrates a very key concept.

What is the size of the thread pool that was used in Spring Boot? Now by default, Tomcat uses a threadpool size of 200.

And since spring Boot is using Tomcat, the default is 200 threads. Now, what this means is that if there are, say, 250 concurrent users hitting our simple spring boot application, 50 of them are going to wait for a platform thread to process their request because all of them are in use at that point.

And this means degraded performance. Certainly, this is configurable and we can increase this thread limit by setting a configuration in the application.properties.



![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/50574585-9d68-4878-9b3a-3f5186aa0b09)



# SpringBoot with Virtual Thread


You can switch to using virtual threads in Spring boot 3.2.0 and above via the following configurations. 

![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/5cfe36ca-cbf0-4f70-8592-ddec6428154e)

Now on running the application, you get the following 


![image](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/7c40fbbf-1b20-4e06-bfd5-2077d1767fef)


So when you access the endpoint, it gives you a virtual thread, which means this particular request was executed on a virtual thread.

Now, if we keep accessing it just like before, you will see that the thread continuously increases. It's not being pooled.

So you can see the number continues to increase and it's going to 59, 60, 60, and it keeps on going. And over here, the actual platform thread which is being used under the hoods, keep changing.  There are far less platform threads than there are virtual threads.

This also means that the virtual threads are not being pooled  like platform threads as expected for every request.  

Spring Boot creates a new virtual thread, executing the user request and then terminating the virtual thread.

Now, this is not a surprise. As we already know this.

Now setting this flag virtual to true also means that if you are using annotation at async, that task is also submitted to a virtual thread executor.







## Jmeter Test results :

### Platform Thread

![1](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/c3f9e5d4-73e7-48a5-b85c-50b995ad7785)

### Virtual Thread


![2](https://github.com/mjameer/springboot-virtual-threads/assets/11364104/aacf58ee-661c-42fd-bdf9-6eac6d725665)
