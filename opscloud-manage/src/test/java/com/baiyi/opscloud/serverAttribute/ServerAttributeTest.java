package com.baiyi.opscloud.serverAttribute;

import com.alibaba.fastjson.JSON;
import com.baiyi.opscloud.BaseUnit;
import com.baiyi.opscloud.common.config.ServerAttributeConfig;
import com.baiyi.opscloud.common.config.serverAttribute.AttributeGroup;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author baiyi
 * @Date 2020/3/6 3:01 下午
 * @Version 1.0
 */
public class ServerAttributeTest extends BaseUnit {

    @Resource
    private ServerAttributeConfig serverAttributeConfig;

    @Test
    void testAttributeGroups() {
        List<AttributeGroup> list = serverAttributeConfig.getGroups();
        for (AttributeGroup ag : list)
            System.err.println(JSON.toJSONString(ag));
    }

    @Test
    void testYamlJsonParser() {
        List<AttributeGroup> attributeGroups = serverAttributeConfig.getGroups();
        for (AttributeGroup attributeGroup : attributeGroups) {
            Yaml yaml = new Yaml();
            System.err.println("name:" + attributeGroup.getName());
            System.err.println(yaml.dumpAs(attributeGroup, Tag.MAP, DumperOptions.FlowStyle.BLOCK));
        }
    }


}
