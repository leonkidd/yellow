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
* 三大组件都单例

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

扩展性
----------
框架的各组件都支持被扩展,并且扩展后的组件可以供新老应用使用.
### Parser
DelParser, ExcelParser, SheetNParser, TextParser 分别对CSV、Excel、指定Sheet的Excel、文
件文件提供的解析支持. 开发者可以根据解析的文件不同, 开发更多Parser.
### Intercepter
TDIntercepter, NTDIntercepter, TDCellIntercepter, CopyIntercepter 分别对二维表、n张二维
表、指定单元格取数、复制的拦载器做了定义, 并提供了一些较为简便的API. 开发者可以根据处理需求
的不同, 定义更多Intercepter.
### Filler
DelFiller, Sheet0Filler 分别对CSV文件、首个Sheet的Excel实现了填充器. 开发者可以根据输出结
果的要求不同, 开发更多Filler.

注意
----------
使用时Parser, Intercepter, Yellow在类型上要一致, 如TD*、NTD*. Filler任意

自带实现
----------
TD*	二维表格	Excel, Excel(Cell), Del, HTML
FullTextParser	一维(全文)文本 Text, Word