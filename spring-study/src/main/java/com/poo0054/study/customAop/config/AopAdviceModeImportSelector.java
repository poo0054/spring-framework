package com.poo0054.study.customAop.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.util.StringUtils;

import com.poo0054.study.customAop.annotation.EnableAop;

/**
 * @author poo00
 */
public class AopAdviceModeImportSelector extends AdviceModeImportSelector<EnableAop> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        List<String> result = new ArrayList<>(2);
        // 固定写法 需要注入InfrastructureAdvisorAutoProxyCreator
        result.add(AutoProxyRegistrar.class.getName());
        result.add(AopConfiguration.class.getName());
        return StringUtils.toStringArray(result);
    }
}
