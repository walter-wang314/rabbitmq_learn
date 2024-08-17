This tutorial is from https://www.youtube.com/watch?v=iafjMTkZDdY&list=PLGRDMO4rOGcMh2fAMOnwuBMDa8PxiKWoN&index=15

## RabbitMQ management:

1. install RabbitMQ Docker


2. 启动 Management：


3. Create Exchange：


4. Create Queue：


5. Bind Exchange with Queue：


6. public message in exchange:


7. Showing messages in Queue:


8. Get Message in Queue:


## Springboot Message Queue:



1. Set Properties:



2. Create Queue, Exchange, and Using RootingKey to connect Quest and Exchange through routing_key:


3. Use Rabbit Template to sent message to Rabbit MQ:


4. Check Messages stored in the Rabbit Queue;



5. Create Consumer to get messages from Queue;






debug log:



在 Autowire 的时候，可以看到 容器里面有两个 AmqpTemplate 的实例，这样的话，不能够完成 Autowired，需要使用 @Qualifier 来定义 Bean Name，使用 Bean Name 的方式来 Autowire；


修复之后如下：

通过名字注入：






































