package com.baiyi.opscloud.workorder.approve.factory;

import com.baiyi.opscloud.workorder.approve.ITicketApprove;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author baiyi
 * @Date 2022/1/19 3:00 PM
 * @Version 1.0
 */
@Slf4j
public class WorkOrderTicketApproveFactory {

    private static final Map<String, ITicketApprove> context = new ConcurrentHashMap<>();

    public static ITicketApprove getByApprovalType(String approvalType) {
        return context.get(approvalType);
    }

    public static void register(ITicketApprove bean) {
        context.put(bean.getApprovalType(), bean);
        log.info("WorkOrderTicketApproveFactory注册: approvalType = {} , beanName = {}  ", bean.getApprovalType(), bean.getClass().getSimpleName());
    }

}
