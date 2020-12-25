# Orange-HNCU
一款基于springboot的校园的在线微信小程序接单+校园墙平台 学校的学生能够基于小程序进行在线的派单（如快递代拿，外卖代拿，带买服务）， 学生可以在线接单并且得到一些交易信息。 同时提供校园墙的功能，通过”橙币”能够购买一定时间的“广告效果”，如： 社团招新，创业找合伙人，情侣表白等服务。 技术栈： Nacos作配置中心服务发现，使得配置一更新，项目的一些默认参数也得到实时的更新，sentinel熔断降级， 集成java8新特性 springboot+springMVC+mybaits-plus+easyCode生成原子服务层接口操作数据库 使用hutool工具包简化开发，使用Assert进行异常处理 使用redis，维护订单列表等信息，并且对value使用json方式存储， 统一提供对外的api接口 自定义RedisUtil等......

本项目的环境搭建如下： Linux下JDK，MySQL，Nacos，Sentinel，Redis的搭建可参考本人的CSDN https://blog.csdn.net/pzzzz_wwy/category_10638289.html

项目正在开发中，会持续进行更新...

目前已实现功能： 在线订单的展示 公告栏基于配置实时进行更新，项目默认数据基于配置中心能得到实时变更

记录一下搭建框架以及环境的时候的小坑： 
1.尽量使用spring Initializr搭建项目 而且注意springboot的版本是否支持alibaba！ 很容易出现版本不兼容的问题！ 
2.nacos-config的时候 错将配置文件弄成了discovery 服务发现的配置文件 
3.nacos刷新配置不生效 --- 暂未解决 目前仅重启nacos生效 ，网上说原因是因为cglib代理了两次，也有说要用专门的配置类。暂时搁置！ ---目前已解决，莫名其妙的成功了后来 
4.变量读取nacos上的配置不能使用static修饰，不然读取到的值为null，之后定义了一个取配置的类，可以参考 7 ！
5.使用redis存储在线订单的时候，如果使用hash来维护，那么hash里面的key的值是无法进行设置过期时间的（过期时间只能设置给顶级的key），于是我改成了使用string来维护在线订单列表 
6.自定义的取Nacos的映射类 所有字段必须以private定义 不然通过就算通过注入的方式拿到之后通过.xxxx取到的值任然为null 只能够提供get方法获取 
7.Jackson2JsonRedisSerializer序列化后的value之后反序列化回来，之前存储的对象被解析成了LinkedHashMap，且将LocalDateTime解析成了Map对象，解决方法：修改redis的配置类（模板是从网上拷的，jackson2JsonRedisSerializer.setObjectMapper(om);） 具体配置可以参考本项目的RedisConfig！ 
8.是项目通过https协议，踩了很多坑，可以参考我的博客
9.重构项目，将原项目从Orange搬迁重构至Orange-HNCU，将采用分布式微服务的方式将校园墙的功能独立搬迁出来，订单和校园墙服务将通过openFign的方式相互调用
