#创建数据库操作通用类
#数据库操作完成后记录日志 （表名，增加的id，主要列名和值，修改的id，列名和值，删除的id，查看的id）
#根据log日志可以对数据进行逆向追回
#
drop table if exists LOG_OPERATION;
drop table if exists LOG_SETTING;
drop table if exists LOG_SETTING_DETAIL;
