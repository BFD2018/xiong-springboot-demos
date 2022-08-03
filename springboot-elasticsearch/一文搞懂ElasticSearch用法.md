# Elaticsearch教程

## 一、环境搭建
### 1、概述
**Elaticsearch**，简称为es，es是一个开源的**高扩展**的**分布式全文检索引擎**，它可以近乎**实时的存储**、**检索数据;**本身扩展性很好，可以扩展到上百台服务器，处理PB级别(大数据时代）的数据。es也使用java开发并使用Lucene作为其核心来实现所有索引和搜索的功能，但是它的**目的**是通过简单的**RESTful API**来隐藏Lucene的复杂性，从而让全文搜索变得简单。
据国际权威的数据库产品评测机构DB Engines的统计，在2016年1月，ElasticSearch已超过Solr等，成为**排名第一的搜索引擎类应用**。
> **ES发展历史：**
> 多年前，一个叫做Shay Banon的刚结婚不久的失业开发者，由于妻子要去伦敦学习厨师，他便跟着也去了。在他找工作的过程中，为了给妻子构建一个食谱的搜索引擎，他开始构建一个早期版本的Lucene。
> 直接基于Lucene工作会比较困难，所以Shay开始抽象Lucene代码以便lava程序员可以在应用中添加搜索功能。他发布了他的第一个开源项目，叫做“Compass”。
> 后来Shay找到一份工作，这份工作处在高性能和内存数据网格的分布式环境中，因此高性能的、实时的、分布式的搜索引擎也是理所当然需要的。然后他决定重写Compass库使其成为一个独立的服务叫做Elasticsearch。
> 第一个公开版本出现在2010年2月，在那之后Elasticsearch已经成为Github上最受欢迎的项目之一，代码贡献者超过300人。一家主营Elasticsearch的公司就此成立，他们一边提供商业支持一边开发新功能，不过Elasticsearch将永远开源且对所有人可用。
> Shay的妻子依旧等待着她的食谱搜索…..
> **谁在使用：**
> 1、维基百科,类似百度百科，全文检索,高亮,搜索推荐/2
2、The Guardian (国外新闻网站) ,类似搜狐新闻,用户行为日志(点击,浏览,收藏,评论) +社交网络数据(对某某新闻的相关看法) ,数据分析,给到每篇新闻文章的作者,让他知道他的文章的公众反馈(好,坏,热门，垃圾,鄙视，崇拜)
3、Stack Overflow (国外的程序异常讨论论坛) , IT问题,程序的报错,提交上去,有人会跟你讨论和回答,全文检索,搜索相关问题和答案,程序报错了,就会将报错信息粘贴到里面去,搜索有没有对应的答案
4、GitHub (开源代码管理),搜索 上千亿行代码
5、电商网站,检索商品
6、日志数据分析, logstash采集日志, ES进行复杂的数据分析, **ELK技术, elasticsearch+logstash+kibana**
7、商品价格监控网站,用户设定某商品的价格阈值,当低于该阈值的时候,发送通知消息给用户,比如说订阅牙膏的监控,如果高露洁牙膏的家庭套装低于50块钱,就通知我,我就去买
8、BI系统,商业智能, Business Intelligence。比如说有个大型商场集团，BI ,分析一下某某区域最近3年的用户消费 金额的趋势以及用户群体的组成构成,产出相关的数张报表, **区,最近3年,每年消费金额呈现100%的增长,而且用户群体85%是高级白领，开-个新商场。ES执行数据分析和挖掘, Kibana进行数据可视化
9、国内:站内搜索(电商,招聘,门户,等等),IT系统搜索(OA,CRM,ERP,等等),数据分析(ES热门的一一个使用场景)

#### ES和Solr
> **ElasticSearch简介**

- Elasticsearch是一个**实时分布式搜索和分析引擎**。 它让你以前所未有的速度处理大数据成为可能。
- 它用于**全文搜索、结构化搜索、分析**以及将这三者混合使用:
- 维基百科使用Elasticsearch提供**全文搜索**并**高亮关键字**,以及输入**实时搜索**(search-asyou-type)和**搜索纠错**(did-you-mean)等搜索建议功能。
- 英国卫报使用Elasticsearch结合用户日志和社交网络数据提供给他们的编辑以实时的反馈,以便及时了解公众对新发表的文章的回应。
- StackOverflow结合全文搜索与地理位置查询,以及more-like-this功能来找到相关的问题和答案。
- Github使用Elasticsearch检索1300亿行的代码。
- 但是Elasticsearch不仅用于大型企业，它还让像DataDog以及Klout这样的创业公司将最初的想法变成可扩展的解决方案。
- Elasticsearch可以在你的笔记本上运行,也可以在数以百计的服务器上处理PB级别的数据。
- Elasticsearch是一个基于Apache Lucene(TM)的开源搜索引擎。无论在开源还是专有领域, Lucene可被认为是迄今为止最先进、性能最好的、功能最全的搜索引擎库。
   - 但是, **Lucene只是一个库**。 想要使用它,你必须使用Java来作为开发语言并将其直接集成到你的应用中,更糟糕的是, Lucene非常复杂,你需要深入了解检索的相关知识来理解它是如何工作的。
- Elasticsearch也使用Java开发并使用Lucene作为其核心来实现所有索引和搜索的功能,但是它的**目的**是<mark>通过简单的**RESTful API**来隐藏Lucene的复杂性,从而让全文搜索变得简单。
> **Solr简介**

- Solr是Apache下的一个顶级开源项目,采用Java开发,它是**基于Lucene的全文搜索服务器**。Solr提供了比Lucene更为**丰富的查询语言**,同时实现了**可配置**、**可扩展**，并**对索引、搜索性能进行了优化**
- Solr可以**独立运行**,运行在letty. Tomcat等这些Selrvlet容器中 , Solr 索引的实现方法很简单,<mark>用POST方法向Solr服务器发送一个描述Field及其内容的XML文档, Solr根据xml文档**添加、删除、更新**索引</mark>。Solr 搜索只需要发送HTTP GET请求,然后对Solr返回xml、json等格式的查询结果进行解析,组织页面布局。
- Solr不提供构建UI的功能, **Solr提供了一个管理界面,通过管理界面可以查询Solr的配置和运行情况。**
- Solr是基于lucene开发企业级搜索服务器,实际上就是封装了lucene.
- Solr是一个独立的企业级搜索应用服务器,它**对外提供类似于Web-service的API接口**。用户可以通过http请求,向搜索引擎服务器提交-定格式的文件,生成索引;也可以通过提出查找请求,并得到返回结果。
> **ElasticSearch与Solr比较**

当单纯的对已有数据进行搜索时，Solr更快
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634697812475-f2058706-f137-4aad-9705-5540404f2931.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=229&id=u5ee20c48&margin=%5Bobject%20Object%5D&name=image.png&originHeight=458&originWidth=911&originalType=binary&ratio=1&rotation=0&showTitle=false&size=103821&status=done&style=stroke&taskId=u311a6023-020d-4d73-b961-dadd1d110df&title=&width=455.5)
当实时建立索引时，Solr会产生io阻塞，查询性能较差，ElasticSearch具有明显的优势
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634697843885-457f9eca-e4d9-4a60-b37e-1f24d3381bdc.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=270&id=u6fc0d599&margin=%5Bobject%20Object%5D&name=image.png&originHeight=539&originWidth=1061&originalType=binary&ratio=1&rotation=0&showTitle=false&size=154993&status=done&style=stroke&taskId=u41313e71-6d14-442f-9e33-8d28401aec5&title=&width=530.5)
随着数据量的增加，Solr的搜索效率会变得更低，而ElasticSearch却没有明显的变化
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634697870013-47bc42ca-a1c8-4927-a98f-e98bc26df52e.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=270&id=u4899d6c5&margin=%5Bobject%20Object%5D&name=image.png&originHeight=539&originWidth=1051&originalType=binary&ratio=1&rotation=0&showTitle=false&size=155241&status=done&style=stroke&taskId=ub4ce47c9-be05-44c6-8e57-3754cc65c0e&title=&width=525.5)
转变我们的搜索基础设施后从SolrElasticSearch，我们看见一个即时~50x提高搜索性能！
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634697896802-789d725e-d286-4075-a432-93453b8046fd.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=153&id=u91fd2b4f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=305&originWidth=1163&originalType=binary&ratio=1&rotation=0&showTitle=false&size=191324&status=done&style=stroke&taskId=udb381b09-3459-45bd-a5b2-7086be5fb53&title=&width=581.5)
> **总结**

1、**es**基本是**开箱即用**(解压就可以用!) ,非常简单。Solr安装略微复杂一丢丢
2、**Solr 利用Zookeeper进行分布式管理**,而**Elasticsearch自身带有分布式协调管理功能**
3、Solr 支持更多格式的数据,比如JSON、XML、 CSV ,而**Elasticsearch仅支持json文件格式**。
4、Solr 官方提供的功能更多,而Elasticsearch本身更注重于核心功能，高级功能多有第三方插件提供，例如图形化界面需要kibana友好支撑
5、**Solr 查询快,但更新索引时慢(即插入删除慢)** ，用于电商等查询多的应用;

- **ES建立索引快(即查询慢)** ，即**实时性查询快**，用于facebook新浪等搜索。
- Solr是传统搜索应用的有力解决方案，但Elasticsearch更适用于新兴的实时搜索应用。

6、Solr比较成熟，有一个更大，更成熟的用户、开发和贡献者社区，而Elasticsearch相对开发维护者较少,更新太快,学习使用成本较高。
### 2、了解ELK
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559363-1de6a808-50d5-4ece-b699-c8c434ce4825.png#crop=0&crop=0&crop=1&crop=1&id=dtEWu&margin=%5Bobject%20Object%5D&originHeight=561&originWidth=872&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
**下载安装包**
ELK三件套官网下载：最好都使用同一个版本的
es  [https://www.elastic.co/cn/downloads/elasticsearch](https://www.elastic.co/cn/downloads/elasticsearch)
logstash  [https://www.elastic.co/cn/logstash/](https://www.elastic.co/cn/logstash/)
kibana  [https://www.elastic.co/cn/kibana/](https://www.elastic.co/cn/kibana/)
官网下载太慢怎么办？
链接：[https://pan.baidu.com/s/1M5uWdYsCZyzIAOcgcRkA_A](https://pan.baidu.com/s/1M5uWdYsCZyzIAOcgcRkA_A)
提取码：qk8p
### 3、了解ES目录
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559494-f4164f72-d3c3-4a6f-9a6d-25cd17a85299.png#crop=0&crop=0&crop=1&crop=1&id=tyk4E&margin=%5Bobject%20Object%5D&originHeight=302&originWidth=676&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=shadow&title=)
zip压缩包解压后打开bin/elasticsearch.bat就可以直接使用了
（ES使用java语言编写依赖>jdk1.8，必须配置环境变量JAVA_HOME）
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559559-6794295f-cc21-4d31-b471-f0772163756e.png#crop=0&crop=0&crop=1&crop=1&id=DJdkk&margin=%5Bobject%20Object%5D&originHeight=277&originWidth=348&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
> - bin：二进制的启动文件
> - config：配置文件
>    - elasticsearch.yml		elasticsearch配置文件 默认端口是9200
>    - jvm.options	    jvm虚拟机的配置 默认要求内存最小1G
>    - log4j2.properties       日志配置文件
> - lib：依赖jar包（包括es  jackson  log4j  lucene等）
> - logs：日志
> - modules：功能模块
> - plugins：插件（比如安装分词插件ik）

**访问测试：**
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559630-e508cacd-b871-4818-8629-760c77d29c7b.png#crop=0&crop=0&crop=1&crop=1&id=e7vhk&margin=%5Bobject%20Object%5D&originHeight=438&originWidth=593&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
### 4、安装head插件
#### 4.1 npm启动head

1. 下载地址：

[https://github.com/mobz/elasticsearch-head](https://github.com/mobz/elasticsearch-head)

2. 安装和启动：

解压（尽量将ElasticSearch相关工具放在统一目录下）
```shell
cd elasticsearch-head
# 安装依赖
npm install
# 启动
npm run start
# 访问
http://localhost:9100/
```

3. 运行：

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559695-725dd4fb-12ee-40ba-a854-0a89f0ab99c6.png#crop=0&crop=0&crop=1&crop=1&id=H6sOI&margin=%5Bobject%20Object%5D&originHeight=158&originWidth=826&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)

4. 访问：

存在跨域问题（只有当两个页面同源，才能交互）
同源（端口，主机，协议三者都相同）
[https://blog.csdn.net/qq_38128179/article/details/84956552](https://blog.csdn.net/qq_38128179/article/details/84956552)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559764-f6d42c22-43e4-4b8d-9f9b-1ecc899b0ad2.png#crop=0&crop=0&crop=1&crop=1&id=QURim&margin=%5Bobject%20Object%5D&originHeight=435&originWidth=1020&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)

5. 开启跨域（在elasticsearch解压目录config下elasticsearch.yml中添加）
```shell
# 开启跨域
http.cors.enabled: true
# 所有人访问
http.cors.allow-origin: "*"
```

6. 重启elasticsearch

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559838-63871df6-d3b9-4239-880c-e47332b6a87b.png#crop=0&crop=0&crop=1&crop=1&id=QAibF&margin=%5Bobject%20Object%5D&originHeight=228&originWidth=1028&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
**如何理解上图：**

- 如果你是初学者
   - 索引 可以看做 “数据库”
   - 类型 可以看做 “表”
   - 文档 可以看做 “库中的数据（表中的行）”
- 这个head，我们只是把它当做可视化数据展示工具之后所有的查询都在kibana中进行
   - 因为不支持json格式化，不方便
#### 4.2 安装浏览器elasticsearch-head插件（推荐）
如果我们使用谷歌浏览器，建议使用浏览器插件
[https://chrome.google.com/webstore/detail/elasticsearch-head/ffmkiejjmecolpfloofpjologoblkegm](https://chrome.google.com/webstore/detail/elasticsearch-head/ffmkiejjmecolpfloofpjologoblkegm)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653559908-c132c006-94ae-4410-9879-90cca2e85592.png#crop=0&crop=0&crop=1&crop=1&id=zzTeP&margin=%5Bobject%20Object%5D&originHeight=768&originWidth=1011&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
## 二、kibana
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560076-68638b49-33b6-4d3a-a0a9-dd3de0a1cd48.png#crop=0&crop=0&crop=1&crop=1&id=ZR1cz&margin=%5Bobject%20Object%5D&originHeight=169&originWidth=884&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
注意：kibana版本要与ES一致
解压之后 打开/bin/kibana.bat即可使用，以后我们就用开发同居Dev Tools写语句
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560155-0614d193-980b-4563-b4fd-8b1c24dc1c96.png#crop=0&crop=0&crop=1&crop=1&id=fugbD&margin=%5Bobject%20Object%5D&originHeight=573&originWidth=1082&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
### 汉化
需要我们自己在配置文件中设置国际化，
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560227-11a53d91-66d4-4686-ad0a-a704d724e3ee.png#crop=0&crop=0&crop=1&crop=1&id=DyK6f&margin=%5Bobject%20Object%5D&originHeight=125&originWidth=649&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
kibana-7.15.1\config\kibana.yml
```yaml
# Specifies locale to be used for all localizable strings, dates and number formats.
# Supported languages are the following: English - en , by default , Chinese - zh-CN .
#i18n.locale: "en"
i18n.locale: "zh-CN"
```
保存重启即可
### 创建索引
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560328-6d44b479-904b-47c7-a53b-9cf642945726.png#crop=0&crop=0&crop=1&crop=1&id=pKOdv&margin=%5Bobject%20Object%5D&originHeight=256&originWidth=918&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560407-131e35c7-7d39-4503-bd25-2ff4860350ec.png#crop=0&crop=0&crop=1&crop=1&id=EtMyi&margin=%5Bobject%20Object%5D&originHeight=320&originWidth=270&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
## 三、ElasticSearch核心概念
### 概述
1、索引（ElasticSearch）

- 相当于数据库

2、字段类型（映射）

- 表，字段类型映射（字段是整型，还是字符型…）

3、文档

- 数据库中一行记录

4、分片（Lucene索引，倒排索引）
> ElasticSearch是面向文档，关系行数据库和ElasticSearch客观对比！一切都是JSON！

| Relational DB | ElasticSearch |
| --- | --- |
| 数据库（database） | 索引（indices） |
| 表（tables） | types <慢慢会被弃用!> |
| 行（rows） | documents 文档 |
| 字段（columns） | fields |

**elasticsearch（集群）**中可以包含多个**索引（数据库）**，每个索引中可以包含多个**类型（表）** ,每个类型下又包含多个**文档（行）** ,每个文档中又包含多个**字段（列）**。
**物理设计：**
elasticsearch在后台把**每个索引划分成多个分片**，每分分片可以在集群中的不同服务器间迁移
一个人就是一个集群! ，即**启动的ElasticSearch服务，默认就是一个集群，且默认集群名为elasticsearch**
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560480-3dc12d12-83ea-4a35-8621-b19d0aed3630.png#crop=0&crop=0&crop=1&crop=1&id=Z7bdA&margin=%5Bobject%20Object%5D&originHeight=464&originWidth=748&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
**逻辑设计：**
一个索引类型中，包含多个文档，比如说文档1，文档2。当我们索引一篇文档时，可以通过这样的顺序找到它：索引 => 类型 => 文档ID ，通过这个组合我们就能索引到某个具体的文档。 注意：ID不必是整数，实际上它是个字符串。
> 文档（”行“）

之前说elasticsearch是面向文档的，那么就意味着**索引和搜索数据的最小单位是文档**，elasticsearch中，文档有
几个重要属性:

- 自我包含，一篇文档同时包含字段和对应的值，也就是同时包含 key:value
- 可以是层次型的，一个文档中包含自文档，复杂的逻辑实体就是这么来的! {就是一个json对象 ! fastjson进行自动转换 !}
- 灵活的结构，文档不依赖预先定义的模式，我们知道关系型数据库中，要提前定义字段才能使用，在elasticsearch中，对于字段是非常灵活的，有时候,我们可以忽略该字段，或者动态的添加一个新的字段。

尽管我们可以随意的新增或者忽略某个字段，但是，每个字段的类型非常重要，比如一个年龄字段类型，可以是字符串也可以是整形。因为elasticsearch会保存字段和类型之间的映射及其他的设置。这种映射具体到每个映射的每种类型，这也是为什么在elasticsearch中，类型有时候也称为映射类型。
> 类型（“表”）

类型是文档的逻辑容器，就像关系型数据库一样，表格是行的容器。类型中对于字段的定义称为映射，比如name映射为字符串类型。我们说文档是无模式的，它们不需要拥有映射中所定义的所有字段，比如新增一个字段，那么elasticsearch是怎么做的呢?

- elasticsearch会自动的将新字段加入映射，但是这个字段的不确定它是什么类型，elasticsearch就开始猜，如果这个值是18，那么elasticsearch会认为它是整形。但是elasticsearch也可能猜不对，所以最安全的方式就是提前定义好所需要的映射，这点跟关系型数据库殊途同归了，先定义好字段，然后再使用，别整什么幺蛾子。
> 索引（“库”）

索引是映射类型的容器， elasticsearch中的索引是一个非常大的文档集合。 索引存储了映射类型的字段和其他设置。然后它们被存储到了各个分片上了。我们来研究下分片是如何工作的。
**物理设计：节点和分片如何工作?**
创建新索引
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560554-c530b49e-d3c5-46da-9a51-0ba33b5172b2.png#crop=0&crop=0&crop=1&crop=1&id=ntPbi&margin=%5Bobject%20Object%5D&originHeight=123&originWidth=402&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
一个集群至少有一个节点，而一个节点就是一个elasricsearch进程，节点可以有多个索引默认的，如果你创建索引，那么索引将会有个5个分片(primary shard ,又称主分片)构成的，每一个主分片会有一个副本(replica shard，又称复制分片)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560620-97878fdc-27ba-46d9-8f4c-16da3625cdad.png#crop=0&crop=0&crop=1&crop=1&id=wIB5z&margin=%5Bobject%20Object%5D&originHeight=218&originWidth=764&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
上图是一个有3个节点的集群，可以看到主分片和对应的复制分片都不会在同一个节点内，这样有利于某个节点挂掉了，数据也不至于失。实际上，**一个分片是一个Lucene索引（****一个ElasticSearch索引包含多个Lucene索引****）** ，**一个包含倒排索引的文件目录，倒排索引的结构使得elasticsearch在不扫描全部文档的情况下，就能告诉你哪些文档包含特定的关键字**。不过，等等，倒排索引是什么鬼?
> 倒排索引（Lucene索引底层）
> 简单说就是 按（文章关键字，对应的文档<0个或多个>）形式建立索引，根据关键字就可直接查询对应的文档（含关键字的），无需查询每一个文档，如下图

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560693-036a754c-659a-42dd-a6d5-0c517eef608d.png#crop=0&crop=0&crop=1&crop=1&id=d3SYr&margin=%5Bobject%20Object%5D&originHeight=193&originWidth=597&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
[https://www.cnblogs.com/cjsblog/p/10327673.html](https:_www.cnblogs.com_cjsblog_p_10327673)
## 四、IK分词器
IK分词器：中文分词器（elasticsearch插件）
分词：即把一段中文或者别的划分成一个个的关键字，我们在搜索时候会把自己的信息进行分词，会把数据库中或者索引库中的数据进行分词，然后进行一一个匹配操作，**默认的中文分词是将每个字看成一个词**（不使用用IK分词器的情况下），比如“我爱狂神”会被分为”我”，”爱”，”狂”，”神” ，这显然是不符合要求的，所以我们需要安装中文分词器ik来解决这个问题。
**IK提供了两个分词算法**: `ik_smart`和`ik_max_word`
其中`ik_smart`为**最少切分**, `ik_max_word`为**最细粒度划分**!
> 1、下载

注意：版本要与ElasticSearch版本对应
下载地址：[https://github.com/medcl/elasticsearch-analysis-ik/releases](https://github.com/medcl/elasticsearch-analysis-ik/releases)
> 2、安装

解压即可（需要解压到ElasticSearch的 /plugins/ik/ 文件夹下）
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560767-bc765a8c-05bb-48f5-a488-c77109d282a0.png#crop=0&crop=0&crop=1&crop=1&id=twJRn&margin=%5Bobject%20Object%5D&originHeight=274&originWidth=706&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
> 3、重启ElasticSearch

加载了IK分词器:
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560836-7f301a17-8a42-4b5a-bca5-ac99fa7cb7ba.png#crop=0&crop=0&crop=1&crop=1&id=KGYFv&margin=%5Bobject%20Object%5D&originHeight=98&originWidth=955&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
> 4、使用 `ElasticSearch安装目录 /bin/elasticsearch-plugin` 可以查看插件
> elasticsearch-7.6.1\bin> elasticsearch-plugin list

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560905-d754a378-9870-4b30-8c0b-39f3bb1bec6f.png#crop=0&crop=0&crop=1&crop=1&id=GkPSp&margin=%5Bobject%20Object%5D&originHeight=79&originWidth=555&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
> 5、使用kibana测试

`ik_smart`：最少切分
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653560975-b08c0240-3f04-44c1-9ea7-7974d1a5967f.png#crop=0&crop=0&crop=1&crop=1&id=vGjdT&margin=%5Bobject%20Object%5D&originHeight=478&originWidth=983&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
`ik_max_word`：最细粒度划分（穷尽词库的可能）
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561047-6809877f-0ebc-4015-bc7e-45c4dbf7fd7b.png#crop=0&crop=0&crop=1&crop=1&id=WTQs3&margin=%5Bobject%20Object%5D&originHeight=643&originWidth=1046&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
从上面看，感觉分词都比较正常，但是大多数分词都满足不了我们的想法，如下例
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561119-4f8f1835-fed5-4c97-a262-9c534ce32ddb.png#crop=0&crop=0&crop=1&crop=1&id=XMiRX&margin=%5Bobject%20Object%5D&originHeight=596&originWidth=933&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
那么，我们需要手动将该词添加到分词器的词典当中
> 6、添加自定义的词添加到扩展字典中

elasticsearch目录/plugins/ik/config/IKAnalyzer.cfg.xml
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561190-f91c5186-c16d-46f0-b82d-f90b4b1cd215.png#crop=0&crop=0&crop=1&crop=1&id=oziPm&margin=%5Bobject%20Object%5D&originHeight=419&originWidth=871&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
打开 `IKAnalyzer.cfg.xml` 文件，扩展字典
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561261-2d524d09-33b3-4d9e-8937-68973c472fca.png#crop=0&crop=0&crop=1&crop=1&id=gb5CI&margin=%5Bobject%20Object%5D&originHeight=350&originWidth=974&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
创建字典文件，添加字典内容
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561346-87bc19c4-b743-4e5e-9275-b77e9cc9c6d8.png#crop=0&crop=0&crop=1&crop=1&id=FK9FO&margin=%5Bobject%20Object%5D&originHeight=210&originWidth=590&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
重启ElasticSearch，再次使用kibana测试
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561418-fc466b8d-b379-4f87-8ede-a5a466260654.png#crop=0&crop=0&crop=1&crop=1&id=Y0FUr&margin=%5Bobject%20Object%5D&originHeight=703&originWidth=971&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
## 五、Restful风格的ES语句
Restful是一种软件架构风格,而不是标准,只是提供了一组设计原则和约束条件。它主要用于客户端和服务器交互类
的软件。基于这个风格设计的软件可以**更简洁**，**更有层次**，**更易于实现缓存**等机制。
### 1、基本的Rest命令
| method | url地址 | 描述 |
| --- | --- | --- |
| PUT（创建,修改） | localhost:9200/索引名称/类型名称/文档id | 创建文档（指定文档id） |
| POST（创建） | localhost:9200/索引名称/类型名称 | 创建文档（随机文档id） |
| POST（修改） | localhost:9200/索引名称/类型名称/文档id/_update | 修改文档 |
| DELETE（删除） | localhost:9200/索引名称/类型名称/文档id | 删除文档 |
| GET（查询） | localhost:9200/索引名称/类型名称/文档id | 查询文档通过文档ID |
| POST（查询） | localhost:9200/索引名称/类型名称/文档id/_search | 查询所有数据 |

### 2、测试
#### 1、创建一个索引，添加
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561489-518fa441-fe3e-43dc-bc07-a9be367e687e.png#crop=0&crop=0&crop=1&crop=1&id=VCrhp&margin=%5Bobject%20Object%5D&originHeight=315&originWidth=940&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561559-4643fca3-ed38-40e7-966d-6b07b8b6ec96.png#crop=0&crop=0&crop=1&crop=1&id=DklgJ&margin=%5Bobject%20Object%5D&originHeight=347&originWidth=778&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 2、字段数据类型

- 字符串类型
   - text、keyword
      - text：支持分词，全文检索,支持模糊、精确查询,不支持聚合,排序操作;text类型的最大支持的字符长度无限制,适合大字段存储；
      - keyword：不进行分词，直接索引、支持模糊、支持精确匹配，支持聚合、排序操作。keyword类型的最大支持的长度为——32766个UTF-8类型的字符,可以通过设置ignore_above指定自持字符长度，超过给定长度后的数据将不被索引，无法通过term精确匹配检索返回结果。
- 数值型
   - long、Integer、short、byte、double、float、**half float**、**scaled float**
- 日期类型
   - date
- te布尔类型
   - boolean
- 二进制类型
   - binary
- 等等…
#### 3、指定字段的类型（使用PUT）
类似于建库（建立索引和字段对应类型），也可看做规则的建立
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561627-8a0b2b3d-60ee-43a1-a51f-5955877a888f.png#crop=0&crop=0&crop=1&crop=1&id=sayv7&margin=%5Bobject%20Object%5D&originHeight=238&originWidth=899&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 4、获取索引建立的规则
> GET test2

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561700-c076a1e2-6025-403f-bfaa-77dd19845989.png#crop=0&crop=0&crop=1&crop=1&id=Q7hR2&margin=%5Bobject%20Object%5D&originHeight=557&originWidth=1009&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 5、获取默认信息
> `_doc` 默认类型（default type），type 在未来的版本中会逐渐弃用，因此产生一个默认类型进行代替

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561798-08321916-477b-4c5f-a8de-c08c74e5e711.png#crop=0&crop=0&crop=1&crop=1&id=RfWv6&margin=%5Bobject%20Object%5D&originHeight=258&originWidth=844&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561878-90eb6505-3887-4d03-b919-629babb5055a.png#crop=0&crop=0&crop=1&crop=1&id=XOVdS&margin=%5Bobject%20Object%5D&originHeight=561&originWidth=1064&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
如果自己的文档字段没有被指定，那么ElasticSearch就会给我们默认配置字段类型
扩展：通过`get _cat/` 可以获取ElasticSearch的当前的很多信息！
```shell
GET _cat/indices
GET _cat/aliases
GET _cat/allocation
GET _cat/count
GET _cat/fielddata
GET _cat/health
GET _cat/indices
GET _cat/master
GET _cat/nodeattrs
GET _cat/nodes
GET _cat/pending_tasks
GET _cat/plugins
GET _cat/recovery
GET _cat/repositories
GET _cat/segments
GET _cat/shards
GET _cat/snapshots
GET _cat/tasks
GET _cat/templates
GET _cat/thread_pool
```
#### 6、修改
两种方案

- 旧的（使用put覆盖原来的值）
   - 版本+1（_version）
   - 但是如果漏掉某个字段没有写，那么更新后，没有写的字段会消失

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653561957-c3401896-a3eb-4ea2-80d0-c96d52fb4bd3.png#crop=0&crop=0&crop=1&crop=1&id=qXF3P&margin=%5Bobject%20Object%5D&originHeight=437&originWidth=645&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562028-2b514513-7979-44ad-beb6-f1f17353712c.png#crop=0&crop=0&crop=1&crop=1&id=ZAp4h&margin=%5Bobject%20Object%5D&originHeight=371&originWidth=905&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)

- 新的（使用post的update）
   - version不会改变
   - 需要注意doc
   - 不会丢失字段

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562099-cbb58e4b-5895-40cd-8abf-f22fba34a41e.png#crop=0&crop=0&crop=1&crop=1&id=Fx9YB&margin=%5Bobject%20Object%5D&originHeight=344&originWidth=884&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
只需要写要更新的字段内容，没有写的字段，更新后保持不变
#### 7、删除
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562170-93416870-85c8-40fe-8372-b8089ae24cd3.png#crop=0&crop=0&crop=1&crop=1&id=qQX2z&margin=%5Bobject%20Object%5D&originHeight=90&originWidth=703&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562232-9feda2bf-ce37-4e05-ad7a-2bcd3a39140c.png#crop=0&crop=0&crop=1&crop=1&id=QeUeh&margin=%5Bobject%20Object%5D&originHeight=438&originWidth=955&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
### 3、简单查询
> GET /test3/_doc/_search?q=name:流柚

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562302-70e76788-5fbf-4b8c-a511-e5e03900680c.png#crop=0&crop=0&crop=1&crop=1&id=G99HE&margin=%5Bobject%20Object%5D&originHeight=487&originWidth=805&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
### 4、复杂查询
先建一个test2索引
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562371-497de632-1ae8-4949-abd7-3c013bf464ab.png#crop=0&crop=0&crop=1&crop=1&id=h5jGs&margin=%5Bobject%20Object%5D&originHeight=200&originWidth=518&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 1、查询匹配

- `match`：匹配（会使用分词器解析（先分析文档，然后进行查询）
- `_source`：过滤字段
- `sort`：排序
- `from`：从哪里开始（即start）
- `size` ：每页显示条数（即pageSize）

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562436-bf6a8b84-a84a-4155-9096-b58bc24edbfc.png#crop=0&crop=0&crop=1&crop=1&id=jszjp&margin=%5Bobject%20Object%5D&originHeight=670&originWidth=825&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
示例：
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562509-47c55119-e000-42c2-866f-1c673cadf19d.png#crop=0&crop=0&crop=1&crop=1&id=fx0o6&margin=%5Bobject%20Object%5D&originHeight=647&originWidth=1077&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 2、多条件查询（bool）

- `must`  相当于 `and`
- `should`   相当于 `or`
- `must_not`   相当于 `not (... and ...)`
- `filter`  过滤

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562582-97363dcf-7eff-4a2b-9758-19c48b181fc1.png#crop=0&crop=0&crop=1&crop=1&id=YyFvd&margin=%5Bobject%20Object%5D&originHeight=681&originWidth=854&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
示例：
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562655-2d490b4a-cf6e-4fc1-84b2-ffed3eba72dd.png#crop=0&crop=0&crop=1&crop=1&id=EZizV&margin=%5Bobject%20Object%5D&originHeight=483&originWidth=824&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 3、匹配数组

- 貌似不能与其它字段一起使用
- 可以多关键字查（空格隔开）— 匹配字段也是符合的
- `match` 会使用分词器解析（先分析文档，然后进行查询）
- 搜词

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562722-245bdf8f-d1b6-4027-82a0-d7762374c056.png#crop=0&crop=0&crop=1&crop=1&id=R4i61&margin=%5Bobject%20Object%5D&originHeight=790&originWidth=881&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 4、精确查询（term）

- `term` 直接通过 倒排索引 指定**词条**查询
- 适合查询 number、date、keyword ，不适合text

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562796-77ec6bff-1ea3-489a-8977-4171ae917657.png#crop=0&crop=0&crop=1&crop=1&id=AYLVG&margin=%5Bobject%20Object%5D&originHeight=400&originWidth=828&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
[ElasticSearch 使用term搜索中文失败](https://www.pianshen.com/article/73851011377/)
#### 5、text 和 keyword

- text：
   - **支持分词**，**全文检索**、支持模糊、精确查询,不支持聚合,排序操作;
   - text类型的最大支持的字符长度无限制,适合大字段存储；
- keyword：
   - **不进行分词**，**直接索引**、支持模糊、支持精确匹配，支持聚合、排序操作。
   - keyword类型的最大支持的长度为——32766个UTF-8类型的字符,可以通过设置ignore_above指定自持字符长度，超过给定长度后的数据将不被索引，**无法通过term精确匹配检索返回结果**。
```shell
PUT /test
{
  "mappings": {
    "properties": {
      "text":{
        "type":"text"
      },
      "keyword":{
        "type":"keyword"
      }
    }
  }
}
// 设置字段数据
PUT /test/_doc/1
{
  "text":"测试keyword和text是否支持分词",
  "keyword":"测试keyword和text是否支持分词"
}
// text 支持分词
// keyword 不支持分词
GET /test/_doc/_search
{
  "query":{
   "match":{
      "text":"测试"
   }
  }
}	// 查的到
GET /test/_doc/_search
{
  "query":{
   "match":{
      "keyword":"测试"
   }
  }
}	// 查不到，必须是 "测试keyword和text是否支持分词" 才能查到
GET _analyze
{
  "analyzer": "keyword",
  "text": ["测试liu"]
}	// 不会分词，即： 测试liu
GET _analyze
{
  "analyzer": "standard",
  "text": ["测试liu"]
}// 分为： 测 试 liu
GET _analyze
{
  "analyzer":"ik_max_word",
  "text": ["测试liu"]
}	// 分为： 测试 liu
```
#### 6、高亮查询
```shell
GET blog/user/_search
{
  "query": {
    "match": {
      "name":"流"
    }
  },
  "highlight": {
    "fields": {
      "name": {}
    }
  }
}
// 自定义前缀和后缀
GET blog/user/_search
{
  "query": {
    "match": {
      "name":"流"
    }
  }
  ,
  "highlight": {
    "pre_tags": "<p class='key' style='color:red'>",
    "post_tags": "</p>", 
    "fields": {
      "name": {}
    }
  }
}
```
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562865-35572ddc-dd7c-4770-8fda-34e33d914fc3.png#crop=0&crop=0&crop=1&crop=1&id=lEXh5&margin=%5Bobject%20Object%5D&originHeight=780&originWidth=813&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
示例：
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653562937-1ecfc4b4-9425-4613-bf90-e510b3388cca.png#crop=0&crop=0&crop=1&crop=1&id=Azz98&margin=%5Bobject%20Object%5D&originHeight=604&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
## 六、SpringBoot整合
### 1、查看官方文档
[https://www.elastic.co/guide/en/elasticsearch/client/index.html](https:_www.elastic.co_guide_en_elasticsearch_client_index)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563008-71aae467-c310-4b97-882c-4696f4aec19f.png#crop=0&crop=0&crop=1&crop=1&id=z4IiN&margin=%5Bobject%20Object%5D&originHeight=651&originWidth=857&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563080-4c91a9f0-2f5d-44b0-bc80-222fdcc3d43c.png#crop=0&crop=0&crop=1&crop=1&id=yrkmo&margin=%5Bobject%20Object%5D&originHeight=507&originWidth=849&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563150-174cd6e3-e4c3-477d-a194-a18bd45b7637.png#crop=0&crop=0&crop=1&crop=1&id=OAaCR&margin=%5Bobject%20Object%5D&originHeight=896&originWidth=361&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
### 2、准备工作
#### 2.1 创建工程
Springboot中自动导入配置都是以 xxxAutoConfiguration    xxxProperties
查看自动配置类  ElasticsearchRestClientProperties
由于我们使用的是[Java High Level REST Client](https:_www.elastic.co_guide_en_elasticsearch_client_java-rest_current_java-rest-high) ，多研究一下
ElasticsearchRestClientAutoConfiguration    ElasticsearchRestClientProperties
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563232-ce545868-afcf-450c-b2dc-67a36a056cf8.png#crop=0&crop=0&crop=1&crop=1&id=eKdNe&margin=%5Bobject%20Object%5D&originHeight=770&originWidth=1378&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
注意：依赖maven的es版本和安装的版本一致
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563318-91d4126f-eb8e-4c1f-aff0-1ae4c8939b67.png#crop=0&crop=0&crop=1&crop=1&id=aJBak&margin=%5Bobject%20Object%5D&originHeight=534&originWidth=1290&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)
#### 2.2 创建ES配置类
```java
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        return client;
    }
}
```
#### 2.3 创建一个实体类
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -3843548915035470817L;
    private String name;
    private Integer age;
}
```
### 3、测试API
所有测试api均在 `DemoApplicationTests`中编写，注入 `RestHighLevelClient`
```
@Autowired
private RestHighLevelClient restHighLevelClient;
```
#### 3.1 索引操作

- 创建索引

![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634653563390-7454759c-6c23-416e-8d1c-b7739724301a.png#crop=0&crop=0&crop=1&crop=1&id=bZ3gC&margin=%5Bobject%20Object%5D&originHeight=306&originWidth=636&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&title=)

- 索引的获取，并判断其是否存在
```java
// 测试获取索引，并判断其是否存在
@Test
public void testIndexIsExists() throws IOException {
    GetIndexRequest request = new GetIndexRequest("index");
    boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);		// 索引是否存在
    restHighLevelClient.close();
}
```

- 索引的删除
```java
// 测试索引删除
@Test
public void testDeleteIndex() throws IOException {
    DeleteIndexRequest request = new DeleteIndexRequest("liuyou_index");
    AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    System.out.println(response.isAcknowledged());	// 是否删除成功
    restHighLevelClient.close();
}
```
#### 3.2 文档的操作

- 文档的添加
```java
// 测试添加文档(先创建一个User实体类，添加fastjson依赖)
@Test
public void testAddDocument() throws IOException {
    // 创建一个User对象
    User liuyou = new User("liuyou", 18);
    // 创建请求
    IndexRequest request = new IndexRequest("liuyou_index");
    // 制定规则 PUT /liuyou_index/_doc/1
    request.id("1");// 设置文档ID
    request.timeout(TimeValue.timeValueMillis(1000));// request.timeout("1s")
    // 将我们的数据放入请求中
    request.source(JSON.toJSONString(liuyou), XContentType.JSON);
    // 客户端发送请求，获取响应的结果
    IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    System.out.println(response.status());	// 获取建立索引的状态信息 CREATED
    System.out.println(response);	// 查看返回内容 IndexResponse[index=liuyou_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
}
```

- 文档信息的获取
```java
// 测试获得文档信息
@Test
public void testGetDocument() throws IOException {
    GetRequest request = new GetRequest("liuyou_index","1");
    GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
    System.out.println(response.getSourceAsString());// 打印文档内容
    System.out.println(request);// 返回的全部内容和命令是一样的
    restHighLevelClient.close();
}
```

- 文档的获取，并判断其是否存在
```java
// 获取文档，判断是否存在 get /liuyou_index/_doc/1
@Test
public void testDocumentIsExists() throws IOException {
    GetRequest request = new GetRequest("liuyou_index", "1");
    // 不获取返回的 _source的上下文了
    request.fetchSourceContext(new FetchSourceContext(false));
    request.storedFields("_none_");
    boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);
}
```

- 文档的更新
```java
// 测试更新文档内容
@Test
public void testUpdateDocument() throws IOException {
    UpdateRequest request = new UpdateRequest("liuyou_index", "1");
    User user = new User("lmk",11);
    request.doc(JSON.toJSONString(user),XContentType.JSON);
    UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
    System.out.println(response.status()); // OK
    restHighLevelClient.close();
}
```

- 文档的删除
```java
// 测试删除文档
@Test
public void testDeleteDocument() throws IOException {
    DeleteRequest request = new DeleteRequest("liuyou_index", "1");
    request.timeout("1s");
    DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
    System.out.println(response.status());// OK
}
```

- 文档的查询

参考：[https://www.cnblogs.com/wenbronk/p/6432990.html](https://www.cnblogs.com/wenbronk/p/6432990.html)
```java
// 查询
// SearchRequest 搜索请求
// SearchSourceBuilder 条件构造
// HighlightBuilder 高亮
// TermQueryBuilder 精确查询
// MatchAllQueryBuilder
// xxxQueryBuilder ...
@Test
public void testSearch() throws IOException {
    // 1.创建查询请求对象
    SearchRequest searchRequest = new SearchRequest();
    // 2.构建搜索条件
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    // (1)查询条件 使用QueryBuilders工具类创建
    // 精确查询
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "liuyou");
    //        // 匹配查询
    //        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
    // (2)其他<可有可无>：（可以参考 SearchSourceBuilder 的字段部分）
    // 设置高亮
    searchSourceBuilder.highlighter(new HighlightBuilder());
    //        // 分页
    //        searchSourceBuilder.from();
    //        searchSourceBuilder.size();
    searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
    // (3)条件投入
    searchSourceBuilder.query(termQueryBuilder);
    // 3.添加条件到请求
    searchRequest.source(searchSourceBuilder);
    // 4.客户端查询请求
    SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // 5.查看返回结果
    SearchHits hits = search.getHits();
    System.out.println(JSON.toJSONString(hits));
    System.out.println("=======================");
    for (SearchHit documentFields : hits.getHits()) {
        System.out.println(documentFields.getSourceAsMap());
    }
}
```

- 前面的操作都无法批量添加数据
```java
// 上面的这些api无法批量增加数据（只会保留最后一个source）
@Test
public void test() throws IOException {
    IndexRequest request = new IndexRequest("bulk");// 没有id会自动生成一个随机ID
    request.source(JSON.toJSONString(new User("liu",1)),XContentType.JSON);
    request.source(JSON.toJSONString(new User("min",2)),XContentType.JSON);
    request.source(JSON.toJSONString(new User("kai",3)),XContentType.JSON);
    IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    System.out.println(index.status());// created
}
```

- 批量添加数据
```java
// 特殊的，真的项目一般会 批量插入数据
@Test
public void testBulk() throws IOException {
    BulkRequest bulkRequest = new BulkRequest();
    bulkRequest.timeout("10s");
    ArrayList<User> users = new ArrayList<>();
    users.add(new User("liuyou-1",1));
    users.add(new User("liuyou-2",2));
    users.add(new User("liuyou-3",3));
    users.add(new User("liuyou-4",4));
    users.add(new User("liuyou-5",5));
    users.add(new User("liuyou-6",6));
    // 批量请求处理
    for (int i = 0; i < users.size(); i++) {
        bulkRequest.add(
                // 这里是数据信息
                new IndexRequest("bulk")
                        .id(""+(i + 1)) // 没有设置id 会自定生成一个随机id
                        .source(JSON.toJSONString(users.get(i)),XContentType.JSON)
        );
    }
    BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    System.out.println(bulk.status());// ok
}
```
## 七、实战-仿京东商城搜索（高亮）
### 1、工程springboot创建
#### 1.1 导入依赖
```xml
<properties>
    <java.version>1.8</java.version>
    <elasticsearch.version>7.6.1</elasticsearch.version>
</properties>
<dependencies>
    <!-- jsoup解析页面 -->
    <!-- 解析网页 爬视频可 研究tiko -->
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.10.2</version>
    </dependency>
    <!-- fastjson -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.70</version>
    </dependency>
    <!-- ElasticSearch -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
    <!-- thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <!-- web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- devtools热部署 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!--  -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
    <!-- lombok 需要安装插件 -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!-- test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```
#### 1.2 导入前端素材
将提前写好的前端页面的css js文件导入static目录，html文件放在templates目录

#### 1.3 编写 application.preperties配置文件
```shell
server.port=9999
spring.thymeleaf.cache=false
```
#### 1.4 测试controller和view
```java
@Controller
public class IndexController {
    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }
}
```
访问 localhost:9999
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634654802845-83eb165b-aadf-4c94-a0b6-7b74d15d2ffd.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=386&id=u957b1b3f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=514&originWidth=934&originalType=binary&ratio=1&rotation=0&showTitle=false&size=118435&status=done&style=stroke&taskId=u1c5e7481-1d5d-4279-bfe9-d8d8109e135&title=&width=701)
#### 1.5 编写ElasticSearchConfig
```java
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        return client;
    }
}
```
#### 1.6 编写service
因为是爬取的数据，那么就不走Dao，以下编写都不会编写接口，开发中必须严格要求编写
**ContentService**
```java
@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    // 1、解析数据放入 es 索引中
    public Boolean parseContent(String keyword) throws IOException {
        // 获取内容
        List<Content> contents = HtmlParseUtil.parseJD(keyword);
        // 内容放入 es 中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m"); // 可更具实际业务是指
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(contents.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        restHighLevelClient.close();
        return !bulk.hasFailures();
    }
        // 2、根据keyword分页查询结果
    public List<Map<String, Object>> search(String keyword, Integer pageIndex, Integer pageSize) throws IOException {
        if (pageIndex < 0){
            pageIndex = 0;
        }
        SearchRequest jd_goods = new SearchRequest("jd_goods");
        // 创建搜索源建造者对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 条件采用：精确查询 通过keyword查字段name
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));// 60s
        // 分页
        searchSourceBuilder.from(pageIndex);
        searchSourceBuilder.size(pageSize);
        // 高亮
        // ....
        // 搜索源放入搜索请求中
        jd_goods.source(searchSourceBuilder);
        // 执行查询，返回结果
        SearchResponse searchResponse = restHighLevelClient.search(jd_goods, RequestOptions.DEFAULT);
        restHighLevelClient.close();
        // 解析结果
        SearchHits hits = searchResponse.getHits();
        List<Map<String,Object>> results = new ArrayList<>();
        for (SearchHit documentFields : hits.getHits()) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            results.add(sourceAsMap);
        }
        // 返回查询的结果
        return results;
    }
}
```
#### 1.7 编写controller
```java
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @ResponseBody
    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword);
    }
    @ResponseBody
    @GetMapping("/search/{keyword}/{pageIndex}/{pageSize}")
    public List<Map<String, Object>> parse(@PathVariable("keyword") String keyword,
                                           @PathVariable("pageIndex") Integer pageIndex,
                                           @PathVariable("pageSize") Integer pageSize) throws IOException {
        return contentService.search(keyword,pageIndex,pageSize);
    }
}
```
#### 1.8 测试结果
**1、解析数据放入 es 索引中**
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634698305478-986ae3a6-9a66-4427-ad4f-ed11fad8a289.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=u91f271b1&margin=%5Bobject%20Object%5D&originHeight=120&originWidth=785&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&taskId=ud167a053-e5a9-4802-bd10-cbbb85baed6&title=)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634698388816-0e39f071-4187-4711-bb6d-88725e65d859.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=282&id=u7d8f1963&margin=%5Bobject%20Object%5D&name=image.png&originHeight=564&originWidth=1893&originalType=binary&ratio=1&rotation=0&showTitle=false&size=211146&status=done&style=stroke&taskId=ub01c0f61-263e-4ef0-b1ff-5328d7c5aba&title=&width=946.5)
**2、根据keyword分页查询结果**
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634698487712-8a59ae8e-38e9-4726-a92b-499a4dd07b91.png#clientId=u314c60c3-4fc3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=496&id=u11907fcb&margin=%5Bobject%20Object%5D&name=image.png&originHeight=991&originWidth=1519&originalType=binary&ratio=1&rotation=0&showTitle=false&size=1095411&status=done&style=stroke&taskId=u02dde7b8-3c93-4740-a211-46adab9018b&title=&width=759.5)

### 2、爬虫（jsoup）
#### 2.1 分析京东搜索页面
[https://search.jd.com/Search?keyword=java](https://search.jd.com/Search?keyword=java)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655267694-0281e907-511d-48b0-b4a3-ea951f1b7a1d.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=489&id=u558b2c27&margin=%5Bobject%20Object%5D&name=image.png&originHeight=977&originWidth=1693&originalType=binary&ratio=1&rotation=0&showTitle=false&size=631415&status=done&style=stroke&taskId=u8f0de4eb-355e-4a4f-93e1-2207956a76a&title=&width=846.5)
**审查页面元素**
页面列表id：J_goodsList
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655360477-211a0748-9979-426c-9bf0-82ef0e3485de.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=331&id=u63587f94&margin=%5Bobject%20Object%5D&name=image.png&originHeight=661&originWidth=1682&originalType=binary&ratio=1&rotation=0&showTitle=false&size=416858&status=done&style=stroke&taskId=u18433ddd-1d62-494a-9024-9b36df095cd&title=&width=841)
**目标元素：img、price、name**
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655376670-37132e96-2db5-4db4-9c55-d655c94d1ac7.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=u2b80c050&margin=%5Bobject%20Object%5D&originHeight=464&originWidth=751&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&taskId=uf1535584-db4a-49e6-9dea-121c9e3d047&title=)
#### 2.2 爬取数据
获取请求返回的页面信息，筛选出可用的，创建HtmlParseUtil
```java
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        /// 使用前需要联网
        // 请求url
        String url = "http://search.jd.com/search?keyword=java";
        // 1.解析网页(jsoup 解析返回的对象是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 使用document可以使用在js对document的所有操作
        // 2.获取元素（通过id）
        Element j_goodsList = document.getElementById("J_goodsList");
        // 3.获取J_goodsList ul 每一个 li
        Elements lis = j_goodsList.getElementsByTag("li");
        // 4.获取li下的 img、price、name
        for (Element li : lis) {
            String img = li.getElementsByTag("img").eq(0).attr("src");// 获取li下 第一张图片
            String name = li.getElementsByClass("p-name").eq(0).text();
            String price = li.getElementsByClass("p-price").eq(0).text();
            System.out.println("=======================");
            System.out.println("img : " + img);
            System.out.println("name : " + name);
            System.out.println("price : " + price);
        }
    }
}
```
**运行结果**
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655449596-8603a56d-46f7-42ed-997e-e488eb17427e.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=u4e88cc52&margin=%5Bobject%20Object%5D&originHeight=523&originWidth=741&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&taskId=u2b264f86-dc4a-4567-96e8-3f6e23321f2&title=)
**原因是啥？**
一般图片特别多的网站，所有的图片都是通过延迟加载的
```java
// 打印标签内容
Elements lis = j_goodsList.getElementsByTag("li");
System.out.println(lis);
```
打印所有li标签，发现img标签中并没有属性src的设置，只是data-lazy-ing设置图片加载的地址
![](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655483232-64930d22-66b5-4072-a4b3-b2a721eca8c7.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=uf18e1dd0&margin=%5Bobject%20Object%5D&originHeight=490&originWidth=1777&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=stroke&taskId=u18324b75-b110-4359-b4b3-f5f4b5cda04&title=)
**改写HtmlParseUtil**

- 更改图片获取属性为 data-lazy-img
- 与实体类结合，实体类如下
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Serializable {
    private static final long serialVersionUID = -8049497962627482693L;
    private String name;
    private String img;
    private String price;
}
```

- 封装为方法
```java
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        System.out.println(parseJD("java"));
    }
    public static List<Content> parseJD(String keyword) throws IOException {
        /// 使用前需要联网
        // 请求url
        String url = "http://search.jd.com/search?keyword=" + keyword;
        // 1.解析网页(jsoup 解析返回的对象是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 使用document可以使用在js对document的所有操作
        // 2.获取元素（通过id）
        Element j_goodsList = document.getElementById("J_goodsList");
        // 3.获取J_goodsList ul 每一个 li
        Elements lis = j_goodsList.getElementsByTag("li");
//        System.out.println(lis);
        // 4.获取li下的 img、price、name
        // list存储所有li下的内容
        List<Content> contents = new ArrayList<Content>();
        for (Element li : lis) {
            // 由于网站图片使用懒加载，将src属性替换为data-lazy-img
            String img = li.getElementsByTag("img").eq(0).attr("data-lazy-img");// 获取li下 第一张图片
            String name = li.getElementsByClass("p-name").eq(0).text();
            String price = li.getElementsByClass("p-price").eq(0).text();
            // 封装为对象
            Content content = new Content(name,img,price);
            // 添加到list中
            contents.add(content);
        }
//        System.out.println(contents);
        // 5.返回 list
        return contents;
    }
}
```
**结果展示**
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655668988-16f63e3e-b0cd-4f41-b27e-1d1893c25b19.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=160&id=ud619c6c5&margin=%5Bobject%20Object%5D&name=image.png&originHeight=319&originWidth=1909&originalType=binary&ratio=1&rotation=0&showTitle=false&size=484737&status=done&style=stroke&taskId=u5e9aacdc-7739-44f8-a8db-3e4430100e1&title=&width=954.5)
### 3、搜索高亮
#### 3.1 ContentService
```java
// 3、 在2的基础上进行高亮查询
public List<Map<String, Object>> highlightSearch(String keyword, Integer pageIndex, Integer pageSize) throws IOException {
    SearchRequest searchRequest = new SearchRequest("jd_goods");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    // 精确查询，添加查询条件
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", keyword);
    searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
    searchSourceBuilder.query(termQueryBuilder);
    // 分页
    searchSourceBuilder.from(pageIndex);
    searchSourceBuilder.size(pageSize);
    // 高亮 =========
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    highlightBuilder.field("name");
    highlightBuilder.preTags("<span style='color:red'>");
    highlightBuilder.postTags("</span>");
    searchSourceBuilder.highlighter(highlightBuilder);
    // 执行查询
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // 解析结果 ==========
    SearchHits hits = searchResponse.getHits();
    List<Map<String, Object>> results = new ArrayList<>();
    for (SearchHit documentFields : hits.getHits()) {
        // 使用新的字段值（高亮），覆盖旧的字段值
        Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
        // 高亮字段
        Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
        HighlightField name = highlightFields.get("name");
        // 替换
        if (name != null){
            Text[] fragments = name.fragments();
            StringBuilder new_name = new StringBuilder();
            for (Text text : fragments) {
                new_name.append(text);
            }
            sourceAsMap.put("name",new_name.toString());
        }
        results.add(sourceAsMap);
    }
    return results;
}
```
#### 3.2 ContentController
```java
@ResponseBody
@GetMapping("/h_search/{keyword}/{pageIndex}/{pageSize}")
public List<Map<String, Object>> highlightParse(@PathVariable("keyword") String keyword,
                                       @PathVariable("pageIndex") Integer pageIndex,
                                       @PathVariable("pageSize") Integer pageSize) throws IOException {
    return contentService.highlightSearch(keyword,pageIndex,pageSize);
}
```
#### 3.3 结果展示
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655771479-0d14392e-166e-49f2-9fc5-3a237072e6b6.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=226&id=u88a49d3c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=451&originWidth=1908&originalType=binary&ratio=1&rotation=0&showTitle=false&size=392551&status=done&style=stroke&taskId=u32f83cd4-c8cd-4496-aa8c-bfaff59f4ee&title=&width=954)
### 4、前后端分离（Vue+SpringBoot）
#### 4.1 修改静态页面
> <script th:src="@{/js/vue.min.js}"></script>
> <script th:src="@{/js/axios.min.js}"></script>

**修改后的index.html**
```java
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <title>狂神说Java-ES仿京东实战</title>
    <link rel="stylesheet" th:href="@{/css/style.css}"/>
    <script th:src="@{/js/jquery.min.js}"></script>
</head>
<body class="pg">
<div class="page">
    <div id="app" class=" mallist tmall- page-not-market ">
        <!-- 头部搜索 -->
        <div id="header" class=" header-list-app">
            <div class="headerLayout">
                <div class="headerCon ">
                    <!-- Logo-->
                    <h1 id="mallLogo">
                        <img th:src="@{/images/jdlogo.png}" alt="">
                    </h1>
                    <div class="header-extra">
                        <!--搜索-->
                        <div id="mallSearch" class="mall-search">
                            <form name="searchTop" class="mallSearch-form clearfix">
                                <fieldset>
                                    <legend>天猫搜索</legend>
                                    <div class="mallSearch-input clearfix">
                                        <div class="s-combobox" id="s-combobox-685">
                                            <div class="s-combobox-input-wrap">
                                                <input v-model="keyword"  type="text" autocomplete="off" id="mq"
                                                       class="s-combobox-input"  aria-haspopup="true">
                                            </div>
                                        </div>
                                        <button type="submit" @click.prevent="searchKey" id="searchbtn">搜索</button>
                                    </div>
                                </fieldset>
                            </form>
                            <ul class="relKeyTop">
                                <li><a>狂神说Java</a></li>
                                <li><a>狂神说前端</a></li>
                                <li><a>狂神说Linux</a></li>
                                <li><a>狂神说大数据</a></li>
                                <li><a>狂神聊理财</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 商品详情页面 -->
        <div id="content">
            <div class="main">
                <!-- 品牌分类 -->
                <form class="navAttrsForm">
                    <div class="attrs j_NavAttrs" style="display:block">
                        <div class="brandAttr j_nav_brand">
                            <div class="j_Brand attr">
                                <div class="attrKey">
                                    品牌
                                </div>
                                <div class="attrValues">
                                    <ul class="av-collapse row-2">
                                        <li><a href="#"> 狂神说 </a></li>
                                        <li><a href="#"> Java </a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <!-- 排序规则 -->
                <div class="filter clearfix">
                    <a class="fSort fSort-cur">综合<i class="f-ico-arrow-d"></i></a>
                    <a class="fSort">人气<i class="f-ico-arrow-d"></i></a>
                    <a class="fSort">新品<i class="f-ico-arrow-d"></i></a>
                    <a class="fSort">销量<i class="f-ico-arrow-d"></i></a>
                    <a class="fSort">价格<i class="f-ico-triangle-mt"></i><i class="f-ico-triangle-mb"></i></a>
                </div>
                <!-- 商品详情 -->
                <div class="view grid-nosku" >
                    <div class="product" v-for="result in results">
                        <div class="product-iWrap">
                            <!--商品封面-->
                            <div class="productImg-wrap">
                                <a class="productImg">
                                    <img :src="result.img">
                                </a>
                            </div>
                            <!--价格-->
                            <p class="productPrice">
                                <em v-text="result.price"></em>
                            </p>
                            <!--标题-->
                            <p class="productTitle">
                                <a v-html="result.name"></a>
                            </p>
                            <!-- 店铺名 -->
                            <div class="productShop">
                                <span>店铺： 狂神说Java </span>
                            </div>
                            <!-- 成交信息 -->
                            <p class="productStatus">
                                <span>月成交<em>999笔</em></span>
                                <span>评价 <a>3</a></span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script th:src="@{/js/vue.min.js}"></script>
<script th:src="@{/js/axios.min.js}"></script>
<script>
    new Vue({
        el:"#app",
        data:{
            "keyword": '', // 搜索的关键字
            "results":[] // 后端返回的结果
        },
        methods:{
            searchKey(){
                var keyword = this.keyword;
                console.log(keyword);
                axios.get('h_search/'+keyword+'/0/20').then(response=>{
                    console.log(response.data);
                    this.results=response.data;
                })
            }
        }
    });
</script>
</body>
</html>
```
#### 4.2 测试
![image.png](https://cdn.nlark.com/yuque/0/2021/png/1559629/1634655918213-9d3c0c7a-646c-4dea-8eac-6fd917745c53.png#clientId=uab02926e-a6f7-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=455&id=u41103bb7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=909&originWidth=1816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=768675&status=done&style=stroke&taskId=u2fb22746-3f41-42c0-9041-0de18955f43&title=&width=908)
