Yellow
========

处理某种数据文件成为另一种数据文件的自定义框架


项目原本在jkit下, 名为excel, 于20110904迁移到fairy目项下, 名为yellow.

大文件分段同时读的功能
读，解，读，三个操作动态均衡

提示yellow.yml中的类不存在（现在报的错是castException）等，

多个Sheet问题迭代问题



组件介绍
1.Parser		分析器,只面向于如何从不同的源文件中获取"遂条"(遂行)的数据,以行列的接口形式呈现数据
				不关心数据的存储问题,数据类型由分析器控制.prototype
				(ExcelParser可以提供更多事件，sheet截入等响应)
				例如:经管报表分析器,逗号引号分析器，CSV分析器，Excel分析器
				
2.Interceptor	拦截器,singleton
3.Filler		填充器,
4.Row			存放数据的实体对象，一般一个Row对应一个Parser.

//5.DataSource	数据源,Map（内存中）、数据库等


核心调用关系
Manager 操作 组件