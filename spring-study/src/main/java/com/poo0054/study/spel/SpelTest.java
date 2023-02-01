package com.poo0054.study.spel;

import java.lang.reflect.Method;
import java.util.List;

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
    public static void main(String[] args) {
        extracted();
    }

    public static void test() {
        SpelParserConfiguration config =
            new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, SpelTest.class.getClassLoader());
        SpelExpressionParser parser = new SpelExpressionParser(config);
        Expression expr = parser.parseExpression("payload");
        Demo demo = new Demo();
        Object payload = expr.getValue(demo);
        System.out.println(payload);
    }

    private static void extracted1() {
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser(config);
        Expression expression = spelExpressionParser.parseExpression("list[3]");
        Demo demo = new Demo();
        Object o = expression.getValue(demo);
        System.out.println(o);
    }

    public static void simpleTest() {
        ExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("'Hello World'.bytes.length");
        Object value = expression.getValue();
        System.out.println(value);
    }

    private static void extracted() {
        Demo demo = new Demo();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(demo);
        context.setVariable("demo", demo);
        parser.parseExpression("#demo.name").setValue(context, "123asd");
        System.out.println(parser.parseExpression("#root.name").getValue(demo));
    }

    private static void extracted2() {
        Demo demo = new Demo();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(demo);
        parser.parseExpression("#root.name").setValue(context, "123asd");
        System.out.println(parser.parseExpression("#root.name").getValue(context));
    }

    private static void aVoid() {
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
