Yellow
==========

处理某种数据文件成为另一种数据文件的解析处理框架

项目历史
----------
项目原本在jkit下, 名为excel, 于20110904迁移到fairy目项下, 名为yellow.
为了解决API设计上的问题(不易用), 整体重实现, 框架结构演用0.0.1版本.


组件介绍
----------
* 开发者可以选取任意不同的Parser和Filler, 与自己实现的Interceptor无缝对接.
* 数据流程: 输入文件 --> Parser --> Interceptor --> Filler --> 输出文件

### Parser 解析器
只关注文件格式, 将文件内容统一成标准格式交由Intercepter处理.

### Intercepter 拦截器
只关注标准格式下的内容数据处理成另一种标准格式, 具体的处理规则一般由框架使用者的业务逻辑来确定实现.
	
### Filler 填充器
只关注将拦截器输出的标准格式内容, 转化成文件.

### Info
输入流或输入文件的信息, 与Yellow, Intercepter相关, 与Parser, Filler无关

### Yellow 调节器
全局调度, 接收文件或输入流信息, 将流将给Parser解析, 再将结果与信息一起交由Intercepter处理,
在调用Intercepter的over方法返回FillObject对象后, 用Filler将其中的data填入info指定的介质中.


自带实现
----------
TD*	二维表格	Excel, Excel(Cell), Del, HTML
FullTextParser	一维(全文)文本 Text, Word