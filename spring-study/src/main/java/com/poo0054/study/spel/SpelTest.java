package com.poo0054.study.spel;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ReflectionUtils;

public class SpelTest {

    @Test
    void test() {
        SpelParserConfiguration config =
            new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, SpelTest.class.getClassLoader());
        SpelExpressionParser parser = new SpelExpressionParser(config);
        Expression expr = parser.parseExpression("payload");
        Demo demo = new Demo();
        Object payload = expr.getValue(demo);
        System.out.println(payload);
    }

    @Test
    void test1() {
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser(config);
        Expression expression = spelExpressionParser.parseExpression("list[3]");
        Demo demo = new Demo();
        Object o = expression.getValue(demo);
        System.out.println(o);
    }

    @Test
    void test2() {
        ExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("'Hello World'.bytes.length");
        Object value = expression.getValue();
        System.out.println(value);
    }

    @Test
    void test3() {
        Demo demo = new Demo();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(demo);
        context.setVariable("demo", demo);
        parser.parseExpression("#demo.name").setValue(context, "123asd");
        System.out.println(parser.parseExpression("#root.name").getValue(demo));
    }

    @Test
    void test4() {
        Demo demo = new Demo();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(demo);
        parser.parseExpression("#root.name").setValue(context, "123asd");
        System.out.println(parser.parseExpression("#root.name").getValue(context));
    }

    @Test
    void test5() {
        SpelServiceImpl spelService = new SpelServiceImpl();
        Method method = ReflectionUtils.findMethod(SpelServiceImpl.class, "send", String.class);
        DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        EvaluationContext evaluationContext =
            new MethodBasedEvaluationContext(spelService, method, new Object[] {"asd"}, parameterNameDiscoverer);
        String s = "#str+'::arg::'+T(com.poo0054.study.spel.SpelServiceImpl).STRING";
        ExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(s);
        System.out.println(expression.getValue(evaluationContext));
    }

    static class Demo {
        public List<String> list;
        public String name;
    }

}
