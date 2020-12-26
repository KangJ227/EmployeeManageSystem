# Java课程设计作业
用Java swing+MySQL写的员工管理系统，实现了简单的增删改查功能。

# 数据库设计

**login**
field | type | description | constraint |
|--|--|--|--|
Username | char(20) | 用户名 | primary key |
Password | char(20) | 密码 |  | 
<br/>

**department**
field | type | description | constraint |
|--|--|--|--|
|dno|char(20)|部门号|primary key|
|dname|char(20)|部门名|not null|
|dmanager|char(20)|部门领导| |
<br/>

**employee**
field | type | description | constraint |
|--|--|--|--|
|eno|char(20)|员工号|primary key|
|ename|char(20)|员工姓名|not null|
|esex|char(20)|性别| |
|ebirth|Date|出生年月日| |
|eno|char(20)|员工号| |
|eaddress|char(20)|住址| |
|ephoneno|char(20)|手机号| |
|dno|char(20)|部门号|foregin-key to department.dno |
<br/>

**attendance**
field | type | description | constraint |
|--|--|--|--|
|eno|char(20)|员工号|primary key|
|AttendNum|int|正常考勤天数|not null|
|AskLeaveNum|int|请假天数|not null|
|AbsentNum|int|缺席天数|not null|
<br/>

**salary**
field | type | description | constraint |
|--|--|--|--|
|eno|char(20)|员工号|primary key|
|BaseSalary|double|基础薪资|not null|
|Bouns|double|奖金|not null|
|Fine|double|罚款|not null|
|Insurance|double|五险一金|not null|
|Tax|double|税|not null|
|Fsalary|double|实发工资|not null|

# 运行截图
![image](https://github.com/KangJ227/EmployeeManagedSystem/blob/main/screenshot/登录窗口.png)
![image](https://github.com/KangJ227/EmployeeManagedSystem/blob/main/screenshot/主界面.png)
![image](https://github.com/KangJ227/EmployeeManagedSystem/blob/main/screenshot/使用菜单.png)
![image](https://github.com/KangJ227/EmployeeManagedSystem/blob/main/screenshot/查询.png)
