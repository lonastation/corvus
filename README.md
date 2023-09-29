# story

库存管理;库存盘点;统计报表;

消耗品周期提醒;

导入导出;系统日志;

# step

1. thing, tag, category;

2. filter, order, batch update;

3. deadline;

4. log, import, export;

5. statistic;

# design

1. view

a. search + filter + order

b. add/update/archive/active, batch, sort category, OCR

c. tree / list

2. deadline - todo

a. 下次提醒/不再提醒/x天后提醒

b. 批量

3. tag / category manage

a. add/rename/delete

4. log/setting

setting: export/import/about us;

5. dashboard

按年份显示，

# database

1. thing

```sql
create table thing (
id bigint, 
code varchar(255),
name varchar(128), 
category_id bigint,
location varchar(128), 
quantity int, 
rate_of_loss int,
remark varchar(255), 
price decimal,
purchase_time date, 
purchase_channel varchar(128), 
status int,
delete_reason varchar(255),
delete_time date, 
create_time date, 
last_update_time date
)
```

2. deadline

```sql
create table deadline (
id bigint, 
product_id bigint, 
expire_days int, 
expire_time date
)
```

2. category

```sql
create table category (
id bigint, 
name varchar(128), 
index int
)
```

3. tag

```sql
create table tag (
id bigint, 
name varchar(128), 
color varchar(30)
)
```

4. thing-tag-mapping

```sql
create table thing_tag (
id bigint, 
thing-id bigint, 
tag-id bigint, 
)
```

5. log

```sql
create table corvus_log (
id bigint, 
action varchar(128),
module varchar(128),
act_time date,
act_result varchar(255),
request-data text,
response-data text
)
```

6. file

```sql
create table thing_file (
id int,
thing_id int,
file_name text,
file_path text,
file_size int,
file_index int
)
```

action: add/update/batch update/delete/import/export

module: thing/category/tag/excel

response: failure/success
