package com.baiyi.opscloud.facade.template.factory.impl;

import com.baiyi.opscloud.common.constants.enums.DsTypeEnum;
import com.baiyi.opscloud.common.datasource.KubernetesConfig;
import com.baiyi.opscloud.datasource.kubernetes.driver.KubernetesDeploymentDriver;
import com.baiyi.opscloud.domain.generator.opscloud.BusinessTemplate;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceConfig;
import com.baiyi.opscloud.domain.constants.DsAssetTypeConstants;
import com.baiyi.opscloud.facade.template.factory.base.AbstractTemplateConsume;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.springframework.stereotype.Component;

/**
 * @Author baiyi
 * @Date 2021/12/7 4:11 PM
 * @Version 1.0
 */
@Component
public class KubernetesDeploymentTemplateConsume extends AbstractTemplateConsume<Deployment> {

    @Override
    protected Deployment produce(BusinessTemplate bizTemplate, String content) {
        DatasourceConfig dsConfig = dsConfigHelper.getConfigByInstanceUuid(bizTemplate.getInstanceUuid());
        KubernetesConfig.Kubernetes config = dsConfigHelper.build(dsConfig, KubernetesConfig.class).getKubernetes();
        return KubernetesDeploymentDriver.createOrReplaceDeployment(config, content);
    }

    @Override
    public String getAssetType() {
        return DsAssetTypeConstants.KUBERNETES_DEPLOYMENT.name();
    }

    @Override
    public String getInstanceType() {
        return DsTypeEnum.KUBERNETES.getName();
    }


    @Override
    public String getTemplateKey() {
        return "DEPLOYMENT";
    }
}
