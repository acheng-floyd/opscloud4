package com.baiyi.opscloud.kubernetes.terminal.processor;

import com.baiyi.opscloud.common.datasource.KubernetesConfig;
import com.baiyi.opscloud.common.exception.common.CommonRuntimeException;
import com.baiyi.opscloud.core.factory.DsConfigHelper;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceInstanceAsset;
import com.baiyi.opscloud.domain.generator.opscloud.TerminalSession;
import com.baiyi.opscloud.domain.constants.BusinessTypeEnum;
import com.baiyi.opscloud.domain.constants.DsAssetTypeConstants;
import com.baiyi.opscloud.kubernetes.terminal.factory.KubernetesTerminalProcessFactory;
import com.baiyi.opscloud.service.datasource.DsInstanceAssetService;
import com.baiyi.opscloud.service.terminal.TerminalSessionService;
import com.baiyi.opscloud.sshcore.ITerminalProcessor;
import com.baiyi.opscloud.sshcore.facade.SimpleTerminalSessionFacade;
import com.baiyi.opscloud.sshcore.message.KubernetesMessage;
import com.baiyi.opscloud.sshcore.model.JSchSessionContainer;
import com.baiyi.opscloud.sshcore.model.KubernetesResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * @Author baiyi
 * @Date 2021/7/15 10:08 上午
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractKubernetesTerminalProcessor<T extends KubernetesMessage.BaseMessage> implements ITerminalProcessor, InitializingBean {

    @Resource
    protected TerminalSessionService terminalSessionService;

    @Resource
    protected SimpleTerminalSessionFacade simpleTerminalSessionFacade;

    @Resource
    protected DsInstanceAssetService dsInstanceAssetService;

    @Resource
    private DsConfigHelper dsConfigHelper;

    abstract protected T getMessage(String message);

    protected KubernetesConfig buildConfig(KubernetesResource kubernetesResource) {
        DatasourceInstanceAsset asset = getAssetByResource(kubernetesResource);
        return buildConfig(asset.getInstanceUuid());
    }

    private KubernetesConfig buildConfig(String instanceUuid) {
        return dsConfigHelper.buildKubernetesConfig(instanceUuid);
    }

    private DatasourceInstanceAsset getAssetByResource(KubernetesResource kubernetesResource) {
        if (kubernetesResource.getBusinessType() == BusinessTypeEnum.ASSET.getType()) {
            DatasourceInstanceAsset asset = dsInstanceAssetService.getById(kubernetesResource.getBusinessId());
            if (DsAssetTypeConstants.KUBERNETES_DEPLOYMENT.name().equals(asset.getAssetType()))
                return asset;
        }
        throw new CommonRuntimeException("类型不符合");
    }

    protected Boolean isBatch(TerminalSession terminalSession) {
        Boolean isBatch = JSchSessionContainer.getBatchBySessionId(terminalSession.getSessionId());
        return isBatch != null && isBatch;
    }

    protected void heartbeat(String sessionId) {
        // redisUtil.set(TerminalKeyUtil.buildSessionHeartbeatKey(sessionId), true, 60L);
    }

    /**
     * 注册
     */
    @Override
    public void afterPropertiesSet() {
        KubernetesTerminalProcessFactory.register(this);
    }

}
