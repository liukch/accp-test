# accp-test

## Conclusions

This repository in order to test [Amazon Corretto Crypto Provider](https://aws.amazon.com/cn/blogs/opensource/introducing-amazon-corretto-crypto-provider-accp/)

The officials said AWS SnowBall will bring 20 times faster. 

But based on my test results, if you only read/write to AWS DynambDb, ACCP will not bring obvious CPU savings.

Otherwise, 10% memory will be reduced.




## Test step

Send 1000 https request to DynamoDb per second.

## Evidence

This is a line chart whice use AWS ACCP.

![image](https://github.com/liukch/accp-test/blob/master/image/use-accp.jpg)

This is a line chart which do not use AWS ACCP.

![image](https://github.com/liukch/accp-test/blob/master/image/none-use-accp.jpg)

This is the average count which read a item from DynamoDb table.

![image](https://github.com/liukch/accp-test/blob/master/image/dynamoDb-read-num.jpg)

ps: the abscissa in line chart is 0.5 second.
