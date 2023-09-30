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
id INTEGER PRIMARY KEY AUTOINCREMENT not null, 
code text,
name text not null, 
location text, 
quantity integer default 1, 
rate_of_loss real,
note TEXT, 
price real,
purchase_time text, 
purchase_channel text, 
status integer,
delete_reason text,
delete_time text, 
create_time text, 
last_update_time text
)
```

2. expire_config

```sql
create table expire_config (
id INTEGER PRIMARY KEY AUTOINCREMENT not null,
thing_id integer not null, 
expire_days integer not null, 
expire_time text not null
)
```

3. tag

```sql
create table tag (
id integer PRIMARY KEY AUTOINCREMENT NOT NULL, 
name TEXT not null, 
type_id integer not null
)
```

4. tag_type

```sql
create table tag_type (
id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
name text not null,
icon_name text,
icon_color text
)
```

5. thing-tag-mapping

```sql
create table thing_tag (
id integer PRIMARY KEY AUTOINCREMENT NOT NULL, 
thing_id integer not null, 
tag_id integer not null
)
```

6. log

```sql
create table act_log (
id integer PRIMARY KEY AUTOINCREMENT NOT NULL, 
activity text not null,
module text not null,
act_time text,
act_result text,
request_data text,
response_data text
)
```

action: add/update/delete/import/export

module: thing/category/tag/excel

response: failure/success

7. file

```sql
create table thing_file (
id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
thing_id integer nt null,
file_name text not null,
file_path text not null,
file_size integer,
file_index integer
)
```


