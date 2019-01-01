select t.orgname as 姓名, t3.alldevelopcount as 开发任务数, t4.exceedtaskcount as 超期任务数, t4.exceedtaskcount / t3.alldevelopcount as 超期任务比
from sirmpm.sprt_orgobject t
     inner join sirmpm.org_userextendinfo t2 on t.userid = t2.userid
                                                  and t2.where1 = 4 and t.Inservice=1-- 所有任务数
     inner join (
                select t.ownerid, count(1) as alldevelopcount
                from (
                     select distinct t.ownerid, t.taskid,t2.name
                     from sirmpm.sirmpm_taskowner t
                          left join sirmpm.sirmpm_task t2 on t.taskid = t2.objid
                          inner join sirmpm.sirmpm_requirement t3 on t2.requirementid=t3.objid and t3.checktask=1 and t3.checktesttask=1
                          inner join --     人员考核期内有多少个任务
                          (
                          select t1.ownerid, count(1) as requirementcount
                          from sirmpm.sirmpm_taskowner t1 -- 最新
                               left join sirmpm.sirmpm_task t2 on t1.taskid = t2.objid
                               inner join sirmpm.sirmpm_requirement t3 on t2.requirementid = t3.objid and t3.checktask = 1 and t3.checktesttask = 1
                          where 1 = 1 and t1.flag = 1 and t1.type != 1
                          --考核期内 任务。
                            and to_char(t2.startdate, 'yyyyMMdd') >= '20180101' and to_char(t2.enddate, 'yyyyMMdd') <= '20181231'
                          group by t1.ownerid) t4 on t.ownerid =t4.ownerid  and t4.requirementcount > 4
                     where t.flag = 1 and t.type <> 1
                       and t2.type<>2
                     -- 考核期内任务。
                       and to_char(t2.StartDate,'yyyyMMdd') >= '20180101' and to_char(t2.endDate,'yyyyMMdd')<='20181231'
                     ) t
                group by t.ownerid) t3 on t.orgid = t3.ownerid
     left join (
               select t.ownerid
--超期的数量
                    , count(1) as exceedtaskcount
               from (
                    select distinct t.ownerid, t.taskid
-- 实际结束日期-预计结束日期  大于0 超期
                                  , sign(trunc(t4.f0004) - trunc(t.enddate)) as exceedflag
                    from sirmpm.sirmpm_taskowner t
                         left join sirmpm.sirmpm_task t2 on t.taskid = t2.objid and t2.type = 1
                         left join sirmpm.sirmpm_updatetasklog t3 on t3.taskid = t.taskid and t3.type = 3
                         left join (
                                   select t.taskid, t.ownerid, t2.f0004, rank()over (partition by t2.taskid,t2.ownerid order by t2.f0004 desc) as rn
                                   from sirmpm.sirmpm_taskowner t
                                        left join sirmpm.sirmpm_updatetasklog t2 on t.taskid = t2.taskid and t.ownerid = t2.ownerid) t4
                         on t.ownerid = t4.ownerid and t.taskid = t4.taskid and t4.rn = 1 --考核期内任务。
                         inner join sirmpm.sirmpm_task t5 on t2.requirementid = t5.requirementid --考核期内 任务。
                         inner join sirmpm.sirmpm_requirement t6 on t2.requirementid=t6.objid and t6.checktask=1 and t6.checktesttask=1
                         inner join --     人员考核期内有多少个需求
                         (
                         select t1.ownerid, count(1) as requirementcount
                         from sirmpm.sirmpm_taskowner t1 -- 最新
                              left join sirmpm.sirmpm_task t2 on t1.taskid = t2.objid
                              inner join sirmpm.sirmpm_requirement t3 on t2.requirementid = t3.objid and t3.checktask = 1 and t3.checktesttask = 1
                         where 1 = 1 and t1.flag = 1 and t1.type != 1
                         --考核期内 任务。
                           and to_char(t2.startdate, 'yyyyMMdd') >= '20180101' and to_char(t2.enddate, 'yyyyMMdd') <= '20181231'
                         group by t1.ownerid) t7 on t7.ownerid = t.ownerid and t7.requirementcount > 4
                    where 1 = 1 and t.flag = 1 and t.type <> 1
                    -- 考核期内 任务。
                      and to_char(t5.StartDate,'yyyyMMdd') >= '20180101' and to_char(t5.endDate,'yyyyMMdd')<='20181231'
-- 不为BUG任务
                      and t2.type<>2
                    ) t
               where 1 = 1 and t.exceedflag > 0
               group by t.ownerid) t4 on t.orgid = t4.ownerid
-- 超期任务数
where 1 = 1 and t4.exceedtaskcount > 0
order by nlssort(t.orgname,'NLS_SORT=SCHINESE_PINYIN_M')